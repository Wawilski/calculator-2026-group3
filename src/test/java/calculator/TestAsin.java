package calculator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.functions.Acos;
import calculator.functions.Asin;
import calculator.functions.Atan;
import calculator.functions.Sin;
import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

class TestAsin {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
  }

  @Test
  void testAsin() throws IllegalConstruction {
    BaseNumber result = calc.eval(new Asin(List.of(new RealNumber(0.5))));
    assertTrue(result instanceof RealNumber);
    assertEquals(Math.asin(0.5), ((RealNumber) result).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testAsinOnIntegerAndRational() throws IllegalConstruction {
    BaseNumber integerResult = calc.eval(new Asin(List.of(new IntegerNumber(1))));
    BaseNumber rationalResult = calc.eval(new Asin(List.of(new RationalNumber(1, 2))));

    assertEquals(Math.asin(1), ((RealNumber) integerResult).getValue().doubleValue(), 1e-9);
    assertEquals(Math.asin(0.5), ((RealNumber) rationalResult).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testAsinSpecialCases() throws IllegalConstruction {
    BaseNumber asinInfinity = calc.eval(new Asin(List.of(new RealNumber(SpecialNumber.PositiveInfinity))));
    BaseNumber asinOutOfDomain = calc.eval(new Asin(List.of(new RealNumber(2))));

    assertEquals(SpecialNumber.NaN, ((RealNumber) asinInfinity).getSpecialValue());
    assertEquals(SpecialNumber.NaN, ((RealNumber) asinOutOfDomain).getSpecialValue());
  }

  @Test
  void testInverseTrigOnComplexHasExpectedCompositions() throws IllegalConstruction {
    ComplexNumber input = new ComplexNumber(0.4, -0.3);

    ComplexNumber asin = (ComplexNumber) calc.eval(new Asin(List.of(input)));
    ComplexNumber acos = (ComplexNumber) calc.eval(new Acos(List.of(input)));
    ComplexNumber atan = (ComplexNumber) calc.eval(new Atan(List.of(input)));
    ComplexNumber sinAsin = (ComplexNumber) calc.eval(new Sin(List.of(asin)));

    assertEquals(input.getReal().doubleValue(), sinAsin.getReal().doubleValue(), 1e-6);
    assertEquals(input.getImaginary().doubleValue(), sinAsin.getImaginary().doubleValue(), 1e-6);
    assertFalse(atan.isNaN());
    assertEquals(Math.PI / 2.0, asin.getReal().doubleValue() + acos.getReal().doubleValue(), 1e-6);
    assertEquals(0.0, asin.getImaginary().doubleValue() + acos.getImaginary().doubleValue(), 1e-6);
  }
}
