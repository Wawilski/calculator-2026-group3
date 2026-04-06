package calculator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.functions.Atan;
import calculator.numbers.BaseNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

class TestAtan {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
  }

  @Test
  void testAtan() throws IllegalConstruction {
    BaseNumber result = calc.eval(new Atan(List.of(new RealNumber(0.5))));
    assertTrue(result instanceof RealNumber);
    assertEquals(Math.atan(0.5), ((RealNumber) result).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testAtanOnIntegerAndRational() throws IllegalConstruction {
    BaseNumber integerResult = calc.eval(new Atan(List.of(new IntegerNumber(1))));
    BaseNumber rationalResult = calc.eval(new Atan(List.of(new RationalNumber(1, 2))));

    assertEquals(Math.atan(1), ((RealNumber) integerResult).getValue().doubleValue(), 1e-9);
    assertEquals(Math.atan(0.5), ((RealNumber) rationalResult).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testAtanSpecialCases() throws IllegalConstruction {
    BaseNumber atanPositiveInfinity = calc.eval(new Atan(List.of(new RealNumber(SpecialNumber.PositiveInfinity))));
    BaseNumber atanNegativeInfinity = calc.eval(new Atan(List.of(new RealNumber(SpecialNumber.NegativeInfinity))));
    BaseNumber atanNaN = calc.eval(new Atan(List.of(new RealNumber(SpecialNumber.NaN))));

    assertEquals(Math.PI / 2.0, ((RealNumber) atanPositiveInfinity).getValue().doubleValue(), 1e-9);
    assertEquals(-Math.PI / 2.0, ((RealNumber) atanNegativeInfinity).getValue().doubleValue(), 1e-9);
    assertEquals(SpecialNumber.NaN, ((RealNumber) atanNaN).getSpecialValue());
  }
}
