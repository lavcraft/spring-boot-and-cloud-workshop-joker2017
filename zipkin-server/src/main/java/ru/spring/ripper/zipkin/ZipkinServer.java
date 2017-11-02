package ru.spring.ripper.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

/**
 * @author tolkv
 * @version 02/11/2017
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class ZipkinServer {
  public static void main(String[] args) {
    SpringApplication.run(ZipkinServer.class, args);
  }
}
