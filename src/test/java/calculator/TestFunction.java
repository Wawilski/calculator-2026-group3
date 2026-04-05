package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;
import calculator.functions.*;
import visitor.CountingVisitor;

class TestFunction {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
  }

  @Test
  void testSqrtInteger() throws IllegalConstruction {
    List<Expression> args = List.of(new IntegerNumber(9));
    BaseNumber result = calc.eval(new Sqrt(args));

    assertTrue(result instanceof RealNumber);
    assertEquals(3.0, ((RealNumber) result).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testAbsInteger() throws IllegalConstruction {
    List<Expression> args = List.of(new IntegerNumber(-7));
    assertEquals(new IntegerNumber(7), calc.eval(new Abs(args)));
  }

  @Test
  void testPowerIntegerBinary() throws IllegalConstruction {
    List<Expression> args = Arrays.asList(new IntegerNumber(2), new IntegerNumber(5));
    assertEquals(new IntegerNumber(32), calc.eval(new Power(args)));
    assertEquals("2 ** 5", calc.prettyFormat(new Power(args)));
  }

  @Test
  void testLnSpecialCases() throws IllegalConstruction {
    BaseNumber lnZero = calc.eval(new Ln(List.of(new IntegerNumber(0))));
    BaseNumber lnNegative = calc.eval(new Ln(List.of(new IntegerNumber(-1))));

    assertEquals(new RealNumber(SpecialNumber.NegativeInfinity), lnZero);
    assertEquals(new RealNumber(SpecialNumber.NaN), lnNegative);
  }

  @Test
  void testLogAndExponentialOnReal() throws IllegalConstruction {
    BaseNumber logResult = calc.eval(new Log(List.of(new RealNumber(100), new RealNumber(10))));

    assertTrue(logResult instanceof RealNumber);

    assertEquals(2.0, ((RealNumber) logResult).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testLogSupportsCustomBaseOrder() throws IllegalConstruction {
    BaseNumber logResult = calc.eval(new Log(List.of(new IntegerNumber(1), new IntegerNumber(2))));

    assertTrue(logResult instanceof RealNumber);
    assertEquals(0.0, ((RealNumber) logResult).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testSinCosTanOnInteger() throws IllegalConstruction {
    BaseNumber sinResult = calc.eval(new Sin(List.of(new IntegerNumber(1))));
    BaseNumber cosResult = calc.eval(new Cos(List.of(new IntegerNumber(1))));
    BaseNumber tanResult = calc.eval(new Tan(List.of(new IntegerNumber(1))));
    BaseNumber asinResult = calc.eval(new Asin(List.of(new IntegerNumber(1))));
    BaseNumber acosResult = calc.eval(new Acos(List.of(new IntegerNumber(1))));
    BaseNumber atanResult = calc.eval(new Atan(List.of(new IntegerNumber(1))));

    assertTrue(sinResult instanceof RealNumber);
    assertTrue(cosResult instanceof RealNumber);
    assertTrue(tanResult instanceof RealNumber);
    assertTrue(asinResult instanceof RealNumber);
    assertTrue(acosResult instanceof RealNumber);
    assertTrue(atanResult instanceof RealNumber);

    assertEquals(Math.sin(1), ((RealNumber) sinResult).getValue().doubleValue(), 1e-9);
    assertEquals(Math.cos(1), ((RealNumber) cosResult).getValue().doubleValue(), 1e-9);
    assertEquals(Math.tan(1), ((RealNumber) tanResult).getValue().doubleValue(), 1e-9);
    assertEquals(Math.asin(1), ((RealNumber) asinResult).getValue().doubleValue(), 1e-9);
    assertEquals(Math.acos(1), ((RealNumber) acosResult).getValue().doubleValue(), 1e-9);
    assertEquals(Math.atan(1), ((RealNumber) atanResult).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testSinAndCosOnComplexUseHyperbolicFormulas() throws IllegalConstruction {
    ComplexNumber input = new ComplexNumber(1.2, -0.7);
    ComplexNumber sinResult = (ComplexNumber) calc.eval(new Sin(List.of(input)));
    ComplexNumber cosResult = (ComplexNumber) calc.eval(new Cos(List.of(input)));

    double sinExpectedReal = Math.sin(1.2) * Math.cosh(-0.7);
    double sinExpectedImaginary = Math.cos(1.2) * Math.sinh(-0.7);
    double cosExpectedReal = Math.cos(1.2) * Math.cosh(-0.7);
    double cosExpectedImaginary = -Math.sin(1.2) * Math.sinh(-0.7);

    assertEquals(sinExpectedReal, sinResult.getReal().doubleValue(), 1e-6);
    assertEquals(sinExpectedImaginary, sinResult.getImaginary().doubleValue(), 1e-6);
    assertEquals(cosExpectedReal, cosResult.getReal().doubleValue(), 1e-6);
    assertEquals(cosExpectedImaginary, cosResult.getImaginary().doubleValue(), 1e-6);
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

    // Principal-branch identity: asin(z) + acos(z) = pi/2
    assertEquals(Math.PI / 2.0, asin.getReal().doubleValue() + acos.getReal().doubleValue(), 1e-6);
    assertEquals(0.0, asin.getImaginary().doubleValue() + acos.getImaginary().doubleValue(), 1e-6);
  }

  @Test
  void testFunctionNotation() throws IllegalConstruction {
    Expression e = new Sin(List.of(new IntegerNumber(8)));

    assertEquals("sin(8)", calc.format(e, Notation.PREFIX));
    assertEquals("sin(8)", calc.format(e, Notation.INFIX));
    assertEquals("(8) sin", calc.format(e, Notation.POSTFIX));

    Expression asin = new Asin(List.of(new IntegerNumber(8)));
    assertEquals("asin(8)", calc.format(asin, Notation.PREFIX));
    assertEquals("asin(8)", calc.format(asin, Notation.INFIX));
    assertEquals("(8) asin", calc.format(asin, Notation.POSTFIX));
  }

  @Test
  void testInverseTrigSpecialCases() throws IllegalConstruction {
    BaseNumber atanPositiveInfinity = calc.eval(new Atan(List.of(new RealNumber(SpecialNumber.PositiveInfinity))));
    BaseNumber atanNegativeInfinity = calc.eval(new Atan(List.of(new RealNumber(SpecialNumber.NegativeInfinity))));
    BaseNumber asinInfinity = calc.eval(new Asin(List.of(new RealNumber(SpecialNumber.PositiveInfinity))));
    BaseNumber acosInfinity = calc.eval(new Acos(List.of(new RealNumber(SpecialNumber.PositiveInfinity))));

    assertEquals(Math.PI / 2.0, ((RealNumber) atanPositiveInfinity).getValue().doubleValue(), 1e-9);
    assertEquals(-Math.PI / 2.0, ((RealNumber) atanNegativeInfinity).getValue().doubleValue(), 1e-9);
    assertEquals(SpecialNumber.NaN, ((RealNumber) asinInfinity).getSpecialValue());
    assertEquals(SpecialNumber.NaN, ((RealNumber) acosInfinity).getSpecialValue());
  }

  @Test
  void testFunctionCountedAsOperation() throws IllegalConstruction {
    Expression nested = new Plus(Arrays.asList(
        new Sin(List.of(new IntegerNumber(3))),
        new IntegerNumber(2)));

    CountingVisitor visitor = new CountingVisitor();
    nested.accept(visitor);

    assertEquals(2, visitor.getOpsCount());
  }

  @Test
  void testEmptyFunctionReturnsNeutral() throws IllegalConstruction {
    BaseNumber result = calc.eval(new Sin(new ArrayList<>()));
    assertEquals(new IntegerNumber(0), result);
  }
}
