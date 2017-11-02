package ru.spring.ripper.math;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.ripper.examinator.domain.Exercise;

import java.util.List;

/**
 * @author tolkv
 * @version 30/10/2017
 */
@RestController
@RequiredArgsConstructor
public class MathController {
  private final MathService mathService;

  @GetMapping("/exercise/random")
  public List<Exercise> randomExercises(@RequestParam(defaultValue = "5") int count) {
    return mathService.generateRandomExercise(count);
  }

}
