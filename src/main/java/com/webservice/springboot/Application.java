package com.webservice.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @EnableJpaAuditing // JPA Auditing 활성화 -> JpaConfig로 이동
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        System.out.println("■■■■■■■■■■ Application Start ■■■■■■■■■■");
        System.out.println("http://localhost:8080/");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

        System.out.println("■■■■■■■■■■ Database Edit ■■■■■■■■■■");
        System.out.println("http://localhost:8080/h2-console/");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    }
}