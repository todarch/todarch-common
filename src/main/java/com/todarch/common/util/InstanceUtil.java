package com.todarch.common.util;

import java.net.InetAddress;

/**
 * Common utilities for instances of services.
 *
 * @author selimssevgi
 */
public final class InstanceUtil {

  private InstanceUtil() {
    throw new AssertionError("No instance of utility class");
  }

  private static final String DEFAULT_INSTANCE_ID = "-1";

  /**
   * Tries to extract an identifier for an services,
   * from its ip address. Using the part after last dot.
   * It is only for debugging service discovery,
   * It can fail back to default value in case of any error.
   *
   * @return part after the last comma, or a default value.
   */
  public static String instanceId() {
    try {
      String hostAddress = InetAddress.getLocalHost().getHostAddress();
      return hostAddress.substring(hostAddress.lastIndexOf('.') + 1);
    } catch (Exception forLog) {
      return DEFAULT_INSTANCE_ID;
    }
  }

}
