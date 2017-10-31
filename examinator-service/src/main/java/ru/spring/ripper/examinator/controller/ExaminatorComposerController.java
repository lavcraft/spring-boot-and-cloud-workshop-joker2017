package ru.spring.ripper.examinator.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.spring.ripper.examinator.ExternalServiceProperties;
import ru.spring.ripper.examinator.domain.Examine;
import ru.spring.ripper.examinator.domain.Exercise;
import ru.spring.ripper.examinator.domain.Section;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * @author tolkv
 * @version 30/10/2017
 */
@RestController
public class ExaminatorComposerController {
  private final RestTemplate restTemplate;
  private ExternalServiceProperties externalServiceProperties;

  public ExaminatorComposerController(
      RestTemplateBuilder restTemplate,
      LoadBalancerInterceptor loadBalancerInterceptor,
      ExternalServiceProperties externalServiceProperties
  ) {
    this.restTemplate = restTemplate.interceptors(loadBalancerInterceptor).build();
    this.externalServiceProperties = externalServiceProperties;
  }

  @PostMapping("/examine/collect")
  public Examine collectExam(@RequestBody Map<String, Integer> examSpecification) {

    List<Section> sections = examSpecification.entrySet().stream()
        .map(stringIntegerEntry -> {
          String serviceName = stringIntegerEntry.getKey();
          Integer exerciseCount = stringIntegerEntry.getValue();

          String url = discoverExerciseEndpoint(serviceName, exerciseCount);

          try {
            return Section.builder()
                .title(serviceName)
                .exercises(
                    asList(restTemplate.getForObject(url, Exercise[].class)))
                .build();
          } catch (RestClientException e) {
            System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
            return Section.builder()
                .title(serviceName + " with error " + e.getLocalizedMessage())
                .build();
          }
        }).collect(toList());

    return Examine.builder()
        .title("My favorite exam")
        .sections(sections)
        .build();
  }

  private String discoverExerciseEndpoint(String serviceName, Integer exerciseCount) {

    return "http://" + serviceName + "-service/exercise/random?count=" + exerciseCount;
  }

}
