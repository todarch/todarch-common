package com.todarch.common.rest;

public final class Endpoints {
  private Endpoints() {
    throw new AssertionError("Cannot create objects from util class");
  }

  public static final String NON_SECURED = "/non-secured";
  public static final String UP = NON_SECURED + "/up";
  public static final String BUILD_INFO = NON_SECURED + "/bi";

}
