package com.kuldeep.examples.liquibase;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by iDiot on 8/12/17.
 */
@Component
public class TestComponent implements CommandLineRunner{
    @Value("${simple.property}")
    private String simpleProperty;

    @Autowired
    private StringEncryptor encryptor;


    @Override
    public void run(String... args) throws Exception {
        System.out.println(simpleProperty);
//        System.out.println(encryptor.decrypt(simpleProperty));

    }

}
