package calculator.numbers;

import java.math.BigDecimal;

import calculator.Operation;
import visitor.Visitor;
import calculator.numbers.visitor.*;

/**
 * RealNumber is a concrete class that represents real numbers.
 *
 * @see BaseNumber
 */
public class RealNumber implements BaseNumber {

  private BigDecimal value;

  private boolean special;
  private SpecialNumbers specialValue;

  public enum SpecialNumbers {
    NaN,
    PositiveInfinity,
    NegativeInfinity,

  }

  public RealNumber(BigDecimal value) {
    this.value = value;
    this.special = false;
    this.specialValue = null;
  }

  public RealNumber(SpecialNumbers specialValue, boolean isSpecial) {
    this.value = null;
    this.special = true;
    this.specialValue = specialValue;
  }

  /**
   * getter method to obtain the value contained in the object
   *
   * @return The BigDecimal contained in the object
   */
  public BigDecimal getValue() {
    return value;
  }

  public SpecialNumbers getSpecialValue() {
    return specialValue;
  }

  public boolean isSpecial() {
    return special;
  }

  public void setPrecision(int precision) {
    value.setScale(precision);
  }

  public void accept(TypeVisitor v) {
    v.visit(this);
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public BaseNumber op(Operation o, BaseNumber rightHand) {
    return o.op(this, (RealNumber) rightHand);
  }

  public int sign() {
    int sign;
    if (this.specialValue == SpecialNumbers.PositiveInfinity || this.value.compareTo(BigDecimal.ZERO) == 1) {
      sign = 1;
    } else if (this.specialValue == SpecialNumbers.NegativeInfinity || this.value.compareTo(BigDecimal.ZERO) == -1) {
      sign = -1;
    } else {
      sign = 0;
    }

    return sign;
  }

  @Override
  public String toString() {
    return value.toString();
  }

}
