package com.todarch.common.rest.error;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Auto-configures rest error controller.
 *
 * @author selimssevgi
 */
@Configuration
@ConditionalOnProperty(
    value = "todarch.resterrorcontroller.enabled",
    havingValue = "true",
    matchIfMissing = true)
@ComponentScan("com.todarch.common.rest.error")
public class RestErrorControllerAutoConfiguration {
}
