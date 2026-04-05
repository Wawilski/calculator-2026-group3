package calculator;

import java.math.BigDecimal;
import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.NumberType;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;
import calculator.numbers.visitor.TypeCaster;

/** Natural logarithm function: ln(x). */
public final class Ln extends UnaryFunction {

  /**
   * Build a logarithm.
   *
   * @param elist function argument list
   * @throws IllegalConstruction if the argument list is invalid
   */
  public Ln(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "ln";
    neutral = 0;
  }

  @Override
  public int function(int value) {
    return (int) Math.log(value);
  }

  @Override
  public BaseNumber function(IntegerNumber value) {
    if (value.getValue() == 0) {
      return new RealNumber(SpecialNumber.NegativeInfinity);
    }
    if (value.getValue() < 0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.log(value.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber value) {
    TypeCaster caster = new TypeCaster(NumberType.REAL);
    value.accept(caster);
    return function((RealNumber) caster.getResult());
  }

  /**
   * Compute ln(x) for real values.
   *
   * <p>Special handling:
   * +inf -&gt; +inf, 0 -&gt; -inf, negatives and NaN-like values -&gt; NaN.
   */
  @Override
  public BaseNumber function(RealNumber value) {
    if (value.isSpecial()) {
      if (value.getSpecialValue() == SpecialNumber.PositiveInfinity) {
        return new RealNumber(SpecialNumber.PositiveInfinity);
      }
      return new RealNumber(SpecialNumber.NaN);
    }
    int cmp = value.getValue().compareTo(BigDecimal.ZERO);
    if (cmp == 0) {
      return new RealNumber(SpecialNumber.NegativeInfinity);
    }
    if (cmp < 0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.log(value.getValue().doubleValue()));
  }

  @Override
  public BaseNumber function(ComplexNumber value) {
    return new ComplexNumber();
  }
}
