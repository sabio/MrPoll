package com.mrpoll.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.mrpoll")
//@EntityScan(basePackages = "com.mrpoll")
public class SpringBootApp {
    public static void main(String[] args){
        SpringApplication.run(SpringBootApp.class, args);
    }
}
