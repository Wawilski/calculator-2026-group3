package calculator;

import java.util.List;

/** This class represents the tangent operation "tan".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Plus
 * @see Minus
 * @see Times
 * @see Divides
 * @see Sqrt
 * @see Sin
 * @see Cos
 * @see Ln
 * @see Log
 * @see Reciprocal
 * @see Exponential
 * @see PowerTwo
 * @see Power
 * @see Abs
 */
public final class Tan extends Operation
{
  /**
   * Class constructor specifying a number of Expressions for tangent.
   *
   * @param elist The list of Expressions to apply tangent to
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Tan(List<Expression>,Notation)
   */
  public /*constructor*/ Tan(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions for tangent,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to apply tangent to
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Tan(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Tan(List<Expression> elist, Notation n) throws IllegalConstruction {
	super(elist,n);
	symbol = "tan";
	neutral = 1;
  }

  /**
   * The actual computation of the tangent of an integer (in radians)
   * @param l The integer to compute the tangent of
   * @param r Unused for unary operation
   * @return The integer that is the result of the tangent
   */
  public int op(int l, int r)
    { return (int) Math.tan(l); }
}
