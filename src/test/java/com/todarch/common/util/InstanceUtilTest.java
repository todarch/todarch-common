package com.todarch.common.util;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class InstanceUtilTest {

  @Test
  public void instanceId() {
    String id = InstanceUtil.instanceId();
    Assertions.assertThat(id).isNotNull();
  }
}
