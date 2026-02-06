package fun.server.controller.solr.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class SolrFilesConfig {
    // 创建 Solr 客户端（单例模式推荐，避免频繁创建连接）
    public static SolrClient getSolrClient(String SOLR_URL) {
        return new HttpSolrClient.Builder(SOLR_URL)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
    }
}
