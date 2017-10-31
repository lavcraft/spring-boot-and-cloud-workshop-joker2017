package ru.spring.ripper.theology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import ru.spring.ripper.examinator.domain.Exercise;
import ru.spring.ripper.theology.repository.ExerciseRepository;

import javax.annotation.PostConstruct;

/**
 * @author tolkv
 * @version 29/10/2017
 */
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan(basePackageClasses = Exercise.class)
@EnableConfigurationProperties(TheologyApplicationProperties.class)
public class TheologyApplication {
  @Autowired
  ExerciseRepository exerciseRepository;
  @Autowired
  TheologyApplicationProperties theologyApplicationProperties;

  @PostConstruct //flyway для бедных
  public void init() {
    theologyApplicationProperties.getQuestionsAndAnswers().forEach((q, a) -> {
      exerciseRepository.save(Exercise.builder()
          .question(q)
          .answer(a)
          .build());
    });
  }

  public static void main(String[] args) {
    SpringApplication.run(TheologyApplication.class, args);
  }
}
