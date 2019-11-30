package com.devbridge.feedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static final String PATH = "api";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
