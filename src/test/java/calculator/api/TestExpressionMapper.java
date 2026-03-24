package calculator.api;

import calculator.Divides;
import calculator.Expression;
import calculator.Minus;
import calculator.MyNumber;
import calculator.Plus;
import calculator.Times;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestExpressionMapper {

    private final ExpressionMapper mapper = new ExpressionMapper();

    @Test
    void testMapNumber() {
        // A number request should become a MyNumber expression.
        ExpressionRequest request = new ExpressionRequest("number", 8, null);

        Expression expression = mapper.map(request);

        MyNumber number = assertInstanceOf(MyNumber.class, expression);
        assertEquals(8, number.getValue());
    }

    @Test
    void testMapPlusOperation() {
        // A plus node should keep both children after mapping.
        ExpressionRequest left = new ExpressionRequest("number", 3, null);
        ExpressionRequest right = new ExpressionRequest("number", 4, null);
        ExpressionRequest request = new ExpressionRequest("plus", null, List.of(left, right));

        Expression expression = mapper.map(request);

        Plus plus = assertInstanceOf(Plus.class, expression);
        assertEquals(2, plus.getArgs().size());
    }

    @Test
    void testMapNestedOperation() {
        // This checks that recursive mapping works for nested expressions.
        ExpressionRequest plus = new ExpressionRequest(
                "plus",
                null,
                List.of(
                        new ExpressionRequest("number", 3, null),
                        new ExpressionRequest("number", 4, null)
                )
        );
        ExpressionRequest minus = new ExpressionRequest(
                "minus",
                null,
                List.of(
                        new ExpressionRequest("number", 9, null),
                        new ExpressionRequest("number", 2, null)
                )
        );
        ExpressionRequest request = new ExpressionRequest("times", null, List.of(plus, minus));

        Expression expression = mapper.map(request);

        Times times = assertInstanceOf(Times.class, expression);
        assertInstanceOf(Plus.class, times.getArgs().get(0));
        assertInstanceOf(Minus.class, times.getArgs().get(1));
    }

    @Test
    void testMapDividesOperation() {
        // Basic check for the division operation type.
        ExpressionRequest request = new ExpressionRequest(
                "divides",
                null,
                List.of(
                        new ExpressionRequest("number", 8, null),
                        new ExpressionRequest("number", 2, null)
                )
        );

        Expression expression = mapper.map(request);

        assertInstanceOf(Divides.class, expression);
    }

    @Test
    void testMapRejectsNullRequest() {
        // Null input should be rejected immediately.
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> mapper.map(null));

        assertEquals("Expression request must not be null.", exception.getMessage());
    }

    @Test
    void testMapRejectsUnknownType() {
        // Unknown operation names should not be accepted.
        ExpressionRequest request = new ExpressionRequest("modulo", null, List.of());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> mapper.map(request));

        assertEquals("Unknown expression type: modulo", exception.getMessage());
    }

    @Test
    void testMapRejectsMissingNumberValue() {
        // A number node without value is invalid.
        ExpressionRequest request = new ExpressionRequest("number", null, null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> mapper.map(request));

        assertEquals("Number expression must provide a non-null value.", exception.getMessage());
    }

    @Test
    void testMapRejectsNullArgsList() {
        // An operation must have an args list, even if it is empty.
        ExpressionRequest request = new ExpressionRequest("plus", null, null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> mapper.map(request));

        assertEquals("Operation 'plus' must provide a non-null args list.", exception.getMessage());
    }

    @Test
    void testMapRejectsNullArgumentElement() {
        // Null children inside args should also be rejected.
        ExpressionRequest request = new ExpressionRequest(
                "minus",
                null,
                Arrays.asList(new ExpressionRequest("number", 1, null), null)
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> mapper.map(request));

        assertEquals("Operation arguments must not contain null elements.", exception.getMessage());
    }
}
