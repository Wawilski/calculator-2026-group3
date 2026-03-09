// Generated from calculator.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link calculatorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface calculatorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link calculatorParser#equation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquation(calculatorParser.EquationContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#infix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfix(calculatorParser.InfixContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#add_infix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd_infix(calculatorParser.Add_infixContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#mul_infix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul_infix(calculatorParser.Mul_infixContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#pow_infix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPow_infix(calculatorParser.Pow_infixContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(calculatorParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefix(calculatorParser.PrefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#space_prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpace_prefix(calculatorParser.Space_prefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#paren_prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen_prefix(calculatorParser.Paren_prefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#postfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfix(calculatorParser.PostfixContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#space_postfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpace_postfix(calculatorParser.Space_postfixContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#paren_postfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen_postfix(calculatorParser.Paren_postfixContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(calculatorParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(calculatorParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#scientific}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScientific(calculatorParser.ScientificContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#real}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReal(calculatorParser.RealContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#complex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplex(calculatorParser.ComplexContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSign(calculatorParser.SignContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#mul}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(calculatorParser.MulContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#pow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPow(calculatorParser.PowContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(calculatorParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#num_const}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNum_const(calculatorParser.Num_constContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#fct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFct(calculatorParser.FctContext ctx);
}