package ru.spring.ripper.examinator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.spring.ripper.examinator.controller.ExamineController;
import ru.spring.ripper.examinator.domain.*;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author tolkv
 * @version 25/10/2017
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class ExaminatorControllerIntegTest {
  @Autowired
  MockMvc mockMvc;

  @Test
  public void checkExamineContract() throws Exception {

    mockMvc.perform(
        post("/examine")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"title\":\"exam\", \"student\": \"Толкачёв Кирилл\" }")
    )
        .andExpect(jsonPath("$.mark", allOf(greaterThan(0), lessThan(101))))
        .andExpect(jsonPath("$.title", equalTo("exam")))
        .andExpect(jsonPath("$.student").doesNotExist())
        .andExpect(jsonPath("$.examine").doesNotExist())
        .andExpect(status().isOk());

  }

  @Autowired
  ObjectMapper objectMapper;

  @Test
  public void shouldParseSolvedExam() throws IOException {
    SolvedExam o = objectMapper.readerFor(SolvedExam.class).readValue(
        "{ \"title\":\"exam\", \"student\": \"Толкачёв Кирилл\" }"
    );

    assertThat(o.getExamine().getTitle(), equalTo("exam"));
  }

  @Autowired
  ExamineController examineController;

  @Test
  public void shouldConvertToCheckedExam() {
    CheckedExam checkedExam = examineController.checkExamine(SolvedExam.builder()
        .examine(Examine.builder()
            .title("title")
            .build()
        ).build());

    assertThat(checkedExam.getExamine().getTitle(), equalTo("title"));
  }

  @Test
  public void name() throws JsonProcessingException {
    SolvedExam build = SolvedExam.builder()
        .examine(Examine.builder()
            .title("Вступительный экзамен на JokerConf")
            .section(Section.builder()
                .title("Java")
                .description("простые вопросы по java")
                .exercise(Exercise.builder()
                    .question("в чем разница между spring string и swing")
                    .answer("без spring`а не обойтись")
                    .build())
                .exercise(Exercise.builder()
                    .question("в чем разница между final finally finalize")
                    .answer("за finalize отрывают руки")
                    .build())
                .build())
            .section(Section.builder()
                .title("философия")
                .description("сложные вопросы по философии")
                .exercise(Exercise.builder()
                    .question("В чем смысл жизни")
                    .answer("42")
                    .build())
                .exercise(Exercise.builder()
                    .question("Что появилось раньше, яйцо или курица")
                    .answer("петух")
                    .build())
                .build())
            .build()
        )
        .student("Борисов Евгений")
        .build();

    String s = objectMapper.writeValueAsString(build);
    System.out.println("s = " + s);
  }
}