package com.assodepicche.minesweeper.view;

import java.util.Arrays;
import java.util.Iterator;

import com.assodepicche.minesweeper.exception.ExplosionException;
import com.assodepicche.minesweeper.exception.QuitException;
import com.assodepicche.minesweeper.model.contract.Boardlike;

public class BoardCLI extends CLI {
  private final Boardlike BOARD;

  public BoardCLI(Boardlike board) {
    this.BOARD = board;

    execute();
  }

  private void execute() {
    try {
      boolean inGame = true;

      while (inGame) {
        turn();

        output("Another match? (Y/n)");

        inGame = !input().equalsIgnoreCase("n");

        BOARD.reset();
      }
    } catch (QuitException exception) {
      output("Bye Bye");
    } finally {
      SCANNER.close();
    }
  }

  private void turn() {
    try {
      while (!BOARD.isSolved()) {
        output(BOARD);

        Iterator<Integer> coordinates = Arrays.stream(input("Type the coordinates of field (x, y): ").split(","))
            .map(integer -> Integer.parseInt(integer.trim()))
            .iterator();

        int x = coordinates.next(), y = coordinates.next();

        switch (input("[1] Open field\n[2] Mark/Unmark field: ")) {
          case "1":
            BOARD.open(x, y);
            break;

          case "2":
            BOARD.toggleMarked(x, y);
            break;

          default:
            output("Choose a valid option");
        }
      }

      finalScreen("You WIN!");
    } catch (ExplosionException exception) {
      finalScreen("You LOOSE!");
    }
  }

  private void finalScreen(String message) {
    output(BOARD);

    output(message);
  }
}
