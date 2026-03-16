package visitor;

import calculator.MyNumber;
import calculator.Operation;
import calculator.numbers.RealNumber;
import calculator.numbers.IntegerNumber;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * CountingVisitor computes depth, number of operations and number of numbers in
 * an expression
 * 
 * @author Oussama Hakik
 */
public class CountingVisitor extends Visitor {

  private int opsCount; // number of operations
  private int numbersCount; // number of numbers
  private final Deque<Integer> depths = new ArrayDeque<>(); // stack to keep track of the depth of the current
                                                            // expression

  /**
   * The Visitor can traverse a number (a subtype of Expression)
   * 
   * @param n The number being visited
   */
  @Override
  public void visit(MyNumber n) {
    numbersCount++;
    depths.push(0);
  }

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

  public int getDepth() {
    return depths.isEmpty() ? 0 : depths.peek();
  }

  public int getOpsCount() {
    return opsCount;
  }

  public int getNumbersCount() {
    return numbersCount;
  }
}
