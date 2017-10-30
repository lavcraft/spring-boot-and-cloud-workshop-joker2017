package ru.spring.ripper.math;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.ripper.examinator.domain.Exercise;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @author tolkv
 * @version 30/10/2017
 */
@RestController
public class MathController {

  @GetMapping("/exercise/random")
  public List<Exercise> randomExercises(@RequestParam(defaultValue = "5") int count) {
    return IntStream.range(0, count).mapToObj(i -> Exercise.builder()
        .answer("Answer #" + i)
        .question("Question #" + i)
        .id((long) i)
        .build()
    ).collect(toList());
  }

}
