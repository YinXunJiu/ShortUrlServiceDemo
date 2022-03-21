package org.feidian.short_url_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yinchao
 */
@SpringBootApplication(scanBasePackages = {"org.feidian.short_url_service"})
public class ShortUrlServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortUrlServiceApplication.class, args);
    }
}
