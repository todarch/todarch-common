package com.todarch.common.rest.error;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Tests error handling in restful way.
 *
 * @author selimssevgi
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestErrorControllerIntTest {

  @LocalServerPort
  private int port;

  private final RestTemplate restTemplate = new RestTemplate();

  @Test
  public void customizeJsonErrorResponse() {
    try {
      restTemplate.getForObject("http://localhost:{port}/pageNotFound", String.class, port);
    } catch (HttpClientErrorException ex) {
      assertNotFound(ex);
    }
  }

  @Test
  public void doNotShowWhileLabelErrorPage() {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.TEXT_HTML, MediaType.ALL));

    HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

    try {
      restTemplate.exchange("http://localhost:{port}/pageNotFound",
          HttpMethod.GET, entity, String.class, port);
    } catch (HttpClientErrorException ex) {
      assertNotFound(ex);
    }
  }

  private void assertNotFound(HttpClientErrorException ex) {
    Assertions.assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    String bodyAsString = ex.getResponseBodyAsString();
    Assertions.assertThat(bodyAsString).contains("\"errorMessage\":\"Not Found\"");
  }

}
