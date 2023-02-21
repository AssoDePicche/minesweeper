package com.assodepicche.minesweeper.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.assodepicche.minesweeper.model.contract.Boardlike;

public final class BoardTest {
  private Boardlike board;

  @BeforeEach
  public void build() {
    board = new Board(3, 3, 2);
  }

  @Test
  public void shouldReturnAllSlashesWhenStartTheGame() {
    String expected = "  0  1  2 \n0 #  #  # \n1 #  #  # \n2 #  #  # \n";

    assertEquals(expected, board.toString());
  }
}
