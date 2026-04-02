package calculator;

import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/** Sine function: sin(x). */
public final class Sin extends UnaryFunction {

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
    return new RealNumber(Math.sin(value.getValue()));
  }

  @Override
  public BaseNumber function(RationalNumber value) {
    double ratio = ((double) value.getNumerator()) / value.getDenominator();
    return new RealNumber(Math.sin(ratio));
  }

  @Override
  public BaseNumber function(RealNumber value) {
    if (value.isSpecial()) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.sin(value.getValue().doubleValue()));
  }

  @Override
  public BaseNumber function(ComplexNumber value) {
    return new ComplexNumber();
  }
}
