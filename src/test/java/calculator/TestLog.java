package calculator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.functions.Log;
import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

class TestLog {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
  }

  @Test
  void testLog() throws IllegalConstruction {
    BaseNumber result = calc.eval(new Log(List.of(new RealNumber(100), new RealNumber(10))));
    assertTrue(result instanceof RealNumber);
    assertEquals(2.0, ((RealNumber) result).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testLogSupportsCustomBaseOrder() throws IllegalConstruction {
    BaseNumber logResult = calc.eval(new Log(List.of(new IntegerNumber(1), new IntegerNumber(2))));
    assertTrue(logResult instanceof RealNumber);
    assertEquals(0.0, ((RealNumber) logResult).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testLogSpecialAndDomainCases() throws IllegalConstruction {
    BaseNumber invalidBase = calc.eval(new Log(List.of(new IntegerNumber(10), new IntegerNumber(1))));
    BaseNumber zeroValue = calc.eval(new Log(List.of(new IntegerNumber(0), new IntegerNumber(10))));
    BaseNumber negativeValue = calc.eval(new Log(List.of(new IntegerNumber(-5), new IntegerNumber(10))));
    BaseNumber posInfValue = calc.eval(new Log(List.of(new RealNumber(SpecialNumber.PositiveInfinity), new RealNumber(10))));
    BaseNumber specialBase = calc.eval(new Log(List.of(new RealNumber(10), new RealNumber(SpecialNumber.PositiveInfinity))));

    assertEquals(SpecialNumber.NaN, ((RealNumber) invalidBase).getSpecialValue());
    assertEquals(SpecialNumber.NegativeInfinity, ((RealNumber) zeroValue).getSpecialValue());
    assertEquals(SpecialNumber.NaN, ((RealNumber) negativeValue).getSpecialValue());
    assertEquals(SpecialNumber.PositiveInfinity, ((RealNumber) posInfValue).getSpecialValue());
    assertEquals(SpecialNumber.NaN, ((RealNumber) specialBase).getSpecialValue());
  }

  @Test
  void testLogRationalAndComplexBranches() throws IllegalConstruction {
    BaseNumber rationalLog = calc.eval(new Log(List.of(new RationalNumber(16, 1), new RationalNumber(4, 1))));
    BaseNumber complexLog = calc.eval(new Log(List.of(new ComplexNumber(2, 3), new ComplexNumber(1, 0))));

    assertEquals(2.0, ((RealNumber) rationalLog).getValue().doubleValue(), 1e-9);
    assertTrue(((ComplexNumber) complexLog).isNaN());
  }
}
