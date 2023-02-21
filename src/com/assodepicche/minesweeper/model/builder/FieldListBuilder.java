package com.assodepicche.minesweeper.model.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.assodepicche.minesweeper.model.Field;
import com.assodepicche.minesweeper.model.contract.Fieldlike;

public final class FieldListBuilder {
  private static final List<Fieldlike> fields = new ArrayList<>();

  public static Collection<Fieldlike> from(int rows, int columns) {
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        fields.add(new Field(row, column));
      }
    }

    return fields;
  }
}
