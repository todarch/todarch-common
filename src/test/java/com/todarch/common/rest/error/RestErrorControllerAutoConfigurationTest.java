package com.todarch.common.rest.error;

import org.junit.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.autoconfigure.AutoConfigurations.of;

/**
 * Test auto-configuration of rest error controller.
 *
 * @author selimssevgi
 */
public class RestErrorControllerAutoConfigurationTest {

  private final ApplicationContextRunner contextRunner =
      new ApplicationContextRunner()
          .withConfiguration(of(RestErrorControllerAutoConfiguration.class));

  @Test
  public void restErrorControllerEnabledByDefault() {
    this.contextRunner.run(context -> {
      assertThat(context).hasSingleBean(RestErrorController.class);
    });
  }

  @Test
  public void canDisableRestErrorController() {
    this.contextRunner
        .withPropertyValues("todarch.resterrorcontroller.enabled=false")
        .run(context -> {
          assertThat(context).doesNotHaveBean(RestErrorController.class);
        });
  }

}
