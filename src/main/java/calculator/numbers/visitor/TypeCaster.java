
package calculator.numbers.visitor;

import java.math.BigDecimal;
import calculator.numbers.*;

/**
 * TypeCaster is a concrete visitor that serves to
 * cast a type of BaseNumber to another (e.g IntegerNumber -> RealNumber).
 */
public class TypeCaster extends TypeVisitor {

  /** Type to cast to */
  private NumberType type;

  /** Cast result */
  private BaseNumber result;

  /**
   * class constructor
   * 
   * @param type the type to cast to
   */
  public /* constructor */ TypeCaster(NumberType type) {
    this.type = type;
  }

  /**
   * getter method to obtain the cast result
   *
   * @return The BaseNumber object contained in result
   */
  public BaseNumber getResult() {
    return result;
  }

  /**
   * Visiting an IntegerNumber to cast it to the specified type of the visitor
   *
   * @param i The visited IntegerNumber to be cast
   */
  @Override
  public void visit(IntegerNumber i) {
    switch (type) {
      case INTEGER:
        result = i;
        break;
      case REAL:
        result = new RealNumber(new BigDecimal(i.getValue()));
        break;
      case RATIONAL:
        result = new RationalNumber(i.getValue(), 1);
        break;
      case COMPLEX:
        result = new ComplexNumber(i.getValue(), 0);
        break;
      default:
        throw new IllegalCast();
    }
  }

  /**
   * Visiting an RationalNumber to cast it to the specified type of the visitor
   *
   * @param r The visited RationalNumber to be cast
   */
  @Override
  public void visit(RationalNumber r) {
    switch (type) {
      case REAL:
        result = new RealNumber(r.getNumerator() / r.getDenominator());
        break;
      case RATIONAL:
        result = r;
        break;
      case COMPLEX:
        result = new ComplexNumber(r.getNumerator() / r.getDenominator(), 0);
        break;
      default:
        throw new IllegalCast();
    }
  }

  /**
   * Visiting a RealNumber to cast it to the specified type of the visitor
   *
   * @param r The visited RealNumber to be cast
   */
  @Override
  public void visit(RealNumber r) {
    switch (type) {
      case REAL:
        result = r;
        break;
      case COMPLEX:
        result = (r.isSpecial()) ? new ComplexNumber() : new ComplexNumber(r.getValue(), new BigDecimal(0));
        break;
      default:
        throw new IllegalCast();
    }
  }

  /**
   * Visiting a ComplexNumber to cast it to the specified type of the visitor
   *
   * @param c The visited ComplexlNumber to be cast
   */
  @Override
  public void visit(ComplexNumber c) {
    switch (type) {
      case COMPLEX:
        result = c;
        break;
      default:
        throw new IllegalCast();
    }
  }

}
