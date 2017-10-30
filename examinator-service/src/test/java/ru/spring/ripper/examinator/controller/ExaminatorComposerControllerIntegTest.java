package ru.spring.ripper.examinator.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author tolkv
 * @version 30/10/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
public class ExaminatorComposerControllerIntegTest {
  @Autowired
  MockMvc mvc;
  @Autowired
  MockRestServiceServer mockServer;
  @Value("classpath:example.theology.service.response.json")
  Resource theologyResponse;

  @Test
  public void should_invoke_theology_service_and_return_exam() throws Exception {

    mockServer.expect(
        requestTo("http://localhost:8080/exercise/random?count=2")
    ).andExpect(method(GET))
          .andExpect(queryParam("count", equalTo("2")))
        .andRespond(
            withSuccess()
                .body(theologyResponse)
                .contentType(APPLICATION_JSON)
        );

    mvc.perform(post("/examine/collect")
        .contentType(APPLICATION_JSON)
        .content("{ \"theology\": 2 }")
    )
        .andExpect(status().isOk())
        .andExpect(
            jsonPath("$.sections", hasSize(1))
        )
        .andExpect(
            jsonPath("$.sections.[0].exercises", hasSize(2))
        )
        .andExpect(
            jsonPath("$.sections.[0].exercises.[0].answer", equalTo("nope"))
        )
    ;


  }
}