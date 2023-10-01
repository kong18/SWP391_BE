package com.FPTU;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main Application.
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableAspectJAutoProxy
public class DrawingCourseSellingApplication {
  public static void main(String[] args) {
    SpringApplication.run(DrawingCourseSellingApplication.class, args);
  }

}
