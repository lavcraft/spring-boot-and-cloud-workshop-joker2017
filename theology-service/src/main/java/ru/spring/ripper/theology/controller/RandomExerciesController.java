package ru.spring.ripper.theology.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.ripper.examinator.domain.Exercise;
import ru.spring.ripper.theology.repository.ExerciseRepository;

import java.util.Collections;
import java.util.List;

/**
 * @author tolkv
 * @version 30/10/2017
 */
@RestController
@RequiredArgsConstructor
public class RandomExerciesController {
  private final ExerciseRepository exerciseRepository;

  @GetMapping("/exercise/random")
  public List<Exercise> randomExercises(@RequestParam(defaultValue = "5") int count) {
    List<Exercise> allIds = exerciseRepository.findAll();
    Collections.shuffle(allIds);
    return allIds.subList(0, count);
  }

}
