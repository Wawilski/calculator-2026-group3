package calculator;

import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/** Sine function: sin(x). */
public final class Sin extends Function {

  public Sin(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "sin";
    neutral = 0;
    arity = 1;
  }

  @Override
  public int function(int l, int r) {
    return (int) Math.sin(l);
  }

  @Override
  public BaseNumber function(IntegerNumber l, IntegerNumber r) {
    return new RealNumber(Math.sin(l.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber l, RationalNumber r) {
    double value = ((double) l.getNumerator()) / l.getDenominator();
    return new RealNumber(Math.sin(value));
  }

  @Override
  public BaseNumber function(RealNumber l, RealNumber r) {
    if (l.isSpecial()) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.sin(l.getValue().doubleValue()));
  }

  @Override
  public BaseNumber function(ComplexNumber l, ComplexNumber r) {
    return new ComplexNumber();
  }
}
