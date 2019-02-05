package com.springinaction.tacocloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import javax.sql.DataSource;


import  org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


@SpringBootApplication
public class TacocloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacocloudApplication.class, args);

        System.out.println("Here");
    }




}

