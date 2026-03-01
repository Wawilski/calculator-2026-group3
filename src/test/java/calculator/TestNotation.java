package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;


class TestNotation {

    /* This is an auxilary method to avoid code duplication.
     */
	void testNotation(String s,Operation o,Notation n) {
		assertEquals(s, o.toString(n));
		o.notation = n;
		assertEquals(s, o.toString());
	}

	/* This is an auxilary method to avoid code duplication.
     */
	void testNotations(String symbol,int value1,int value2,Operation op) {
		//prefix notation:
		testNotation(symbol +" (" + value1 + ", " + value2 + ")", op, Notation.PREFIX);
		//infix notation:
		testNotation("( " + value1 + " " + symbol + " " + value2 + " )", op, Notation.INFIX);
		//postfix notation:
		testNotation("(" + value1 + ", " + value2 + ") " + symbol, op, Notation.POSTFIX);
	}

	@ParameterizedTest
	@ValueSource(strings = {"*", "+", "/", "-"})
	void testOutput(String symbol) {
		int value1 = 8;
		int value2 = 6;
		Operation op = null;
		List<Expression> params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
		try {
			//construct another type of operation depending on the input value of the parameterised test
			switch (symbol) {
				case "+"	->	op = new Plus(params);
				case "-"	->	op = new Minus(params);
				case "*"	->	op = new Times(params);
				case "/"	->	op = new Divides(params);
				default		->	fail();
			}
		} catch (IllegalConstruction _) {
			fail();
		}
		testNotations(symbol, value1, value2, op);
	}

	private Operation buildCompositeExpressionWithMixedDefaultNotations() throws IllegalConstruction {
		Operation plus = new Plus(Arrays.asList(new MyNumber(3), new MyNumber(4), new MyNumber(5)));
		Operation minus = new Minus(Arrays.asList(new MyNumber(5), new MyNumber(4)));

		plus.notation = Notation.INFIX;
		minus.notation = Notation.POSTFIX;

		return new Divides(Arrays.asList(plus, minus, new MyNumber(7)));
	}

	@Test
	void testCompositeExpressionRespectsPrefixNotationEverywhere() throws IllegalConstruction {
		Operation root = buildCompositeExpressionWithMixedDefaultNotations();
		String expected = "/ (+ (3, 4, 5), - (5, 4), 7)";

		assertEquals(expected, root.toString(Notation.PREFIX));

		root.notation = Notation.PREFIX;
		assertEquals(expected, root.toString());
	}

	@Test
	void testCompositeExpressionRespectsPostfixNotationEverywhere() throws IllegalConstruction {
		Operation root = buildCompositeExpressionWithMixedDefaultNotations();
		String expected = "((3, 4, 5) +, (5, 4) -, 7) /";

		assertEquals(expected, root.toString(Notation.POSTFIX));

		root.notation = Notation.POSTFIX;
		assertEquals(expected, root.toString());
	}

	@Test
	void testNonRegressionMixedNotationsShouldNotLeakFromChildren() throws IllegalConstruction {
		Operation root = buildCompositeExpressionWithMixedDefaultNotations();
		assertEquals("/ (+ (3, 4, 5), - (5, 4), 7)", root.toString(Notation.PREFIX));
		assertEquals("((3, 4, 5) +, (5, 4) -, 7) /", root.toString(Notation.POSTFIX));
	}

	@Test
	void testCompositeExpressionWithEachChildUsingDifferentDefaultNotation() throws IllegalConstruction {
		Operation plus = new Plus(Arrays.asList(new MyNumber(1), new MyNumber(2)));
		Operation minus = new Minus(Arrays.asList(new MyNumber(8), new MyNumber(3)));
		Operation times = new Times(Arrays.asList(new MyNumber(2), new MyNumber(3)));
		plus.notation = Notation.PREFIX;
		minus.notation = Notation.INFIX;
		times.notation = Notation.POSTFIX;
		Operation root = new Divides(Arrays.asList(plus, minus, times));

		assertEquals("/ (+ (1, 2), - (8, 3), * (2, 3))", root.toString(Notation.PREFIX));
		assertEquals("( ( 1 + 2 ) / ( 8 - 3 ) / ( 2 * 3 ) )", root.toString(Notation.INFIX));
		assertEquals("((1, 2) +, (8, 3) -, (2, 3) *) /", root.toString(Notation.POSTFIX));
	}

	@Test
	void testDepthThreeExpressionExactPrefixAndPostfixStrings() throws IllegalConstruction {
		Operation left = new Times(Arrays.asList(
				new Plus(Arrays.asList(new MyNumber(1), new MyNumber(2))),
				new Minus(Arrays.asList(new MyNumber(3), new MyNumber(4)))
		));
		Operation right = new Plus(Arrays.asList(new MyNumber(5), new MyNumber(6)));
		Operation root = new Divides(Arrays.asList(left, right));

		assertEquals("/ (* (+ (1, 2), - (3, 4)), + (5, 6))", root.toString(Notation.PREFIX));
		assertEquals("(((1, 2) +, (3, 4) -) *, (5, 6) +) /", root.toString(Notation.POSTFIX));
	}

	@Test
	void testNonBinaryCompositeExpressionWithFourArguments() throws IllegalConstruction {
		Operation plus = new Plus(Arrays.asList(new MyNumber(2), new MyNumber(3), new MyNumber(4)));
		Operation minus = new Minus(Arrays.asList(new MyNumber(10), new MyNumber(5)));
		Operation root = new Times(Arrays.asList(new MyNumber(1), plus, minus, new MyNumber(6)));
		plus.notation = Notation.POSTFIX;
		minus.notation = Notation.PREFIX;

		assertEquals("* (1, + (2, 3, 4), - (10, 5), 6)", root.toString(Notation.PREFIX));
		assertEquals("( 1 * ( 2 + 3 + 4 ) * ( 10 - 5 ) * 6 )", root.toString(Notation.INFIX));
		assertEquals("(1, (2, 3, 4) +, (10, 5) -, 6) *", root.toString(Notation.POSTFIX));
	}

	@Test
	void testRootDefaultNotationIsStableEvenIfChildNotationChanges() throws IllegalConstruction {
		Operation root = buildCompositeExpressionWithMixedDefaultNotations();
		root.notation = Notation.PREFIX;
		String expected = "/ (+ (3, 4, 5), - (5, 4), 7)";

		assertEquals(expected, root.toString());
		Operation firstChild = (Operation) root.getArgs().get(0);
		firstChild.notation = Notation.POSTFIX;
		assertEquals(expected, root.toString());
	}

}
