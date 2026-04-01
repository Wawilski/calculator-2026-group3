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
public final class Reciprocal extends Function {

  public Reciprocal(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "1/";
    neutral = 1;
    arity = 1;
  }

  @Override
  public int function(int l, int r) {
    if (l == 0) {
      throw new ArithmeticException("Division by zero is not allowed.");
    }
    return 1 / l;
  }

  @Override
  public BaseNumber function(IntegerNumber l, IntegerNumber r) {
    int value = l.getValue();
    if (value == 0) {
      throw new ArithmeticException("Division by zero is not allowed.");
    }
    return new RationalNumber(1, value);
  }

  @Override
  public BaseNumber function(RationalNumber l, RationalNumber r) {
    if (l.getNumerator() == 0) {
      throw new ArithmeticException("Division by zero is not allowed.");
    }
    return new RationalNumber(l.getDenominator(), l.getNumerator());
  }

  @Override
  public BaseNumber function(RealNumber l, RealNumber r) {
    if (l.isSpecial()) {
      if (l.getSpecialValue() == SpecialNumber.NaN) {
        return new RealNumber(SpecialNumber.NaN);
      }
      return new RealNumber(BigDecimal.ZERO);
    }
    if (l.getValue().compareTo(BigDecimal.ZERO) == 0) {
      return new RealNumber(SpecialNumber.PositiveInfinity);
    }
    return new RealNumber(BigDecimal.ONE.divide(l.getValue(), 16, RoundingMode.CEILING));
  }

  @Override
  public BaseNumber function(ComplexNumber l, ComplexNumber r) {
    if (l.isNaN()) {
      return new ComplexNumber();
    }

    BigDecimal real = l.getReal();
    BigDecimal imaginary = l.getImaginary();
    BigDecimal mod = real.pow(2).add(imaginary.pow(2));

    if (mod.compareTo(BigDecimal.ZERO) == 0) {
      return new ComplexNumber();
    }

    return new ComplexNumber(
        real.divide(mod, 16, RoundingMode.CEILING),
        imaginary.negate().divide(mod, 16, RoundingMode.CEILING));
  }
}
