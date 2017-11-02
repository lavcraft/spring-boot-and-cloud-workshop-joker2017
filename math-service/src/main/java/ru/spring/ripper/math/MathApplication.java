package ru.spring.ripper.math;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @author tolkv
 * @version 30/10/2017
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
public class MathApplication {
  public static void main(String[] args) {
    SpringApplication.run(MathApplication.class, args);
  }
}
