package calculator;

import calculator.numbers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * This test class checks the pretty printing of expressions, including nested
 * expressions and operator precedence
 * 
 * @author Oussama Hakik
 */
class TestPrettyPrint {

  private final Calculator calc = new Calculator(); // instance of the Calculator class to be used for pretty printing
                                                    // expressions

  /**
   * Test the pretty printing of a simple expression with two numbers and an
   * operator
   */
  @Test
  void testPrettyPrintNestedExpression() throws IllegalConstruction {
    Expression left = new Plus(Arrays.asList(new IntegerNumber(3), new IntegerNumber(4)));
    Expression right = new Minus(Arrays.asList(new IntegerNumber(5), new IntegerNumber(2)));
    Expression expr = new Times(Arrays.asList(left, right));
    assertEquals("(3 + 4) * (5 - 2)", calc.prettyFormat(expr));
  }

  /**
   * Test the pretty printing of an expression with operator precedence, where
   * multiplication should be evaluated before addition
   */
  @Test
  void testPrettyPrintWithPrecedence() throws IllegalConstruction {
    Expression expr = new Plus(Arrays.asList(
        new IntegerNumber(3),
        new Times(Arrays.asList(new IntegerNumber(4), new IntegerNumber(2)))));
    assertEquals("3 + 4 * 2", calc.prettyFormat(expr));
  }

  /**
   * Test the pretty printing of an expression with multiple operators and nested
   * expressions, to check that parentheses are correctly placed
   */
  @Test
  void testPrettyPrintEmptyOperation() throws IllegalConstruction {
    Expression expr = new Plus(new ArrayList<>());
    assertEquals("+()", calc.prettyFormat(expr));
  }

  @Test
  void testPrettyPrintDivisionParenthesesOnRightOperand() throws IllegalConstruction {
    Expression expr = new Divides(List.of(
        new IntegerNumber(8),
        new Divides(List.of(new IntegerNumber(4), new IntegerNumber(2)))));
    assertEquals("8 / (4 / 2)", calc.prettyFormat(expr));
  }

  @Test
  void testPrettyPrintKeepsLeftAssociativeDivisionFlat() throws IllegalConstruction {
    Expression expr = new Divides(List.of(
        new Divides(List.of(new IntegerNumber(8), new IntegerNumber(4))),
        new IntegerNumber(2)));
    assertEquals("8 / 4 / 2", calc.prettyFormat(expr));
  }

  @Test
  void testPrettyPrintSubtractionParenthesesOnRightOperand() throws IllegalConstruction {
    Expression expr = new Minus(List.of(
        new IntegerNumber(10),
        new Minus(List.of(new IntegerNumber(4), new IntegerNumber(1)))));
    assertEquals("10 - (4 - 1)", calc.prettyFormat(expr));
  }
}
