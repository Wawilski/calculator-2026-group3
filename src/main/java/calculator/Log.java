package calculator;

import java.math.BigDecimal;
import java.util.List;

import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/** General logarithm function: log(value, base). */
public final class Log extends BinaryFunction {

  /**
   * Build a logarithm with: value and base.
   *
   * @param elist function argument list
   * @throws IllegalConstruction if the argument list is invalid
   */
  public Log(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "log";
    neutral = 0;
  }

  @Override
  public int function(int value, int base) {
    return (int) (Math.log(value) / Math.log(base));
  }

  /**
   * Compute log(value, base) for integer inputs.
   *
   * <p>Domain checks are applied on base/value before evaluating.
   */
  @Override
  public BaseNumber function(IntegerNumber value, IntegerNumber base) {
    if (base.getValue() <= 0 || base.getValue() == 1) {
      return new RealNumber(SpecialNumber.NaN);
    }
    if (value.getValue() == 0) {
      return new RealNumber(SpecialNumber.NegativeInfinity);
    }
    if (value.getValue() < 0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.log(value.getValue()) / Math.log(base.getValue()));
  }

  /**
   * Compute log(value, base) for rational inputs.
   *
   * <p>Rationals are converted through {@link RationalMath#toDouble(RationalNumber)}.
   */
  @Override
  public BaseNumber function(RationalNumber value, RationalNumber base) {
    double baseValue = new RationalMath().toDouble(base);
    double valueValue = new RationalMath().toDouble(value);
    if (baseValue <= 0.0 || baseValue == 1.0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    if (valueValue == 0.0) {
      return new RealNumber(SpecialNumber.NegativeInfinity);
    }
    if (valueValue < 0.0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.log(valueValue) / Math.log(baseValue));
  }

  /**
   * Compute log(value, base) for real inputs with special-value handling.
   */
  @Override
  public BaseNumber function(RealNumber value, RealNumber base) {
    if (value.isSpecial() || base.isSpecial()) {
      if (value.isSpecial() && value.getSpecialValue() == SpecialNumber.PositiveInfinity
          && !base.isSpecial()
          && base.getValue().compareTo(BigDecimal.ZERO) > 0
          && base.getValue().compareTo(BigDecimal.ONE) != 0) {
        return new RealNumber(SpecialNumber.PositiveInfinity);
      }
      return new RealNumber(SpecialNumber.NaN);
    }

    int baseCmpZero = base.getValue().compareTo(BigDecimal.ZERO);
    if (baseCmpZero <= 0 || base.getValue().compareTo(BigDecimal.ONE) == 0) {
      return new RealNumber(SpecialNumber.NaN);
    }

    int valueCmpZero = value.getValue().compareTo(BigDecimal.ZERO);
    if (valueCmpZero == 0) {
      return new RealNumber(SpecialNumber.NegativeInfinity);
    }
    if (valueCmpZero < 0) {
      return new RealNumber(SpecialNumber.NaN);
    }
    return new RealNumber(Math.log(value.getValue().doubleValue()) / Math.log(base.getValue().doubleValue()));
  }

  @Override
  public BaseNumber function(ComplexNumber l, ComplexNumber r) {
    return new ComplexNumber();
  }
}
