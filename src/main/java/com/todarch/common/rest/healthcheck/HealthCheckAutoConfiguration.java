package com.todarch.common.rest.healthcheck;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "todarch.healtcheck.endpoints.enabled", havingValue = "true")
@ComponentScan("com.todarch.common.rest.healthcheck")
public class HealthCheckAutoConfiguration {
}
