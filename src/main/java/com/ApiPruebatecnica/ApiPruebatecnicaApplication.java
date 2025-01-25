package com.ApiPruebatecnica;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ApiPruebatecnica.Repository")
@EntityScan(basePackages = "com.ApiPruebatecnica.Entity")
public class ApiPruebatecnicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPruebatecnicaApplication.class, args);
    }

}
