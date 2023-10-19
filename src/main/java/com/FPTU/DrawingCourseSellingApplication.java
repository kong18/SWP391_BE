package com.FPTU;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

/**
 * Main Application.
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableAspectJAutoProxy
@EnableTransactionManagement

public class DrawingCourseSellingApplication {
  public static void main(String[] args) {
    SpringApplication.run(DrawingCourseSellingApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
