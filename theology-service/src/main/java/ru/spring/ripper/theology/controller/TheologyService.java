package ru.spring.ripper.theology.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.ripper.examinator.domain.Exercise;
import ru.spring.ripper.theology.repository.ExerciseRepository;

import java.util.Collections;
import java.util.List;

/**
 * @author tolkv
 * @version 02/11/2017
 */
@Service
@RequiredArgsConstructor
public class TheologyService {
  private final ExerciseRepository exerciseRepository;

  @HystrixCommand
  public List<Exercise> getExercises(@RequestParam(defaultValue = "5") int count) {
    List<Exercise> allIds = exerciseRepository.findAll();
    Collections.shuffle(allIds);
    return allIds.subList(0, count);
  }

}
