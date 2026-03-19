
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
   * Visiting a Real Atom to cast it to the specified type of the visitor
   *
   * @param r The visited RealNumber to be cast
   * @throws IllegalCasting in case of an impossible cast
   */
  @Override
  public void visit(RealNumber r) {
    switch (type) {
      case REAL:
        result = r;
        break;
      default:
        break;
    }
  }

  @Override
  public void visit(IntegerNumber i) {
    switch (type) {
      case INTEGER:
        result = i;
        break;
      case REAL:
        result = new RealNumber(new BigDecimal(i.getValue()));
        break;
      default:
        break;
    }
  }

}
