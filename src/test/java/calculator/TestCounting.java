package calculator;

//Import Junit5 libraries for unit testing:
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import visitor.CountingVisitor;

class TestCounting {

    private int value1, value2;
    private Expression e;

    @BeforeEach
    void setUp() {
        value1 = 8;
        value2 = 6;
        e = null;
    }

    @Test
    void testNumberCounting() {
        e = new MyNumber(value1);
        CountingVisitor visitor = new CountingVisitor();
        e.accept(visitor);
        //test whether a number has zero depth (i.e. no nested expressions)
        assertEquals(0, visitor.getDepth());
        //test whether a number contains zero operations
        assertEquals(0, visitor.getOpsCount());
        //test whether a number contains 1 number
        assertEquals(1, visitor.getNumbersCount());
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-"})
    void testOperationCounting(String symbol) {
        List<Expression> params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (symbol) {
                case "+"	->	e = new Plus(params);
                case "-"	->	e = new Minus(params);
                case "*"	->	e = new Times(params);
                case "/"	->	e = new Divides(params);
                default		->	fail();
            }
        } catch (IllegalConstruction _) {
            fail();
        }
        CountingVisitor visitor = new CountingVisitor();
        e.accept(visitor);
        //test whether a binary operation has depth 1
        assertEquals(1, visitor.getDepth(),"counting depth of an Operation");
        //test whether a binary operation contains 1 operation
        assertEquals(1, visitor.getOpsCount());
        //test whether a binary operation contains 2 numbers
        assertEquals(2, visitor.getNumbersCount());
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-"})
    void testEmptyOperationCounting(String symbol) throws IllegalConstruction {
        Expression expr = switch (symbol) {
            case "+" -> new Plus(new ArrayList<>());
            case "-" -> new Minus(new ArrayList<>());
            case "*" -> new Times(new ArrayList<>());
            case "/" -> new Divides(new ArrayList<>());
            default -> throw new IllegalStateException("Unexpected symbol: " + symbol);
        };
        CountingVisitor visitor = new CountingVisitor();
        expr.accept(visitor);
        assertEquals(1, visitor.getDepth());
        assertEquals(1, visitor.getOpsCount());
        assertEquals(0, visitor.getNumbersCount());
    }

}
