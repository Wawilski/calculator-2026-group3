package calculator;

import calculator.numbers.*;
import visitor.Evaluator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestMinus {

  private final int value1 = 8;
  private final int value2 = 6;
  private Minus op;
  private List<Expression> params;

  @BeforeEach
  void setUp() {
    params = Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2));
    try {
      op = new Minus(params);
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testConstructor1() {
    // It should not be possible to create an expression without null parameter list
    assertThrows(IllegalConstruction.class, () -> op = new Minus(null));
  }

  @SuppressWarnings("AssertBetweenInconvertibleTypes")
  @Test
  void testConstructor2() {
    // A Times expression should not be the same as a Minus expression
    try {
      assertNotSame(op, new Times(new ArrayList<>()));
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testEquals() {
    // Two similar expressions, constructed separately (and using different
    // constructors) should not be equal
    List<Expression> p = Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2));
    try {
      Minus e = new Minus(p);
      assertEquals(op, e);
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
      Minus e = new Minus(p);
      assertEquals(e.hashCode(), op.hashCode());
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testNullParamList() {
    params = null;
    assertThrows(IllegalConstruction.class, () -> op = new Minus(params));
  }

  @Test
  // INFINITY decreased by a real number shou:d return INFINITY
  void testInfinityMinusReal() {
    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(new RealNumber(SpecialNumber.PositiveInfinity), new RealNumber(new BigDecimal(value1))));
    try {
      Minus e = new Minus(p);
      Evaluator v = new Evaluator();
      e.accept(v);
      RealNumber result = (RealNumber) v.getResult();
      System.out.println(result.getSpecialValue());
      assertEquals(SpecialNumber.PositiveInfinity, result.getSpecialValue());
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  void testRealMinusInfinity() {
    // A real number decreased by an infinite value shou:d return the opposite of
    // the infinite value
    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(new RealNumber(new BigDecimal(value1)), new RealNumber(SpecialNumber.PositiveInfinity)));
    try {
      Minus e = new Minus(p);
      Evaluator v = new Evaluator();
      e.accept(v);
      RealNumber result = (RealNumber) v.getResult();
      assertEquals(SpecialNumber.NegativeInfinity, result.getSpecialValue());
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  // Subtract 2 Infinity of same signs should return a NaN
  void testSameSignInfinityMinus() {
    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(new RealNumber(SpecialNumber.PositiveInfinity),
            new RealNumber(SpecialNumber.PositiveInfinity)));
    try {
      Minus e = new Minus(p);
      Evaluator v = new Evaluator();
      e.accept(v);
      RealNumber result = (RealNumber) v.getResult();
      assertEquals(SpecialNumber.NaN, result.getSpecialValue());
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testRationalSubtraction() {
    RationalNumber left = new RationalNumber(1, 2);
    RationalNumber right = new RationalNumber(3, 2);

    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(left, right));
    try {
      Minus t = new Minus(p);
      Evaluator v = new Evaluator();
      t.accept(v);
      RationalNumber result = (RationalNumber) v.getResult();

      assertEquals(result, new RationalNumber(-1, 1));
    } catch (IllegalConstruction _) {
      fail();
    }

  }

  @Test
  void testComplexSubtraction() {
    ComplexNumber left = new ComplexNumber("1.1", "6.8");
    ComplexNumber right = new ComplexNumber("8.9", "4");

    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(left, right));
    try {
      Minus t = new Minus(p);
      Evaluator v = new Evaluator();
      t.accept(v);
      ComplexNumber result = (ComplexNumber) v.getResult();

      assertEquals(result, new ComplexNumber("-7.8", "2.8"));
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testComplexNaNSubtraction() {
    ComplexNumber left = new ComplexNumber(1, 6);
    ComplexNumber right = new ComplexNumber();

    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(left, right));
    try {
      Minus t = new Minus(p);
      Evaluator v = new Evaluator();
      t.accept(v);
      ComplexNumber result = (ComplexNumber) v.getResult();

      assertEquals(result, new ComplexNumber());
    } catch (IllegalConstruction _) {
      fail();
    }
  }
}
