package com.todarch.common.rest.error;

import lombok.Data;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Overrides the behavior of BasicErrorController, getting rid of white label page error.
 *
 * @author selimssevgi
 */
@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class RestErrorController extends AbstractErrorController {

  public RestErrorController() {
    super(new DefaultErrorAttributes());
  }

  /**
   * Handles the erroneous cases returning json response instead of while label error page.
   */
  @RequestMapping
  public ResponseEntity<Object> restError(HttpServletRequest request) {
    HttpStatus status = getStatus(request);

    RestError restError = new RestError();
    restError.setErrorCode(String.valueOf(status.value()));
    restError.setErrorMessage(status.getReasonPhrase());

    return ResponseEntity.status(status).body(restError);
  }

  @Override
  public String getErrorPath() {
    return null;
  }

  @Data
  private class RestError {
    private String errorCode;
    private String errorMessage;
  }
}
