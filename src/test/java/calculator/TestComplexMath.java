package calculator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import calculator.functions.ComplexMath;
import calculator.numbers.ComplexNumber;

class TestComplexMath {

  @Test
  void testSinCosTanAsinAtanReturnNaNOnInvalidInputs() {
    ComplexMath cm = new ComplexMath();
    ComplexNumber nanInput = new ComplexNumber();

    assertTrue(cm.sin(null).isNaN());
    assertTrue(cm.sin(nanInput).isNaN());

    assertTrue(cm.cos(null).isNaN());
    assertTrue(cm.cos(nanInput).isNaN());

    assertTrue(cm.tan(null).isNaN());
    assertTrue(cm.tan(nanInput).isNaN());

    assertTrue(cm.asin(null).isNaN());
    assertTrue(cm.asin(nanInput).isNaN());

    assertTrue(cm.atan(null).isNaN());
    assertTrue(cm.atan(nanInput).isNaN());
  }

  @Test
  void testTanDenominatorZeroBranchReturnsNaN() {
    ComplexMath cm = new ComplexMath();

    // For z = pi/2 + 0i, denominator is cos(pi) + cosh(0) = -1 + 1 = 0.
    ComplexNumber result = cm.tan(new ComplexNumber(Math.PI / 2.0, 0.0));

    assertTrue(result.isNaN());
  }

  @Test
  void testAcosAndToBranches() {
    ComplexMath cm = new ComplexMath();

    ComplexNumber acosValid = cm.acos(new ComplexNumber(0.2, -0.1));
    ComplexNumber acosInvalid = cm.acos(new ComplexNumber());

    assertFalse(acosValid.isNaN());
    assertTrue(acosInvalid.isNaN());

    assertFalse(cm.to(1.0, -2.0).isNaN());
    assertTrue(cm.to(Double.NaN, 0.0).isNaN());
    assertTrue(cm.to(Double.POSITIVE_INFINITY, 0.0).isNaN());
  }
}
