package ru.spring.ripper.examinator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.ripper.examinator.ExamineService;
import ru.spring.ripper.examinator.domain.Examine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tolkv
 * @version 30/10/2017
 */
@RestController
@RequiredArgsConstructor
public class ExaminatorComposerController {
  private final ExamineService examineService;

  @GetMapping("/examine/collect")
  public Examine collectExam() {
    Map<String, Integer> sections = new HashMap<>();

    sections.put("math", 1);
    sections.put("theology", 1);
    return examineService.collectExam(sections);
  }

  @PostMapping("/examine/collect")
  public Examine collectExam(@RequestBody Map<String, Integer> examSpecification) {

    return examineService.collectExam(examSpecification);

  }

}
