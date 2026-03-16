package calculator.numbers.visitor;

import calculator.numbers.IntegerNumber;

import calculator.numbers.RealNumber;

/**
 * TypeVisitor
 */
public abstract class TypeVisitor {

  public abstract void visit(RealNumber r);

  public abstract void visit(IntegerNumber i);

}
