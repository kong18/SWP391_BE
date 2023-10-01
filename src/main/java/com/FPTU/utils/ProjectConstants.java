package com.FPTU.utils;

import java.util.Locale;

/**
 * Created on April 2023.
 *
 * @author Jay
 */
public final class ProjectConstants {

  public static final String DEFAULT_ENCODING = "UTF-8";

  public static final Locale DEFAULT_LOCALE
      = new Locale.Builder().setLanguage("en").setRegion("EN").build();

  private ProjectConstants() {

    throw new UnsupportedOperationException();
  }

}
