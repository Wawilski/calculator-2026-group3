package calculator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.functions.Cos;
import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

class TestCos {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
  }

  @Test
  void testCos() throws IllegalConstruction {
    BaseNumber result = calc.eval(new Cos(List.of(new IntegerNumber(1))));
    assertTrue(result instanceof RealNumber);
    assertEquals(Math.cos(1), ((RealNumber) result).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testCosOnRationalAndSpecial() throws IllegalConstruction {
    BaseNumber rational = calc.eval(new Cos(List.of(new RationalNumber(1, 2))));
    BaseNumber special = calc.eval(new Cos(List.of(new RealNumber(SpecialNumber.PositiveInfinity))));

    assertEquals(Math.cos(0.5), ((RealNumber) rational).getValue().doubleValue(), 1e-9);
    assertEquals(SpecialNumber.NaN, ((RealNumber) special).getSpecialValue());
  }

  @Test
  void testCosOnComplexUsesHyperbolicFormula() throws IllegalConstruction {
    ComplexNumber input = new ComplexNumber(1.2, -0.7);
    ComplexNumber cosResult = (ComplexNumber) calc.eval(new Cos(List.of(input)));

    double cosExpectedReal = Math.cos(1.2) * Math.cosh(-0.7);
    double cosExpectedImaginary = -Math.sin(1.2) * Math.sinh(-0.7);

    assertEquals(cosExpectedReal, cosResult.getReal().doubleValue(), 1e-6);
    assertEquals(cosExpectedImaginary, cosResult.getImaginary().doubleValue(), 1e-6);
  }
}
