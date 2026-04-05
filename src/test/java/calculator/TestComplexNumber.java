
package calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.numbers.ComplexNumber;
import calculator.numbers.RealNumber;

/**
 * TestComplexNumber
 */
public class TestComplexNumber {

  private final double real = Math.PI;
  private final double imaginary = Math.PI;
  private ComplexNumber number;

  @BeforeEach
  void setUp() {
    number = new ComplexNumber(real, imaginary);
  }

  @Test
  void testEquals() {

    assertEquals(new ComplexNumber(real, imaginary), number);

    int otherReal = 1;
    int otherImaginary = 7;

    assertNotEquals(new ComplexNumber(otherReal, otherImaginary), number);

    assertEquals(number, number);

    assertNotEquals(number, real);

    try {
      assertNotEquals(new Times(new ArrayList<>()), number);
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testToString() {
    BigDecimal real = new BigDecimal(this.real).setScale(RealNumber.getScale(), RoundingMode.CEILING);
    BigDecimal imaginary = new BigDecimal(this.imaginary).setScale(RealNumber.getScale(), RoundingMode.CEILING);

    ComplexNumber realComplex = new ComplexNumber(this.real, 0);
    ComplexNumber imaginaryComplex = new ComplexNumber(0, this.imaginary);
    ComplexNumber NaNComplex = new ComplexNumber();

    assertEquals(real.toString() + " + " + imaginary.toString() + "i", number.toString());
    assertEquals(real.toString(), realComplex.toString());
    assertEquals(imaginary.toString() + "i", imaginaryComplex.toString());
    assertEquals("NaN", NaNComplex.toString());

  }

  @Test
  void testHashCode() {
    int hashReal = new BigDecimal(real).setScale(RealNumber.getScale(), RoundingMode.CEILING).hashCode();
    int hashImaginary = new BigDecimal(imaginary).setScale(RealNumber.getScale(), RoundingMode.CEILING).hashCode();
    assertEquals(hashReal + hashImaginary, number.hashCode());

  }
}
