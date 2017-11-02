package ru.spring.ripper.math;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import ru.spring.ripper.examinator.domain.Exercise;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @author tolkv
 * @version 02/11/2017
 */
@Service
public class MathService {
  @HystrixCommand
  public List<Exercise> generateRandomExercise(int count) {
    try {
      Thread.sleep(400);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return IntStream.range(0, count).mapToObj(i -> Exercise.builder()
        .answer("Answer #" + i)
        .question("Question #" + i)
        .id((long) i)
        .build()
    ).collect(toList());
  }
}
