package com.assodepicche.minesweeper.view.helper;

public class StringHelper {
  public static String append(Object... text) {
    StringBuilder builder = new StringBuilder();

    for (char string : text.toString().toCharArray()) {
      builder.append(string);
    }

    return builder.toString();
  }
}
