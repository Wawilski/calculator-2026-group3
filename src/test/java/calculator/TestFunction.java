package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.numbers.BaseNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;
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
  }

  @Test
  void testReciprocalInteger() throws IllegalConstruction {
    List<Expression> args = List.of(new IntegerNumber(4));
    assertEquals(new RationalNumber(1, 4), calc.eval(new Reciprocal(args)));
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

    assertTrue(sinResult instanceof RealNumber);
    assertTrue(cosResult instanceof RealNumber);
    assertTrue(tanResult instanceof RealNumber);

    assertEquals(Math.sin(1), ((RealNumber) sinResult).getValue().doubleValue(), 1e-9);
    assertEquals(Math.cos(1), ((RealNumber) cosResult).getValue().doubleValue(), 1e-9);
    assertEquals(Math.tan(1), ((RealNumber) tanResult).getValue().doubleValue(), 1e-9);
  }

  @Test
  void testFunctionNotation() throws IllegalConstruction {
    Expression e = new Sin(List.of(new IntegerNumber(8)));

    assertEquals("sin(8)", calc.format(e, Notation.PREFIX));
    assertEquals("sin(8)", calc.format(e, Notation.INFIX));
    assertEquals("(8) sin", calc.format(e, Notation.POSTFIX));
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
