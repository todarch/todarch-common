package com.todarch.common.rest.healthcheck;

import com.todarch.common.rest.Endpoints;
import com.todarch.common.util.InstanceUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HealthCheckController {

  private final BuildInfo buildInfo;

  @Value("${spring.application.name:defaultAppName}")
  private static String appName;

  private static final String INSTANCE_ID = InstanceUtil.instanceId();

  @GetMapping(Endpoints.UP)
  public String up() {
    return "I am " + appName + "#" + INSTANCE_ID + " , up and running";
  }

  @GetMapping(Endpoints.BUILD_INFO)
  public BuildInfo buildInfo() {
    return buildInfo;
  }
}

