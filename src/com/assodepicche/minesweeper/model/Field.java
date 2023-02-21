package com.assodepicche.minesweeper.model;

import java.util.ArrayList;
import java.util.List;

import com.assodepicche.minesweeper.exception.ExplosionException;
import com.assodepicche.minesweeper.model.contract.Fieldlike;

public final class Field implements Fieldlike {
  public static final String MARKED = "x", MINED = "*", OPEN = " ", CLOSED = "#";

  private final int ROW, COLUMN;

  private boolean open, mined, marked;

  private final List<Fieldlike> NEIGHBORS = new ArrayList<>();

  public Field(int row, int column) {
    this.ROW = row;

    this.COLUMN = column;
  }

  public int getRow() {
    return ROW;
  }

  public int getColumn() {
    return COLUMN;
  }

  public boolean isOpen() {
    return open;
  }

  public boolean isMined() {
    return mined;
  }

  public boolean isMarked() {
    return marked;
  }

  public boolean isSolved() {
    boolean solved = !mined && open;

    boolean locked = mined && marked;

    return solved || locked;
  }

  public boolean isNeighborhoodSafe() {
    return NEIGHBORS.stream().noneMatch(neighbor -> neighbor.isMined());
  }

  public boolean addNeighbor(Fieldlike neighbor) {
    int rowDelta = Math.abs(ROW - neighbor.getRow());

    int columnDelta = Math.abs(COLUMN - neighbor.getColumn());

    int delta = rowDelta + columnDelta;

    boolean inRange = delta == 1 || delta == 2;

    if (inRange) {
      return NEIGHBORS.add(neighbor);
    }

    return false;
  }

  public boolean open() throws ExplosionException {
    if (isOpen() || isMarked()) {
      return false;
    }

    open = true;

    if (isMined()) {
      throw new ExplosionException();
    }

    if (isNeighborhoodSafe()) {
      NEIGHBORS.forEach(neighbor -> neighbor.open());
    }

    return true;
  }

  public long getMinesInNeighbor() {
    return NEIGHBORS.stream().filter(neighbor -> neighbor.isMined()).count();
  }

  public void toggleMarked() {
    if (!isOpen()) {
      marked = !marked;
    }
  }

  public void mine() {
    mined = true;
  }

  public void reset() {
    open = false;

    mined = false;

    marked = false;
  }

  public String toString() {
    if (marked) {
      return MARKED;
    }

    if (open && mined) {
      return MINED;
    }

    if (open && getMinesInNeighbor() > 0) {
      return Long.toString(getMinesInNeighbor());
    }

    if (open) {
      return OPEN;
    }

    return CLOSED;
  }
}
