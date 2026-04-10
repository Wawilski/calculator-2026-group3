package calculator.functions;

import java.util.Arrays;
import java.util.List;

import calculator.Divides;
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
import visitor.Evaluator;

/** Hyperbolic Tangent function: cosh(x). */
public final class Tanh extends UnaryFunction {

  /**
   * Build a hyperbolic tangent.
   *
   * @param elist function argument list
   * @throws IllegalConstruction if the argument list is invalid
   */
  public Tanh(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "cosh";
    neutral = 1;
  }

  @Override
  public int function(int value) {
    return (int) Math.tanh(value);
  }

  @Override
  public BaseNumber function(IntegerNumber value) {
    double angle = (ExpressionParser.isAngleUnitDegree) ? Math.toRadians(value.getValue())
        : value.getValue();
    return new RealNumber(Math.tanh(angle));
  }

  @Override
  public BaseNumber function(RationalNumber value) {
    TypeCaster caster = new TypeCaster(NumberType.REAL);
    value.accept(caster);
    return function((RealNumber) caster.getResult());
  }

  /**
   * Compute tanh(x) for real values.
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
    return new RealNumber(Math.tanh(angle));
  }

  @Override
  public BaseNumber function(ComplexNumber value) {
    ComplexNumber result;
    if (value.isNaN()) {
      result = new ComplexNumber();
    } else {

      double real = value.getReal().doubleValue();
      double imaginary = value.getImaginary().doubleValue();

      ComplexNumber numerator = new ComplexNumber(Math.sinh(real) * Math.cos(imaginary),
          Math.cosh(real) * Math.sin(imaginary));
      ComplexNumber denominator = new ComplexNumber(Math.cosh(real) * Math.cos(imaginary),
          Math.sinh(real) * Math.sin(imaginary));
      ComplexNumber[] div = { numerator, denominator };
      try {
        Evaluator e = new Evaluator();
        Divides tanh = new Divides(Arrays.asList(div));
        tanh.accept(e);
        return e.getResult();

      } catch (IllegalConstruction e) {
        throw new RuntimeException();
      }
    }

    return result;
  }
}
