package com.todarch.common.rest.error;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
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
 * Checks BasicErrorController behavior provided by Spring Boot.
 *
 * @author selimssevgi
 */
@Ignore("The behavior is replaced, it was just explorative testing")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicErrorControllerIntTest {

  @LocalServerPort
  private int port;

  private RestTemplate restTemplate = new RestTemplate();

  @Test
  public void defaultSpringJsonErrorResponse() {
    try {
      restTemplate.getForObject("http://localhost:{port}/pageNotFound", String.class, port);
    } catch (HttpClientErrorException ex) {
      Assertions.assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      String bodyAsString = ex.getResponseBodyAsString();
      Assertions.assertThat(bodyAsString).contains("\"path\":\"/pageNotFound\"}");
    }
  }

  @Test
  public void whileLabelErrorHtmlResponse() {
    HttpHeaders headers = new HttpHeaders();
    // browser's accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
    headers.setAccept(Arrays.asList(MediaType.TEXT_HTML, MediaType.ALL));

    HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

    try {
      restTemplate.exchange("http://localhost:{port}/pageNotFound",
          HttpMethod.GET, entity, String.class, port);
    } catch (HttpClientErrorException ex) {
      Assertions.assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      String bodyAsString = ex.getResponseBodyAsString();
      Assertions.assertThat(bodyAsString).contains("Whitelabel Error Page");
    }
  }
}
