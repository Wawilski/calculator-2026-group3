package calculator;

import java.math.BigDecimal;
import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/** Exponential function: e^x. */
public final class Exponential extends Function {

  public Exponential(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "exp";
    neutral = 1;
    arity = 1;
  }

  @Override
  public int function(int l, int r) {
    return (int) Math.exp(l);
  }

  @Override
  public BaseNumber function(IntegerNumber l, IntegerNumber r) {
    return new RealNumber(Math.exp(l.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber l, RationalNumber r) {
    double value = ((double) l.getNumerator()) / l.getDenominator();
    return new RealNumber(Math.exp(value));
  }

  @Override
  public BaseNumber function(RealNumber l, RealNumber r) {
    if (l.isSpecial()) {
      if (l.getSpecialValue() == SpecialNumber.PositiveInfinity) {
        return new RealNumber(SpecialNumber.PositiveInfinity);
      }
      if (l.getSpecialValue() == SpecialNumber.NegativeInfinity) {
        return new RealNumber(BigDecimal.ZERO);
      }
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.exp(l.getValue().doubleValue()));
  }

  @Override
  public BaseNumber function(ComplexNumber l, ComplexNumber r) {
    return new ComplexNumber();
  }
}
