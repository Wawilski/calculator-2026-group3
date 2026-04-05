package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.numbers.RealNumber;

class TestRealNumber {
  private final double value = Math.PI;
  private RealNumber number;

  @BeforeEach
  void setUp() {
    number = new RealNumber(value);
  }

  @Test
  void testEquals() {

    assertEquals(new RealNumber(value), number);

    int otherValue = 7;

    assertNotEquals(new RealNumber(otherValue), number);

    assertEquals(number, number);

    assertNotEquals(number, value);

    try {
      assertNotEquals(new Times(new ArrayList<>()), number);
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testHashCode() {
    assertEquals(new BigDecimal(value).setScale(RealNumber.getScale(), RoundingMode.CEILING).hashCode(),
        number.hashCode());
  }

}
