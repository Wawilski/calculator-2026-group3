package calculator;

import calculator.numbers.*;
//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

class TestEvaluator {

  private Calculator calc;
  private int value1, value2;
  private BigDecimal value3, value4;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
    value1 = 8;
    value2 = 6;
    value3 = new BigDecimal("4.5");
    value4 = new BigDecimal("1.2");

  }

  @Test
  void testEvaluatorIntegerNumber() {
    assertEquals(new IntegerNumber(value1), calc.eval(new IntegerNumber(value1)));
  }

  @ParameterizedTest
  @ValueSource(strings = { "*", "+", "/", "-" })
  void testEvaluateIntegerOperations(String symbol) {
    List<Expression> params = Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2));
    try {
      // construct another type of operation depending on the input value
      // of the parameterised test
      switch (symbol) {
        case "+" -> assertEquals(new IntegerNumber(value1 + value2), calc.eval(new Plus(params)));
        case "-" -> assertEquals(new IntegerNumber(value1 - value2), calc.eval(new Minus(params)));
        case "*" -> assertEquals(new IntegerNumber(value1 * value2), calc.eval(new Times(params)));
        case "/" -> assertEquals(new RationalNumber(value1, value2), calc.eval(new Divides(params)));
        default -> fail();
      }
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @ParameterizedTest
  @ValueSource(strings = { "*", "+", "/", "-" })
  void testEvaluateRealOperations(String symbol) {
    List<Expression> params = Arrays.asList(new RealNumber(value3), new RealNumber(value4));
    try {
      // construct another type of operation depending on the input value
      // of the parameterised test
      switch (symbol) {
        case "+" ->
          assertEquals(new RealNumber(value3.add(value4, MathContext.UNLIMITED)), calc.eval(new Plus(params)));
        case "-" ->
          assertEquals(new RealNumber(value3.subtract(value4, MathContext.UNLIMITED)), calc.eval(new Minus(params)));
        case "*" ->
          assertEquals(new RealNumber(value3.multiply(value4, MathContext.UNLIMITED)), calc.eval(new Times(params)));
        case "/" ->
          assertEquals(new RealNumber(value3.divide(value4, RealNumber.getScale(), RoundingMode.CEILING)),
              calc.eval(new Divides(params)));
        default -> fail();
      }
    } catch (IllegalConstruction _) {
      fail();

    }
  }

  @ParameterizedTest
  @ValueSource(strings = { "*", "+", "/", "-" })
  void testEvaluateRationalOperations(String symbol) {
    int numLeft = 1;
    int denoLeft = 2;
    int numRight = 3;
    int denoRight = 4;
    List<Expression> params = Arrays.asList(new RationalNumber(numLeft, denoLeft),
        new RationalNumber(numRight, denoRight));
    try {
      // construct another type of operation depending on the input value
      // of the parameterised test
      switch (symbol) {
        case "+" ->
          assertEquals(new RationalNumber(numLeft * denoRight + numRight * denoLeft, denoLeft * denoRight),
              calc.eval(new Plus(params)));
        case "-" ->
          assertEquals(new RationalNumber(numLeft * denoRight - numRight * denoLeft, denoLeft * denoRight),
              calc.eval(new Minus(params)));
        case "*" ->
          assertEquals(new RationalNumber(numLeft * numRight, denoLeft * denoRight), calc.eval(new Times(params)));
        case "/" ->
          assertEquals(new RationalNumber(numLeft * denoRight, denoLeft * numRight), calc.eval(new Divides(params)));
        default -> fail();
      }
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @ParameterizedTest
  @ValueSource(strings = { "*", "+", "/", "-" })
  void testEvaluateComplexOperations(String symbol) {
    BigDecimal realLeft = new BigDecimal("1");
    BigDecimal imLeft = new BigDecimal("6");
    BigDecimal realRight = new BigDecimal("8");
    BigDecimal imRight = new BigDecimal("4");
    List<Expression> params = Arrays.asList(new ComplexNumber(realLeft, imLeft),
        new ComplexNumber(realRight, imRight));

    BigDecimal realPartTimes = realLeft.multiply(realRight).subtract(imLeft.multiply(imRight));
    BigDecimal imPartTimes = realLeft.multiply(imRight).add(realRight.multiply(imLeft));

    ComplexNumber times = new ComplexNumber(realPartTimes, imPartTimes);

    try {
      // construct another type of operation depending on the input value
      // of the parameterised test

      switch (symbol) {
        case "+" ->
          assertEquals(new ComplexNumber(realLeft.add(realRight), imLeft.add(imRight)),
              calc.eval(new Plus(params)));
        case "-" ->
          assertEquals(new ComplexNumber(realLeft.subtract(realRight), imLeft.subtract(imRight)),
              calc.eval(new Minus(params)));
        case "*" ->
          assertEquals(times, calc.eval(new Times(params)));
        case "/" ->
          assertEquals(new ComplexNumber("0.4", "0.55"), calc.eval(new Divides(params)));
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
