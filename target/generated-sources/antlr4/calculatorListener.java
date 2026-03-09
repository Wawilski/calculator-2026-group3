// Generated from calculator.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link calculatorParser}.
 */
public interface calculatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link calculatorParser#equation}.
	 * @param ctx the parse tree
	 */
	void enterEquation(calculatorParser.EquationContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#equation}.
	 * @param ctx the parse tree
	 */
	void exitEquation(calculatorParser.EquationContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#infix}.
	 * @param ctx the parse tree
	 */
	void enterInfix(calculatorParser.InfixContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#infix}.
	 * @param ctx the parse tree
	 */
	void exitInfix(calculatorParser.InfixContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#add_infix}.
	 * @param ctx the parse tree
	 */
	void enterAdd_infix(calculatorParser.Add_infixContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#add_infix}.
	 * @param ctx the parse tree
	 */
	void exitAdd_infix(calculatorParser.Add_infixContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#mul_infix}.
	 * @param ctx the parse tree
	 */
	void enterMul_infix(calculatorParser.Mul_infixContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#mul_infix}.
	 * @param ctx the parse tree
	 */
	void exitMul_infix(calculatorParser.Mul_infixContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#pow_infix}.
	 * @param ctx the parse tree
	 */
	void enterPow_infix(calculatorParser.Pow_infixContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#pow_infix}.
	 * @param ctx the parse tree
	 */
	void exitPow_infix(calculatorParser.Pow_infixContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(calculatorParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(calculatorParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#prefix}.
	 * @param ctx the parse tree
	 */
	void enterPrefix(calculatorParser.PrefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#prefix}.
	 * @param ctx the parse tree
	 */
	void exitPrefix(calculatorParser.PrefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#space_prefix}.
	 * @param ctx the parse tree
	 */
	void enterSpace_prefix(calculatorParser.Space_prefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#space_prefix}.
	 * @param ctx the parse tree
	 */
	void exitSpace_prefix(calculatorParser.Space_prefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#paren_prefix}.
	 * @param ctx the parse tree
	 */
	void enterParen_prefix(calculatorParser.Paren_prefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#paren_prefix}.
	 * @param ctx the parse tree
	 */
	void exitParen_prefix(calculatorParser.Paren_prefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#postfix}.
	 * @param ctx the parse tree
	 */
	void enterPostfix(calculatorParser.PostfixContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#postfix}.
	 * @param ctx the parse tree
	 */
	void exitPostfix(calculatorParser.PostfixContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#space_postfix}.
	 * @param ctx the parse tree
	 */
	void enterSpace_postfix(calculatorParser.Space_postfixContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#space_postfix}.
	 * @param ctx the parse tree
	 */
	void exitSpace_postfix(calculatorParser.Space_postfixContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#paren_postfix}.
	 * @param ctx the parse tree
	 */
	void enterParen_postfix(calculatorParser.Paren_postfixContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#paren_postfix}.
	 * @param ctx the parse tree
	 */
	void exitParen_postfix(calculatorParser.Paren_postfixContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(calculatorParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(calculatorParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(calculatorParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(calculatorParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#scientific}.
	 * @param ctx the parse tree
	 */
	void enterScientific(calculatorParser.ScientificContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#scientific}.
	 * @param ctx the parse tree
	 */
	void exitScientific(calculatorParser.ScientificContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#real}.
	 * @param ctx the parse tree
	 */
	void enterReal(calculatorParser.RealContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#real}.
	 * @param ctx the parse tree
	 */
	void exitReal(calculatorParser.RealContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#complex}.
	 * @param ctx the parse tree
	 */
	void enterComplex(calculatorParser.ComplexContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#complex}.
	 * @param ctx the parse tree
	 */
	void exitComplex(calculatorParser.ComplexContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#sign}.
	 * @param ctx the parse tree
	 */
	void enterSign(calculatorParser.SignContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#sign}.
	 * @param ctx the parse tree
	 */
	void exitSign(calculatorParser.SignContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#mul}.
	 * @param ctx the parse tree
	 */
	void enterMul(calculatorParser.MulContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#mul}.
	 * @param ctx the parse tree
	 */
	void exitMul(calculatorParser.MulContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#pow}.
	 * @param ctx the parse tree
	 */
	void enterPow(calculatorParser.PowContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#pow}.
	 * @param ctx the parse tree
	 */
	void exitPow(calculatorParser.PowContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(calculatorParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(calculatorParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#num_const}.
	 * @param ctx the parse tree
	 */
	void enterNum_const(calculatorParser.Num_constContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#num_const}.
	 * @param ctx the parse tree
	 */
	void exitNum_const(calculatorParser.Num_constContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#fct}.
	 * @param ctx the parse tree
	 */
	void enterFct(calculatorParser.FctContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#fct}.
	 * @param ctx the parse tree
	 */
	void exitFct(calculatorParser.FctContext ctx);
}