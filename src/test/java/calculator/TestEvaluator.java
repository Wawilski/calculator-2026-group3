package calculator;

import calculator.numbers.*;
//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class TestEvaluator {

  private Calculator calc;
  private int value1, value2;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
    value1 = 8;
    value2 = 6;
  }

  @Test
  void testEvaluatorIntegerNumber() {
    assertEquals(new IntegerNumber(value1), calc.eval(new IntegerNumber(value1)));
  }

  @ParameterizedTest
  @ValueSource(strings = { "*", "+", "/", "-" })
  void testEvaluateOperations(String symbol) {
    List<Expression> params = Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2));
    try {
      // construct another type of operation depending on the input value
      // of the parameterised test
      switch (symbol) {
        case "+" -> assertEquals(new IntegerNumber(value1 + value2), calc.eval(new Plus(params)));
        case "-" -> assertEquals(new IntegerNumber(value1 - value2), calc.eval(new Minus(params)));
        case "*" -> assertEquals(new IntegerNumber(value1 * value2), calc.eval(new Times(params)));
        case "/" -> assertEquals(new IntegerNumber(value1 / value2), calc.eval(new Divides(params)));
        default -> fail();
      }
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @ParameterizedTest
  @ValueSource(strings = { "*", "+", "/", "-" })
  void testEvaluateEmptyOperations(String symbol) throws IllegalConstruction {
    Operation op = switch (symbol) {
      case "+" -> new Plus(new ArrayList<>());
      case "-" -> new Minus(new ArrayList<>());
      case "*" -> new Times(new ArrayList<>());
      case "/" -> new Divides(new ArrayList<>());
      default -> throw new IllegalStateException("Unexpected symbol: " + symbol);
    };
    int expected = (symbol.equals("*") || symbol.equals("/")) ? 1 : 0;
    assertEquals(new IntegerNumber(expected), calc.eval(op));
  }

}
