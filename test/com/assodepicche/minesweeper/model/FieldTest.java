package com.assodepicche.minesweeper.model;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.assodepicche.minesweeper.exception.ExplosionException;
import com.assodepicche.minesweeper.model.contract.Fieldlike;

public final class FieldTest {
  private Fieldlike field;

  @BeforeEach
  public void build() {
    field = new Field(3, 3);
  }

  private Fieldlike buildNeighbor(int row, int column) {
    return new Field(row, column);
  }

  @Test
  public void shouldAddNeighborInRangeOfOneField() {
    Fieldlike neighbor = buildNeighbor(3, 2);

    boolean result = field.addNeighbor(neighbor);

    assertTrue(result);
  }

  @Test
  public void shouldAddNeighborInRangeOfTwoFields() {
    Fieldlike neighbor = buildNeighbor(3, 4);

    boolean result = field.addNeighbor(neighbor);

    assertTrue(result);
  }

  @Test
  public void shouldNotAddNeighborOutOfRangeUpToTwoFields() {
    Fieldlike neighbor = buildNeighbor(6, 6);

    boolean result = field.addNeighbor(neighbor);

    assertFalse(result);
  }

  @Test
  public void shouldStartUnmarked() {
    assertFalse(field.isMarked());
  }

  @Test
  public void shouldBecomeMarkedWhenToggleMarkedIsCalledOneTime() {
    field.toggleMarked();

    assertTrue(field.isMarked());
  }

  @Test
  public void shouldBecomeUnmarkedWhenToggleMarkedIsCalledTwice() {
    field.toggleMarked();

    field.toggleMarked();

    assertFalse(field.isMarked());
  }

  @Test
  public void shouldOpenWhenUnmarked() {
    assertTrue(field.open());
  }

  @Test
  public void shouldNotOpenWhenMarked() {
    field.toggleMarked();

    assertFalse(field.open());
  }

  @Test
  public void shouldThrowExplosionExceptionWhenOpenAMinedField() {
    field.mine();

    assertThrows(ExplosionException.class, () -> {
      field.open();
    });
  }

  @Test
  public void shouldOpenNeighbors() {
    Fieldlike f1 = buildNeighbor(1, 1);

    Fieldlike f2 = buildNeighbor(2, 2);

    f2.addNeighbor(f1);

    field.addNeighbor(f2);

    field.open();

    assertTrue(f1.isOpen());
  }

  @Test
  public void shouldBeSolvedWhenLocked() {
    field.mine();

    field.toggleMarked();

    assertTrue(field.isSolved());
  }

  @Test
  public void shouldReturnATokenWhenCalledToString() {
    field.open();

    assertEquals(Field.OPEN, field.toString());
  }
}
