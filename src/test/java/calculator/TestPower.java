package calculator;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

class TestPower {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
  }

  @Test
  void testPower() throws IllegalConstruction {
    BaseNumber result = calc.eval(new Power(List.of(new IntegerNumber(2), new IntegerNumber(6))));
    assertEquals(new IntegerNumber(64), result);
  }

  @Test
  void testPowerIntegerBinaryAndFormat() throws IllegalConstruction {
    List<Expression> args = Arrays.asList(new IntegerNumber(2), new IntegerNumber(5));
    assertEquals(new IntegerNumber(32), calc.eval(new Power(args)));
    assertEquals("2 ** 5", calc.prettyFormat(new Power(args)));
  }

  @Test
  void testPowerCoversAllTypedBranches() throws IllegalConstruction {
    Power power = new Power(List.of(new IntegerNumber(2), new IntegerNumber(3)));

    assertEquals(8, power.op(2, 3));
    assertEquals(new IntegerNumber(16), power.op(new IntegerNumber(2), new IntegerNumber(4)));

    BaseNumber rationalPow = power.op(new RationalNumber(9, 4), new RationalNumber(1, 2));
    assertEquals(1.5, ((RealNumber) rationalPow).getValue().doubleValue(), 1e-6);

    BaseNumber realFinite = power.op(new RealNumber(4), new RealNumber(0.5));
    assertEquals(2.0, ((RealNumber) realFinite).getValue().doubleValue(), 1e-6);

    BaseNumber realNaN = power.op(new RealNumber(-1), new RealNumber(0.5));
    assertEquals(SpecialNumber.NaN, ((RealNumber) realNaN).getSpecialValue());

    BaseNumber realInf = power.op(new RealNumber(Double.MAX_VALUE), new RealNumber(2));
    assertEquals(SpecialNumber.PositiveInfinity, ((RealNumber) realInf).getSpecialValue());

    BaseNumber specialLeftNaN = power.specialOp(new RealNumber(SpecialNumber.NaN), new RealNumber(3));
    BaseNumber specialRightNaN = power.specialOp(new RealNumber(2), new RealNumber(SpecialNumber.NaN));
    BaseNumber specialRightInf = power.specialOp(new RealNumber(2), new RealNumber(SpecialNumber.PositiveInfinity));
    BaseNumber specialLeftInf = power.specialOp(new RealNumber(SpecialNumber.PositiveInfinity), new RealNumber(2));
    BaseNumber specialLeftNegInf = power.specialOp(new RealNumber(SpecialNumber.NegativeInfinity), new RealNumber(2));
    BaseNumber specialFallback = power.specialOp(new RealNumber(2), new RealNumber(3));

    assertEquals(SpecialNumber.NaN, ((RealNumber) specialLeftNaN).getSpecialValue());
    assertEquals(SpecialNumber.NaN, ((RealNumber) specialRightNaN).getSpecialValue());
    assertEquals(SpecialNumber.NaN, ((RealNumber) specialRightInf).getSpecialValue());
    assertEquals(SpecialNumber.PositiveInfinity, ((RealNumber) specialLeftInf).getSpecialValue());
    assertEquals(SpecialNumber.NaN, ((RealNumber) specialLeftNegInf).getSpecialValue());
    assertEquals(SpecialNumber.NaN, ((RealNumber) specialFallback).getSpecialValue());

    BaseNumber complexNaNInput = power.op(new ComplexNumber(), new ComplexNumber(2, 0));
    BaseNumber complexImaginaryInput = power.op(new ComplexNumber(2, 1), new ComplexNumber(2, 0));
    BaseNumber complexFinite = power.op(new ComplexNumber(2, 0), new ComplexNumber(3, 0));
    BaseNumber complexOverflow = power.op(new ComplexNumber(Double.MAX_VALUE, 0), new ComplexNumber(2, 0));

    assertTrue(((ComplexNumber) complexNaNInput).isNaN());
    assertTrue(((ComplexNumber) complexImaginaryInput).isNaN());
    assertEquals(8.0, ((ComplexNumber) complexFinite).getReal().doubleValue(), 1e-6);
    assertEquals(0.0, ((ComplexNumber) complexFinite).getImaginary().doubleValue(), 1e-6);
    assertTrue(((ComplexNumber) complexOverflow).isNaN());
  }
}
