package fun.server.controller.solr.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {

	 // 修改默认Solr URL为新提供的地址
    @Value("${solr.url:http://172.30.75.189:9498/solr}")
    private String solrUrl;
    
    // 修改默认core为files
    @Value("${solr.core:files}")
    private String solrCore;
    @Bean
    public SolrClient solrClient() {
        // 创建Solr客户端连接
        String url = solrUrl + "/" + solrCore;
        return new HttpSolrClient.Builder(url)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
    }
}
