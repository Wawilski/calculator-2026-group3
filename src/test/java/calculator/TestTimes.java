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

class TestTimes {

  private final int value1 = 8;
  private final int value2 = 6;
  private Times op;
  private List<Expression> params;

  @BeforeEach
  void setUp() {
    params = Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2));
    try {
      op = new Times(params);
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testConstructor1() {
    // It should not be possible to create an expression without null parameter list
    assertThrows(IllegalConstruction.class, () -> op = new Times(null));
  }

  @Test
  void testConstructor2() {
    // A Plus expression should not be the same as a Times expression
    try {
      assertNotSame(op, new Plus(new ArrayList<>()));
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
      Times e = new Times(p);
      assertEquals(op, e);
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testNull() {
    assertDoesNotThrow(() -> op == null); // Direct way to test if the null case is handled.
  }

  @Test
  void testHashCode() {
    // Two similar expressions, constructed separately (and using different
    // constructors) should have the same hashcode
    List<Expression> p = Arrays.asList(new IntegerNumber(value1), new IntegerNumber(value2));
    try {
      Times e = new Times(p);
      assertEquals(e.hashCode(), op.hashCode());
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testNullParamList() {
    params = null;
    assertThrows(IllegalConstruction.class, () -> op = new Times(params));
  }

  @Test
  // An infinite value times 0 should return a NaN
  void testInfinityTimesZero() {

    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(new RealNumber(new BigDecimal(0)), new RealNumber(SpecialNumber.PositiveInfinity)));
    try {
      Times e = new Times(p);
      Evaluator v = new Evaluator();
      e.accept(v);
      RealNumber result = (RealNumber) v.getResult();
      assertEquals(SpecialNumber.NaN, result.getSpecialValue());
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testRationalMultiplication() {
    RationalNumber left = new RationalNumber(1, 2);
    RationalNumber right = new RationalNumber(3, 2);

    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(left, right));
    try {
      Times t = new Times(p);
      Evaluator v = new Evaluator();
      t.accept(v);
      RationalNumber result = (RationalNumber) v.getResult();

      assertEquals(result, new RationalNumber(3, 4));
    } catch (IllegalConstruction _) {
      fail();
    }

  }

  @Test
  void testComplexMultiplication() {
    ComplexNumber left = new ComplexNumber("1", "6.00");
    ComplexNumber right = new ComplexNumber("8.00", "4");

    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(left, right));
    try {
      Times t = new Times(p);
      Evaluator v = new Evaluator();
      t.accept(v);
      ComplexNumber result = (ComplexNumber) v.getResult();

      assertEquals(result, new ComplexNumber("-16", "52"));
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testComplexNaNMultiplication() {
    ComplexNumber left = new ComplexNumber(1, 6);
    ComplexNumber right = new ComplexNumber();

    ArrayList<Expression> p = new ArrayList<>(
        Arrays.asList(left, right));
    try {
      Times t = new Times(p);
      Evaluator v = new Evaluator();
      t.accept(v);
      ComplexNumber result = (ComplexNumber) v.getResult();

      assertEquals(result, new ComplexNumber());
    } catch (IllegalConstruction _) {
      fail();
    }
  }

}
