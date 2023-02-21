package com.assodepicche.minesweeper.view;

import java.util.Scanner;

import com.assodepicche.minesweeper.exception.QuitException;

public abstract class CLI {
  protected final Scanner SCANNER = new Scanner(System.in);

  protected void output(Object output) {
    System.out.println(output);
  }

  protected String input(String placeholder) {
    System.out.print(placeholder);

    return input();
  }

  protected String input() {
    String input = SCANNER.nextLine();

    if (input.equalsIgnoreCase("\\q")) {
      throw new QuitException();
    }

    return input;
  }
}
