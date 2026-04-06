package calculator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.functions.Ln;
import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

class TestLn {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
  }

  @Test
  void testLn() throws IllegalConstruction {
    BaseNumber result = calc.eval(new Ln(List.of(new RealNumber(2.5))));
    assertTrue(result instanceof RealNumber);
    assertEquals(Math.log(2.5), ((RealNumber) result).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testLnSpecialCases() throws IllegalConstruction {
    BaseNumber lnZero = calc.eval(new Ln(List.of(new IntegerNumber(0))));
    BaseNumber lnNegative = calc.eval(new Ln(List.of(new IntegerNumber(-1))));
    BaseNumber lnPosInfinity = calc.eval(new Ln(List.of(new RealNumber(SpecialNumber.PositiveInfinity))));
    BaseNumber lnNegInfinity = calc.eval(new Ln(List.of(new RealNumber(SpecialNumber.NegativeInfinity))));

    assertEquals(SpecialNumber.NegativeInfinity, ((RealNumber) lnZero).getSpecialValue());
    assertEquals(SpecialNumber.NaN, ((RealNumber) lnNegative).getSpecialValue());
    assertEquals(SpecialNumber.PositiveInfinity, ((RealNumber) lnPosInfinity).getSpecialValue());
    assertEquals(SpecialNumber.NaN, ((RealNumber) lnNegInfinity).getSpecialValue());
  }

  @Test
  void testLnRationalAndComplexBranches() throws IllegalConstruction {
    BaseNumber lnRational = calc.eval(new Ln(List.of(new RationalNumber(9, 4))));
    BaseNumber lnNegativeRational = calc.eval(new Ln(List.of(new RationalNumber(-3, 2))));
    BaseNumber lnComplex = calc.eval(new Ln(List.of(new ComplexNumber(2, -1))));

    assertEquals(Math.log(2.25), ((RealNumber) lnRational).getValue().doubleValue(), 1e-9);
    assertEquals(SpecialNumber.NaN, ((RealNumber) lnNegativeRational).getSpecialValue());
    assertTrue(((ComplexNumber) lnComplex).isNaN());
  }
}
