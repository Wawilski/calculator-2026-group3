package visitor;

import java.util.ArrayDeque;
import java.util.Deque;

import calculator.functions.Function;
import calculator.Operation;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;

/**
 * CountingVisitor computes depth, number of operations and number of numbers in
 * an expression
 * 
 * @author Oussama Hakik
 */
@SuppressWarnings("javadocs")
public class CountingVisitor extends Visitor {

  private int opsCount; // number of operations
  private int numbersCount; // number of numbers
  private final Deque<Integer> depths = new ArrayDeque<>(); // stack to keep track of the depth of the current
                                                            // expression

  @Override
  public void visit(RealNumber r) {
    numbersCount++;
    depths.push(0);

  }

  @Override
  public void visit(IntegerNumber r) {
    numbersCount++;
    depths.push(0);
  }

  @Override
  public void visit(RationalNumber r) {
    numbersCount += 2;
    opsCount++;
    depths.push(2);
  }

  @Override
  public void visit(ComplexNumber c) {
    numbersCount += 2;
    opsCount++;
    depths.push(2);
  }

  /**
   * The Visitor can traverse an operation (a subtype of Expression)
   * 
   * @param o The operation being visited
   */
  @Override
  public void visit(Operation o) {
    opsCount++;
    int maxDepth = 0;
    for (int i = 0; i < o.getArgs().size(); i++) {
      maxDepth = Math.max(maxDepth, depths.pop());
    }
    depths.push(maxDepth + 1);
  }

  @Override
  public void visit(Function f) {
    opsCount++;
    int maxDepth = 0;
    for (int i = 0; i < f.getArgs().size(); i++) {
      maxDepth = Math.max(maxDepth, depths.pop());
    }
    depths.push(maxDepth + 1);
  }

  /**
   * getter method to obtain the depth of the current expression
   * 
   * @return the depth of the expression
   */

  public int getDepth() {
    return depths.isEmpty() ? 0 : depths.peek();
  }

  /**
   * getter method to obtain the number of operations
   * 
   * @return the number of operations
   */

  public int getOpsCount() {
    return opsCount;
  }

  /**
   * getter method to obtain the number of numbers
   * 
   * @return the number of numbers
   */
  public int getNumbersCount() {
    return numbersCount;
  }
}
