
package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IllegalCast;
import calculator.numbers.IntegerNumber;
import calculator.numbers.NumberType;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;
import calculator.numbers.visitor.TypeCaster;

/**
 * TestTypeVisitor
 */
public class TestCaster {

  private TypeCaster typeCaster;
  private IntegerNumber integer = new IntegerNumber(1);
  private RationalNumber rational = new RationalNumber(1, 2);
  private RealNumber real = new RealNumber("1.5");
  private RealNumber specialReal = new RealNumber(SpecialNumber.PositiveInfinity);
  private ComplexNumber complex = new ComplexNumber("1.5", "0.5");
  private BaseNumber casted;

  @Test
  void testCasterInteger() {
    typeCaster = new TypeCaster(NumberType.INTEGER);

    integer.accept(typeCaster);
    casted = typeCaster.getResult();
    assertEquals(new IntegerNumber(1), casted);
    assertThrows(IllegalCast.class, () -> rational.accept(typeCaster));
    assertThrows(IllegalCast.class, () -> real.accept(typeCaster));
    assertThrows(IllegalCast.class, () -> specialReal.accept(typeCaster));
    assertThrows(IllegalCast.class, () -> complex.accept(typeCaster));
  }

  @Test
  void testCasterRational() {
    typeCaster = new TypeCaster(NumberType.RATIONAL);

    integer.accept(typeCaster);
    casted = typeCaster.getResult();
    assertEquals(new RationalNumber(1, 1), casted);

    rational.accept(typeCaster);
    casted = typeCaster.getResult();
    assertEquals(new RationalNumber(1, 2), casted);

    assertThrows(IllegalCast.class, () -> real.accept(typeCaster));
    assertThrows(IllegalCast.class, () -> specialReal.accept(typeCaster));
    assertThrows(IllegalCast.class, () -> complex.accept(typeCaster));
  }

  @Test
  void testCasterReal() {
    typeCaster = new TypeCaster(NumberType.REAL);

    integer.accept(typeCaster);
    casted = typeCaster.getResult();
    assertEquals(new RealNumber(1), casted);

    rational.accept(typeCaster);
    casted = typeCaster.getResult();
    assertEquals(new RealNumber(0.5), casted);

    real.accept(typeCaster);
    casted = typeCaster.getResult();
    assertEquals(new RealNumber(1.5), casted);

    specialReal.accept(typeCaster);
    casted = typeCaster.getResult();
    assertEquals(new RealNumber(SpecialNumber.PositiveInfinity), casted);

    assertThrows(IllegalCast.class, () -> complex.accept(typeCaster));
  }

  @Test
  void testCasterComplex() {
    typeCaster = new TypeCaster(NumberType.COMPLEX);

    integer.accept(typeCaster);
    casted = typeCaster.getResult();
    assertEquals(new ComplexNumber("1", "0"), casted);

    rational.accept(typeCaster);
    casted = typeCaster.getResult();
    assertEquals(new ComplexNumber("0.5", "0"), casted);

    real.accept(typeCaster);
    casted = typeCaster.getResult();
    assertEquals(new ComplexNumber("1.5", "0"), casted);

    specialReal.accept(typeCaster);
    casted = typeCaster.getResult();
    assertEquals(new ComplexNumber(), casted);

    complex.accept(typeCaster);
    casted = typeCaster.getResult();
    assertEquals(new ComplexNumber("1.5", "0.5"), casted);
  }

}
