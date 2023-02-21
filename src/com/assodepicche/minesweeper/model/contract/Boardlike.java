package com.assodepicche.minesweeper.model.contract;

public interface Boardlike {
  public boolean isSolved();

  public void open(int row, int column);

  public void toggleMarked(int row, int column);

  public void reset();

  public String toString();
}
