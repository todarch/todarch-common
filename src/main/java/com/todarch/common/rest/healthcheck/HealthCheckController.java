package com.todarch.common.rest.healthcheck;

import com.todarch.common.rest.Endpoints;
import com.todarch.common.util.InstanceUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

  private final BuildInfo buildInfo;

  private final String appName;

  private static final String INSTANCE_ID = InstanceUtil.instanceId();

  /**
   * Lombok does not create such constructor without configuration.
   * https://github.com/rzwitserloot/lombok/issues/745
   */
  public HealthCheckController(BuildInfo buildInfo,
                               @Value("${spring.application.name:defaultAppName}")
                               String appName) {
    this.buildInfo = buildInfo;
    this.appName = appName;
  }

  @GetMapping(Endpoints.UP)
  public String up() {
    return "I am " + appName + "#" + INSTANCE_ID + " , up and running";
  }

  @GetMapping(Endpoints.BUILD_INFO)
  public BuildInfo buildInfo() {
    return buildInfo;
  }
}

