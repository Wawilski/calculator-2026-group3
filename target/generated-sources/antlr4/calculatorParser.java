// Generated from calculator.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class calculatorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		I=1, E=2, PI=3, EULER=4, PHI=5, COS=6, SIN=7, TAN=8, ACOS=9, ASIN=10, 
		ATAN=11, LN=12, LOG=13, SQRT=14, PLUS=15, MINUS=16, TIMES=17, DIV=18, 
		POW=19, INT=20, LPAREN=21, RPAREN=22, DOT=23, COMMAT=24, WS=25;
	public static final int
		RULE_equation = 0, RULE_infix = 1, RULE_add_infix = 2, RULE_mul_infix = 3, 
		RULE_pow_infix = 4, RULE_factor = 5, RULE_prefix = 6, RULE_space_prefix = 7, 
		RULE_paren_prefix = 8, RULE_postfix = 9, RULE_space_postfix = 10, RULE_paren_postfix = 11, 
		RULE_atom = 12, RULE_number = 13, RULE_scientific = 14, RULE_real = 15, 
		RULE_complex = 16, RULE_sign = 17, RULE_mul = 18, RULE_pow = 19, RULE_operator = 20, 
		RULE_num_const = 21, RULE_fct = 22;
	private static String[] makeRuleNames() {
		return new String[] {
			"equation", "infix", "add_infix", "mul_infix", "pow_infix", "factor", 
			"prefix", "space_prefix", "paren_prefix", "postfix", "space_postfix", 
			"paren_postfix", "atom", "number", "scientific", "real", "complex", "sign", 
			"mul", "pow", "operator", "num_const", "fct"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'i'", "'E'", "'pi'", "'e'", "'phi'", "'cos'", "'sin'", "'tan'", 
			"'acos'", "'asin'", "'atan'", "'ln'", "'log'", "'sqrt'", "'+'", "'-'", 
			"'*'", "'/'", "'**'", null, "'('", "')'", "'.'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "I", "E", "PI", "EULER", "PHI", "COS", "SIN", "TAN", "ACOS", "ASIN", 
			"ATAN", "LN", "LOG", "SQRT", "PLUS", "MINUS", "TIMES", "DIV", "POW", 
			"INT", "LPAREN", "RPAREN", "DOT", "COMMAT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "calculator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public calculatorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EquationContext extends ParserRuleContext {
		public PostfixContext postfix() {
			return getRuleContext(PostfixContext.class,0);
		}
		public InfixContext infix() {
			return getRuleContext(InfixContext.class,0);
		}
		public PrefixContext prefix() {
			return getRuleContext(PrefixContext.class,0);
		}
		public EquationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterEquation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitEquation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitEquation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EquationContext equation() throws RecognitionException {
		EquationContext _localctx = new EquationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_equation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(46);
				postfix();
				}
				break;
			case 2:
				{
				setState(47);
				infix();
				}
				break;
			case 3:
				{
				setState(48);
				prefix();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InfixContext extends ParserRuleContext {
		public Add_infixContext add_infix() {
			return getRuleContext(Add_infixContext.class,0);
		}
		public InfixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterInfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitInfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitInfix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfixContext infix() throws RecognitionException {
		InfixContext _localctx = new InfixContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_infix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			add_infix();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Add_infixContext extends ParserRuleContext {
		public List<Mul_infixContext> mul_infix() {
			return getRuleContexts(Mul_infixContext.class);
		}
		public Mul_infixContext mul_infix(int i) {
			return getRuleContext(Mul_infixContext.class,i);
		}
		public List<SignContext> sign() {
			return getRuleContexts(SignContext.class);
		}
		public SignContext sign(int i) {
			return getRuleContext(SignContext.class,i);
		}
		public Add_infixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_add_infix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterAdd_infix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitAdd_infix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitAdd_infix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Add_infixContext add_infix() throws RecognitionException {
		Add_infixContext _localctx = new Add_infixContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_add_infix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			mul_infix();
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(54);
				sign();
				setState(55);
				mul_infix();
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Mul_infixContext extends ParserRuleContext {
		public List<Pow_infixContext> pow_infix() {
			return getRuleContexts(Pow_infixContext.class);
		}
		public Pow_infixContext pow_infix(int i) {
			return getRuleContext(Pow_infixContext.class,i);
		}
		public List<MulContext> mul() {
			return getRuleContexts(MulContext.class);
		}
		public MulContext mul(int i) {
			return getRuleContext(MulContext.class,i);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public List<TerminalNode> LPAREN() { return getTokens(calculatorParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(calculatorParser.LPAREN, i);
		}
		public List<InfixContext> infix() {
			return getRuleContexts(InfixContext.class);
		}
		public InfixContext infix(int i) {
			return getRuleContext(InfixContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(calculatorParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(calculatorParser.RPAREN, i);
		}
		public Mul_infixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mul_infix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterMul_infix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitMul_infix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitMul_infix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Mul_infixContext mul_infix() throws RecognitionException {
		Mul_infixContext _localctx = new Mul_infixContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_mul_infix);
		int _la;
		try {
			setState(90);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				pow_infix();
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==TIMES || _la==DIV) {
					{
					{
					setState(63);
					mul();
					setState(64);
					pow_infix();
					}
					}
					setState(70);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LPAREN) {
					{
					{
					setState(71);
					match(LPAREN);
					setState(72);
					infix();
					setState(73);
					match(RPAREN);
					}
					}
					setState(79);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(80);
				atom();
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LPAREN) {
					{
					{
					setState(81);
					match(LPAREN);
					setState(82);
					infix();
					setState(83);
					match(RPAREN);
					}
					}
					setState(89);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Pow_infixContext extends ParserRuleContext {
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<PowContext> pow() {
			return getRuleContexts(PowContext.class);
		}
		public PowContext pow(int i) {
			return getRuleContext(PowContext.class,i);
		}
		public Pow_infixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pow_infix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterPow_infix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitPow_infix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitPow_infix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pow_infixContext pow_infix() throws RecognitionException {
		Pow_infixContext _localctx = new Pow_infixContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_pow_infix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			factor();
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==POW) {
				{
				{
				setState(93);
				pow();
				setState(94);
				factor();
				}
				}
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FactorContext extends ParserRuleContext {
		public FctContext fct() {
			return getRuleContext(FctContext.class,0);
		}
		public List<TerminalNode> LPAREN() { return getTokens(calculatorParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(calculatorParser.LPAREN, i);
		}
		public List<InfixContext> infix() {
			return getRuleContexts(InfixContext.class);
		}
		public InfixContext infix(int i) {
			return getRuleContext(InfixContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(calculatorParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(calculatorParser.RPAREN, i);
		}
		public List<TerminalNode> COMMAT() { return getTokens(calculatorParser.COMMAT); }
		public TerminalNode COMMAT(int i) {
			return getToken(calculatorParser.COMMAT, i);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public SignContext sign() {
			return getRuleContext(SignContext.class,0);
		}
		public List<Num_constContext> num_const() {
			return getRuleContexts(Num_constContext.class);
		}
		public Num_constContext num_const(int i) {
			return getRuleContext(Num_constContext.class,i);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_factor);
		int _la;
		try {
			setState(135);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COS:
			case SIN:
			case TAN:
			case ACOS:
			case ASIN:
			case ATAN:
			case LN:
			case LOG:
			case SQRT:
				enterOuterAlt(_localctx, 1);
				{
				setState(101);
				fct();
				setState(102);
				match(LPAREN);
				setState(103);
				infix();
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMAT) {
					{
					{
					setState(104);
					match(COMMAT);
					setState(105);
					infix();
					}
					}
					setState(110);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(111);
				match(RPAREN);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				match(LPAREN);
				setState(114);
				infix();
				setState(115);
				match(RPAREN);
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LPAREN) {
					{
					{
					setState(116);
					match(LPAREN);
					setState(117);
					infix();
					setState(118);
					match(RPAREN);
					}
					}
					setState(124);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case I:
			case PI:
			case EULER:
			case PHI:
			case PLUS:
			case MINUS:
			case INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(125);
					sign();
					}
				}

				setState(128);
				atom();
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0)) {
					{
					{
					setState(129);
					num_const();
					}
					}
					setState(134);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrefixContext extends ParserRuleContext {
		public Space_prefixContext space_prefix() {
			return getRuleContext(Space_prefixContext.class,0);
		}
		public Paren_prefixContext paren_prefix() {
			return getRuleContext(Paren_prefixContext.class,0);
		}
		public PrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitPrefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixContext prefix() throws RecognitionException {
		PrefixContext _localctx = new PrefixContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_prefix);
		try {
			setState(139);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				space_prefix();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				paren_prefix();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Space_prefixContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(calculatorParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(calculatorParser.RPAREN, 0); }
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public FctContext fct() {
			return getRuleContext(FctContext.class,0);
		}
		public List<Space_prefixContext> space_prefix() {
			return getRuleContexts(Space_prefixContext.class);
		}
		public Space_prefixContext space_prefix(int i) {
			return getRuleContext(Space_prefixContext.class,i);
		}
		public SignContext sign() {
			return getRuleContext(SignContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public Space_prefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_space_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterSpace_prefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitSpace_prefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitSpace_prefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Space_prefixContext space_prefix() throws RecognitionException {
		Space_prefixContext _localctx = new Space_prefixContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_space_prefix);
		int _la;
		try {
			setState(166);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(143);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PLUS:
				case MINUS:
				case TIMES:
				case DIV:
				case POW:
					{
					setState(141);
					operator();
					}
					break;
				case COS:
				case SIN:
				case TAN:
				case ACOS:
				case ASIN:
				case ATAN:
				case LN:
				case LOG:
				case SQRT:
					{
					setState(142);
					fct();
					}
					break;
				case LPAREN:
					break;
				default:
					break;
				}
				setState(145);
				match(LPAREN);
				setState(147); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(146);
					space_prefix();
					}
					}
					setState(149); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 4194298L) != 0) );
				setState(151);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				fct();
				setState(154);
				space_prefix();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(156);
				operator();
				setState(157);
				space_prefix();
				setState(158);
				space_prefix();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(160);
				match(LPAREN);
				setState(161);
				sign();
				setState(162);
				atom();
				setState(163);
				match(RPAREN);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(165);
				atom();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Paren_prefixContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(calculatorParser.LPAREN, 0); }
		public List<Paren_prefixContext> paren_prefix() {
			return getRuleContexts(Paren_prefixContext.class);
		}
		public Paren_prefixContext paren_prefix(int i) {
			return getRuleContext(Paren_prefixContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(calculatorParser.RPAREN, 0); }
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public FctContext fct() {
			return getRuleContext(FctContext.class,0);
		}
		public List<TerminalNode> COMMAT() { return getTokens(calculatorParser.COMMAT); }
		public TerminalNode COMMAT(int i) {
			return getToken(calculatorParser.COMMAT, i);
		}
		public SignContext sign() {
			return getRuleContext(SignContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public Paren_prefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paren_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterParen_prefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitParen_prefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitParen_prefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Paren_prefixContext paren_prefix() throws RecognitionException {
		Paren_prefixContext _localctx = new Paren_prefixContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_paren_prefix);
		int _la;
		try {
			setState(192);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(170);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PLUS:
				case MINUS:
				case TIMES:
				case DIV:
				case POW:
					{
					setState(168);
					operator();
					}
					break;
				case COS:
				case SIN:
				case TAN:
				case ACOS:
				case ASIN:
				case ATAN:
				case LN:
				case LOG:
				case SQRT:
					{
					setState(169);
					fct();
					}
					break;
				case LPAREN:
					break;
				default:
					break;
				}
				setState(172);
				match(LPAREN);
				setState(173);
				paren_prefix();
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMAT) {
					{
					{
					setState(174);
					match(COMMAT);
					setState(175);
					paren_prefix();
					}
					}
					setState(180);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(181);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				fct();
				setState(184);
				paren_prefix();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(186);
				match(LPAREN);
				setState(187);
				sign();
				setState(188);
				atom();
				setState(189);
				match(RPAREN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(191);
				atom();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PostfixContext extends ParserRuleContext {
		public Space_postfixContext space_postfix() {
			return getRuleContext(Space_postfixContext.class,0);
		}
		public Paren_postfixContext paren_postfix() {
			return getRuleContext(Paren_postfixContext.class,0);
		}
		public PostfixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterPostfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitPostfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitPostfix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixContext postfix() throws RecognitionException {
		PostfixContext _localctx = new PostfixContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_postfix);
		try {
			setState(196);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(194);
				space_postfix(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(195);
				paren_postfix(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Space_postfixContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(calculatorParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(calculatorParser.RPAREN, 0); }
		public List<Space_postfixContext> space_postfix() {
			return getRuleContexts(Space_postfixContext.class);
		}
		public Space_postfixContext space_postfix(int i) {
			return getRuleContext(Space_postfixContext.class,i);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public FctContext fct() {
			return getRuleContext(FctContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public SignContext sign() {
			return getRuleContext(SignContext.class,0);
		}
		public Space_postfixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_space_postfix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterSpace_postfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitSpace_postfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitSpace_postfix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Space_postfixContext space_postfix() throws RecognitionException {
		return space_postfix(0);
	}

	private Space_postfixContext space_postfix(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Space_postfixContext _localctx = new Space_postfixContext(_ctx, _parentState);
		Space_postfixContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_space_postfix, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(199);
				match(LPAREN);
				setState(201); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(200);
					space_postfix(0);
					}
					}
					setState(203); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 3145786L) != 0) );
				setState(205);
				match(RPAREN);
				setState(208);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(206);
					operator();
					}
					break;
				case 2:
					{
					setState(207);
					fct();
					}
					break;
				}
				}
				break;
			case 2:
				{
				setState(210);
				match(LPAREN);
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(211);
					sign();
					}
				}

				setState(214);
				atom();
				setState(215);
				match(RPAREN);
				}
				break;
			case 3:
				{
				setState(217);
				atom();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(228);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(226);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new Space_postfixContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_space_postfix);
						setState(220);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(221);
						fct();
						}
						break;
					case 2:
						{
						_localctx = new Space_postfixContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_space_postfix);
						setState(222);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(223);
						space_postfix(0);
						setState(224);
						operator();
						}
						break;
					}
					} 
				}
				setState(230);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Paren_postfixContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(calculatorParser.LPAREN, 0); }
		public List<Paren_postfixContext> paren_postfix() {
			return getRuleContexts(Paren_postfixContext.class);
		}
		public Paren_postfixContext paren_postfix(int i) {
			return getRuleContext(Paren_postfixContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(calculatorParser.RPAREN, 0); }
		public List<TerminalNode> COMMAT() { return getTokens(calculatorParser.COMMAT); }
		public TerminalNode COMMAT(int i) {
			return getToken(calculatorParser.COMMAT, i);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public FctContext fct() {
			return getRuleContext(FctContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public SignContext sign() {
			return getRuleContext(SignContext.class,0);
		}
		public Paren_postfixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paren_postfix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterParen_postfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitParen_postfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitParen_postfix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Paren_postfixContext paren_postfix() throws RecognitionException {
		return paren_postfix(0);
	}

	private Paren_postfixContext paren_postfix(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Paren_postfixContext _localctx = new Paren_postfixContext(_ctx, _parentState);
		Paren_postfixContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_paren_postfix, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(232);
				match(LPAREN);
				setState(233);
				paren_postfix(0);
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMAT) {
					{
					{
					setState(234);
					match(COMMAT);
					setState(235);
					paren_postfix(0);
					}
					}
					setState(240);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(241);
				match(RPAREN);
				setState(244);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(242);
					operator();
					}
					break;
				case 2:
					{
					setState(243);
					fct();
					}
					break;
				}
				}
				break;
			case 2:
				{
				setState(246);
				match(LPAREN);
				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(247);
					sign();
					}
				}

				setState(250);
				atom();
				setState(251);
				match(RPAREN);
				}
				break;
			case 3:
				{
				setState(253);
				atom();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(260);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Paren_postfixContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_paren_postfix);
					setState(256);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(257);
					fct();
					}
					} 
				}
				setState(262);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ComplexContext complex() {
			return getRuleContext(ComplexContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_atom);
		try {
			setState(265);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(263);
				number();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(264);
				complex();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public RealContext real() {
			return getRuleContext(RealContext.class,0);
		}
		public ScientificContext scientific() {
			return getRuleContext(ScientificContext.class,0);
		}
		public Num_constContext num_const() {
			return getRuleContext(Num_constContext.class,0);
		}
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_number);
		try {
			setState(270);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(267);
				real();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(268);
				scientific();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(269);
				num_const();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScientificContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(calculatorParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(calculatorParser.INT, i);
		}
		public TerminalNode E() { return getToken(calculatorParser.E, 0); }
		public SignContext sign() {
			return getRuleContext(SignContext.class,0);
		}
		public ScientificContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scientific; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterScientific(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitScientific(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitScientific(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScientificContext scientific() throws RecognitionException {
		ScientificContext _localctx = new ScientificContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_scientific);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(INT);
			setState(273);
			match(E);
			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(274);
				sign();
				}
			}

			setState(277);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RealContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(calculatorParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(calculatorParser.INT, i);
		}
		public TerminalNode DOT() { return getToken(calculatorParser.DOT, 0); }
		public RealContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_real; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterReal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitReal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitReal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RealContext real() throws RecognitionException {
		RealContext _localctx = new RealContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_real);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			match(INT);
			setState(282);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(280);
				match(DOT);
				setState(281);
				match(INT);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComplexContext extends ParserRuleContext {
		public TerminalNode I() { return getToken(calculatorParser.I, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ComplexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterComplex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitComplex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitComplex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComplexContext complex() throws RecognitionException {
		ComplexContext _localctx = new ComplexContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_complex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1048632L) != 0)) {
				{
				setState(284);
				number();
				}
			}

			setState(287);
			match(I);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SignContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(calculatorParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(calculatorParser.MINUS, 0); }
		public SignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterSign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitSign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitSign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SignContext sign() throws RecognitionException {
		SignContext _localctx = new SignContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_sign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MulContext extends ParserRuleContext {
		public TerminalNode TIMES() { return getToken(calculatorParser.TIMES, 0); }
		public TerminalNode DIV() { return getToken(calculatorParser.DIV, 0); }
		public MulContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mul; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterMul(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitMul(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitMul(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulContext mul() throws RecognitionException {
		MulContext _localctx = new MulContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_mul);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			_la = _input.LA(1);
			if ( !(_la==TIMES || _la==DIV) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PowContext extends ParserRuleContext {
		public TerminalNode POW() { return getToken(calculatorParser.POW, 0); }
		public PowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterPow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitPow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitPow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PowContext pow() throws RecognitionException {
		PowContext _localctx = new PowContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_pow);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(POW);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OperatorContext extends ParserRuleContext {
		public SignContext sign() {
			return getRuleContext(SignContext.class,0);
		}
		public MulContext mul() {
			return getRuleContext(MulContext.class,0);
		}
		public PowContext pow() {
			return getRuleContext(PowContext.class,0);
		}
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_operator);
		try {
			setState(298);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(295);
				sign();
				}
				break;
			case TIMES:
			case DIV:
				enterOuterAlt(_localctx, 2);
				{
				setState(296);
				mul();
				}
				break;
			case POW:
				enterOuterAlt(_localctx, 3);
				{
				setState(297);
				pow();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Num_constContext extends ParserRuleContext {
		public TerminalNode PI() { return getToken(calculatorParser.PI, 0); }
		public TerminalNode EULER() { return getToken(calculatorParser.EULER, 0); }
		public TerminalNode PHI() { return getToken(calculatorParser.PHI, 0); }
		public Num_constContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_num_const; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterNum_const(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitNum_const(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitNum_const(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Num_constContext num_const() throws RecognitionException {
		Num_constContext _localctx = new Num_constContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_num_const);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FctContext extends ParserRuleContext {
		public TerminalNode COS() { return getToken(calculatorParser.COS, 0); }
		public TerminalNode TAN() { return getToken(calculatorParser.TAN, 0); }
		public TerminalNode SIN() { return getToken(calculatorParser.SIN, 0); }
		public TerminalNode ACOS() { return getToken(calculatorParser.ACOS, 0); }
		public TerminalNode ATAN() { return getToken(calculatorParser.ATAN, 0); }
		public TerminalNode ASIN() { return getToken(calculatorParser.ASIN, 0); }
		public TerminalNode LN() { return getToken(calculatorParser.LN, 0); }
		public TerminalNode SQRT() { return getToken(calculatorParser.SQRT, 0); }
		public TerminalNode LOG() { return getToken(calculatorParser.LOG, 0); }
		public FctContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterFct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitFct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof calculatorVisitor ) return ((calculatorVisitor<? extends T>)visitor).visitFct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FctContext fct() throws RecognitionException {
		FctContext _localctx = new FctContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_fct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 32704L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 10:
			return space_postfix_sempred((Space_postfixContext)_localctx, predIndex);
		case 11:
			return paren_postfix_sempred((Paren_postfixContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean space_postfix_sempred(Space_postfixContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean paren_postfix_sempred(Paren_postfixContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0019\u0131\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0001\u0000\u0001\u0000\u0001\u0000\u0003"+
		"\u00002\b\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0005\u0002:\b\u0002\n\u0002\f\u0002=\t\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003C\b\u0003\n\u0003"+
		"\f\u0003F\t\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005"+
		"\u0003L\b\u0003\n\u0003\f\u0003O\t\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0005\u0003V\b\u0003\n\u0003\f\u0003Y\t"+
		"\u0003\u0003\u0003[\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0005\u0004a\b\u0004\n\u0004\f\u0004d\t\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005k\b\u0005\n\u0005"+
		"\f\u0005n\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005y\b"+
		"\u0005\n\u0005\f\u0005|\t\u0005\u0001\u0005\u0003\u0005\u007f\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0005\u0005\u0083\b\u0005\n\u0005\f\u0005\u0086"+
		"\t\u0005\u0003\u0005\u0088\b\u0005\u0001\u0006\u0001\u0006\u0003\u0006"+
		"\u008c\b\u0006\u0001\u0007\u0001\u0007\u0003\u0007\u0090\b\u0007\u0001"+
		"\u0007\u0001\u0007\u0004\u0007\u0094\b\u0007\u000b\u0007\f\u0007\u0095"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00a7\b\u0007\u0001\b"+
		"\u0001\b\u0003\b\u00ab\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005\b\u00b1"+
		"\b\b\n\b\f\b\u00b4\t\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u00c1\b\b\u0001\t\u0001"+
		"\t\u0003\t\u00c5\b\t\u0001\n\u0001\n\u0001\n\u0004\n\u00ca\b\n\u000b\n"+
		"\f\n\u00cb\u0001\n\u0001\n\u0001\n\u0003\n\u00d1\b\n\u0001\n\u0001\n\u0003"+
		"\n\u00d5\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00db\b\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n\u00e3\b\n\n\n\f\n\u00e6"+
		"\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005"+
		"\u000b\u00ed\b\u000b\n\u000b\f\u000b\u00f0\t\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u00f5\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u00f9\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u00ff\b\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u0103\b\u000b\n\u000b"+
		"\f\u000b\u0106\t\u000b\u0001\f\u0001\f\u0003\f\u010a\b\f\u0001\r\u0001"+
		"\r\u0001\r\u0003\r\u010f\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0003"+
		"\u000e\u0114\b\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0003\u000f\u011b\b\u000f\u0001\u0010\u0003\u0010\u011e\b\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014"+
		"\u012b\b\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0000\u0002\u0014\u0016\u0017\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,\u0000\u0004\u0001\u0000"+
		"\u000f\u0010\u0001\u0000\u0011\u0012\u0001\u0000\u0003\u0005\u0001\u0000"+
		"\u0006\u000e\u014d\u00001\u0001\u0000\u0000\u0000\u00023\u0001\u0000\u0000"+
		"\u0000\u00045\u0001\u0000\u0000\u0000\u0006Z\u0001\u0000\u0000\u0000\b"+
		"\\\u0001\u0000\u0000\u0000\n\u0087\u0001\u0000\u0000\u0000\f\u008b\u0001"+
		"\u0000\u0000\u0000\u000e\u00a6\u0001\u0000\u0000\u0000\u0010\u00c0\u0001"+
		"\u0000\u0000\u0000\u0012\u00c4\u0001\u0000\u0000\u0000\u0014\u00da\u0001"+
		"\u0000\u0000\u0000\u0016\u00fe\u0001\u0000\u0000\u0000\u0018\u0109\u0001"+
		"\u0000\u0000\u0000\u001a\u010e\u0001\u0000\u0000\u0000\u001c\u0110\u0001"+
		"\u0000\u0000\u0000\u001e\u0117\u0001\u0000\u0000\u0000 \u011d\u0001\u0000"+
		"\u0000\u0000\"\u0121\u0001\u0000\u0000\u0000$\u0123\u0001\u0000\u0000"+
		"\u0000&\u0125\u0001\u0000\u0000\u0000(\u012a\u0001\u0000\u0000\u0000*"+
		"\u012c\u0001\u0000\u0000\u0000,\u012e\u0001\u0000\u0000\u0000.2\u0003"+
		"\u0012\t\u0000/2\u0003\u0002\u0001\u000002\u0003\f\u0006\u00001.\u0001"+
		"\u0000\u0000\u00001/\u0001\u0000\u0000\u000010\u0001\u0000\u0000\u0000"+
		"2\u0001\u0001\u0000\u0000\u000034\u0003\u0004\u0002\u00004\u0003\u0001"+
		"\u0000\u0000\u00005;\u0003\u0006\u0003\u000067\u0003\"\u0011\u000078\u0003"+
		"\u0006\u0003\u00008:\u0001\u0000\u0000\u000096\u0001\u0000\u0000\u0000"+
		":=\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000"+
		"\u0000<\u0005\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000>D\u0003"+
		"\b\u0004\u0000?@\u0003$\u0012\u0000@A\u0003\b\u0004\u0000AC\u0001\u0000"+
		"\u0000\u0000B?\u0001\u0000\u0000\u0000CF\u0001\u0000\u0000\u0000DB\u0001"+
		"\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000E[\u0001\u0000\u0000\u0000"+
		"FD\u0001\u0000\u0000\u0000GH\u0005\u0015\u0000\u0000HI\u0003\u0002\u0001"+
		"\u0000IJ\u0005\u0016\u0000\u0000JL\u0001\u0000\u0000\u0000KG\u0001\u0000"+
		"\u0000\u0000LO\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000MN\u0001"+
		"\u0000\u0000\u0000NP\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000"+
		"PW\u0003\u0018\f\u0000QR\u0005\u0015\u0000\u0000RS\u0003\u0002\u0001\u0000"+
		"ST\u0005\u0016\u0000\u0000TV\u0001\u0000\u0000\u0000UQ\u0001\u0000\u0000"+
		"\u0000VY\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000WX\u0001\u0000"+
		"\u0000\u0000X[\u0001\u0000\u0000\u0000YW\u0001\u0000\u0000\u0000Z>\u0001"+
		"\u0000\u0000\u0000ZM\u0001\u0000\u0000\u0000[\u0007\u0001\u0000\u0000"+
		"\u0000\\b\u0003\n\u0005\u0000]^\u0003&\u0013\u0000^_\u0003\n\u0005\u0000"+
		"_a\u0001\u0000\u0000\u0000`]\u0001\u0000\u0000\u0000ad\u0001\u0000\u0000"+
		"\u0000b`\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000c\t\u0001\u0000"+
		"\u0000\u0000db\u0001\u0000\u0000\u0000ef\u0003,\u0016\u0000fg\u0005\u0015"+
		"\u0000\u0000gl\u0003\u0002\u0001\u0000hi\u0005\u0018\u0000\u0000ik\u0003"+
		"\u0002\u0001\u0000jh\u0001\u0000\u0000\u0000kn\u0001\u0000\u0000\u0000"+
		"lj\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mo\u0001\u0000\u0000"+
		"\u0000nl\u0001\u0000\u0000\u0000op\u0005\u0016\u0000\u0000p\u0088\u0001"+
		"\u0000\u0000\u0000qr\u0005\u0015\u0000\u0000rs\u0003\u0002\u0001\u0000"+
		"sz\u0005\u0016\u0000\u0000tu\u0005\u0015\u0000\u0000uv\u0003\u0002\u0001"+
		"\u0000vw\u0005\u0016\u0000\u0000wy\u0001\u0000\u0000\u0000xt\u0001\u0000"+
		"\u0000\u0000y|\u0001\u0000\u0000\u0000zx\u0001\u0000\u0000\u0000z{\u0001"+
		"\u0000\u0000\u0000{\u0088\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000"+
		"\u0000}\u007f\u0003\"\u0011\u0000~}\u0001\u0000\u0000\u0000~\u007f\u0001"+
		"\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0084\u0003"+
		"\u0018\f\u0000\u0081\u0083\u0003*\u0015\u0000\u0082\u0081\u0001\u0000"+
		"\u0000\u0000\u0083\u0086\u0001\u0000\u0000\u0000\u0084\u0082\u0001\u0000"+
		"\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0088\u0001\u0000"+
		"\u0000\u0000\u0086\u0084\u0001\u0000\u0000\u0000\u0087e\u0001\u0000\u0000"+
		"\u0000\u0087q\u0001\u0000\u0000\u0000\u0087~\u0001\u0000\u0000\u0000\u0088"+
		"\u000b\u0001\u0000\u0000\u0000\u0089\u008c\u0003\u000e\u0007\u0000\u008a"+
		"\u008c\u0003\u0010\b\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008b\u008a"+
		"\u0001\u0000\u0000\u0000\u008c\r\u0001\u0000\u0000\u0000\u008d\u0090\u0003"+
		"(\u0014\u0000\u008e\u0090\u0003,\u0016\u0000\u008f\u008d\u0001\u0000\u0000"+
		"\u0000\u008f\u008e\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000"+
		"\u0000\u0090\u0091\u0001\u0000\u0000\u0000\u0091\u0093\u0005\u0015\u0000"+
		"\u0000\u0092\u0094\u0003\u000e\u0007\u0000\u0093\u0092\u0001\u0000\u0000"+
		"\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u0093\u0001\u0000\u0000"+
		"\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000"+
		"\u0000\u0097\u0098\u0005\u0016\u0000\u0000\u0098\u00a7\u0001\u0000\u0000"+
		"\u0000\u0099\u009a\u0003,\u0016\u0000\u009a\u009b\u0003\u000e\u0007\u0000"+
		"\u009b\u00a7\u0001\u0000\u0000\u0000\u009c\u009d\u0003(\u0014\u0000\u009d"+
		"\u009e\u0003\u000e\u0007\u0000\u009e\u009f\u0003\u000e\u0007\u0000\u009f"+
		"\u00a7\u0001\u0000\u0000\u0000\u00a0\u00a1\u0005\u0015\u0000\u0000\u00a1"+
		"\u00a2\u0003\"\u0011\u0000\u00a2\u00a3\u0003\u0018\f\u0000\u00a3\u00a4"+
		"\u0005\u0016\u0000\u0000\u00a4\u00a7\u0001\u0000\u0000\u0000\u00a5\u00a7"+
		"\u0003\u0018\f\u0000\u00a6\u008f\u0001\u0000\u0000\u0000\u00a6\u0099\u0001"+
		"\u0000\u0000\u0000\u00a6\u009c\u0001\u0000\u0000\u0000\u00a6\u00a0\u0001"+
		"\u0000\u0000\u0000\u00a6\u00a5\u0001\u0000\u0000\u0000\u00a7\u000f\u0001"+
		"\u0000\u0000\u0000\u00a8\u00ab\u0003(\u0014\u0000\u00a9\u00ab\u0003,\u0016"+
		"\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00aa\u00a9\u0001\u0000\u0000"+
		"\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000"+
		"\u0000\u00ac\u00ad\u0005\u0015\u0000\u0000\u00ad\u00b2\u0003\u0010\b\u0000"+
		"\u00ae\u00af\u0005\u0018\u0000\u0000\u00af\u00b1\u0003\u0010\b\u0000\u00b0"+
		"\u00ae\u0001\u0000\u0000\u0000\u00b1\u00b4\u0001\u0000\u0000\u0000\u00b2"+
		"\u00b0\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3"+
		"\u00b5\u0001\u0000\u0000\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b5"+
		"\u00b6\u0005\u0016\u0000\u0000\u00b6\u00c1\u0001\u0000\u0000\u0000\u00b7"+
		"\u00b8\u0003,\u0016\u0000\u00b8\u00b9\u0003\u0010\b\u0000\u00b9\u00c1"+
		"\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005\u0015\u0000\u0000\u00bb\u00bc"+
		"\u0003\"\u0011\u0000\u00bc\u00bd\u0003\u0018\f\u0000\u00bd\u00be\u0005"+
		"\u0016\u0000\u0000\u00be\u00c1\u0001\u0000\u0000\u0000\u00bf\u00c1\u0003"+
		"\u0018\f\u0000\u00c0\u00aa\u0001\u0000\u0000\u0000\u00c0\u00b7\u0001\u0000"+
		"\u0000\u0000\u00c0\u00ba\u0001\u0000\u0000\u0000\u00c0\u00bf\u0001\u0000"+
		"\u0000\u0000\u00c1\u0011\u0001\u0000\u0000\u0000\u00c2\u00c5\u0003\u0014"+
		"\n\u0000\u00c3\u00c5\u0003\u0016\u000b\u0000\u00c4\u00c2\u0001\u0000\u0000"+
		"\u0000\u00c4\u00c3\u0001\u0000\u0000\u0000\u00c5\u0013\u0001\u0000\u0000"+
		"\u0000\u00c6\u00c7\u0006\n\uffff\uffff\u0000\u00c7\u00c9\u0005\u0015\u0000"+
		"\u0000\u00c8\u00ca\u0003\u0014\n\u0000\u00c9\u00c8\u0001\u0000\u0000\u0000"+
		"\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb\u00c9\u0001\u0000\u0000\u0000"+
		"\u00cb\u00cc\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000"+
		"\u00cd\u00d0\u0005\u0016\u0000\u0000\u00ce\u00d1\u0003(\u0014\u0000\u00cf"+
		"\u00d1\u0003,\u0016\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000\u00d0\u00cf"+
		"\u0001\u0000\u0000\u0000\u00d0\u00d1\u0001\u0000\u0000\u0000\u00d1\u00db"+
		"\u0001\u0000\u0000\u0000\u00d2\u00d4\u0005\u0015\u0000\u0000\u00d3\u00d5"+
		"\u0003\"\u0011\u0000\u00d4\u00d3\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001"+
		"\u0000\u0000\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u00d7\u0003"+
		"\u0018\f\u0000\u00d7\u00d8\u0005\u0016\u0000\u0000\u00d8\u00db\u0001\u0000"+
		"\u0000\u0000\u00d9\u00db\u0003\u0018\f\u0000\u00da\u00c6\u0001\u0000\u0000"+
		"\u0000\u00da\u00d2\u0001\u0000\u0000\u0000\u00da\u00d9\u0001\u0000\u0000"+
		"\u0000\u00db\u00e4\u0001\u0000\u0000\u0000\u00dc\u00dd\n\u0004\u0000\u0000"+
		"\u00dd\u00e3\u0003,\u0016\u0000\u00de\u00df\n\u0003\u0000\u0000\u00df"+
		"\u00e0\u0003\u0014\n\u0000\u00e0\u00e1\u0003(\u0014\u0000\u00e1\u00e3"+
		"\u0001\u0000\u0000\u0000\u00e2\u00dc\u0001\u0000\u0000\u0000\u00e2\u00de"+
		"\u0001\u0000\u0000\u0000\u00e3\u00e6\u0001\u0000\u0000\u0000\u00e4\u00e2"+
		"\u0001\u0000\u0000\u0000\u00e4\u00e5\u0001\u0000\u0000\u0000\u00e5\u0015"+
		"\u0001\u0000\u0000\u0000\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e7\u00e8"+
		"\u0006\u000b\uffff\uffff\u0000\u00e8\u00e9\u0005\u0015\u0000\u0000\u00e9"+
		"\u00ee\u0003\u0016\u000b\u0000\u00ea\u00eb\u0005\u0018\u0000\u0000\u00eb"+
		"\u00ed\u0003\u0016\u000b\u0000\u00ec\u00ea\u0001\u0000\u0000\u0000\u00ed"+
		"\u00f0\u0001\u0000\u0000\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ee"+
		"\u00ef\u0001\u0000\u0000\u0000\u00ef\u00f1\u0001\u0000\u0000\u0000\u00f0"+
		"\u00ee\u0001\u0000\u0000\u0000\u00f1\u00f4\u0005\u0016\u0000\u0000\u00f2"+
		"\u00f5\u0003(\u0014\u0000\u00f3\u00f5\u0003,\u0016\u0000\u00f4\u00f2\u0001"+
		"\u0000\u0000\u0000\u00f4\u00f3\u0001\u0000\u0000\u0000\u00f4\u00f5\u0001"+
		"\u0000\u0000\u0000\u00f5\u00ff\u0001\u0000\u0000\u0000\u00f6\u00f8\u0005"+
		"\u0015\u0000\u0000\u00f7\u00f9\u0003\"\u0011\u0000\u00f8\u00f7\u0001\u0000"+
		"\u0000\u0000\u00f8\u00f9\u0001\u0000\u0000\u0000\u00f9\u00fa\u0001\u0000"+
		"\u0000\u0000\u00fa\u00fb\u0003\u0018\f\u0000\u00fb\u00fc\u0005\u0016\u0000"+
		"\u0000\u00fc\u00ff\u0001\u0000\u0000\u0000\u00fd\u00ff\u0003\u0018\f\u0000"+
		"\u00fe\u00e7\u0001\u0000\u0000\u0000\u00fe\u00f6\u0001\u0000\u0000\u0000"+
		"\u00fe\u00fd\u0001\u0000\u0000\u0000\u00ff\u0104\u0001\u0000\u0000\u0000"+
		"\u0100\u0101\n\u0003\u0000\u0000\u0101\u0103\u0003,\u0016\u0000\u0102"+
		"\u0100\u0001\u0000\u0000\u0000\u0103\u0106\u0001\u0000\u0000\u0000\u0104"+
		"\u0102\u0001\u0000\u0000\u0000\u0104\u0105\u0001\u0000\u0000\u0000\u0105"+
		"\u0017\u0001\u0000\u0000\u0000\u0106\u0104\u0001\u0000\u0000\u0000\u0107"+
		"\u010a\u0003\u001a\r\u0000\u0108\u010a\u0003 \u0010\u0000\u0109\u0107"+
		"\u0001\u0000\u0000\u0000\u0109\u0108\u0001\u0000\u0000\u0000\u010a\u0019"+
		"\u0001\u0000\u0000\u0000\u010b\u010f\u0003\u001e\u000f\u0000\u010c\u010f"+
		"\u0003\u001c\u000e\u0000\u010d\u010f\u0003*\u0015\u0000\u010e\u010b\u0001"+
		"\u0000\u0000\u0000\u010e\u010c\u0001\u0000\u0000\u0000\u010e\u010d\u0001"+
		"\u0000\u0000\u0000\u010f\u001b\u0001\u0000\u0000\u0000\u0110\u0111\u0005"+
		"\u0014\u0000\u0000\u0111\u0113\u0005\u0002\u0000\u0000\u0112\u0114\u0003"+
		"\"\u0011\u0000\u0113\u0112\u0001\u0000\u0000\u0000\u0113\u0114\u0001\u0000"+
		"\u0000\u0000\u0114\u0115\u0001\u0000\u0000\u0000\u0115\u0116\u0005\u0014"+
		"\u0000\u0000\u0116\u001d\u0001\u0000\u0000\u0000\u0117\u011a\u0005\u0014"+
		"\u0000\u0000\u0118\u0119\u0005\u0017\u0000\u0000\u0119\u011b\u0005\u0014"+
		"\u0000\u0000\u011a\u0118\u0001\u0000\u0000\u0000\u011a\u011b\u0001\u0000"+
		"\u0000\u0000\u011b\u001f\u0001\u0000\u0000\u0000\u011c\u011e\u0003\u001a"+
		"\r\u0000\u011d\u011c\u0001\u0000\u0000\u0000\u011d\u011e\u0001\u0000\u0000"+
		"\u0000\u011e\u011f\u0001\u0000\u0000\u0000\u011f\u0120\u0005\u0001\u0000"+
		"\u0000\u0120!\u0001\u0000\u0000\u0000\u0121\u0122\u0007\u0000\u0000\u0000"+
		"\u0122#\u0001\u0000\u0000\u0000\u0123\u0124\u0007\u0001\u0000\u0000\u0124"+
		"%\u0001\u0000\u0000\u0000\u0125\u0126\u0005\u0013\u0000\u0000\u0126\'"+
		"\u0001\u0000\u0000\u0000\u0127\u012b\u0003\"\u0011\u0000\u0128\u012b\u0003"+
		"$\u0012\u0000\u0129\u012b\u0003&\u0013\u0000\u012a\u0127\u0001\u0000\u0000"+
		"\u0000\u012a\u0128\u0001\u0000\u0000\u0000\u012a\u0129\u0001\u0000\u0000"+
		"\u0000\u012b)\u0001\u0000\u0000\u0000\u012c\u012d\u0007\u0002\u0000\u0000"+
		"\u012d+\u0001\u0000\u0000\u0000\u012e\u012f\u0007\u0003\u0000\u0000\u012f"+
		"-\u0001\u0000\u0000\u0000%1;DMWZblz~\u0084\u0087\u008b\u008f\u0095\u00a6"+
		"\u00aa\u00b2\u00c0\u00c4\u00cb\u00d0\u00d4\u00da\u00e2\u00e4\u00ee\u00f4"+
		"\u00f8\u00fe\u0104\u0109\u010e\u0113\u011a\u011d\u012a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}