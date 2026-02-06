package fun.mbg;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import java.io.IOException;

public class test2 {

    // Solr 核心地址（格式：http://ip:端口/solr/核心名）
    private static final String SOLR_URL = "http://39.102.232.225:9498/solr/files";

    // 创建 Solr 客户端（单例模式推荐，避免频繁创建连接）
    public static SolrClient getSolrClient() {
        return new HttpSolrClient.Builder(SOLR_URL)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
    }
    public static void main(String[] args) {
        SolrClient solrClient = getSolrClient();
        //t_project_files_1420381772193075200

        try {
            // 方式1：按id删除（删除id=1002的文档）
            solrClient.deleteById("t_course_resourcefile_1440374011388366848");

            // 方式2：按查询条件删除（删除name包含"Java"的所有文档）
            // solrClient.deleteByQuery("name:Java");

            // 提交删除
            solrClient.commit();
            System.out.println("数据删除成功！");

        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                solrClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
