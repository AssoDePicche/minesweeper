package com.assodepicche.minesweeper.model.contract;

public interface Fieldlike {
  public int getRow();

  public int getColumn();

  public boolean isOpen();

  public boolean isMined();

  public boolean isMarked();

  public boolean isSolved();

  public boolean isNeighborhoodSafe();

  public boolean addNeighbor(Fieldlike neighbor);

  public boolean open();

  public long getMinesInNeighbor();

  public void toggleMarked();

  public void mine();

  public void reset();

  public String toString();
}
