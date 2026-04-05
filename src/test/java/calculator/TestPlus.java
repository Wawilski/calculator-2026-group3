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

class TestPlus {

  private final int value1 = 8;
  private final int value2 = 6;
  private Plus op;
  private List<Expression> params;

  @BeforeEach
  void setUp() {
    params = new ArrayList<>(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2)));
    try {
      op = new Plus(params);
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testConstructor1() {
    // It should not be possible to create a Plus expression without null parameter
    // list
    assertThrows(IllegalConstruction.class, () -> op = new Plus(null));
  }

  @SuppressWarnings("AssertBetweenInconvertibleTypes")
  @Test
  void testConstructor2() {
    // A Times expression should not be the same as a Plus expression
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
    ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2)));
    try {
      Plus e = new Plus(p);
      assertEquals(op, e);
      assertEquals(e, e);
      assertNotEquals(e, new Plus(new ArrayList<>(Arrays.asList(new IntegerNumber(5), new IntegerNumber(4)))));
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
    ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2)));
    try {
      Plus e = new Plus(p);
      assertEquals(e.hashCode(), op.hashCode());
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testNullParamList() {
    params = null;
    assertThrows(IllegalConstruction.class, () -> op = new Plus(params));
  }

  @Test
  // A number added to INFINITY shou:d return INFINITY
  void testInfinityPlus() {
    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(new RealNumber(new BigDecimal(value1)), new RealNumber(SpecialNumber.PositiveInfinity)));
    try {
      Plus e = new Plus(p);
      Evaluator v = new Evaluator();
      e.accept(v);
      RealNumber result = (RealNumber) v.getResult();
      assertEquals(SpecialNumber.PositiveInfinity, result.getSpecialValue());
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testNaNPlus() {

    List<Expression> params = Arrays.asList(new RealNumber(SpecialNumber.NaN), new RealNumber(1));
    try {
      Plus e = new Plus(params);
      Evaluator v = new Evaluator();
      e.accept(v);
      RealNumber result = (RealNumber) v.getResult();
      assertEquals(new RealNumber(SpecialNumber.NaN), result);
    } catch (IllegalConstruction _) {
      fail();
    }

  }

  @Test
  // Add 2 Infinity of opposite signs should return a NaN
  void testOppositeInfinityPlus() {
    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(new RealNumber(SpecialNumber.NegativeInfinity),
            new RealNumber(SpecialNumber.PositiveInfinity)));
    try {
      Plus e = new Plus(p);
      Evaluator v = new Evaluator();
      e.accept(v);
      RealNumber result = (RealNumber) v.getResult();
      assertEquals(SpecialNumber.NaN, result.getSpecialValue());
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testRationalAddition() {
    RationalNumber left = new RationalNumber(1, 2);
    RationalNumber right = new RationalNumber(3, 2);

    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(left, right));
    try {
      Plus t = new Plus(p);
      Evaluator v = new Evaluator();
      t.accept(v);
      RationalNumber result = (RationalNumber) v.getResult();

      assertEquals(result, new RationalNumber(2, 1));
    } catch (IllegalConstruction _) {
      fail();
    }

  }

  @Test
  void testComplexAddition() {
    ComplexNumber left = new ComplexNumber("1.1", "6.8");
    ComplexNumber right = new ComplexNumber("8.9", "4");

    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(left, right));
    try {
      Plus t = new Plus(p);
      Evaluator v = new Evaluator();
      t.accept(v);
      ComplexNumber result = (ComplexNumber) v.getResult();

      assertEquals(result, new ComplexNumber("10", "10.8"));
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testComplexNaNAddition() {
    ComplexNumber left = new ComplexNumber("1", "6");
    ComplexNumber right = new ComplexNumber();

    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(left, right));
    try {
      Plus t = new Plus(p);
      Evaluator v = new Evaluator();
      t.accept(v);
      ComplexNumber result = (ComplexNumber) v.getResult();

      assertEquals(result, new ComplexNumber());
    } catch (IllegalConstruction _) {
      fail();
    }
  }
}
