package calculator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.functions.Sin;
import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

class TestSin {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
  }

  @Test
  void testSin() throws IllegalConstruction {
    BaseNumber result = calc.eval(new Sin(List.of(new IntegerNumber(1))));
    assertTrue(result instanceof RealNumber);
    assertEquals(Math.sin(1), ((RealNumber) result).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testSinOnRationalAndSpecial() throws IllegalConstruction {
    BaseNumber rational = calc.eval(new Sin(List.of(new RationalNumber(1, 2))));
    BaseNumber special = calc.eval(new Sin(List.of(new RealNumber(SpecialNumber.PositiveInfinity))));

    assertEquals(Math.sin(0.5), ((RealNumber) rational).getValue().doubleValue(), 1e-9);
    assertEquals(SpecialNumber.NaN, ((RealNumber) special).getSpecialValue());
  }

  @Test
  void testSinOnComplexUsesHyperbolicFormula() throws IllegalConstruction {
    ComplexNumber input = new ComplexNumber(1.2, -0.7);
    ComplexNumber sinResult = (ComplexNumber) calc.eval(new Sin(List.of(input)));

    double sinExpectedReal = Math.sin(1.2) * Math.cosh(-0.7);
    double sinExpectedImaginary = Math.cos(1.2) * Math.sinh(-0.7);

    assertEquals(sinExpectedReal, sinResult.getReal().doubleValue(), 1e-6);
    assertEquals(sinExpectedImaginary, sinResult.getImaginary().doubleValue(), 1e-6);
  }
}
