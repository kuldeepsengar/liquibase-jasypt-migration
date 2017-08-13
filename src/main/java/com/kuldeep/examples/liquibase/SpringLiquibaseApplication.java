package com.kuldeep.examples.liquibase;

import org.jasypt.spring31.properties.EncryptablePropertySourcesPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * Created by iDiot on 8/11/17.
 */
@SpringBootApplication
@ComponentScan("com.kuldeep.examples.liquibase")
public class SpringLiquibaseApplication {

    @Value("${com.db.erds.database.url")
    private String url;

    @Value("${com.db.erds.database.user")
    private String user;

    @Value("${com.db.erds.database.password")
    private String password;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringLiquibaseApplication.class, args);
    }

}
