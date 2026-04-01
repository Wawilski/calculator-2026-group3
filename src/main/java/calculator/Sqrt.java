package calculator;

import java.math.BigDecimal;
import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/** Square root function: sqrt(x). */
public final class Sqrt extends Function {

  public Sqrt(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "sqrt";
    neutral = 1;
    arity = 1;
  }

  @Override
  public int function(int l, int r) {
    return (int) Math.sqrt(l);
  }

  @Override
  public BaseNumber function(IntegerNumber l, IntegerNumber r) {
    if (l.getValue() < 0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.sqrt(l.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber l, RationalNumber r) {
    double value = ((double) l.getNumerator()) / l.getDenominator();
    if (value < 0.0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.sqrt(value));
  }

  @Override
  public BaseNumber function(RealNumber l, RealNumber r) {
    if (l.isSpecial()) {
      if (l.getSpecialValue() == SpecialNumber.PositiveInfinity) {
        return new RealNumber(SpecialNumber.PositiveInfinity);
      }
      return new RealNumber(SpecialNumber.NaN);
    }
    if (l.getValue().compareTo(BigDecimal.ZERO) < 0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.sqrt(l.getValue().doubleValue()));
  }

  @Override
  public BaseNumber function(ComplexNumber l, ComplexNumber r) {
    return new ComplexNumber();
  }
}
