package com.FPTU.security.dto;

import java.util.Locale;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Request common class.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Request {

  private String locale;

  /**
   * get locale object by string locale.
   *
   * @return Locale
   */
  public Locale getLocale() {
    if (locale != null) {
      return new Locale.Builder().setLanguage(locale).build();
    } else {
      return null;
    }
  }

}