package calculator;

import java.util.List;

/** This class represents the square root operation "sqrt".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Plus
 * @see Minus
 * @see Times
 * @see Divides
 * @see Sin
 * @see Cos
 * @see Tan
 * @see Ln
 * @see Log
 * @see Reciprocal
 * @see Exponential
 * @see PowerTwo
 * @see Power
 * @see Abs
 */
public final class Sqrt extends Operation
{
  /**
   * Class constructor specifying a number of Expressions for square root.
   *
   * @param elist The list of Expressions to apply square root to
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Sqrt(List<Expression>,Notation)
   */
  public /*constructor*/ Sqrt(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions for square root,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to apply square root to
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Sqrt(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Sqrt(List<Expression> elist, Notation n) throws IllegalConstruction {
	super(elist,n);
	symbol = "sqrt";
	neutral = 1;
  }

  /**
   * The actual computation of the square root of an integer
   * @param l The integer to compute the square root of
   * @param r Unused for unary operation
   * @return The integer that is the result of the square root
   */
  public int op(int l, int r)
    { return (int) Math.sqrt(l); }
}
