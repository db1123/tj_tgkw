package fun.server.controller.solr.controller;

import fun.server.controller.solr.model.SearchResult;
import fun.server.controller.solr.service.SolrSearchService;
import fun.tools.FileToUrlTools;
import fun.tools.ParamTools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequestMapping("/s/solr")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class DocumentController {

    @Autowired
    private SolrSearchService solrSearchService;

    /**
     * 搜索文档接口
     */
    @ResponseBody
    @RequestMapping(value = "/searchDocuments", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String searchDocuments(
            @RequestBody SearchRequest request) {
        SearchResult result = solrSearchService.searchDocuments(
                request.getKeyword(),
                request.getDocType(),
                request.getPage(),
                request.getResults(),
                request.isHighlight()
        );
        // 这里需要添加将SearchResult转换为JSON字符串的逻辑
        // 例如使用Jackson或Gson等JSON库进行序列化
        return convertToJson(result);
    }

    /**
     * 在线预览文档接口
     */
    @RequestMapping(value = "/preview", method = RequestMethod.GET)
    public ResponseEntity<Resource> previewDocument(
            @RequestParam String id,
            HttpServletRequest request) {
        try {
            // 根据ID查询文档信息获取文件路径
            SearchResult result = solrSearchService.searchDocuments(id, null, 1, 1, false);
            if (result.getList() == null || result.getList().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
            String filePath = result.getList().get(0).getFilePath();
            File file = new File(filePath);
            
            if (!file.exists()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
            // 根据文件类型设置合适的Content-Type
            String contentType = request.getServletContext().getMimeType(file.getAbsolutePath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            
            // 对于PDF等浏览器可直接预览的文件，设置inline模式
            String fileName = URLEncoder.encode(file.getName(), StandardCharsets.UTF_8.name());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, contentType);
            
            // 判断是否为浏览器可直接预览的类型
            if (contentType.contains("pdf") || contentType.contains("text") || 
                contentType.contains("image") || contentType.contains("json")) {
                headers.add(HttpHeaders.CONTENT_DISPOSITION, 
                           "inline; filename*=UTF-8''" + fileName);
            } else {
                // 其他类型默认使用attachment
                headers.add(HttpHeaders.CONTENT_DISPOSITION, 
                           "attachment; filename*=UTF-8''" + fileName);
            }
            
            Resource resource = new FileSystemResource(file);
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 将对象转换为JSON字符串
     */
    private String convertToJson(Object obj) {
        // 实际项目中应使用Jackson、Gson等JSON库
        // 这里仅作示例
        try {
            // 示例：使用Jackson
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("对象转JSON失败", e);
        }
    }
    
    
    
    /**
     * solr路径替换
     */
    @ResponseBody
    @RequestMapping(value = "/solrurl_change", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String solrurl_change(HttpServletRequest request)
    	    throws UnsupportedEncodingException, IOException {
    	    JSONObject jsonParam = ParamTools.getParameters(request);
    	    JSONObject object = new JSONObject();
    	    try {
	            String url = jsonParam.getString("url");//文件url
	            String ip = jsonParam.getString("ip");//ip
	            String real_url = FileToUrlTools.convertPathToUrl(ip,url);
	            // 返回值
	            object.put("url", real_url);
	            object.put("result", "success");
			 
    	    } catch (Exception e) {
                e.printStackTrace();
                object.put("result", "error");
                object.put("error", e.toString());
            }
            return object.toString();
    }
    
    
    public static class SearchRequest {
        private String keyword;
        private String docType;
        private int page;
        private int results;
        private boolean highlight;
        private String sort; // 添加这个属性，因为前端传递了sort参数
        
        // 确保所有属性都有getter和setter方法
        public String getKeyword() { return keyword; }
        public void setKeyword(String keyword) { this.keyword = keyword; }
        public String getDocType() { return docType; }
        public void setDocType(String docType) { this.docType = docType; }
        public int getPage() { return page; }
        public void setPage(int page) { this.page = page; }
        public int getResults() { return results; }
        public void setResults(int results) { this.results = results; }
        public boolean isHighlight() { return highlight; }
        public void setHighlight(boolean highlight) { this.highlight = highlight; }
        public String getSort() { return sort; } // 添加sort的getter
        public void setSort(String sort) { this.sort = sort; } // 添加sort的setter
    }
}