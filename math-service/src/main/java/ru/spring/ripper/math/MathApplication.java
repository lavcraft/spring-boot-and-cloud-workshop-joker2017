package ru.spring.ripper.math;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tolkv
 * @version 30/10/2017
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MathApplication {
  public static void main(String[] args) {
    SpringApplication.run(MathApplication.class, args);
  }
}
