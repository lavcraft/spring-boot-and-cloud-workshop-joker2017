package ru.spring.ripper.examinator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author tolkv
 * @version 24/10/2017
 */
@SpringBootApplication
@EnableConfigurationProperties(ExternalServiceProperties.class)
public class ExaminatorApplication {
  public static void main(String[] args) {
    SpringApplication.run(ExaminatorApplication.class, args);
  }
}
