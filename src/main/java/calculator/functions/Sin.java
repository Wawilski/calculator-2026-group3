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

/** Sine function: sin(x). */
public final class Sin extends UnaryFunction {

  /**
   * Build a sine function.
   *
   * @param elist function argument list
   * @throws IllegalConstruction if the argument list is invalid
   */
  public Sin(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "sin";
    neutral = 0;
  }

  @Override
  public int function(int value) {
    return (int) Math.sin(value);
  }

  @Override
  public BaseNumber function(IntegerNumber value) {
    double angle = (ExpressionParser.isAngleUnitDegree) ? Math.toRadians(value.getValue())
        : value.getValue();
    return new RealNumber(Math.sin(angle));
  }

  @Override
  public BaseNumber function(RationalNumber value) {
    TypeCaster caster = new TypeCaster(NumberType.REAL);
    value.accept(caster);
    return function((RealNumber) caster.getResult());
  }

  /**
   * Compute sin(x) for real values.
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
    return new RealNumber(Math.sin(angle));
  }

  @Override
  public BaseNumber function(ComplexNumber value) {
    return new ComplexMath().sin(value);
  }
}
