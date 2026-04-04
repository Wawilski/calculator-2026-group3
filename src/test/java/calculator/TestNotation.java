package calculator;

import calculator.numbers.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class TestNotation {

  private final Calculator calc = new Calculator();

  /*
   * This is an auxilary method to avoid code duplication.
   */
  void testNotation(String s, Expression e, Notation n) {
    assertEquals(s, calc.format(e, n));
  }

  /*
   * This is an auxilary method to avoid code duplication.
   */
  void testNotations(String symbol, int value1, int value2, Operation op) {
    // prefix notation:
    testNotation(symbol + " (" + value1 + ", " + value2 + ")", op, Notation.PREFIX);
    // infix notation:
    testNotation("( " + value1 + " " + symbol + " " + value2 + " )", op, Notation.INFIX);
    // postfix notation:
    testNotation("(" + value1 + ", " + value2 + ") " + symbol, op, Notation.POSTFIX);
    assertEquals("( " + value1 + " " + symbol + " " + value2 + " )", op.toString());
  }

  @ParameterizedTest
  @ValueSource(strings = { "*", "+", "/", "-" })
  void testOutput(String symbol) {
    int value1 = 8;
    int value2 = 6;
    Operation op = null;
    List<Expression> params = Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2));
    try {
      // construct another type of operation depending on the input value of the
      // parameterised test
      switch (symbol) {
        case "+" -> op = new Plus(params);
        case "-" -> op = new Minus(params);
        case "*" -> op = new Times(params);
        case "/" -> op = new Divides(params);
        default -> fail();
      }
    } catch (IllegalConstruction _) {
      fail();
    }
    testNotations(symbol, value1, value2, op);
  }

  @ParameterizedTest
  @ValueSource(strings = { "*", "+", "/", "-" })
  void testOutputForEmptyOperation(String symbol) throws IllegalConstruction {
    Operation op = switch (symbol) {
      case "+" -> new Plus(new ArrayList<>());
      case "-" -> new Minus(new ArrayList<>());
      case "*" -> new Times(new ArrayList<>());
      case "/" -> new Divides(new ArrayList<>());
      default -> throw new IllegalStateException("Unexpected symbol: " + symbol);
    };
    assertEquals("( )", calc.format(op, Notation.INFIX));
    assertEquals(symbol + " ()", calc.format(op, Notation.PREFIX));
    assertEquals("() " + symbol, calc.format(op, Notation.POSTFIX));
  }

}
