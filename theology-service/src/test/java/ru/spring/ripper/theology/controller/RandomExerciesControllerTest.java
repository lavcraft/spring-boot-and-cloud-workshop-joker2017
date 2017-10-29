package ru.spring.ripper.theology.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.spring.ripper.theology.TheologyApplicationProperties;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isOneOf;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author tolkv
 * @version 30/10/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RandomExerciesControllerTest {
  @Autowired
  MockMvc mvc;

  @Autowired
  TheologyApplicationProperties theologyApplicationProperties;


  @Test
  public void should_return_random_exercise() throws Exception {
    mvc.perform(get("/exercise/random?count=3")
        .contentType(APPLICATION_JSON)
    )
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$.[0].question",
            isOneOf(theologyApplicationProperties.getQuestionsAndAnswers().keySet().toArray(new String[0])))
        );
  }
}