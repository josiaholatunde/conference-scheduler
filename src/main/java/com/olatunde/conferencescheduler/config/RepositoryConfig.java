package com.olatunde.conferencescheduler.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class RepositoryConfig {

    @Value("${datasource.url}")
    private String dataSourceUrl;

    @Value("${datasource.driver-class-name}")
    private String driverClassName;

    @Value("${datasource.username}")
    private String dataSourceUsername;

    @Value("${datasource.password}")
    private String dataSourcePassword;

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username(dataSourceUsername);
        dataSourceBuilder.password(dataSourcePassword);
        dataSourceBuilder.url(dataSourceUrl);
        dataSourceBuilder.driverClassName(driverClassName);
        return dataSourceBuilder.build();
    }
    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource());
        springLiquibase.setChangeLog("classpath:liquibase-changeLog.xml");
        springLiquibase.setShouldRun(true);
        return springLiquibase;
    }
}
