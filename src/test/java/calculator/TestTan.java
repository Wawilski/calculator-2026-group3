package calculator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.functions.Cos;
import calculator.functions.Sin;
import calculator.functions.Tan;
import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

class TestTan {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
  }

  @Test
  void testTan() throws IllegalConstruction {
    BaseNumber result = calc.eval(new Tan(List.of(new IntegerNumber(1))));
    assertTrue(result instanceof RealNumber);
    assertEquals(Math.tan(1), ((RealNumber) result).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testTanOnRationalAndSpecial() throws IllegalConstruction {
    BaseNumber rational = calc.eval(new Tan(List.of(new RationalNumber(1, 2))));
    BaseNumber special = calc.eval(new Tan(List.of(new RealNumber(SpecialNumber.PositiveInfinity))));

    assertEquals(Math.tan(0.5), ((RealNumber) rational).getValue().doubleValue(), 1e-9);
    assertEquals(SpecialNumber.NaN, ((RealNumber) special).getSpecialValue());
  }

  @Test
  void testTanOnComplexMatchesSinDividedByCos() throws IllegalConstruction {
    ComplexNumber input = new ComplexNumber(0.5, 0.25);

    ComplexNumber sin = (ComplexNumber) calc.eval(new Sin(List.of(input)));
    ComplexNumber cos = (ComplexNumber) calc.eval(new Cos(List.of(input)));
    ComplexNumber tan = (ComplexNumber) calc.eval(new Tan(List.of(input)));

    double denominator = cos.getReal().doubleValue() * cos.getReal().doubleValue()
        + cos.getImaginary().doubleValue() * cos.getImaginary().doubleValue();
    double expectedReal = (sin.getReal().doubleValue() * cos.getReal().doubleValue()
        + sin.getImaginary().doubleValue() * cos.getImaginary().doubleValue()) / denominator;
    double expectedImaginary = (sin.getImaginary().doubleValue() * cos.getReal().doubleValue()
        - sin.getReal().doubleValue() * cos.getImaginary().doubleValue()) / denominator;

    assertEquals(expectedReal, tan.getReal().doubleValue(), 1e-6);
    assertEquals(expectedImaginary, tan.getImaginary().doubleValue(), 1e-6);
  }
}
