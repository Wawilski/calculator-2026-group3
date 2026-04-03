package calculator.numbers;

import calculator.Operation;
import calculator.numbers.visitor.TypeVisitor;
import visitor.Visitor;

/**
 * This class represents the rational numbers.
 * The class implements the inteface BaseNumber.
 * 
 * @see BaseNumber
 * @see IntegerNumber
 * @see RealNumber
 * @see ComplexNumber
 */
public class RationalNumber implements BaseNumber {

  // Numerator of the rational number
  private int numerator;

  // Denominator of the rational number
  private int denominator;

  /**
   * class constructor which specify the numerator and denominator of the rational
   * number
   *
   * @throws IllegalNumberConstruction the denominator can't be 0
   *
   * @param numerator   the integer representing the numerator
   * @param denominator the integer representing the denominator
   */
  public /* constructor */ RationalNumber(int numerator, int denominator) throws IllegalNumberConstruction {

    if (denominator == 0) {
      throw new IllegalNumberConstruction();
    }
    this.numerator = numerator;
    this.denominator = denominator;
    this.simplify();
  }

  /**
   * getter method to obtain the numerator of the rational number
   *
   * @return the numerator of the rational number
   */
  public int getNumerator() {
    return numerator;
  }

  /**
   * getter method to obtain the denominator of the rational number
   *
   * @return the denominator of the rational number
   */
  public int getDenominator() {
    return denominator;
  }

  /**
   * accept method to implement the visitor design pattern to traverse arithmetic
   * expressions.
   * Each number will pass itself to the visitor object to get processed by the
   * visitor.
   *
   * @param v The visitor object
   */
  @Override
  public void accept(TypeVisitor v) {
    v.visit(this);
  }

  /**
   * accept method to implement the visitor design pattern to cost the
   * RationalNumber
   * into another type of number (IntegerNumber, RealNumber, ComplexNumber) or
   * compare its type with another BaseNumber.
   * 
   * Each number will pass itself to the visitor object to get processed by the
   * visitor.
   *
   * @param v The visitor object
   * @see TypeVisitor
   */
  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }

  /**
   * simplify the rational number such as pgc(numerator, denominator) == 1
   */
  public void simplify() {
    int num = Math.abs(this.numerator);
    int den = Math.abs(this.denominator);
    int sign = ((this.numerator < 0) ^ (this.denominator) < 0) ? -1 : 1;
    int temp;
    while (den != 0) {
      temp = den;
      den = num % den;
      num = temp;
    }

    this.numerator = (int) sign * Math.abs(numerator / num);
    this.denominator = (int) Math.abs(denominator / num);

  }

  /**
   * apply an operation between this and another RationalNumber
   *
   * @param o         The operation to apply
   * @param rightHand The other hand of the operation
   *
   */
  @Override
  public BaseNumber op(Operation o, BaseNumber rightHand) {
    return o.op(this, ((RationalNumber) rightHand));
  }

  /**
   * Two RationalNumber expressions are equal if the values they contain are equal
   *
   * @param o The object to compare to
   * @return A boolean representing the result of the equality test
   */
  @Override
  public boolean equals(Object o) {
    // No object should be equal to null (not including this check can result in an
    // exception if a RealNumber is tested against null)
    if (o == null)
      return false;

    // If the object is compared to itself then return true
    if (o == this) {
      return true;
    }

    if (!(o instanceof RationalNumber)) {
      return false;
    }

    return this.numerator == ((RationalNumber) o).getNumerator()
        && this.denominator == ((RationalNumber) o).getDenominator();
  }

  /**
   * The method hashCode needs to be overridden if the equals method is
   * overridden;
   * otherwise there may be problems when you use your object in hashed
   * collections
   * such as HashMap, HashSet, LinkedHashSet.
   *
   * @return The result of computing the hash.
   */
  @Override
  public int hashCode() {
    return Math.powExact(numerator, 2) + Math.powExact(denominator, 3);
  }

  @Override
  public String toString() {

    String denominatorString = (this.denominator == 1) ? "" : " / " + ((Integer) this.denominator).toString();

    return ((Integer) this.numerator).toString() + denominatorString;
  }

}
