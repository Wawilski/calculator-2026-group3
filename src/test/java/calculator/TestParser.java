package calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.jupiter.api.*;

import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;

/**
 * TestParser
 */
public class TestParser {

  private final ExpressionParser parser = new ExpressionParser();
  private final Calculator c = new Calculator();
  private Expression e;
  private final double epsilon = 1E-12;

  @Test
  void testSpacePrefixWrappedOp() {
    e = parser.parse("+ ( 1 2 3 4 5 )");
    assertEquals(new IntegerNumber(15), c.eval(e));

    e = parser.parse("+ ( 1.0 2.0 3.5 4.5 5)");
    assertEquals(new RealNumber(new BigDecimal(16)), c.eval(e));

    e = parser.parse("+ ( i 2.0 3.5 4.5 5)");
    assertEquals(new ComplexNumber(new BigDecimal(15), new BigDecimal(1)),
        c.eval(e));

    e = parser.parse("+ ( i 2.0 * 3 4.5 5)");
    assertEquals(new ComplexNumber(new BigDecimal(20.5), new BigDecimal(1)),
        c.eval(e));

  }

  @Test
  void testSpacePrefixSimpleOp() {
    e = parser.parse("* 4 2");
    assertEquals(new IntegerNumber(8), c.eval(e));

    e = parser.parse("+ 1 * ( 3 2 i )");
    assertEquals(new ComplexNumber(new BigDecimal(1), new BigDecimal(6)),
        c.eval(e));

    e = parser.parse("+ 1 *( (-2) 3 i)");
    assertEquals(new ComplexNumber(new BigDecimal(1), new BigDecimal(-6)), c.eval(e));
  }

  @Test
  void testSpacePostfixWrappedOp() {
    e = parser.parse("( 1 2 3 4 5 ) +");
    assertEquals(new IntegerNumber(15), c.eval(e));

    e = parser.parse("( 1.0 2.0 3.5 4.5 5) +");
    assertEquals(new RealNumber(new BigDecimal(16)), c.eval(e));

    e = parser.parse("( i 2.0 3.5 4.5 5) +");
    assertEquals(new ComplexNumber(new BigDecimal(15), new BigDecimal(1)),
        c.eval(e));

    e = parser.parse("( i 2.0 3 4.5 * 5) +");
    assertEquals(new ComplexNumber(new BigDecimal(20.5), new BigDecimal(1)),
        c.eval(e));

  }

  @Test
  void testSpacePostfixSimpleOp() {
    e = parser.parse("4 2 *");
    assertEquals(new IntegerNumber(8), c.eval(e));

    e = parser.parse(" 1 ( 3 2 i ) * +");
    assertEquals(new ComplexNumber(new BigDecimal(1), new BigDecimal(6)),
        c.eval(e));

    e = parser.parse("1 ( (-2) 3 i) * +");
    assertEquals(new ComplexNumber(new BigDecimal(1), new BigDecimal(-6)), c.eval(e));
  }

  @Test
  void testParenPrefixWrappedOp() {
    e = parser.parse("+ ( 1, 2, 3, 4, 5 )");
    assertEquals(new IntegerNumber(15), c.eval(e));

    e = parser.parse("+ ( 1.0, 2.0, 3.5 ,4.5, 5) ");
    assertEquals(new RealNumber(new BigDecimal(16)), c.eval(e));

    e = parser.parse(" + ( i, 2.0, 3.5, 4.5, 5) ");
    assertEquals(new ComplexNumber(new BigDecimal(15), new BigDecimal(1)),
        c.eval(e));

    e = parser.parse("+( i, 2.0, *( 3, 4.5), 5)");
    assertEquals(new ComplexNumber(new BigDecimal(20.5), new BigDecimal(1)),
        c.eval(e));
  }

  @Test
  void testParenPostfixWrappedOp() {
    e = parser.parse(" ( 1, 2, 3, 4, 5 ) +");
    assertEquals(new IntegerNumber(15), c.eval(e));

    e = parser.parse(" ( 1.0, 2.0, 3.5 ,4.5, 5) + ");
    assertEquals(new RealNumber(new BigDecimal(16)), c.eval(e));

    e = parser.parse("( i, 2.0, 3.5, 4.5, 5) +");
    assertEquals(new ComplexNumber(new BigDecimal(15), new BigDecimal(1)),
        c.eval(e));

    e = parser.parse("( i, 2.0, ( 3, 4.5)*, 5) +");
    assertEquals(new ComplexNumber(new BigDecimal(20.5), new BigDecimal(1)),
        c.eval(e));
  }

  @Test
  void testParenPrefixSimpleOp() {
    e = parser.parse("* (4, 2)");
    assertEquals(new IntegerNumber(8), c.eval(e));

    e = parser.parse("+ (1, * ( 3, 2, i ))");
    assertEquals(new ComplexNumber(new BigDecimal(1), new BigDecimal(6)),
        c.eval(e));

    e = parser.parse("+ (1 ,*( (-2) ,3 ,i))");
    assertEquals(new ComplexNumber(new BigDecimal(1), new BigDecimal(-6)), c.eval(e));
  }

  @Test
  void testParenPostfixSimpleOp() {
    e = parser.parse("(4, 2) *");
    assertEquals(new IntegerNumber(8), c.eval(e));

    e = parser.parse("(4, 2)");
    assertEquals(new IntegerNumber(8), c.eval(e));

    e = parser.parse(" (1, ( 3, 2, i ) *) +");
    assertEquals(new ComplexNumber(new BigDecimal(1), new BigDecimal(6)),
        c.eval(e));

    e = parser.parse("(1, ( (-2), 3 ,i) *) +");
    assertEquals(new ComplexNumber(new BigDecimal(1), new BigDecimal(-6)), c.eval(e));

  }

  @Test
  void testInfix() {
    e = parser.parse("1+2(3+5)i+ 9 * 5 - 4 + (-5 + 2)(2)");
    assertEquals(new ComplexNumber(new BigDecimal(36), new BigDecimal(16)), c.eval(e));

    e = parser.parse("5 ** 5");
    assertEquals(new IntegerNumber(3125), c.eval(e));

    // As functions are approximation we can't be precise so an epsilon is set
    e = parser.parse("(1+2i)**2");
    ComplexNumber sol = (ComplexNumber) c.eval(e);
    assertEquals(true, epsilon > Math.abs(-3 - sol.getReal().doubleValue()));
    assertEquals(true, epsilon > Math.abs(4 - sol.getImaginary().doubleValue()));

  }

  @Test
  void testFactor() {
    e = parser.parse("1");
    assertEquals(new IntegerNumber(1), c.eval(e));

    e = parser.parse("(1+1)");
    assertEquals(new IntegerNumber(2), c.eval(e));
    e = parser.parse("(1.0+1)");
    assertEquals(new RealNumber(2), c.eval(e));

    e = parser.parse("-1");
    assertEquals(new IntegerNumber(-1), c.eval(e));

    e = parser.parse("2E16");
    assertEquals(new RealNumber(new BigDecimal(2E16)), c.eval(e));

    e = parser.parse("-2");
    assertEquals(new IntegerNumber(-(2)), c.eval(e));

    e = parser.parse("1.0i");
    assertEquals(new ComplexNumber("0", "1"), c.eval(e));

    e = parser.parse("1/(2i)");
    assertEquals(new ComplexNumber("0", "-0.5"), c.eval(e));

    e = parser.parse("1/2i");
    assertEquals(new ComplexNumber("0", "0.5"), c.eval(e));

    e = parser.parse("1/2");
    assertEquals(new RationalNumber(1, 2), c.eval(e));

    e = parser.parse("2pii");

    String pi = Double.toString(Math.PI);
    BigDecimal twoPi = new BigDecimal("2").multiply(new BigDecimal(pi),
        MathContext.UNLIMITED);

    assertEquals(new ComplexNumber(new BigDecimal(0), twoPi),
        c.eval(e));

  }
}
