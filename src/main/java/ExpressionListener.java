import calculator.Expression;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import calculator.Notation;
import calculator.Operation;

/**
 * EquationListener
 */
public class ExpressionListener extends calculatorBaseListener {

  private List<Expression> params = new ArrayList<>();
  private Notation notation;
  private Expression current;

  public void setNotation(Notation notation) {
    this.notation = notation;
  }

  public void setCurrent(Expression current) {
    this.current = current;
  }

  public void setParams(List<Expression> params) {
    this.params = params;
  }

  @Override
  public void enterEquation(calculatorParser.EquationContext ctx) {
    System.out.println(ctx.children);
  }

  @Override
  public void exitEquation(calculatorParser.EquationContext ctx) {
  }

  @Override
  public void enterInfix(calculatorParser.InfixContext ctx) {
    this.notation = Notation.INFIX;
  }

  @Override
  public void exitInfix(calculatorParser.InfixContext ctx) {
  }

  @Override
  public void enterAdd_infix(calculatorParser.Add_infixContext ctx) {

  }

  @Override
  public void exitAdd_infix(calculatorParser.Add_infixContext ctx) {
  }

  @Override
  public void enterMul_infix(calculatorParser.Mul_infixContext ctx) {
  }

  @Override
  public void exitMul_infix(calculatorParser.Mul_infixContext ctx) {
  }

  @Override
  public void enterPow_infix(calculatorParser.Pow_infixContext ctx) {
  }

  @Override
  public void exitPow_infix(calculatorParser.Pow_infixContext ctx) {
  }

  @Override
  public void enterFactor(calculatorParser.FactorContext ctx) {
  }

  @Override
  public void exitFactor(calculatorParser.FactorContext ctx) {
  }

  @Override
  public void enterPrefix(calculatorParser.PrefixContext ctx) {
    this.notation = Notation.PREFIX;
  }

  @Override
  public void exitPrefix(calculatorParser.PrefixContext ctx) {
  }

  @Override
  public void enterSpace_prefix(calculatorParser.Space_prefixContext ctx) {
  }

  @Override
  public void exitSpace_prefix(calculatorParser.Space_prefixContext ctx) {
  }

  @Override
  public void enterParen_prefix(calculatorParser.Paren_prefixContext ctx) {
  }

  @Override
  public void exitParen_prefix(calculatorParser.Paren_prefixContext ctx) {
  }

  @Override
  public void enterPostfix(calculatorParser.PostfixContext ctx) {
    this.notation = Notation.POSTFIX;
  }

  @Override
  public void exitPostfix(calculatorParser.PostfixContext ctx) {
  }

  @Override
  public void enterSpace_postfix(calculatorParser.Space_postfixContext ctx) {
  }

  @Override
  public void exitSpace_postfix(calculatorParser.Space_postfixContext ctx) {
  }

  @Override
  public void enterParen_postfix(calculatorParser.Paren_postfixContext ctx) {
  }

  @Override
  public void exitParen_postfix(calculatorParser.Paren_postfixContext ctx) {
  }

  @Override
  public void enterAtom(calculatorParser.AtomContext ctx) {
  }

  @Override
  public void exitAtom(calculatorParser.AtomContext ctx) {
  }

  @Override
  public void enterNumber(calculatorParser.NumberContext ctx) {
  }

  @Override
  public void exitNumber(calculatorParser.NumberContext ctx) {
  }

  @Override
  public void enterScientific(calculatorParser.ScientificContext ctx) {
  }

  @Override
  public void exitScientific(calculatorParser.ScientificContext ctx) {
  }

  @Override
  public void enterReal(calculatorParser.RealContext ctx) {
  }

  @Override
  public void exitReal(calculatorParser.RealContext ctx) {
  }

  @Override
  public void enterComplex(calculatorParser.ComplexContext ctx) {
  }

  @Override
  public void exitComplex(calculatorParser.ComplexContext ctx) {
  }

  @Override
  public void enterSign(calculatorParser.SignContext ctx) {
  }

  @Override
  public void exitSign(calculatorParser.SignContext ctx) {
  }

  @Override
  public void enterMul(calculatorParser.MulContext ctx) {
  }

  @Override
  public void exitMul(calculatorParser.MulContext ctx) {
  }

  @Override
  public void enterPow(calculatorParser.PowContext ctx) {
  }

  @Override
  public void exitPow(calculatorParser.PowContext ctx) {
  }

  @Override
  public void enterOperator(calculatorParser.OperatorContext ctx) {
  }

  @Override
  public void exitOperator(calculatorParser.OperatorContext ctx) {
  }

  @Override
  public void enterNum_const(calculatorParser.Num_constContext ctx) {
  }

  @Override
  public void exitNum_const(calculatorParser.Num_constContext ctx) {
  }

  @Override
  public void enterFct(calculatorParser.FctContext ctx) {
  }

  @Override
  public void exitFct(calculatorParser.FctContext ctx) {
  }

  @Override
  public void enterEveryRule(ParserRuleContext ctx) {
    System.out.println(ctx.getText());
  }

  @Override
  public void exitEveryRule(ParserRuleContext ctx) {
    System.out.print(4);
    System.out.println(ctx.getClass());
  }

  @Override
  public void visitTerminal(TerminalNode node) {
  }

  @Override
  public void visitErrorNode(ErrorNode node) {
  }
}
