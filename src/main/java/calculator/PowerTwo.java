package calculator;

import java.util.List;

/** This class represents the square operation "x^2".
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
 * @see Reciprocal
 * @see Exponential
 * @see Power
 * @see Abs
 */
public final class PowerTwo extends Operation
{
  /**
   * Class constructor specifying a number of Expressions for square.
   *
   * @param elist The list of Expressions to apply square to
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #PowerTwo(List<Expression>,Notation)
   */
  public /*constructor*/ PowerTwo(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions for square,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to apply square to
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #PowerTwo(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public PowerTwo(List<Expression> elist, Notation n) throws IllegalConstruction {
	super(elist,n);
	symbol = "^2";
	neutral = 1;
  }

  /**
   * The actual computation of the square of an integer
   * @param l The integer to compute the square of
   * @param r Unused for unary operation
   * @return The integer that is the result of the square
   */
  public int op(int l, int r)
    { return l * l; }
}
