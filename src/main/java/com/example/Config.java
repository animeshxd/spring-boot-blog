package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class Config {


    @Bean
    public JdbcTemplate jdbc(){

        var d = new DriverManagerDataSource();
        d.setDriverClassName("org.sqlite.JDBC");
        d.setUrl("jdbc:sqlite:blogs.db");
        return new JdbcTemplate(d);
        
    }
    
}
