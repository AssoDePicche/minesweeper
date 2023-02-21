package com.assodepicche.minesweeper.model;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.assodepicche.minesweeper.exception.ExplosionException;

public final class FieldTest {
  private Field field;

  @BeforeEach
  public void build() {
    field = new Field(3, 3);
  }

  @Test
  public void shouldAddNeighborInRangeOfOneField() {
    Field neighbor = new Field(3, 2);

    boolean result = field.addNeighbor(neighbor);

    assertTrue(result);
  }

  @Test
  public void shouldAddNeighborInRangeOfTwoFields() {
    Field neighbor = new Field(3, 4);

    boolean result = field.addNeighbor(neighbor);

    assertTrue(result);
  }

  @Test
  public void shouldNotAddNeighborOutOfRangeUpToTwoFields() {
    Field neighbor = new Field(6, 6);

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
    Field f1 = new Field(1, 1);

    Field f2 = new Field(2, 2);

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
