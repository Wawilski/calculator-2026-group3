package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/**
 * TestNegate
 */
public class TestNegate {
  @Test
  void testRealNumberNegation() {
    RealNumber r = new RealNumber("5");
    RealNumber positiveInf = new RealNumber(SpecialNumber.PositiveInfinity);
    RealNumber negativeInf = new RealNumber(SpecialNumber.NegativeInfinity);
    RealNumber NaN = new RealNumber(SpecialNumber.NaN);

    assertEquals(new RealNumber("-5"), r.negate());
    assertEquals(new RealNumber(SpecialNumber.NegativeInfinity), positiveInf.negate());
    assertEquals(new RealNumber(SpecialNumber.PositiveInfinity), negativeInf.negate());
    assertEquals(new RealNumber(SpecialNumber.NaN), NaN.negate());
  }

  @Test
  void testIntegerNumberNegation() {
    assertEquals(new IntegerNumber(5), new IntegerNumber(-5).negate());
  }

  @Test
  void testRationalNumberNegation() {
    assertEquals(new RationalNumber(5, 6), new RationalNumber(-5, 6).negate());
    assertEquals(new RationalNumber(-5, -6), new RationalNumber(-5, 6).negate());
  }

  @Test
  void testComplexNumberNegation() {
    assertEquals(new ComplexNumber(5, 6), new ComplexNumber(-5, -6).negate());
  }

}
