package ru.spring.ripper.examinator;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author tolkv
 * @version 30/10/2017
 */
@Data
@ConfigurationProperties(prefix = "exercises")
public class ExternalServiceProperties {

  /**
   * Microservice-name vs this service host
   */
  Map<String, String> urls;
}
