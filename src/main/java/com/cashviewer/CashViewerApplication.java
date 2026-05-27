package com.cashviewer;

import com.cashviewer.infrastructure.security.jwt.JwtConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(
        JwtConfigurationProperties.class
)
public class CashViewerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashViewerApplication.class, args);
    }

}
