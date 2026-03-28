package visitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.math.BigDecimal;

import calculator.Divides;
import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Minus;
import calculator.Operation;
import calculator.Plus;
import calculator.Times;
import calculator.calculatorBaseVisitor;
import calculator.calculatorParser.*;
import calculator.numbers.BaseNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;

/**
 * EquationListener
 */

public class ParserVisitor extends calculatorBaseVisitor<Expression> {
  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPostFix(PostFixContext ctx) {
    return visit(ctx.postfix());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitInFix(InFixContext ctx) {
    return visit(ctx.infix());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPreFix(PreFixContext ctx) {
    return visit(ctx.prefix());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitInBase(InBaseContext ctx) {
    return visit(ctx.add_infix());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitInAdd(InAddContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    ArrayList<String> signs = new ArrayList<>();
    for (Mul_infixContext mul_infix : ctx.mul_infix()) {
      args.add(visit(mul_infix));
    }
    for (SignContext sign : ctx.sign()) {
      signs.add(sign.getText());
    }

    Expression operation = createOp(signs.get(0), args.subList(0, 2));
    for (int i = 1; i < signs.size(); i++) {
      ArrayList<Expression> temp = new ArrayList<>();
      temp.add(operation);
      temp.add(args.get(i + 1));
      operation = createOp(signs.get(i), temp);

    }
    return operation;
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitInMul(InMulContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    ArrayList<String> signs = new ArrayList<>();
    for (Pow_infixContext mul_infix : ctx.pow_infix()) {
      args.add(visit(mul_infix));
    }
    for (MulContext sign : ctx.mul()) {
      signs.add(sign.getText());
    }

    Expression operation = createOp(signs.get(0), args.subList(0, 2));
    for (int i = 1; i < signs.size(); i++) {
      ArrayList<Expression> temp = new ArrayList<>();
      temp.add(operation);
      temp.add(args.get(i + 1));
      operation = createOp(signs.get(i), temp);

    }
    return operation;
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitInMulAtom(InMulAtomContext ctx) {

    ArrayList<Expression> args = new ArrayList<>();
    for (InfixContext infix : ctx.infix()) {
      args.add(visit(infix));
    }
    args.add(visit(ctx.atom()));

    return createOp("*", args);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitInPow(InPowContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    for (FactorContext factor : ctx.factor()) {
      args.add(visit(factor));
    }

    Expression operation = createOp("**", args);
    return operation;
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitInFct(InFctContext ctx) {
    return new RealNumber(1);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitInParenthesis(InParenthesisContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    for (InfixContext infix : ctx.infix()) {

      args.add(visit(infix));
    }
    Expression operation;
    if (args.size() > 1) {
      operation = createOp("*", args);
    } else {
      operation = args.get(1);
    }
    return operation;
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitInAtom(InAtomContext ctx) {
    return visit(ctx.atom());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitSpacedPreFix(SpacedPreFixContext ctx) {
    return visitChildren(ctx);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitParenthesisPreFix(ParenthesisPreFixContext ctx) {
    return visitChildren(ctx);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPreSpaceWrappedOp(PreSpaceWrappedOpContext ctx) {
    return visitChildren(ctx);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPreSpaceFct(PreSpaceFctContext ctx) {
    return visitChildren(ctx);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPreSpaceSigned(PreSpaceSignedContext ctx) {
    return visitChildren(ctx);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPreSpaceAtom(PreSpaceAtomContext ctx) {
    return visit(ctx.atom());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPreWrappedOp(PreWrappedOpContext ctx) {
    return visitChildren(ctx);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPreFct(PreFctContext ctx) {
    return visitChildren(ctx);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPreSigned(PreSignedContext ctx) {
    return visitChildren(ctx);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPreAtom(PreAtomContext ctx) {
    return visit(ctx.atom());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitSpacedPostFix(SpacedPostFixContext ctx) {
    return visit(ctx.space_postfix());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitParenthesisPostFix(ParenthesisPostFixContext ctx) {
    return visit(ctx.paren_postfix());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPostSpaceFct(PostSpaceFctContext ctx) {
    return new RealNumber(1);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPostSpaceWrappedOp(PostSpaceWrappedOpContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    for (Space_postfixContext postFix : ctx.space_postfix()) {
      args.add(visit(postFix));
    }

    Expression operator = createOp(ctx.operator().getText(), args);
    return operator;
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPostSpaceSigned(PostSpaceSignedContext ctx) {
    BaseNumber result;
    if (ctx.sign().getText() == "-") {
      result = ((BaseNumber) visit(ctx.atom())).negate();
    } else {
      result = (BaseNumber) visit(ctx.atom());
    }
    return result;
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPostSpaceSimpleOp(PostSpaceSimpleOpContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();

    args.add(visit(ctx.space_postfix(0)));
    args.add(visit(ctx.space_postfix(1)));
    return createOp(ctx.operator().getText(), args);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPostSpaceAtom(PostSpaceAtomContext ctx) {
    return visit(ctx.atom());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPostAtom(PostAtomContext ctx) {
    return visit(ctx.atom());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPostFct(PostFctContext ctx) {
    return new RealNumber(1);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPostWrappedOp(PostWrappedOpContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    for (Paren_postfixContext postFix : ctx.paren_postfix()) {
      args.add(visit(postFix));
    }

    Expression operator = createOp(ctx.operator().getText(), args);
    return operator;

  }

  @Override
  public Expression visitPostWrappedFct(PostWrappedFctContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    for (Paren_postfixContext postFix : ctx.paren_postfix()) {
      args.add(visit(postFix));
    }

    return new RealNumber(1);

  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPostSigned(PostSignedContext ctx) {
    BaseNumber result;
    if (ctx.sign().getText() == "-") {
      result = ((BaseNumber) visit(ctx.atom())).negate();
    } else {
      result = (BaseNumber) visit(ctx.atom());
    }
    return result;
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitGlobalNumber(GlobalNumberContext ctx) {
    return visit(ctx.number());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitComplexNumber(ComplexNumberContext ctx) {
    return visit(ctx.complex());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitRealNumber(RealNumberContext ctx) {
    return visit(ctx.real());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitScientificNumber(ScientificNumberContext ctx) {
    return visit(ctx.scientific());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitConstant(ConstantContext ctx) {
    return visit(ctx.num_const());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitBaseScientificNumber(BaseScientificNumberContext ctx) {
    return new RealNumber(Double.parseDouble(ctx.getText()));
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitBaseNumber(BaseNumberContext ctx) {
    String s = ctx.getText();
    BaseNumber result;
    if (ctx.children.size() == 1) {
      result = new IntegerNumber(Integer.parseInt(s));
    } else {
      result = new RealNumber(new BigDecimal(ctx.getText()));
    }
    return result;
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitBaseComplexNumber(BaseComplexNumberContext ctx) {
    if (ctx.number().isEmpty()) {
      return new ComplexNumber(0, 0);
    }
    ComplexNumber result;
    Expression e = visit(ctx.number());
    if (e instanceof IntegerNumber) {
      result = new ComplexNumber(0, ((IntegerNumber) e).getValue());
    }
    if (e instanceof RealNumber && !((RealNumber) e).isSpecial()) {
      result = new ComplexNumber(new BigDecimal(0), ((RealNumber) e).getValue());
    }
    if (e instanceof RationalNumber) {
      result = new ComplexNumber(0, ((RationalNumber) e).getNumerator() / ((RationalNumber) e).getDenominator());
    } else {
      result = new ComplexNumber();
    }

    return result;

  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPi(PiContext ctx) {
    return new RealNumber(Math.PI);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitEulerNumber(EulerNumberContext ctx) {
    return new RealNumber(Math.E);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitPhi(PhiContext ctx) {
    return new RealNumber((1 + Math.sqrt(5)) / 2);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitFctCos(FctCosContext ctx) {
    return new RealNumber(1);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitFctTan(FctTanContext ctx) {
    return new RealNumber(1);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitFctSin(FctSinContext ctx) {
    return new RealNumber(1);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitFctAcos(FctAcosContext ctx) {
    return new RealNumber(1);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitFctAtan(FctAtanContext ctx) {
    return new RealNumber(1);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitFctAsin(FctAsinContext ctx) {
    return new RealNumber(1);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitFctLn(FctLnContext ctx) {
    return new RealNumber(1);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitFctSqrt(FctSqrtContext ctx) {
    return new RealNumber(1);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.
   * </p>
   */
  @Override
  public Expression visitFctLog(FctLogContext ctx) {
    return new RealNumber(1);
  }

  public Expression createOp(String sign, List<Expression> args) {
    Operation op;
    try {
      switch (sign) {
        case "+":
          op = new Plus(args);
          break;
        case "-":
          op = new Minus(args);
          break;
        case "*":
          op = new Times(args);
          break;
        case "/":
          op = new Divides(args);
          break;
        case "**":
          op = new Times(args);
          break;
        case "":
          op = new Times(args);
        default:
          throw new IllegalArgumentException();
      }
      return op;

    } catch (IllegalConstruction e) {
      throw new RuntimeException(e);
    }
  }
}
