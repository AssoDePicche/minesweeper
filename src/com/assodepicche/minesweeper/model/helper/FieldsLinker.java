package com.assodepicche.minesweeper.model.helper;

import java.util.List;

import com.assodepicche.minesweeper.model.contract.Fieldlike;

public final class FieldsLinker {
  public static void link(List<Fieldlike> list) {
    for (Fieldlike field : list) {
      for (Fieldlike neighbor : list) {
        field.addNeighbor(neighbor);
      }
    }
  }
}
