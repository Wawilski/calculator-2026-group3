package calculator;

import calculator.cli.CalculatorCli;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;

/**
 * A very simple calculator in Java
 * University of Mons - UMONS
 * Software Engineering Lab
 * Faculty of Sciences
 *
 * @author tommens
 */
public class Main {

  /**
   * This is the main method of the application.
   * It provides examples of how to use it to construct and evaluate arithmetic
   * expressions.
   *
   * @param args Command-line parameters are not used in this version
   */
  public static void main(String[] args) {

    if (args.length > 0 && "--cli".equals(args[0])) {
      new CalculatorCli().run();
      return;
    }

    Expression e;
    Calculator c = new Calculator();
    Logger logger = Logger.getLogger(Main.class.getName());

    try {

      e = new IntegerNumber(8);
      c.print(e);
      c.eval(e);

      List<Expression> params = new ArrayList<>();
      Collections.addAll(params, new IntegerNumber(3), new IntegerNumber(4), new IntegerNumber(5));
      e = new Plus(params);
      c.printExpressionDetails(e);
      c.eval(e);
      logger.info("Prefix form: " + c.format(e, Notation.PREFIX));

      List<Expression> params2 = new ArrayList<>();
      Collections.addAll(params2, new IntegerNumber(5), new IntegerNumber(3));
      e = new Minus(params2);
      c.print(e);
      c.eval(e);

      List<Expression> params3 = new ArrayList<>();
      Collections.addAll(params3, new Plus(params), new Minus(params2));
      e = new Times(params3);
      c.printExpressionDetails(e);
      c.eval(e);
      logger.info("Pretty: " + c.prettyFormat(e));

      List<Expression> params4 = new ArrayList<>();
      Collections.addAll(params4, new Plus(params), new Minus(params2), new IntegerNumber(5));
      e = new Divides(params4);
      c.print(e);
      c.eval(e);
      logger.info("Postfix form: " + c.format(e, Notation.POSTFIX));

      List<Expression> params5 = new ArrayList<>();
      Collections.addAll(params5, new RealNumber(new BigDecimal(3.5)), new RealNumber(new BigDecimal(4.5)));
      e = new Divides(params5);
      c.print(e);
      c.eval(e);
      logger.info("Postfix form: " + c.format(e, Notation.POSTFIX));

      List<Expression> params6 = new ArrayList<>();
      Collections.addAll(params6, new RationalNumber(1, 2), new RationalNumber(1, 2));
      e = new Plus(params6);
      c.print(e);
      c.eval(e);
      logger.info("Postfix form: " + c.format(e, Notation.POSTFIX));

      List<Expression> params7 = new ArrayList<>();
      Collections.addAll(params7, new ComplexNumber(1, 2), new ComplexNumber(1, 2));
      e = new Plus(params7);
      c.print(e);
      c.eval(e);
      logger.info("Postfix form: " + c.format(e, Notation.POSTFIX));

      ExpressionParser parser = new ExpressionParser();
      e = parser.parse("1 + 1i * 2");
      c.print(e);
      c.eval(e);
      e = parser.parse("1 + 6 - 1i");
      c.print(e);
      c.eval(e);
      e = parser.parse(" + (1, i)");
      c.print(e);
      c.eval(e);
      e = parser.parse("i pi +");
      c.print(e);
      c.eval(e);
      e = parser.parse("(1 + 2)(2.0 / 4.0)*2*(7-1)*4");
      c.print(e);
      c.eval(e);
      e = parser.parse("(3)(0.5)*2*(7-1)*4");
      c.print(e);
      c.eval(e);

      List<Expression> params8 = new ArrayList<>();
      Collections.addAll(params8, new RationalNumber(1, 2), new IntegerNumber(1));
      e = new Plus(params8);
      c.print(e);
      c.eval(e);
      logger.info("Postfix form: " + c.format(e, Notation.POSTFIX));
    }

    catch (IllegalConstruction _) {
      logger.info("cannot create operations without parameters");
    }
  }

}
