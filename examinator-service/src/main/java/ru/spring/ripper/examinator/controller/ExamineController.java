package ru.spring.ripper.examinator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.ripper.examinator.domain.CheckedExam;
import ru.spring.ripper.examinator.domain.SolvedExam;

/**
 * @author tolkv
 * @version 29/10/2017
 */
@RestController
public class ExamineController {

  @PostMapping("/examine")
  public CheckedExam checkExamine(@RequestBody SolvedExam title) {
    return CheckedExam.builder()
        .examine(title.getExamine())
        .mark(4)
        .build();
  }

}
