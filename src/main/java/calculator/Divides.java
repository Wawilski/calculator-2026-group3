package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import calculator.numbers.IntegerNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.SpecialNumber;

/**
 * This class represents the arithmetic division operation "/".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * 
 * @see Operation
 * @see Minus
 * @see Times
 * @see Plus
 */
public final class Divides extends Operation {

  /**
   * Class constructor specifying a number of Expressions to divide.
   *
   * @param elist The list of Expressions to divide
   * @throws IllegalConstruction If an empty list of expressions if passed as
   *                             parameter
   */
  public /* constructor */ Divides(List<Expression> elist) throws IllegalConstruction {
    super(elist);
    symbol = "/";
    neutral = 1;
  }

  /**
   * The actual computation of the (binary) arithmetic division of two integers
   * 
   * @param l The first integer
   * @param r The second integer that should divide the first
   * @return The integer that is the result of the division
   */
  public int op(int l, int r) {
    if (r == 0)
      throw new ArithmeticException("Division by zero is not allowed.");
    return (l / r);
  }

  /**
   * The actual computation of the arithmetic division of two IntegerNumber
   * 
   * @param l The first IntegerNumber
   * @param r The second IntegerNumber that should be divided to the first
   * @return The IntegerNumber that is the result of the division
   */
  public IntegerNumber op(IntegerNumber l, IntegerNumber r) {
    int rightHand = r.getValue();
    if (rightHand == 0)
      throw new ArithmeticException("Division by zero is not allowed.");
    return new IntegerNumber(l.getValue() / rightHand);
  }

  /**
   * The actual computation of the arithmetic division of two RealNumber
   * 
   * @param l The first RealNumber
   * @param r The second RealNumber that should be divided to the first
   * @return The RealNumber that is the result of the division
   */
  public RealNumber op(RealNumber l, RealNumber r) {

    RealNumber result;

    if (l.isSpecial() || r.isSpecial() || r.getValue().compareTo(BigDecimal.ZERO) == 0) {
      result = specialOp(l, r);
    } else {
      BigDecimal lValue = l.getValue();
      BigDecimal rValue = r.getValue();
      BigDecimal value = lValue.divide(rValue, 16, RoundingMode.CEILING);
      result = new RealNumber(value);
    }
    return result;
  }

  /**
   * The actual computation of the arithmetic division of two RealNumber
   * if one of them are special (INFINITY or NaN) or if the divisor is 0
   * 
   * @param l The first RealNumber
   * @param r The second RealNumber that should be divided to the first
   * @return The RealNumber that is the result of the division
   */
  @Override
  public RealNumber specialOp(RealNumber l, RealNumber r) {
    RealNumber result;
    SpecialNumber lSpecialValue = l.getSpecialValue();
    SpecialNumber rSpecialValue = r.getSpecialValue();
    int lSign = l.sign();
    int rSign = r.sign();

    if (lSpecialValue == SpecialNumber.NaN
        || rSpecialValue == SpecialNumber.NaN
        || (l.isSpecial() && r.isSpecial())
        || (l.sign() == 0 && r.sign() == 0)) {
      result = new RealNumber(SpecialNumber.NaN, true);
    } else if ((lSign > 0 && rSign >= 0) || (lSign < 0 && rSign < 0)) {
      result = new RealNumber(SpecialNumber.PositiveInfinity, true);
    } else if ((lSign < 0 && rSign >= 0) || (lSign > 0 && rSign < 0)) {
      result = new RealNumber(SpecialNumber.NegativeInfinity, true);
    } else {
      result = new RealNumber(new BigDecimal(0));

    }
    return result;

  }
}
