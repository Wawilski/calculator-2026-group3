
package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.numbers.IllegalNumberConstruction;
import calculator.numbers.RationalNumber;

/**
 * TestRationalNumber
 */
public class TestRationalNumber {

  private final int numerator = 8;
  private final int denominator = 9;
  private RationalNumber number;

  @BeforeEach
  void setUp() {
    try {
      number = new RationalNumber(numerator, denominator);
    } catch (Exception e) {
      fail();
    }

  }

  @Test
  void testConstructor() {
    // It should not be possible to create an expression without null parameter list
    assertThrows(IllegalNumberConstruction.class, () -> number = new RationalNumber(numerator, 0));
  }

  @Test
  void testEquals() {
    // Two distinct IntegerNumber, constructed separately (using a different
    // constructor) but containing the same value should be equal
    try {
      assertEquals(new RationalNumber(8, 9), number);
      // Two IntegerNumbers containing a distinct value should not be equal:
      int otherValue = 7;
      assertNotEquals(new RationalNumber(8, otherValue), number);

      int mulNumerator = 16;
      int mulDenominator = 18;
      assertEquals(new RationalNumber(mulNumerator, mulDenominator), number);
      assertEquals(number, number); // Identity check (for coverage, as this should always be true)

    } catch (IllegalNumberConstruction _) {
      fail();
    }
  }

  @Test
  void testToString() {
    assertEquals(Integer.toString(numerator) + " / " + Integer.toString(denominator), number.toString());
  }

}
