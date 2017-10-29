package ru.spring.ripper.examinator;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import ru.spring.ripper.examinator.controller.ExamineController;
import ru.spring.ripper.examinator.domain.CheckedExam;
import ru.spring.ripper.examinator.domain.Examine;
import ru.spring.ripper.examinator.domain.SolvedExam;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author tolkv
 * @version 25/10/2017
 */
public class ExaminatorControllerUnitTest {
  @Test
  public void shouldUseDelegate() {
    ExamineController examinatorApplication = new ExamineController();

    CheckedExam checkedExam = examinatorApplication.checkExamine(SolvedExam.builder()
        .examine(Examine.builder()
            .title("title")
            .build()
        ).build());

    assertThat(checkedExam.getExamine().getTitle(), equalTo("title"));

  }
}
