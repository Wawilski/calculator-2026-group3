package calculator;

import java.math.BigDecimal;
import java.util.List;

import calculator.numbers.IntegerNumber;
import calculator.numbers.RealNumber;

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

  public IntegerNumber op(IntegerNumber l, IntegerNumber r) {
    int rightHand = r.getValue();
    if (rightHand == 0)
      throw new ArithmeticException("Division by zero is not allowed.");
    return new IntegerNumber(l.getValue() / rightHand);
  }

  public RealNumber op(RealNumber l, RealNumber r) {

    RealNumber result;

    if (l.isSpecial() || r.isSpecial() || r.getValue().compareTo(BigDecimal.ZERO) == 0) {
      result = specialOp(l, r);
    } else {

      BigDecimal lValue = l.getValue();
      BigDecimal rValue = r.getValue();
      int precision = Math.min(lValue.scale(), rValue.scale());
      BigDecimal value = lValue.divide(rValue);
      value.setScale(precision);
      result = new RealNumber(value);
    }
    return result;
  }

  @Override
  public RealNumber specialOp(RealNumber l, RealNumber r) {
    RealNumber result;
    RealNumber.SpecialNumbers lSpecialValue = l.getSpecialValue();
    RealNumber.SpecialNumbers rSpecialValue = r.getSpecialValue();

    if (lSpecialValue == RealNumber.SpecialNumbers.NaN || rSpecialValue == RealNumber.SpecialNumbers.NaN
        || (l.sign() == 0 && r.sign() == 0)) {
      result = new RealNumber(RealNumber.SpecialNumbers.NaN, true);
    } else if (l.sign() > 0 && r.sign() == 0) {
      result = new RealNumber(RealNumber.SpecialNumbers.PositiveInfinity, true);
    } else if (l.sign() < 0 && r.sign() == 0) {
      result = new RealNumber(RealNumber.SpecialNumbers.NegativeInfinity, true);
    } else {
      result = new RealNumber(new BigDecimal(0));
    }
    return result;

  }
}
