package visitor;

import java.util.ArrayList;
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
   * visits the grammar rule equation : postfix EOF
   * 
   * @return Expression resulting from the equation
   */
  @Override
  public Expression visitPostFix(PostFixContext ctx) {
    return visit(ctx.postfix());
  }

  /**
   * visits the grammar rule equation : infix EOF
   * 
   * @return Expression resulting from the equation
   */
  @Override
  public Expression visitInFix(InFixContext ctx) {
    return visit(ctx.infix());
  }

  /**
   * visits the grammar rule equation : prefix EOF
   * 
   * @return Expression resulting from the equation
   */
  @Override
  public Expression visitPreFix(PreFixContext ctx) {
    return visit(ctx.prefix());
  }

  /**
   * visits the grammar rule infix : add_infix
   * 
   * @return Expression resulting from add_infix
   */
  @Override
  public Expression visitInBase(InBaseContext ctx) {
    return visit(ctx.add_infix());
  }

  /**
   * visits the grammar rule add_infix : mul_infix (sign mul_infix)*
   * 
   * @return Expression representing a sum/difference of mul_infix
   */
  @Override
  public Expression visitInAdd(InAddContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    ArrayList<String> signs = new ArrayList<>();
    for (Mul_infixContext mul_infix : ctx.mul_infix()) {
      args.add(visit(mul_infix));
    }
    for (SignContext sign : ctx.sign()) {
      String op = (sign == null) ? "" : sign.getText();
      signs.add(op);
    }
    Expression expr;
    if (args.size() > 1) {
      expr = createGlobalOp(args, signs);

    } else {
      expr = args.get(0);
    }

    return expr;
  }

  /**
   * visits the grammar rule mul_infix : pow_infix (mul pow_infix)*
   * 
   * @return Expression representing a multiplication/division of pow_infix
   */
  @Override
  public Expression visitInMul(InMulContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    ArrayList<String> muls = new ArrayList<>();
    for (Pow_infixContext mul_infix : ctx.pow_infix()) {
      args.add(visit(mul_infix));
    }
    for (MulContext mul : ctx.mul()) {
      muls.add(mul.getText());
    }

    Expression op;
    if (args.size() > 1) {
      op = createGlobalOp(args, muls);
    } else {
      op = args.get(0);
    }

    return op;
  }

  /**
   * visits the grammar rule
   * mul_infix : (atom LPAREN infix RPAREN)+ (atom)?
   * 
   * @return Expression representing an multiplication of an atom and infixes
   *         expressions between parenthesis
   */
  @Override
  public Expression visitInMulAtom(InMulAtomContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    for (InfixContext infix : ctx.infix()) {
      args.add(visit(infix));
    }
    for (AtomContext atom : ctx.atom()) {
      args.add(visit(atom));
    }

    return createOp("*", args);
  }

  /**
   * visits the grammar rule pow_infix : factor (pow factor)*
   * 
   * @return Expression representing an exponentiation of pow_infix
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
   * visits the grammar rule
   * factor : fct LPAREN infix (COMMAT infix)* RPAREN
   * 
   * @return Expression representing an function
   */
  @Override
  public Expression visitInFct(InFctContext ctx) {
    return new RealNumber(1);
  }

  /**
   * visits the grammar rule
   * factor : LPAREN infix RPAREN (LPAREN infix RPAREN)*
   * 
   * @return Expression representing an expression between parenthesis
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
      operation = args.get(0);
    }
    return operation;
  }

  /**
   * visits the grammar rule
   * factor : (sign)? (num_const)* atom (num_const)*
   * 
   * @return Expression representing an expression between parenthesis
   */
  @Override
  public Expression visitInAtom(InAtomContext ctx) {
    BaseNumber atom = (BaseNumber) visit(ctx.atom());
    if (ctx.sign() != null && ctx.sign().getText() == "-") {
      atom = atom.negate();
    }
    ArrayList<Expression> args = new ArrayList<>();
    for (Num_constContext num_const : ctx.num_const()) {
      args.add(visit(num_const));
    }
    args.add(atom);

    return createOp("*", args);
  }

  /**
   * visits the grammar rule
   * prefix : space_prefix
   *
   * @return Expression representing a prefix expression with spaces
   */
  @Override
  public Expression visitSpacedPreFix(SpacedPreFixContext ctx) {
    return visit(ctx.space_prefix());
  }

  /**
   * visits the grammar rule
   * prefix : paren_prefix
   *
   * @return Expression representing a prefix expression with commas and
   *         parenthesis
   */
  @Override
  public Expression visitParenthesisPreFix(ParenthesisPreFixContext ctx) {
    return visit(ctx.paren_prefix());
  }

  /**
   * visits the grammar rule
   * space_prefix: (operator | fct)? LPAREN space_prefix (space_prefix)+ RPAREN
   * 
   * @return Expression representing a fonction or operation with more than 2
   *         attribute
   */
  @Override
  public Expression visitPreSpaceWrappedOp(PreSpaceWrappedOpContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    for (Space_prefixContext sPreFixContext : ctx.space_prefix()) {
      args.add(visit(sPreFixContext));
    }
    Expression operation;
    if (ctx.fct() != null) {
      operation = new RealNumber(1);
    } else {
      operation = createOp(ctx.operator().getText(), args);
    }
    return operation;

  }

  /**
   * visits the grammar rule
   * space_prefix : fct space_prefix
   *
   * @return Expression representing a function with one parameter
   */
  @Override
  public Expression visitPreSpaceFct(PreSpaceFctContext ctx) {
    return new RealNumber(1);
  }

  /**
   * visits the grammar rule
   * space_prefix : operator space_prefix space_prefix
   *
   * @return Expression representing an operator with two parameters
   */
  @Override
  public Expression visitPreSpaceOperator(PreSpaceOperatorContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    for (Space_prefixContext sPrefixContext : ctx.space_prefix()) {
      args.add(visit(sPrefixContext));
    }
    return createOp(ctx.operator().getText(), args);
  }

  /**
   * visits the grammar rule
   * space_prefix : LPAREN (sign)? atom RPAREN
   * 
   * @return Expression representing a signed number
   */
  @Override
  public Expression visitPreSpaceSigned(PreSpaceSignedContext ctx) {
    BaseNumber atom = (BaseNumber) visit(ctx.atom());
    if (ctx.sign() != null && ctx.sign().getText() == "-") {
      atom = atom.negate();
    }
    return atom;
  }

  /**
   * visits the grammar rule
   * space_prefix : atom
   *
   * @return Expression representing a simple number
   */
  @Override
  public Expression visitPreSpaceAtom(PreSpaceAtomContext ctx) {
    return visit(ctx.atom());
  }

  /**
   * visits the grammar rule
   * paren_prefix: (operator|fct)? LPAREN paren_prefix (COMMAT paren_prefix)*
   * RPAREN
   *
   * @return Expression representing a fonction or operation with more than 2
   *         attribute
   */
  @Override
  public Expression visitPreWrappedOp(PreWrappedOpContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    for (Paren_prefixContext sPreFixContext : ctx.paren_prefix()) {
      args.add(visit(sPreFixContext));
    }
    Expression operation;
    if (ctx.fct() != null) {
      operation = new RealNumber(1);
    } else {
      String op = (ctx.operator() == null) ? "" : ctx.operator().getText();
      operation = createOp(op, args);
    }
    return operation;
  }

  /**
   * visits the grammar rule
   * paren_prefix : fct paren_prefix
   *
   * @return Expression representing a function with one parameter
   */
  @Override
  public Expression visitPreFct(PreFctContext ctx) {
    return new RealNumber(1);
  }

  /**
   * visits the grammar rule
   * paren_prefix : LPAREN (sign)? atom RPAREN
   * 
   * @return Expression representing a signed number
   */
  @Override
  public Expression visitPreSigned(PreSignedContext ctx) {
    BaseNumber atom = (BaseNumber) visit(ctx.atom());
    if (ctx.sign() != null && ctx.sign().getText() == "-") {
      atom = atom.negate();
    }
    return atom;
  }

  /**
   * visits the grammar rule
   * paren_prefix : atom
   *
   * @return Expression representing a simple number
   */
  @Override
  public Expression visitPreAtom(PreAtomContext ctx) {
    return visit(ctx.atom());
  }

  /**
   * visits the grammar rule
   * postfix : space_postfix
   *
   * @return Expression representing a postfix expression with spaces
   */
  @Override
  public Expression visitSpacedPostFix(SpacedPostFixContext ctx) {
    return visit(ctx.space_postfix());
  }

  /**
   * visits the grammar rule
   * postfix : paren_postfix
   *
   * @return Expression representing a postfix expression with commas and
   *         parenthesis
   */
  @Override
  public Expression visitParenthesisPostFix(ParenthesisPostFixContext ctx) {
    return visit(ctx.paren_postfix());
  }

  /**
   * visits the grammar rule
   * space_postfix: LPAREN space_postfix (space_postfix)+ RPAREN (operator)?
   *
   * @return Expression representing a postfix operation with multiple arguments
   */
  @Override
  public Expression visitPostSpaceWrappedOp(PostSpaceWrappedOpContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    for (Space_postfixContext postFix : ctx.space_postfix()) {
      args.add(visit(postFix));
    }

    String op = (ctx.operator() == null) ? "" : ctx.operator().getText();
    Expression operator = createOp(op, args);
    return operator;
  }

  /**
   * visits the grammar rule
   * space_postfix: LPAREN space_postfix (space_postfix)+ RPAREN fct
   *
   * @return Expression representing a postfix function with multiple arguments
   */
  @Override
  public Expression visitPostSpaceWrappedFct(PostSpaceWrappedFctContext ctx) {
    return new RealNumber(1);
  }

  /**
   * visits the grammar rule
   * space_postfix: space_postfix fct
   *
   * @return Expression representing a postfix function with one argument
   */
  @Override
  public Expression visitPostSpaceFct(PostSpaceFctContext ctx) {
    return new RealNumber(1);
  }

  /**
   * visits the grammar rule
   * space_postfix : space_postfix space_postfix (operator)?
   * 
   * @return Expression representing an operator with two parameters
   */
  @Override
  public Expression visitPostSpaceSimpleOp(PostSpaceSimpleOpContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    for (Space_postfixContext sPostfixContext : ctx.space_postfix()) {
      args.add(visit(sPostfixContext));
    }

    String op = (ctx.operator() == null) ? "" : ctx.operator().getText();
    return createOp(op, args);
  }

  /**
   * visits the grammar rule
   * space_postfix: LPAREN (sign)? atom RPAREN
   * 
   * @return Expression representing a signed number
   */
  @Override
  public Expression visitPostSpaceSigned(PostSpaceSignedContext ctx) {
    BaseNumber atom = (BaseNumber) visit(ctx.atom());
    if (ctx.sign() != null && ctx.sign().getText() == "-") {
      atom = atom.negate();
    }
    return atom;
  }

  /**
   * visits the grammar rule
   * space_postfix: atom
   * 
   * @return Expression representing a simple number
   */
  @Override
  public Expression visitPostSpaceAtom(PostSpaceAtomContext ctx) {
    return visit(ctx.atom());
  }

  /**
   * visits the grammar rule
   * paren_postfix: LPAREN paren_postfix (COMMAT paren_postfix)+ RPAREN
   * (operator)?
   *
   * @return Expression representing a postfix operation with multiple arguments,
   *         commas and parentheis
   */
  @Override
  public Expression visitPostWrappedOp(PostWrappedOpContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    for (Paren_postfixContext postFix : ctx.paren_postfix()) {
      args.add(visit(postFix));
    }

    return createOp(ctx.operator().getText(), args);

  }

  /**
   * visits the grammar rule
   * paren_postfix: LPAREN paren_postfix (COMMAT paren_postfix)+ RPAREN fct
   *
   * @return Expression representing a postfix function with multiple arguments,
   *         commas and parentheis
   */
  @Override
  public Expression visitPostWrappedFct(PostWrappedFctContext ctx) {
    ArrayList<Expression> args = new ArrayList<>();
    for (Paren_postfixContext postFix : ctx.paren_postfix()) {
      args.add(visit(postFix));
    }

    return new RealNumber(1);

  }

  /**
   * visits the grammar rule
   * paren_postfix: paren_postfix fct
   *
   * @return Expression representing a postfix function with one argument
   */
  @Override
  public Expression visitPostFct(PostFctContext ctx) {
    return new RealNumber(1);
  }

  /**
   * visits the grammar rule
   * paren_postfix: LPAREN (sign)? atom RPAREN
   * 
   * @return Expression representing a signed number
   */
  @Override
  public Expression visitPostSigned(PostSignedContext ctx) {
    BaseNumber atom = (BaseNumber) visit(ctx.atom());
    if (ctx.sign() != null && ctx.sign().getText() == "-") {
      atom = atom.negate();
    }
    return atom;
  }

  /**
   * visits the grammar rule
   * paren_postfix: atom
   * 
   * @return Expression representing a simple number
   */
  @Override
  public Expression visitPostAtom(PostAtomContext ctx) {
    return visit(ctx.atom());
  }

  /**
   * visits the grammar rule
   * atom : number
   * 
   * @return Expression representing non complex number
   */
  @Override
  public Expression visitGlobalNumber(GlobalNumberContext ctx) {
    return visit(ctx.number());
  }

  /**
   * visits the grammar rule
   * atom : complex
   * 
   * @return Expression representing complex number
   */
  @Override
  public Expression visitComplexNumber(ComplexNumberContext ctx) {
    return visit(ctx.complex());
  }

  /**
   * visits the grammar rule
   * number : real
   * 
   * @return Expression representing a real number
   */
  @Override
  public Expression visitRealNumber(RealNumberContext ctx) {
    return visit(ctx.real());
  }

  /**
   * visits the grammar rule
   * number : scientific
   * 
   * @return Expression representing a real number in scientific notation
   */
  @Override
  public Expression visitScientificNumber(ScientificNumberContext ctx) {
    return visit(ctx.scientific());
  }

  /**
   * visits the grammar rule
   * number : num_const
   * 
   * @return Expression representing a constant number
   */
  @Override
  public Expression visitConstant(ConstantContext ctx) {
    return visit(ctx.num_const());
  }

  /**
   * visits the grammar rule
   * scientific : INT E (sign)? INT
   * 
   * @return Expression representing a real number in scientific notation
   */
  @Override
  public Expression visitBaseScientificNumber(BaseScientificNumberContext ctx) {
    return new RealNumber(Double.parseDouble(ctx.getText()));
  }

  /**
   * visits the grammar rule
   * real : INT (DOT INT)?
   * 
   * @return Expression representing a concrete real number
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
   * visits the grammar rule
   * complex: (number)? I
   * 
   * @return Expression representing a concrete complex number
   */
  @Override
  public Expression visitBaseComplexNumber(BaseComplexNumberContext ctx) {
    if (ctx.number() == null) {
      return new ComplexNumber(0, 1);
    }
    ComplexNumber result;
    Expression e = visit(ctx.number());
    if (e instanceof IntegerNumber) {
      result = new ComplexNumber(0, ((IntegerNumber) e).getValue());
    } else if (e instanceof RealNumber && !((RealNumber) e).isSpecial()) {
      result = new ComplexNumber(new BigDecimal(0), ((RealNumber) e).getValue());
    } else if (e instanceof RationalNumber) {
      result = new ComplexNumber(0, ((RationalNumber) e).getNumerator() / ((RationalNumber) e).getDenominator());
    } else {
      result = new ComplexNumber();
    }

    return result;

  }

  /**
   * visits the grammar rule
   * num_const: PI
   * 
   * @return Expression representing a the Pi constant number
   */
  @Override
  public Expression visitPi(PiContext ctx) {
    return new RealNumber(Math.PI);
  }

  /**
   * visits the grammar rule
   * num_const: EULER
   * 
   * @return Expression representing a the 'e' constant number
   */
  @Override
  public Expression visitEulerNumber(EulerNumberContext ctx) {
    return new RealNumber(Math.E);
  }

  /**
   * visits the grammar rule
   * num_const: PHI
   * 
   * @return Expression representing a the Phi constant number
   */
  @Override
  public Expression visitPhi(PhiContext ctx) {
    return new RealNumber((1 + Math.sqrt(5)) / 2);
  }

  /**
   * create an operation using a list of args and the String representation of an
   * operation sign
   * 
   * @param sign Operation to apply
   * @param args List of arguments
   * @return Expression resoving from the operation and the arguments
   */
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

  /**
   * create an global operation using a list of operation signs and a list of args
   * 
   * @param sign List of operation to apply
   * @param args List of arguments
   * @return Expression resulting from the operations and the arguments
   */
  public Expression createGlobalOp(List<Expression> args, List<String> operands) {

    Expression operation = createOp(operands.get(0), args.subList(0, 2));
    for (int i = 1; i < operands.size(); i++) {
      ArrayList<Expression> temp = new ArrayList<>();
      temp.add(operation);
      temp.add(args.get(i + 1));
      operation = createOp(operands.get(i), temp);
    }
    return operation;

  }

}
