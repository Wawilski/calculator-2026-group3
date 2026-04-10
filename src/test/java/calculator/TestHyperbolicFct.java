
package calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.functions.Cosh;
import calculator.functions.Sinh;
import calculator.functions.Tanh;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;
import visitor.Evaluator;

/**
 * TestHyperbolicFct
 */
public class TestHyperbolicFct {

  @AfterEach
  void setUp() {
    RealNumber.setScale(16);
    ExpressionParser.isAngleUnitDegree = false;
  }

  @Test
  void testCosh() {
    IntegerNumber value = new IntegerNumber(0);
    List<Expression> p = Arrays.asList(value);
    try {
      Cosh e = new Cosh(p);
      Evaluator v = new Evaluator();
      e.accept(v);
      RealNumber result = (RealNumber) v.getResult();
      assertEquals(new RealNumber("1"), result);

      p = Arrays.asList(new RationalNumber(0, 1));
      e = new Cosh(p);
      e.accept(v);
      result = (RealNumber) v.getResult();
      assertEquals(new RealNumber("1"), result);

      RealNumber.setScale(10);
      p = Arrays.asList(new RealNumber(Math.PI));
      e = new Cosh(p);
      e.accept(v);
      result = (RealNumber) v.getResult();
      assertEquals(new RealNumber("11.5919532757"), result);

      p = Arrays.asList(new ComplexNumber(Math.PI, 0));
      e = new Cosh(p);
      e.accept(v);

      ComplexNumber cResult = (ComplexNumber) v.getResult();
      assertEquals(new ComplexNumber("11.5919532757", "0"), cResult);
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testSinh() {
    IntegerNumber value = new IntegerNumber(0);
    List<Expression> p = Arrays.asList(value);
    try {
      Sinh e = new Sinh(p);
      Evaluator v = new Evaluator();
      e.accept(v);
      RealNumber result = (RealNumber) v.getResult();
      assertEquals(new RealNumber("0"), result);

      p = Arrays.asList(new RationalNumber(0, 1));
      e = new Sinh(p);
      e.accept(v);
      result = (RealNumber) v.getResult();
      assertEquals(new RealNumber("0"), result);

      RealNumber.setScale(10);
      p = Arrays.asList(new RealNumber(Math.PI));
      e = new Sinh(p);
      e.accept(v);
      result = (RealNumber) v.getResult();
      assertEquals(new RealNumber("11.5487393574"), result);

      p = Arrays.asList(new ComplexNumber(Math.PI, 0));
      e = new Sinh(p);
      e.accept(v);
      ComplexNumber cResult = (ComplexNumber) v.getResult();
      assertEquals(new ComplexNumber("11.5487393574", "0"), cResult);
    } catch (IllegalConstruction _) {
      fail();
    }
  }

  @Test
  void testTanh() {
    IntegerNumber value = new IntegerNumber(0);
    List<Expression> p = Arrays.asList(value);
    try {
      Tanh e = new Tanh(p);
      Evaluator v = new Evaluator();
      e.accept(v);
      RealNumber result = (RealNumber) v.getResult();
      assertEquals(new RealNumber("0"), result);

      p = Arrays.asList(new RationalNumber(0, 1));
      e = new Tanh(p);
      e.accept(v);
      result = (RealNumber) v.getResult();
      assertEquals(new RealNumber("0"), result);

      RealNumber.setScale(10);
      p = Arrays.asList(new RealNumber(Math.PI));
      e = new Tanh(p);
      e.accept(v);
      result = (RealNumber) v.getResult();
      assertEquals(new RealNumber("0.99627207622"), result);

      p = Arrays.asList(new ComplexNumber(Math.PI, 0));
      e = new Tanh(p);
      e.accept(v);
      ComplexNumber cResult = (ComplexNumber) v.getResult();
      assertEquals(new ComplexNumber("0.99627207622", "0"), cResult);
    } catch (IllegalConstruction _) {
      fail();
    }
  }
}
