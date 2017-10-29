package ru.spring.ripper.examinator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
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

}