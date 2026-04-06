package calculator.functions;

import java.math.BigDecimal;
import java.util.List;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.NumberType;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;
import calculator.numbers.visitor.TypeCaster;

/** Arc cosine function: acos(x). */
public final class Acos extends UnaryFunction {

  /**
   * Build an arc-cosine function with one argument.
   *
   * @param elist function argument list
   * @throws IllegalConstruction if the argument list is invalid
   */
  public Acos(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "acos";
    neutral = 0;
  }

  @Override
  public int function(int value) {
    return (int) Math.acos(value);
  }

  @Override
  public BaseNumber function(IntegerNumber value) {
    return new RealNumber(Math.acos(value.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber value) {
    TypeCaster caster = new TypeCaster(NumberType.REAL);
    value.accept(caster);
    return function((RealNumber) caster.getResult());
  }

  /**
   * Compute acos(x) for real numbers.
   *
   * <p>
   * Returns NaN for special values and for out-of-domain values |x| &gt; 1.
   */
  @Override
  public BaseNumber function(RealNumber value) {
    if (value.isSpecial()) {
      return new RealNumber(SpecialNumber.NaN);
    }

    BigDecimal realValue = value.getValue();
    if (realValue.abs().compareTo(BigDecimal.ONE) > 0) {
      return new RealNumber(SpecialNumber.NaN);
    }

    return new RealNumber(Math.acos(realValue.doubleValue()));
  }

  @Override
  public BaseNumber function(ComplexNumber value) {
    return new ComplexMath().acos(value);
  }
}
