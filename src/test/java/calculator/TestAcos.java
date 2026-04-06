package calculator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.functions.Acos;
import calculator.numbers.BaseNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

class TestAcos {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
  }

  @Test
  void testAcos() throws IllegalConstruction {
    BaseNumber result = calc.eval(new Acos(List.of(new RealNumber(0.5))));
    assertTrue(result instanceof RealNumber);
    assertEquals(Math.acos(0.5), ((RealNumber) result).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testAcosOnIntegerAndRational() throws IllegalConstruction {
    BaseNumber integerResult = calc.eval(new Acos(List.of(new IntegerNumber(1))));
    BaseNumber rationalResult = calc.eval(new Acos(List.of(new RationalNumber(1, 2))));

    assertEquals(Math.acos(1), ((RealNumber) integerResult).getValue().doubleValue(), 1e-9);
    assertEquals(Math.acos(0.5), ((RealNumber) rationalResult).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testAcosSpecialCases() throws IllegalConstruction {
    BaseNumber acosInfinity = calc.eval(new Acos(List.of(new RealNumber(SpecialNumber.PositiveInfinity))));
    BaseNumber acosOutOfDomain = calc.eval(new Acos(List.of(new RealNumber(-2))));

    assertEquals(SpecialNumber.NaN, ((RealNumber) acosInfinity).getSpecialValue());
    assertEquals(SpecialNumber.NaN, ((RealNumber) acosOutOfDomain).getSpecialValue());
  }
}
