package com.assodepicche.minesweeper.model.helper;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.assodepicche.minesweeper.model.contract.Fieldlike;

public final class FieldSearcher {
  public static void exec(List<Fieldlike> list, int row, int column, Consumer<Fieldlike> consumer) {
    Predicate<Fieldlike> equalRows = field -> field.getRow() == row;

    Predicate<Fieldlike> equalColumns = field -> field.getColumn() == column;

    list.parallelStream()
        .filter(equalRows)
        .filter(equalColumns)
        .findFirst()
        .ifPresent(consumer);
  }
}
