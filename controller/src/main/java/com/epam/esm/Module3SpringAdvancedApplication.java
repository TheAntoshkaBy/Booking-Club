package com.epam.esm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class Module3SpringAdvancedApplication {
    public static void main(String[] args) {
        SpringApplication.run(Module3SpringAdvancedApplication.class, args);
    }
}
