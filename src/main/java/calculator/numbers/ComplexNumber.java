
package calculator.numbers;

import java.math.BigDecimal;
import java.math.MathContext;

import calculator.Operation;
import calculator.numbers.visitor.TypeVisitor;
import visitor.Visitor;

/**
 * ComplexNumber
 */
public class ComplexNumber implements BaseNumber {

  private BigDecimal real;

  private BigDecimal imaginary;

  private boolean isNaN;

  public ComplexNumber(BigDecimal real, BigDecimal imaginary) {
    this.real = real;
    this.imaginary = imaginary;
    this.isNaN = false;
  }

  public ComplexNumber(int real, int imaginary) {
    this.real = new BigDecimal(real, MathContext.DECIMAL32);
    this.imaginary = new BigDecimal(imaginary, MathContext.DECIMAL32);
    this.isNaN = false;
  }

  public ComplexNumber(double real, double imaginary) {
    this.real = new BigDecimal(real, MathContext.DECIMAL32);
    this.imaginary = new BigDecimal(imaginary, MathContext.DECIMAL32);
    this.isNaN = false;
  }

  public ComplexNumber() {
    this.real = null;
    this.imaginary = null;
    this.isNaN = true;
  }

  public BigDecimal getReal() {
    return this.real;
  }

  public BigDecimal getImaginary() {
    return this.imaginary;
  }

  public boolean isNaN() {
    return this.isNaN;
  }

  @Override
  public void accept(TypeVisitor v) {
    v.visit(this);
  }

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
    if (o instanceof RealNumber) {
      return this.real.equals(((RealNumber) o).getValue());
    }

    if (o instanceof IntegerNumber) {
      return this.real.compareTo(new BigDecimal(((IntegerNumber) o).getValue())) == 0;
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
    return this.real.intValue() * this.imaginary.intValue();
  }

  @Override
  public String toString() {
    String s;
    if (isNaN) {
      s = "NaN";
    } else {
      String realPart = (this.real.equals(BigDecimal.ZERO)) ? "" : this.real.toString();
      String imPart = (this.imaginary.equals(BigDecimal.ZERO)) ? "" : " + " + this.imaginary.toString() + "i";

      s = realPart + imPart;

    }
    return s;

  }

}
