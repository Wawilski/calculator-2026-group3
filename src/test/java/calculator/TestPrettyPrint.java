package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ArrayList;


/**
 * This test class checks the pretty printing of expressions, including nested expressions and operator precedence
 * @author Oussama Hakik
 */
class TestPrettyPrint {

    private final Calculator calc = new Calculator(); // instance of the Calculator class to be used for pretty printing expressions


    /**
     * Test the pretty printing of a simple expression with two numbers and an operator
     */
    @Test
    void testPrettyPrintNestedExpression() throws IllegalConstruction {
        Expression left = new Plus(Arrays.asList(new MyNumber(3), new MyNumber(4)));
        Expression right = new Minus(Arrays.asList(new MyNumber(5), new MyNumber(2)));
        Expression expr = new Times(Arrays.asList(left, right));
        assertEquals("(3 + 4) * (5 - 2)", calc.prettyFormat(expr));
    }


    /**
     * Test the pretty printing of an expression with operator precedence, where multiplication should be evaluated before addition
     */
    @Test
    void testPrettyPrintWithPrecedence() throws IllegalConstruction {
        Expression expr = new Plus(Arrays.asList(
                new MyNumber(3),
                new Times(Arrays.asList(new MyNumber(4), new MyNumber(2)))
        ));
        assertEquals("3 + 4 * 2", calc.prettyFormat(expr));
    }


    /**
     * Test the pretty printing of an expression with multiple operators and nested expressions, to check that parentheses are correctly placed
     */
    @Test
    void testPrettyPrintEmptyOperation() throws IllegalConstruction {
        Expression expr = new Plus(new ArrayList<>());
        assertEquals("+()", calc.prettyFormat(expr));
    }
}
