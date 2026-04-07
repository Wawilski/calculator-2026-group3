package calculator.api.service;

import calculator.api.EvaluationResponse;
import calculator.api.ExpressionMapper;
import calculator.api.ExpressionRequest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestCalculatorApiService {

    private final CalculatorApiService service = new CalculatorApiService(new ExpressionMapper());

    @Test
    void testEvaluateReturnsComputedRepresentations() {
        // The service should return both the result and the formatted versions.
        ExpressionRequest request = new ExpressionRequest(
                "times",
                null,
                List.of(
                        new ExpressionRequest(
                                "plus",
                                null,
                                List.of(
                                        new ExpressionRequest("number", 3, null),
                                        new ExpressionRequest("number", 4, null)
                                )
                        ),
                        new ExpressionRequest("number", 5, null)
                )
        );

        EvaluationResponse response = service.evaluate(request);

        assertEquals(35, response.getResult());
        assertTrue(response.getInfix().contains("+"));
        assertTrue(response.getInfix().contains("*"));
        assertTrue(response.getPretty().contains("*"));
        assertTrue(response.getPrefix().startsWith("*"));
    }

    @Test
    void testEvaluatePropagatesMappingErrors() {
        // Invalid API input should still fail at service level.
        ExpressionRequest request = new ExpressionRequest("number", null, null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.evaluate(request));

        assertEquals("Number expression must provide a non-null value.", exception.getMessage());
    }

    @Test
    void testEvaluatePropagatesDivisionByZero() {
        // Runtime arithmetic errors should not be hidden by the service.
        ExpressionRequest request = new ExpressionRequest(
                "divides",
                null,
                List.of(
                        new ExpressionRequest("number", 8, null),
                        new ExpressionRequest("number", 0, null)
                )
        );

        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> service.evaluate(request));

        assertEquals("Division by zero is not allowed.", exception.getMessage());
    }

    @Test
    void testEvaluateRejectsNonIntegerResult() {
        ExpressionRequest request = new ExpressionRequest(
                "divides",
                null,
                List.of(
                        new ExpressionRequest("number", 7, null),
                        new ExpressionRequest("number", 5, null)
                )
        );

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> service.evaluate(request));

        assertEquals("API currently supports integer results only.", exception.getMessage());
    }
}
