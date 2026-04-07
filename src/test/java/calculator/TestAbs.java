package calculator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.functions.Abs;
import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

class TestAbs {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
  }

  @Test
  void testAbs() throws IllegalConstruction {
    BaseNumber result = calc.eval(new Abs(List.of(new IntegerNumber(-9))));
    assertEquals(new IntegerNumber(9), result);
  }

  @Test
  void testAbsCoversRealRationalAndComplexBranches() throws IllegalConstruction {
    Abs abs = new Abs(List.of(new IntegerNumber(1)));

    BaseNumber rational = calc.eval(new Abs(List.of(new RationalNumber(-6, -8))));
    BaseNumber realSpecialNaN = calc.eval(new Abs(List.of(new RealNumber(SpecialNumber.NaN))));
    BaseNumber realSpecialNegInf = calc.eval(new Abs(List.of(new RealNumber(SpecialNumber.NegativeInfinity))));
    BaseNumber realFinite = calc.eval(new Abs(List.of(new RealNumber(-12.5))));
    BaseNumber complexMod = calc.eval(new Abs(List.of(new ComplexNumber(3, 4))));
    BaseNumber complexNaN = abs.function(new ComplexNumber());

    assertEquals(new RationalNumber(3, 4), rational);
    assertEquals(SpecialNumber.NaN, ((RealNumber) realSpecialNaN).getSpecialValue());
    assertEquals(SpecialNumber.PositiveInfinity, ((RealNumber) realSpecialNegInf).getSpecialValue());
    assertEquals(12.5, ((RealNumber) realFinite).getValue().doubleValue(), 1e-9);
    assertEquals(5.0, ((RealNumber) complexMod).getValue().doubleValue(), 1e-9);
    assertEquals(SpecialNumber.NaN, ((RealNumber) complexNaN).getSpecialValue());
    assertEquals(7, abs.function(-7));
  }
}
