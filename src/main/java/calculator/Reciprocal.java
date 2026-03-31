package calculator;

import java.util.List;

/** This class represents the reciprocal operation "1/x".
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
 * @see Tan
 * @see Ln
 * @see Log
 * @see Exponential
 * @see PowerTwo
 * @see Power
 * @see Abs
 */
public final class Reciprocal extends Operation
{
  /**
   * Class constructor specifying a number of Expressions for reciprocal.
   *
   * @param elist The list of Expressions to apply reciprocal to
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Reciprocal(List<Expression>,Notation)
   */
  public /*constructor*/ Reciprocal(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions for reciprocal,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to apply reciprocal to
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Reciprocal(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Reciprocal(List<Expression> elist, Notation n) throws IllegalConstruction {
	super(elist,n);
	symbol = "1/";
	neutral = 1;
  }

  /**
   * The actual computation of the reciprocal of an integer
   * @param l The integer to compute the reciprocal of
   * @param r Unused for unary operation
   * @return The integer that is the result of the reciprocal
   */
  public int op(int l, int r)
    { return (l != 0) ? 1 / l : 0; }
}
