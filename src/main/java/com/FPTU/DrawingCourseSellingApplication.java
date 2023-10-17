package com.FPTU;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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

}
