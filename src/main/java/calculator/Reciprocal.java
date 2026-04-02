package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/** Reciprocal function: 1/x. */
public final class Reciprocal extends UnaryFunction {

  public Reciprocal(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "1/x";
    neutral = 1;
  }

  @Override
  public int function(int value) {
    if (value == 0) {
      throw new ArithmeticException("Division by zero is not allowed.");
    }
    return 1 / value;
  }

  @Override
  public BaseNumber function(IntegerNumber value) {
    int number = value.getValue();
    if (number == 0) {
      throw new ArithmeticException("Division by zero is not allowed.");
    }
    return new RationalNumber(1, number);
  }

  @Override
  public BaseNumber function(RationalNumber value) {
    if (value.getNumerator() == 0) {
      throw new ArithmeticException("Division by zero is not allowed.");
    }
    return new RationalNumber(value.getDenominator(), value.getNumerator());
  }

  @Override
  public BaseNumber function(RealNumber value) {
    if (value.isSpecial()) {
      if (value.getSpecialValue() == SpecialNumber.NaN) {
        return new RealNumber(SpecialNumber.NaN);
      }
      return new RealNumber(BigDecimal.ZERO);
    }
    if (value.getValue().compareTo(BigDecimal.ZERO) == 0) {
      return new RealNumber(SpecialNumber.PositiveInfinity);
    }
    return new RealNumber(BigDecimal.ONE.divide(value.getValue(), 16, RoundingMode.CEILING));
  }

  @Override
  public BaseNumber function(ComplexNumber value) {
    if (value.isNaN()) {
      return new ComplexNumber();
    }

    BigDecimal real = value.getReal();
    BigDecimal imaginary = value.getImaginary();
    BigDecimal mod = real.pow(2).add(imaginary.pow(2));

    if (mod.compareTo(BigDecimal.ZERO) == 0) {
      return new ComplexNumber();
    }

    return new ComplexNumber(
        real.divide(mod, 16, RoundingMode.CEILING),
        imaginary.negate().divide(mod, 16, RoundingMode.CEILING));
  }
}
