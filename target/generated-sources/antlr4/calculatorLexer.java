// Generated from calculator.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class calculatorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		I=1, E=2, PI=3, EULER=4, PHI=5, COS=6, SIN=7, TAN=8, ACOS=9, ASIN=10, 
		ATAN=11, LN=12, LOG=13, SQRT=14, PLUS=15, MINUS=16, TIMES=17, DIV=18, 
		POW=19, INT=20, LPAREN=21, RPAREN=22, DOT=23, COMMAT=24, WS=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"I", "E", "PI", "EULER", "PHI", "COS", "SIN", "TAN", "ACOS", "ASIN", 
			"ATAN", "LN", "LOG", "SQRT", "PLUS", "MINUS", "TIMES", "DIV", "POW", 
			"INT", "LPAREN", "RPAREN", "DOT", "COMMAT", "WS"
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


	public calculatorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "calculator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0019\u0086\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0004\u0013t\b\u0013"+
		"\u000b\u0013\f\u0013u\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0004\u0018"+
		"\u0081\b\u0018\u000b\u0018\f\u0018\u0082\u0001\u0018\u0001\u0018\u0000"+
		"\u0000\u0019\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b"+
		"\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b"+
		"\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016"+
		"-\u0017/\u00181\u0019\u0001\u0000\u0001\u0003\u0000\t\n\r\r  \u0087\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00013\u0001\u0000"+
		"\u0000\u0000\u00035\u0001\u0000\u0000\u0000\u00057\u0001\u0000\u0000\u0000"+
		"\u0007:\u0001\u0000\u0000\u0000\t<\u0001\u0000\u0000\u0000\u000b@\u0001"+
		"\u0000\u0000\u0000\rD\u0001\u0000\u0000\u0000\u000fH\u0001\u0000\u0000"+
		"\u0000\u0011L\u0001\u0000\u0000\u0000\u0013Q\u0001\u0000\u0000\u0000\u0015"+
		"V\u0001\u0000\u0000\u0000\u0017[\u0001\u0000\u0000\u0000\u0019^\u0001"+
		"\u0000\u0000\u0000\u001bb\u0001\u0000\u0000\u0000\u001dg\u0001\u0000\u0000"+
		"\u0000\u001fi\u0001\u0000\u0000\u0000!k\u0001\u0000\u0000\u0000#m\u0001"+
		"\u0000\u0000\u0000%o\u0001\u0000\u0000\u0000\'s\u0001\u0000\u0000\u0000"+
		")w\u0001\u0000\u0000\u0000+y\u0001\u0000\u0000\u0000-{\u0001\u0000\u0000"+
		"\u0000/}\u0001\u0000\u0000\u00001\u0080\u0001\u0000\u0000\u000034\u0005"+
		"i\u0000\u00004\u0002\u0001\u0000\u0000\u000056\u0005E\u0000\u00006\u0004"+
		"\u0001\u0000\u0000\u000078\u0005p\u0000\u000089\u0005i\u0000\u00009\u0006"+
		"\u0001\u0000\u0000\u0000:;\u0005e\u0000\u0000;\b\u0001\u0000\u0000\u0000"+
		"<=\u0005p\u0000\u0000=>\u0005h\u0000\u0000>?\u0005i\u0000\u0000?\n\u0001"+
		"\u0000\u0000\u0000@A\u0005c\u0000\u0000AB\u0005o\u0000\u0000BC\u0005s"+
		"\u0000\u0000C\f\u0001\u0000\u0000\u0000DE\u0005s\u0000\u0000EF\u0005i"+
		"\u0000\u0000FG\u0005n\u0000\u0000G\u000e\u0001\u0000\u0000\u0000HI\u0005"+
		"t\u0000\u0000IJ\u0005a\u0000\u0000JK\u0005n\u0000\u0000K\u0010\u0001\u0000"+
		"\u0000\u0000LM\u0005a\u0000\u0000MN\u0005c\u0000\u0000NO\u0005o\u0000"+
		"\u0000OP\u0005s\u0000\u0000P\u0012\u0001\u0000\u0000\u0000QR\u0005a\u0000"+
		"\u0000RS\u0005s\u0000\u0000ST\u0005i\u0000\u0000TU\u0005n\u0000\u0000"+
		"U\u0014\u0001\u0000\u0000\u0000VW\u0005a\u0000\u0000WX\u0005t\u0000\u0000"+
		"XY\u0005a\u0000\u0000YZ\u0005n\u0000\u0000Z\u0016\u0001\u0000\u0000\u0000"+
		"[\\\u0005l\u0000\u0000\\]\u0005n\u0000\u0000]\u0018\u0001\u0000\u0000"+
		"\u0000^_\u0005l\u0000\u0000_`\u0005o\u0000\u0000`a\u0005g\u0000\u0000"+
		"a\u001a\u0001\u0000\u0000\u0000bc\u0005s\u0000\u0000cd\u0005q\u0000\u0000"+
		"de\u0005r\u0000\u0000ef\u0005t\u0000\u0000f\u001c\u0001\u0000\u0000\u0000"+
		"gh\u0005+\u0000\u0000h\u001e\u0001\u0000\u0000\u0000ij\u0005-\u0000\u0000"+
		"j \u0001\u0000\u0000\u0000kl\u0005*\u0000\u0000l\"\u0001\u0000\u0000\u0000"+
		"mn\u0005/\u0000\u0000n$\u0001\u0000\u0000\u0000op\u0005*\u0000\u0000p"+
		"q\u0005*\u0000\u0000q&\u0001\u0000\u0000\u0000rt\u000209\u0000sr\u0001"+
		"\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000us\u0001\u0000\u0000\u0000"+
		"uv\u0001\u0000\u0000\u0000v(\u0001\u0000\u0000\u0000wx\u0005(\u0000\u0000"+
		"x*\u0001\u0000\u0000\u0000yz\u0005)\u0000\u0000z,\u0001\u0000\u0000\u0000"+
		"{|\u0005.\u0000\u0000|.\u0001\u0000\u0000\u0000}~\u0005,\u0000\u0000~"+
		"0\u0001\u0000\u0000\u0000\u007f\u0081\u0007\u0000\u0000\u0000\u0080\u007f"+
		"\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000\u0000\u0082\u0080"+
		"\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083\u0084"+
		"\u0001\u0000\u0000\u0000\u0084\u0085\u0006\u0018\u0000\u0000\u00852\u0001"+
		"\u0000\u0000\u0000\u0003\u0000u\u0082\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}