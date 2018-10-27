package com.todarch.common.rest.healthcheck;

import com.todarch.common.rest.Endpoints;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HealthCheckControllerIntTest {

  @Autowired
  private MockMvc mockMvc;

  private static final String SPRING_APPLICATION_NAME = "co";

  @Test
  public void shouldHaveUpEndpoint() throws Exception {
    mockMvc.perform(get(Endpoints.UP))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(Matchers.containsString(SPRING_APPLICATION_NAME)));
  }

  @Test
  public void shouldHaveBuildInfoEndpoint() throws Exception {
    mockMvc.perform(get(Endpoints.BUILD_INFO)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.buildNum").exists())
        .andExpect(jsonPath("$.commitHash").exists())
        .andExpect(jsonPath("$.commitUser").exists())
        .andExpect(jsonPath("$.repoName").exists())
        .andExpect(jsonPath("$.workflowGuid").exists());
  }
}
