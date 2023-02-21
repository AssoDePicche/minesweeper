package com.assodepicche.minesweeper.model.helper;

import java.util.List;
import java.util.function.Predicate;

import com.assodepicche.minesweeper.model.contract.Fieldlike;

public final class MineScatterer {
  public static void scatter(List<Fieldlike> fields, int mines) {
    long armedMines = 0;

    Predicate<Fieldlike> mined = field -> field.isMined();

    while (armedMines < mines) {
      int seed = (int) (Math.random() * fields.size());

      fields.get(seed).mine();

      armedMines = fields.stream().filter(mined).count();
    }
  }
}
