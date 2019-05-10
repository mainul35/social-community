package com.mainul35.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.mainul35.service"})
public class RootConfig {

    Properties jdbcProperties() {
        Properties prop = new Properties();
        try {
            prop.load(RootConfig.class.getClassLoader().getResourceAsStream("db_config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbcProperties().getProperty("springframework.jdbc.driverClassName"));
        dataSource.setUrl(jdbcProperties().getProperty("springframework.jdbc.url"));
        dataSource.setUsername(jdbcProperties().getProperty("springframework.jdbc.username"));
        dataSource.setPassword(jdbcProperties().getProperty("springframework.jdbc.password"));
        return dataSource;
    }



    @Bean(name = "hibernateProperties")
    Properties hibernateProperties() {
        Properties prop = new Properties();
        try {
            prop.load(RootConfig.class.getClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    @Bean
    MySqlConfig mySqlConfig(){
        return new MySqlConfig();
    }
}
