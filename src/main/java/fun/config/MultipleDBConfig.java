package fun.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@PropertySource("classpath:db.properties")
public class MultipleDBConfig {
	
    @Bean(name = "mysqlDb")
    @ConfigurationProperties(prefix = "ms")
    public DataSource postgresDataSource() {
        return  DataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate postgresJdbcTemplate(@Qualifier("mysqlDb") DataSource dsPostgres) {
        return new JdbcTemplate(dsPostgres);
    }
}