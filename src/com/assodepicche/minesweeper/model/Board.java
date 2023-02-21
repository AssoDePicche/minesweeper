package com.assodepicche.minesweeper.model;

import java.util.function.Consumer;

import java.util.ArrayList;
import java.util.List;

import com.assodepicche.minesweeper.exception.ExplosionException;
import com.assodepicche.minesweeper.model.builder.FieldListBuilder;
import com.assodepicche.minesweeper.model.contract.Boardlike;
import com.assodepicche.minesweeper.model.contract.Fieldlike;
import com.assodepicche.minesweeper.model.helper.FieldSearcher;
import com.assodepicche.minesweeper.model.helper.FieldsLinker;
import com.assodepicche.minesweeper.model.helper.MineScatterer;

public final class Board implements Boardlike {
  private final int rows, columns, mines;

  private final List<Fieldlike> fields = new ArrayList<>();

  public Board(int rows, int columns, int mines) {
    this.rows = rows;

    this.columns = columns;

    this.mines = mines;

    fields.addAll(FieldListBuilder.from(rows, columns));

    FieldsLinker.link(fields);

    MineScatterer.scatter(fields, mines);
  }

  public boolean isSolved() {
    return fields.stream().allMatch(field -> field.isSolved());
  }

  public void open(int row, int column) {
    Consumer<Fieldlike> open = field -> field.open();

    try {
      FieldSearcher.exec(fields, row, column, open);
    } catch (ExplosionException exception) {
      fields.forEach(field -> field.open());

      throw exception;
    }
  }

  public void toggleMarked(int row, int column) {
    Consumer<Fieldlike> toggle = field -> field.toggleMarked();

    FieldSearcher.exec(fields, row, column, toggle);
  }

  public void reset() {
    fields.stream().forEach(field -> field.reset());

    MineScatterer.scatter(fields, mines);
  }

  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append(" ");

    for (int column = 0; column < columns; column++) {
      builder.append(" ");

      builder.append(column);

      builder.append(" ");
    }

    builder.append("\n");

    int field = 0;

    for (int row = 0; row < rows; row++) {
      builder.append(row);

      for (int column = 0; column < columns; column++) {
        builder.append(" ");

        builder.append(fields.get(field));

        builder.append(" ");

        field++;
      }

      builder.append("\n");
    }

    return builder.toString();
  }
}
