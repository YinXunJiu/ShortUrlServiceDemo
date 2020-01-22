package com.feidian.short_url_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.feidian.short_url_service", "com.easycodingnow.fastman.agent"})
public class ShortUrlServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortUrlServiceApplication.class, args);
        System.out.println("project is starting up now");
    }
}
