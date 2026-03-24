package calculator.api;


import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Divides;
import calculator.Minus;
import calculator.MyNumber;
import calculator.Plus;
import calculator.Times;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

/**
 * @author oussama hakik
 * This class is responsible for mapping the incoming ExpressionRequest (the API payload) to the internal Expression model used by the calculator logic.
 * It also performs validation to ensure that the incoming data is well-formed and can be correctly evaluated.
 * The mapping logic is intentionally separated from the API service to keep concerns separated and maintain a clean architecture.
 * The mapping process involves:
 * 1. Validating the "type" field to ensure it corresponds to a known expression type (e.g., "number", "plus", "minus", etc.).
 * 2. For "number" type, validating that the "value" field is present and is an integer.
 * 3. For operation types (e.g., "plus", "minus"), validating that the "args" field is present and contains a list of valid ExpressionRequest objects.
 * 4. Recursively mapping the ExpressionRequest objects in the "args" list to the internal Expression model.
 * 5. Throwing appropriate exceptions (e.g., IllegalArgumentException) if any validation fails, with clear error messages to help diagnose issues with the incoming payload.
 */
@Component
public class ExpressionMapper {

    public ExpressionMapper() {
    }

    // Main entry point used to convert an API request into the internal expression model.
    public Expression map(ExpressionRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Expression request must not be null.");
        }

        String normalizedType = normalizeType(request.getType());
        return switch (normalizedType) {
            case "number" -> mapNumber(request);
            case "plus", "minus", "times", "divides" -> mapOperation(request, normalizedType);
            default -> throw new IllegalArgumentException("Unknown expression type: " + request.getType());
        };
    }

    private String normalizeType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression type must not be null or empty.");
        }
        // Normalize the type to make the mapping case-insensitive.
        return type.trim().toLowerCase(Locale.ROOT);
    }

    private Expression mapNumber(ExpressionRequest request) {
        if (request.getValue() == null) {
            throw new IllegalArgumentException("Number expression must provide a non-null value.");
        }
        return new MyNumber(request.getValue());
    }

    private Expression mapOperation(ExpressionRequest request, String opType) {
        if (request.getArgs() == null) {
            throw new IllegalArgumentException("Operation '" + opType + "' must provide a non-null args list.");
        }

        // First map all children recursively, then build the matching operation object.
        List<Expression> mappedArgs = mapArgs(request.getArgs());
        try {
            return buildOperation(opType, mappedArgs);
        } catch (IllegalConstruction e) {
            throw new IllegalArgumentException("Cannot build operation '" + opType + "': invalid arguments.", e);
        }
    }

    private List<Expression> mapArgs(List<ExpressionRequest> args) {
        List<Expression> mapped = new ArrayList<>();
        for (ExpressionRequest arg : args) {
            if (arg == null) {
                throw new IllegalArgumentException("Operation arguments must not contain null elements.");
            }
            mapped.add(map(arg));
        }
        return mapped;
    }

    private Expression buildOperation(String opType, List<Expression> args) throws IllegalConstruction {
        return switch (opType) {
            case "plus" -> new Plus(args);
            case "minus" -> new Minus(args);
            case "times" -> new Times(args);
            case "divides" -> new Divides(args);
            default -> throw new IllegalArgumentException("Unsupported operation type: " + opType);
        };
    }

}
