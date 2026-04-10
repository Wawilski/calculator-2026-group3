
package calculator.functions;

import java.util.List;

import calculator.Expression;
import calculator.ExpressionParser;
import calculator.IllegalConstruction;
import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.NumberType;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;
import calculator.numbers.visitor.TypeCaster;

/** Hyperbolic Cosine function: cosh(x). */
public final class Cosh extends UnaryFunction {

  /**
   * Build a hyperbolic cosine.
   *
   * @param elist function argument list
   * @throws IllegalConstruction if the argument list is invalid
   */
  public Cosh(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "cosh";
    neutral = 1;
  }

  @Override
  public int function(int value) {
    return (int) Math.cosh(value);
  }

  @Override
  public BaseNumber function(IntegerNumber value) {
    double angle = (ExpressionParser.isAngleUnitDegree) ? Math.toRadians(value.getValue())
        : value.getValue();
    return new RealNumber(Math.cosh(angle));
  }

  @Override
  public BaseNumber function(RationalNumber value) {
    TypeCaster caster = new TypeCaster(NumberType.REAL);
    value.accept(caster);
    return function((RealNumber) caster.getResult());
  }

  /**
   * Compute cosh(x) for real values.
   *
   * Special values are mapped to NaN.
   */
  @Override
  public BaseNumber function(RealNumber value) {
    if (value.isSpecial()) {
      return new RealNumber(SpecialNumber.NaN);
    }
    double angle = (ExpressionParser.isAngleUnitDegree) ? Math.toRadians(value.getValue().doubleValue())
        : value.getValue().doubleValue();
    System.out.println(angle);
    System.out.println(Math.cosh(angle));
    return new RealNumber(Math.cosh(angle));
  }

  @Override
  public BaseNumber function(ComplexNumber value) {
    ComplexNumber result;
    if (value.isNaN()) {
      result = new ComplexNumber();
    } else {

      double real = value.getReal().doubleValue();
      double imaginary = value.getImaginary().doubleValue();

      double resultReal = Math.cosh(real) * Math.cos(imaginary);
      double resultImaginary = Math.sinh(real) * Math.sin(imaginary);

      result = new ComplexNumber(resultReal, resultImaginary);
    }

    return result;
  }
}
