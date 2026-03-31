package calculator;

import java.util.List;

/** This class represents the absolute value operation "abs" or "|x|".
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
 * @see PowerTwo
 * @see Power
 */
public final class Abs extends Operation
{
  /**
   * Class constructor specifying a number of Expressions for absolute value.
   *
   * @param elist The list of Expressions to apply absolute value to
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Abs(List<Expression>,Notation)
   */
  public /*constructor*/ Abs(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions for absolute value,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to apply absolute value to
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Abs(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Abs(List<Expression> elist, Notation n) throws IllegalConstruction {
	super(elist,n);
	symbol = "abs";
	neutral = 1;
  }

  /**
   * The actual computation of the absolute value of an integer
   * @param l The integer to compute the absolute value of
   * @param r Unused for unary operation
   * @return The integer that is the result of the absolute value
   */
  public int op(int l, int r)
    { return Math.abs(l); }
}
