package calculator;

import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/** Cosine function: cos(x). */
public final class Cos extends Function {

  public Cos(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "cos";
    neutral = 1;
    arity = 1;
  }

  @Override
  public int function(int l, int r) {
    return (int) Math.cos(l);
  }

  @Override
  public BaseNumber function(IntegerNumber l, IntegerNumber r) {
    return new RealNumber(Math.cos(l.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber l, RationalNumber r) {
    double value = ((double) l.getNumerator()) / l.getDenominator();
    return new RealNumber(Math.cos(value));
  }

  @Override
  public BaseNumber function(RealNumber l, RealNumber r) {
    if (l.isSpecial()) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.cos(l.getValue().doubleValue()));
  }

  @Override
  public BaseNumber function(ComplexNumber l, ComplexNumber r) {
    return new ComplexNumber();
  }
}
