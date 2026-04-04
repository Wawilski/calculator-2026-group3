
package calculator.numbers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import calculator.Operation;
import calculator.numbers.visitor.TypeVisitor;
import lombok.Getter;
import visitor.Visitor;

/**
 * This class represents the complex numbers.
 * The class implements the inteface BaseNumber.
 * 
 * @see BaseNumber
 * @see IntegerNumber
 * @see RationalNumber
 * @see RealNumber
 */

@Getter
public class ComplexNumber implements BaseNumber {

  // Real part of the complex number

    /**
     * -- GETTER --
     *  getter method to return the real part of the complex number
     *
     * @return The real part of the complex number
     */
    private BigDecimal real;

    /**
     * -- GETTER --
     *  getter method to return the imaginary part of the complex number
     *
     * @return The imaginary part of the complex number
     */
    // Imaginary part of the complex number
  private BigDecimal imaginary;

    /**
     * -- GETTER --
     *  method to tell if the complex number is a NaN
     *
     * @return if the complex number is a NaN
     */
    // Is true if the complex number is a NaN
  // (e.g. if it should represent an infinite value in complex)
  private boolean isNaN;

  /**
   * class constructor which specify the real and imaginary part as BigDecimal
   *
   * @param real      the BigDecimal representing the real part
   * @param imaginary the BigDecimal representing the imaginary part
   */
  public /* constructor */ ComplexNumber(BigDecimal real, BigDecimal imaginary) {
    this.real = real.setScale(RealNumber.getScale(), RoundingMode.CEILING);
    this.imaginary = imaginary.setScale(RealNumber.getScale(), RoundingMode.CEILING);
    this.isNaN = false;
  }

  /**
   * class constructor which specify the real and imaginary part as integer
   *
   * @param real      the integer representing the real part
   * @param imaginary the integer representing the imaginary part
   */
  public /* constructor */ ComplexNumber(int real, int imaginary) {
    this.real = new BigDecimal(real, MathContext.UNLIMITED).setScale(RealNumber.getScale(), RoundingMode.CEILING);
    this.imaginary = new BigDecimal(imaginary, MathContext.UNLIMITED).setScale(RealNumber.getScale(),
        RoundingMode.CEILING);
    this.isNaN = false;
  }

  /**
   * class constructor which specify the real and imaginary part as double
   *
   * @param real      the double representing the real part
   * @param imaginary the double representing the imaginary part
   */
  public /* constructor */ ComplexNumber(double real, double imaginary) {
    this.real = new BigDecimal(real, MathContext.UNLIMITED).setScale(RealNumber.getScale(), RoundingMode.CEILING);
    this.imaginary = new BigDecimal(imaginary, MathContext.UNLIMITED).setScale(RealNumber.getScale(),
        RoundingMode.CEILING);
    this.isNaN = false;
  }

  /**
   * class constructor which specify the real and imaginary part with
   * Strings
   *
   * @param real      the String representing the real part
   * @param imaginary the String representing the imaginary part
   */
  public /* constructor */ ComplexNumber(String real, String imaginary) {
    this.real = new BigDecimal(real, MathContext.UNLIMITED).setScale(RealNumber.getScale(), RoundingMode.CEILING);
    this.imaginary = new BigDecimal(imaginary, MathContext.UNLIMITED).setScale(RealNumber.getScale(),
        RoundingMode.CEILING);
    this.isNaN = false;
  }

  /**
   * Constructor for a NaN complex number
   */
  public /* constructor */ ComplexNumber() {
    this.real = null;
    this.imaginary = null;
    this.isNaN = true;
  }

    /**
   * Accept method to implement the visitor design pattern to numbers.
   * Each operation will delegate the visitor to each of its arguments
   * expressions,
   * and will then pass itself to the visitor object to get processed by the
   * visitor object.
   *
   * @param v The visitor object
   */
  @Override
  public void accept(TypeVisitor v) {
    v.visit(this);
  }

  /**
   * Accept method to implement the visitor design pattern to numbers.
   * Each operation will delegate the visitor to each of its arguments
   * expressions,
   * and will then pass itself to the visitor object to get processed by the
   * visitor object.
   *
   * @param v The visitor object
   */
  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }

  @Override
  public BaseNumber op(Operation o, BaseNumber rightHand) {
    return o.op(this, (ComplexNumber) rightHand);
  }

  /**
   * Two ComplexNumber expressions are equal if the values they contain are equal
   *
   * @param o The object to compare to
   * @return A boolean representing the result of the equality test
   */
  @Override
  public boolean equals(Object o) {
    // No object should be equal to null (not including this check can result in an
    // exception if a ComplexNumber is tested against null)
    if (o == null)
      return false;

    // If the object is compared to itself then return true
    if (o == this) {
      return true;
    }

    // If the object is of another type then return false
    if (!(o instanceof ComplexNumber)) {
      return false;
    }

    ComplexNumber other = (ComplexNumber) o;

    return (this.isNaN() && other.isNaN())
        || (this.real.compareTo(other.getReal()) == 0 && this.imaginary.compareTo(other.getImaginary()) == 0);
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
    return this.real.hashCode() + this.imaginary.hashCode();
  }

  @Override
  public String toString() {
    String s;
    if (isNaN) {
      s = "NaN";
    } else {
      String realPart = (this.real.equals(new BigDecimal(0).setScale(RealNumber.getScale(), RoundingMode.CEILING))) ? ""
          : this.real.toString();
      String imPart = (this.imaginary.equals(new BigDecimal(0).setScale(RealNumber.getScale(), RoundingMode.CEILING)))
          ? ""
          : this.imaginary.toString() + "i";
      String sign = (realPart == "" || imPart == "") ? "" : " + ";

      s = realPart + sign + imPart;

    }
    return s;

  }

}
