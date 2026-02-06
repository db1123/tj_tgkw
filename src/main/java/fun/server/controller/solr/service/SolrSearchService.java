package fun.server.controller.solr.service;

import fun.server.controller.solr.model.Document;
import fun.server.controller.solr.model.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SolrSearchService {

    private static final Logger log = LoggerFactory.getLogger(SolrSearchService.class);

    @Autowired
    private SolrClient solrClient;

    @Value("${file.access.base-url:http://localhost:8080/file}")
    private String fileAccessBaseUrl;

    /**
     * 搜索文档主方法
     * @param keyword 搜索关键词
     * @param docType 文件类型过滤（可选）
     * @param page 页码
     * @param pageSize 每页条数
     * @param highlight 是否开启高亮
     * @return 搜索结果封装
     */
    public SearchResult searchDocuments(String keyword, String docType, int page, int pageSize, boolean highlight) {
        SearchResult result = new SearchResult();
        List<Document> documents = new ArrayList<>();

        try {
            // 1. 构建查询条件
            SolrQuery query = buildSolrQuery(keyword, docType, page, pageSize, highlight);
            
            // 2. 执行查询
            QueryResponse response = solrClient.query(query);
            SolrDocumentList solrDocs = response.getResults();
            
            // 3. 处理查询结果
            documents = convertSolrDocsToDocuments(solrDocs, highlight, response);
            
            // 4. 封装返回结果
            result.setList(documents);
            result.setTotal(solrDocs.getNumFound());
            result.setPage(page);
            result.setPage(pageSize);
            result.setTotal((int) Math.ceil((double) solrDocs.getNumFound() / pageSize));

        } catch (Exception e) {
            log.error("Solr搜索出错", e);
            // 异常时返回空结果集，避免前端报错
            result.setList(new ArrayList<>());
            result.setTotal(0);
            result.setPage(page);
            result.setPage(pageSize);
            result.setTotal(0);
        }
        
        return result;
    }

    /**
     * 构建Solr查询条件
     */
    private SolrQuery buildSolrQuery(String keyword, String docType, int page, int pageSize, boolean highlight) {
        SolrQuery query = new SolrQuery();
        
        // 设置查询关键词（默认查询text字段，匹配模式为OR）
        if (keyword != null && !keyword.trim().isEmpty()) {
            //query.setQuery("text:"+ keyword+"");
        	
        	// 判断关键词是否全为英文（仅包含字母）
        	boolean isEnglish = keyword.matches("^[a-zA-Z]+$");

        	// 根据是否为英文拼接查询条件：中文不变，英文添加通配符*
        	String queryStr;
        	if (isEnglish) {
        	    queryStr = "content:" + keyword + "*"; // 英文添加通配符
        	} else {
        	    queryStr = "content:" + keyword; // 中文保持原样
        	}

        	query.setQuery(queryStr);
        	
        	query.set("q.op", "OR"); // 多关键词OR匹配
            query.set("indent", "true"); // 格式化响应
        } else {
            query.setQuery("*:*"); // 无关键词时查询所有文档
        }
        
        // 按文件类型过滤
        if (docType != null && !docType.trim().isEmpty()) {
            query.addFilterQuery("file_type:" + docType);
        }
        
        // 高亮配置
//        if (highlight && keyword != null && !keyword.trim().isEmpty()) {
//            query.setHighlight(true);
//            query.addHighlightField("file_name");
//            query.addHighlightField("metadata");
//            query.addHighlightField("contentPreview");
//            query.setHighlightSimplePre("<span class='highlight'>");
//            query.setHighlightSimplePost("</span>");
//            query.setHighlightFragsize(150); // 高亮片段长度
//            query.setHighlightSnippets(1); // 每个字段返回1个高亮片段
//        }
        
        if (highlight && keyword != null && !keyword.trim().isEmpty()) {
            query.setHighlight(true);
            query.addHighlightField("file_name");
            query.addHighlightField("metadata");
            query.addHighlightField("content");  // 高亮content字段
            query.setHighlightSimplePre("<span class='highlight'>");
            query.setHighlightSimplePost("</span>");
            query.setHighlightFragsize(150);
            query.setHighlightSnippets(1);
        }
        
        // 分页配置
        query.setStart((page - 1) * pageSize);
        query.setRows(pageSize);
        
        // 排序配置（按相关性降序）
        query.setSort("score", SolrQuery.ORDER.desc);
        
        return query;
    }
    
   

    /**
     * 将Solr文档列表转换为业务文档列表
     */
    private List<Document> convertSolrDocsToDocuments(SolrDocumentList solrDocs, boolean highlight, QueryResponse response) {
        List<Document> documents = new ArrayList<>(solrDocs.size());
        
        for (SolrDocument doc : solrDocs) {
            Document document = new Document();
            
            // 基础字段映射（使用安全转换方法）
            document.setId(getFieldValueAsString(doc, "id"));
            document.setSourceTable(getFieldValueAsString(doc, "source_table"));
            document.setSourceId(getFieldValueAsString(doc, "source_id")); // 可能为Long类型
            document.setFileName(getFieldValueAsString(doc, "file_name"));
            document.setFileType(getFieldValueAsString(doc, "file_type"));
            document.setFilePath(getFieldValueAsString(doc, "file_path"));
            document.setUploadTime(getFieldValueAsString(doc, "upload_time"));
            document.setMetadata(getFieldValueAsString(doc, "metadata"));
            document.setProjectId(getFieldValueAsString(doc, "project_id")); // 可能为Long类型
            document.setEdition(getFieldValueAsString(doc, "edition"));
            document.setEditionNo(getFieldValueAsString(doc, "edition_no")); // 可能为Long类型
            
            // 处理高亮（如果开启）
            if (highlight) {
                applyHighlighting(document, doc, response);
            } else {
                // 非高亮模式下设置默认预览
                document.setContentPreview(generateDefaultPreview(document.getMetadata()));
            }
            
            // 构建文件访问URL
            document.setFileUrl(buildFileAccessUrl(document.getId()));
            
            documents.add(document);
        }
        
        return documents;
    }

    /**
     * 安全将字段值转换为字符串（核心方法，解决类型转换异常）
     */
    private String getFieldValueAsString(SolrDocument doc, String fieldName) {
        Object value = doc.getFieldValue(fieldName);
        if (value == null) {
            return "";
        }
        // 处理多值字段（如果存在）
        if (value instanceof List<?>) {
            List<?> list = (List<?>) value;
            return list.isEmpty() ? "" : list.get(0).toString();
        }
        // 单值字段直接转换
        return value.toString();
    }

    /**
     * 应用高亮处理
     */
    private void applyHighlighting(Document document, SolrDocument solrDoc, QueryResponse response) {
        String docId = (String) solrDoc.getFieldValue("id");
        if (docId == null) return;
        
        // 处理文件名高亮
        if (response.getHighlighting().containsKey(docId) && 
            response.getHighlighting().get(docId).containsKey("file_name")) {
            List<String> highlightedNames = response.getHighlighting().get(docId).get("file_name");
            if (!highlightedNames.isEmpty()) {
                document.setFileName(highlightedNames.get(0));
            }
        }
        
        // 处理元数据高亮（作为内容预览）
//        if (response.getHighlighting().containsKey(docId) && 
//            response.getHighlighting().get(docId).containsKey("metadata")) {
//            List<String> highlightedMetadatas = response.getHighlighting().get(docId).get("metadata");
//            document.setContentPreview(highlightedMetadatas.isEmpty() ? 
//                generateDefaultPreview(document.getMetadata()) : highlightedMetadatas.get(0));
//        } else {
//            document.setContentPreview(generateDefaultPreview(document.getMetadata()));
//        }
        
        if (response.getHighlighting().containsKey(docId) && 
                response.getHighlighting().get(docId).containsKey("content")) {
                List<String> highlightedContents = response.getHighlighting().get(docId).get("content");
                document.setContent(highlightedContents.isEmpty() ? 
                    generateDefaultPreview(getFieldValueAsString(solrDoc, "content")) : highlightedContents.get(0));
            } else {
                document.setContent(generateDefaultPreview(getFieldValueAsString(solrDoc, "content")));
            }
        
    }

    /**
     * 生成默认内容预览（截取元数据前150字符）
     */
//    private String generateDefaultPreview(String metadata) {
//        if (metadata == null || metadata.isEmpty()) {
//            return "无预览内容";
//        }
//        return metadata.length() > 150 ? metadata.substring(0, 150) + "..." : metadata;
//    }
    
    private String generateDefaultPreview(String content) {  // 参数名从metadata改为content
        if (content == null || content.isEmpty()) {
            return "无预览内容";
        }
        return content.length() > 150 ? content.substring(0, 150) + "..." : content;
    }

    /**
     * 构建文件访问URL
     */
    private String buildFileAccessUrl(String docId) {
        if (docId == null || docId.isEmpty()) {
            return "";
        }
        return fileAccessBaseUrl + "?id=" + docId;
    }
}