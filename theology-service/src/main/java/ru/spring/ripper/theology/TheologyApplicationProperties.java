package ru.spring.ripper.theology;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author tolkv
 * @version 30/10/2017
 */
@Data
@ConfigurationProperties(prefix = "theology")
public class TheologyApplicationProperties {
  Map<String, String> questionsAndAnswers;
}
