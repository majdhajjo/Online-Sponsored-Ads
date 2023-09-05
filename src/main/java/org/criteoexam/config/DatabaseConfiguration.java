package org.criteoexam.config;

import com.wix.mysql.EmbeddedMysql;
import org.apache.naming.factory.DataSourceLinkFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.distribution.Version.v5_7_latest;

@Configuration
@EnableJpaRepositories({ "org.criteoexam.repository" })
@EnableTransactionManagement
public class DatabaseConfiguration {

    @Bean
    public DataSource dataSource(){
                EmbeddedMysql mysqld = anEmbeddedMysql(v5_7_latest)
                .addSchema("ads_schema", classPathScript("db.migration/create_tables.sql"),classPathScript("db.migration/init_data.sql"))
                .start();

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://127.0.0.1:3310/ads_schema");
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.username("auser");
        dataSourceBuilder.password("sa");
        return dataSourceBuilder.build();
    }

}
