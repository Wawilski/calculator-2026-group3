package calculator;

import java.util.List;

/** This class represents the cosine operation "cos".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Plus
 * @see Minus
 * @see Times
 * @see Divides
 * @see Sqrt
 * @see Sin
 * @see Tan
 * @see Ln
 * @see Log
 * @see Reciprocal
 * @see Exponential
 * @see PowerTwo
 * @see Power
 * @see Abs
 */
public final class Cos extends Operation
{
  /**
   * Class constructor specifying a number of Expressions for cosine.
   *
   * @param elist The list of Expressions to apply cosine to
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Cos(List<Expression>,Notation)
   */
  public /*constructor*/ Cos(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions for cosine,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to apply cosine to
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Cos(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Cos(List<Expression> elist, Notation n) throws IllegalConstruction {
	super(elist,n);
	symbol = "cos";
	neutral = 1;
  }

  /**
   * The actual computation of the cosine of an integer (in radians)
   * @param l The integer to compute the cosine of
   * @param r Unused for unary operation
   * @return The integer that is the result of the cosine
   */
  public int op(int l, int r)
    { return (int) Math.cos(l); }
}
