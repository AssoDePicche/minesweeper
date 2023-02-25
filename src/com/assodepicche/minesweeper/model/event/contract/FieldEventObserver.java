package com.assodepicche.minesweeper.model.event.contract;

import com.assodepicche.minesweeper.model.contract.Fieldlike;
import com.assodepicche.minesweeper.model.event.FieldEvent;

@FunctionalInterface
public interface FieldEventObserver {
  public void event(Fieldlike field, FieldEvent event);
}
