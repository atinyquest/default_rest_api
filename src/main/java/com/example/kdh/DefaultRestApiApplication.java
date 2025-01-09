package com.example.kdh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DefaultRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DefaultRestApiApplication.class, args);
    }

}
