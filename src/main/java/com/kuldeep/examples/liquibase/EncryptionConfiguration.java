package com.kuldeep.examples.liquibase;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.jasypt.spring31.properties.EncryptablePropertySourcesPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iDiot on 8/13/17.
 */
@Configuration
public class EncryptionConfiguration implements EnvironmentAware{


    private Environment environment;


    private static SimplePBEConfig encryptionConfig(String configFilePassword) {
        EnvironmentStringPBEConfig pbeConfig = new EnvironmentStringPBEConfig();
        pbeConfig.setPassword(configFilePassword);
        return pbeConfig;
    }
    @Bean(name = "configFileEncryptor")
    @Autowired
    public StringEncryptor configurationEncryptor() {
        StandardPBEStringEncryptor se = new StandardPBEStringEncryptor();
        se.setConfig(encryptionConfig("test"));
        return se;
    }
    @Bean
    @Autowired
    public PropertySourcesPlaceholderConfigurer propertyConfigurer(
            @Qualifier("configFileEncryptor") StringEncryptor encryptor,
            @Value("#{ systemProperties['configdir'] }") String configDir) {

        EncryptablePropertySourcesPlaceholderConfigurer pc = new EncryptablePropertySourcesPlaceholderConfigurer(encryptor);
        List<Resource> resources = new ArrayList<>();
        resources.add(new ClassPathResource(String.format("/enc-application-%s.properties", environment.getProperty("spring.profiles.active"))));
        pc.setLocations(resources.toArray(new Resource[resources.size()]));
        return pc;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
