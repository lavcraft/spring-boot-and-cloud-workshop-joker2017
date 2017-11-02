package ru.spring.ripper.examinator;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.spring.ripper.examinator.domain.Examine;
import ru.spring.ripper.examinator.domain.Exercise;
import ru.spring.ripper.examinator.domain.Section;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * @author tolkv
 * @version 02/11/2017
 */
@Service
@RequiredArgsConstructor
public class ExamineService {
  private final RestTemplate restTemplate;

  @HystrixCommand
  public Examine collectExam(Map<String, Integer> examSpecification) {
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
