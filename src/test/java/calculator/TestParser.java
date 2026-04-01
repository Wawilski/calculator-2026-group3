package calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.*;

import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RealNumber;

/**
 * TestParser
 */
public class TestParser {

  private final ExpressionParser parser = new ExpressionParser();
  private final Calculator c = new Calculator();
  private Expression e;

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

}
