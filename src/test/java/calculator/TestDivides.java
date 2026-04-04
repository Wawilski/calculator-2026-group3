package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import calculator.numbers.*;
import visitor.Evaluator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestDivides {

  private final int value1 = 8;
  private final int value2 = 6;
  private Divides op;
  private List<Expression> params;
  private List<Expression> params1;

  @BeforeEach
  void setUp() {
    params = Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2));
    try {
      op = new Divides(params);
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testConstructor1() {
    // It should not be possible to create an expression without null parameter list
    assertThrows(IllegalConstruction.class, () -> op = new Divides(null));
  }

  @SuppressWarnings("AssertBetweenInconvertibleTypes")
  @Test
  void testConstructor2() {
    // A Times expression should not be the same as a Divides expression
    try {
      assertNotSame(op, new Times(new ArrayList<>()));
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testEquals() {
    // Two similar expressions, constructed separately (and using different
    // constructors) should be equal
    List<Expression> p = Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2));
    try {
      Divides d = new Divides(p);
      assertEquals(op, d);
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  void testNull() {
    assertDoesNotThrow(() -> op == null); // Direct way to to test if the null case is handled.
  }

  @Test
  void testHashCode() {
    // Two similar expressions, constructed separately (and using different
    // constructors) should have the same hashcode
    List<Expression> p = Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2));
    try {
      Divides e = new Divides(p);
      assertEquals(e.hashCode(), op.hashCode());
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testNullParamList() {
    params = null;
    assertThrows(IllegalConstruction.class, () -> op = new Divides(params));
  }

  @Test
  void TestDivisionByZeroInteger() {
    int value3 = 0;
    params = Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value3));

    try {

      op = new Divides(params);
      Evaluator e = new Evaluator();
      ArithmeticException exception = assertThrows(ArithmeticException.class, () -> op.accept(e));
      assertEquals("Division by zero is not allowed.", exception.getMessage());

    } catch (IllegalConstruction e) {
      fail();
    }

  }

  @Test
  // A positive real divided by zero should be equal to +INFINITY
  void TestDivisionByZeroPositiveReal() {
    BigDecimal value3 = new BigDecimal(0);
    BigDecimal value4 = new BigDecimal(42);
    params = Arrays.asList(new RealNumber(value4), new RealNumber(value3));
    try {

      op = new Divides(params);
      Evaluator e = new Evaluator();
      op.accept(e);
      RealNumber result = (RealNumber) e.getResult();
      assertEquals(result.getSpecialValue(), SpecialNumber.PositiveInfinity);

    } catch (IllegalConstruction e) {
      fail();
    }
  }

  @Test
  // A negative real divided by zero should be equal to -INFINITY
  void TestDivisionByZeroNegativeReal() {
    BigDecimal value3 = new BigDecimal(0);
    BigDecimal value4 = new BigDecimal(-42);
    params = Arrays.asList(new RealNumber(value4), new RealNumber(value3));
    try {

      op = new Divides(params);
      Evaluator e = new Evaluator();
      op.accept(e);
      RealNumber result = (RealNumber) e.getResult();
      assertEquals(result.getSpecialValue(), SpecialNumber.NegativeInfinity);

    } catch (IllegalConstruction e) {
      fail();
    }
  }

  @Test
  // 0 divided by 0 should return NaN
  void TestDivisionZeroByZeroReal() {
    BigDecimal value3 = new BigDecimal(0);
    BigDecimal value4 = new BigDecimal(0);
    params = Arrays.asList(new RealNumber(value4), new RealNumber(value3));
    try {

      op = new Divides(params);
      Evaluator e = new Evaluator();
      op.accept(e);
      RealNumber result = (RealNumber) e.getResult();
      assertEquals(result.getSpecialValue(), SpecialNumber.NaN);

    } catch (IllegalConstruction e) {
      fail();
    }
  }

  @Test
  void TestDivisionByInfinityReal() {
    RealNumber plusInf = new RealNumber(SpecialNumber.PositiveInfinity);

    RealNumber real = new RealNumber("1");
    RealNumber zero = new RealNumber("0");

    params1 = Arrays.asList(real, plusInf);

    try {

      op = new Divides(params1);
      Evaluator e = new Evaluator();
      op.accept(e);
      RealNumber result = (RealNumber) e.getResult();
      assertEquals(zero, result);

    } catch (IllegalConstruction e) {
      fail();
    }
  }

  @Test
  void TestDivisionInfinityByReal() {
    RealNumber plusInf = new RealNumber(SpecialNumber.PositiveInfinity);
    RealNumber minusInf = new RealNumber(SpecialNumber.NegativeInfinity);

    RealNumber plusReal = new RealNumber("1");
    RealNumber minusReal = new RealNumber("-1");

    List<Expression> params1 = Arrays.asList(plusInf, plusReal);
    List<Expression> params2 = Arrays.asList(minusInf, plusReal);
    List<Expression> params3 = Arrays.asList(plusInf, minusReal);
    List<Expression> params4 = Arrays.asList(minusInf, minusReal);
    try {
      op = new Divides(params1);
      Evaluator e = new Evaluator();
      op.accept(e);
      RealNumber result = (RealNumber) e.getResult();
      assertEquals(new RealNumber(SpecialNumber.PositiveInfinity), result);

      op = new Divides(params2);
      op.accept(e);
      result = (RealNumber) e.getResult();
      assertEquals(new RealNumber(SpecialNumber.NegativeInfinity), result);

      op = new Divides(params3);
      op.accept(e);
      result = (RealNumber) e.getResult();
      assertEquals(new RealNumber(SpecialNumber.NegativeInfinity), result);

      op = new Divides(params4);
      op.accept(e);
      result = (RealNumber) e.getResult();
      assertEquals(new RealNumber(SpecialNumber.PositiveInfinity), result);

    } catch (IllegalConstruction e) {
      fail();
    }
  }

  @Test
  void TestDivisionInfByInfReal() {

    RealNumber plusInf = new RealNumber(SpecialNumber.PositiveInfinity);
    RealNumber minusInf = new RealNumber(SpecialNumber.NegativeInfinity);

    params1 = Arrays.asList(plusInf, minusInf);
    try {

      op = new Divides(params1);
      Evaluator e = new Evaluator();
      op.accept(e);
      RealNumber result = (RealNumber) e.getResult();
      assertEquals(result.getSpecialValue(), SpecialNumber.NaN);

    } catch (IllegalConstruction e) {
      fail();
    }
  }

  @Test
  void testRationalDivision() {
    RationalNumber left = new RationalNumber(1, 2);
    RationalNumber right = new RationalNumber(3, 2);

    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(left, right));
    try {
      Divides t = new Divides(p);
      Evaluator v = new Evaluator();
      t.accept(v);
      RationalNumber result = (RationalNumber) v.getResult();

      assertEquals(result, new RationalNumber(1, 3));
    } catch (IllegalConstruction _) {
      fail();
    }

  }

  @Test
  void testRationalDivisionByZero() {
    RationalNumber left = new RationalNumber(1, 2);
    RationalNumber right = new RationalNumber(0, 1);

    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(left, right));
    try {

      op = new Divides(p);
      Evaluator e = new Evaluator();
      ArithmeticException exception = assertThrows(ArithmeticException.class, () -> op.accept(e));
      assertEquals("Division by zero is not allowed.", exception.getMessage());

    } catch (IllegalConstruction e) {
      fail();
    }

  }

  @Test
  void testComplexDivision() {
    ComplexNumber left = new ComplexNumber("1", "6.00");
    ComplexNumber right = new ComplexNumber("8.00", "4");

    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(left, right));
    try {
      Divides t = new Divides(p);
      Evaluator v = new Evaluator();
      t.accept(v);
      ComplexNumber result = (ComplexNumber) v.getResult();

      ComplexNumber test = new ComplexNumber("0.05555556", "0.07638889");
      // To compare solution and result we need to scale the result to the solution
      BigDecimal real = result.getReal().setScale(8, RoundingMode.CEILING);
      BigDecimal im = result.getImaginary().setScale(8, RoundingMode.CEILING);
      assertEquals(test, new ComplexNumber(real, im));
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testComplexNaNDivision() {
    ComplexNumber left = new ComplexNumber("1", "6");
    ComplexNumber right = new ComplexNumber();

    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(left, right));
    try {
      Divides t = new Divides(p);
      Evaluator v = new Evaluator();
      t.accept(v);
      ComplexNumber result = (ComplexNumber) v.getResult();

      assertEquals(result, new ComplexNumber());
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testComplexDivisionByZero() {
    ComplexNumber left = new ComplexNumber(1, 6);
    ComplexNumber right = new ComplexNumber(0, 0);

    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(left, right));
    try {
      Divides t = new Divides(p);
      Evaluator v = new Evaluator();
      t.accept(v);
      ComplexNumber result = (ComplexNumber) v.getResult();

      assertEquals(result, new ComplexNumber());
    } catch (IllegalConstruction _) {
      fail();
    }
  }

}
