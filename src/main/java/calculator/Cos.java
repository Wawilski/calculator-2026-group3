package calculator;

import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/** Cosine function: cos(x). */
public final class Cos extends UnaryFunction {

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
    return new RealNumber(Math.cos(value.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber value) {
    double ratio = ((double) value.getNumerator()) / value.getDenominator();
    return new RealNumber(Math.cos(ratio));
  }

  @Override
  public BaseNumber function(RealNumber value) {
    if (value.isSpecial()) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.cos(value.getValue().doubleValue()));
  }

  @Override
  public BaseNumber function(ComplexNumber value) {
    return new ComplexNumber();
  }
}
