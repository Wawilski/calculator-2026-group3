package calculator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.functions.Sqrt;
import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

class TestSqrt {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
  }

  @Test
  void testSqrt() throws IllegalConstruction {
    BaseNumber result = calc.eval(new Sqrt(List.of(new IntegerNumber(16))));
    assertTrue(result instanceof RealNumber);
    assertEquals(4.0, ((RealNumber) result).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testSqrtBranchCoverage() throws IllegalConstruction {
    Sqrt sqrt = new Sqrt(List.of(new IntegerNumber(1)));
    assertEquals(4, sqrt.function(16));

    BaseNumber negative = calc.eval(new Sqrt(List.of(new IntegerNumber(-9))));
    BaseNumber positiveInfinity = calc.eval(new Sqrt(List.of(new RealNumber(SpecialNumber.PositiveInfinity))));
    BaseNumber specialNaN = calc.eval(new Sqrt(List.of(new RealNumber(SpecialNumber.NaN))));
    BaseNumber negativeRational = calc.eval(new Sqrt(List.of(new RationalNumber(-1, 4))));
    BaseNumber complex = calc.eval(new Sqrt(List.of(new ComplexNumber(1, 2))));

    assertEquals(SpecialNumber.NaN, ((RealNumber) negative).getSpecialValue());
    assertEquals(SpecialNumber.PositiveInfinity, ((RealNumber) positiveInfinity).getSpecialValue());
    assertEquals(SpecialNumber.NaN, ((RealNumber) specialNaN).getSpecialValue());
    assertEquals(SpecialNumber.NaN, ((RealNumber) negativeRational).getSpecialValue());
    assertTrue(((ComplexNumber) complex).isNaN());
  }
}
