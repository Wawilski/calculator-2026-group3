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

/** Cosine function: cos(x). */
public final class Cos extends UnaryFunction {

  /**
   * Build a cosine.
   *
   * @param elist function argument list
   * @throws IllegalConstruction if the argument list is invalid
   */
  public Cos(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "cos";
    neutral = 1;
  }

  @Override
  public int function(int value) {
    return (int) Math.cos(value);
  }

  @Override
  public BaseNumber function(IntegerNumber value) {
    double angle = (ExpressionParser.isAngleUnitDegree) ? Math.toRadians(value.getValue())
        : value.getValue();
    return new RealNumber(Math.cos(angle));
  }

  @Override
  public BaseNumber function(RationalNumber value) {
    TypeCaster caster = new TypeCaster(NumberType.REAL);
    value.accept(caster);
    return function((RealNumber) caster.getResult());
  }

  /**
   * Compute cos(x) for real values.
   *
   * <p>
   * Special values are mapped to NaN.
   */
  @Override
  public BaseNumber function(RealNumber value) {
    if (value.isSpecial()) {
      return new RealNumber(SpecialNumber.NaN);
    }
    double angle = (ExpressionParser.isAngleUnitDegree) ? Math.toRadians(value.getValue().doubleValue())
        : value.getValue().doubleValue();
    return new RealNumber(Math.cos(angle));
  }

  @Override
  public BaseNumber function(ComplexNumber value) {
    return new ComplexMath().cos(value);
  }
}
