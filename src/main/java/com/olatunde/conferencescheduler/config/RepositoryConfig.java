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

    @Value("${migrations.liquibase.change-log}")
    private String liquibaseChangeLog;

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
        springLiquibase.setChangeLog(liquibaseChangeLog);
        springLiquibase.setShouldRun(true);
        return springLiquibase;
    }
}
