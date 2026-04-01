package calculator;

import calculator.numbers.BaseNumber;
import visitor.Visitor;

/**
 * Expression is an interface that represents arithmetic expressions.
 * It has two concrete subclasses Operation and MyNumber.
 *
 * @see Operation
 * @see BaseNumber
 */

public interface Expression {
  /**
   * accept is a method needed to implement the visitor design pattern
   *
   * @param v The visitor object being passed as a parameter
   */
  void accept(Visitor v);
}
