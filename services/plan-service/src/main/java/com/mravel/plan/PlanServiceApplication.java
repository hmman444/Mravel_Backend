package com.mravel.plan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PlanServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlanServiceApplication.class, args);
    }
}
