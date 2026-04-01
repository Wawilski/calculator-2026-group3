package calculator;

import java.util.List;
import java.lang.Math;
import java.math.BigDecimal;
import java.math.MathContext;

import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/**
 * This class represents the arithmetic sum operation "+".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * 
 * @see Operation
 * @see Minus
 * @see Times
 * @see Divides
 */
public final class Plus extends Operation {

  /**
   * Class constructor specifying a number of Expressions to add.
   *
   * @param elist The list of Expressions to add
   * @throws IllegalConstruction If an empty list of expressions if passed as
   *                             parameter
   */
  public /* constructor */ Plus(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "+";
    neutral = 0;
  }

  /**
   * The actual computation of the (binary) arithmetic addition of two integers
   * 
   * @param l The first integer
   * @param r The second integer that should be added to the first
   * @return The integer that is the result of the addition
   */
  public int op(int l, int r) {
    return (l + r);
  }

  /**
   * The actual computation of the arithmetic addition of two IntegerNumber
   * 
   * @param l The first IntegerNumber
   * @param r The second IntegerNumber that should be added to the first
   * @return The IntegerNumber that is the result of the addition
   */
  public IntegerNumber op(IntegerNumber l, IntegerNumber r) {
    return new IntegerNumber(l.getValue() + r.getValue());
  }

  /**
   * The actual computation of the arithmetic addition of two RationalNumber
   * 
   * @param l The first RationalNumber
   * @param r The second RationalNumber that should be added to the first
   * @return The RationalNumber that is the result of the addition
   */
  public RationalNumber op(RationalNumber l, RationalNumber r) {
    int numerator = l.getNumerator() * r.getDenominator() + r.getNumerator() * l.getDenominator();
    int denominator = l.getDenominator() * r.getDenominator();

    return new RationalNumber(numerator, denominator);
  }

  /**
   * The actual computation of the arithmetic addition of two RealNumber
   * 
   * @param l The first RealNumber
   * @param r The second RealNumber that should be added to the first
   * @return The RealNumber that is the result of the addition
   */
  public RealNumber op(RealNumber l, RealNumber r) {

    RealNumber result;
    if (l.isSpecial() || r.isSpecial()) {
      result = specialOp(l, r);
    } else {

      BigDecimal lValue = l.getValue();
      BigDecimal rValue = r.getValue();

      int precision = Math.min(lValue.scale(), rValue.scale());
      BigDecimal value = lValue.add(rValue, MathContext.DECIMAL32);
      value.setScale(precision);
      result = new RealNumber(value);
    }
    return result;
  }

  /**
   * The actual computation of the arithmetic addition of two RealNumber
   * if one of them are special (INFINITY or NaN)
   * 
   * @param l The first RealNumber
   * @param r The second RealNumber that should be added to the first
   * @return The RealNumber that is the result of the addition
   */
  @Override
  public RealNumber specialOp(RealNumber l, RealNumber r) {
    RealNumber result;

    if (l.isSpecial() && r.isSpecial() && r.sign() != l.sign()) {
      result = new RealNumber(SpecialNumber.NaN);
    } else if (l.isSpecial()) {
      result = new RealNumber(l.getSpecialValue());
    } else {
      result = new RealNumber(r.getSpecialValue());
    }
    return result;

  }

  /**
   * The actual computation of the arithmetic addition of two ComplexNumber
   * 
   * @param l The first ComplexNumber
   * @param r The second ComplexNumber that should be added to the first
   * @return The ComplexNumber that is the result of the addition
   */
  @Override
  public ComplexNumber op(ComplexNumber l, ComplexNumber r) {
    ComplexNumber result;
    if (l.isNaN() || r.isNaN()) {
      result = new ComplexNumber();
    } else {
      result = new ComplexNumber(l.getReal().add(r.getReal()), l.getImaginary().add(r.getImaginary()));
    }
    return result;
  }
}
