package calculator.api;

import calculator.Divides;
import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Minus;
import calculator.MyNumber;
import calculator.Plus;
import calculator.Times;
import calculator.api.exception.RequestValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    private final int maxExpressionDepth;
    private final int maxOperationArgs;

    public ExpressionMapper() {
        this(20, 20);
    }

    @Autowired
    public ExpressionMapper(
            @Value("${calculator.api.max-expression-depth:20}") int maxExpressionDepth,
            @Value("${calculator.api.max-operation-args:20}") int maxOperationArgs
    ) {
        this.maxExpressionDepth = maxExpressionDepth;
        this.maxOperationArgs = maxOperationArgs;
    }

    // Main entry point used to convert an API request into the internal expression model.
    public Expression map(ExpressionRequest request) {
        return map(request, 1);
    }

    private Expression map(ExpressionRequest request, int depth) {
        if (request == null) {
            throw new IllegalArgumentException("Expression request must not be null.");
        }
        enforceMaxDepth(depth);

        String normalizedType = normalizeType(request.getType());
        return switch (normalizedType) {
            case "number" -> mapNumber(request);
            case "plus", "minus", "times", "divides" -> mapOperation(request, normalizedType, depth);
            default -> throw new IllegalArgumentException("Unknown expression type: " + request.getType());
        };
    }

    private String normalizeType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression type must not be null or empty.");
        }
        return type.trim().toLowerCase(Locale.ROOT);
    }

    private Expression mapNumber(ExpressionRequest request) {
        if (request.getValue() == null) {
            throw new IllegalArgumentException("Number expression must provide a non-null value.");
        }
        return new MyNumber(request.getValue());
    }

    private Expression mapOperation(ExpressionRequest request, String opType, int depth) {
        if (request.getArgs() == null) {
            throw new IllegalArgumentException("Operation '" + opType + "' must provide a non-null args list.");
        }
        enforceMaxArgs(opType, request.getArgs().size());

        List<Expression> mappedArgs = mapArgs(request.getArgs(), depth + 1);
        try {
            return buildOperation(opType, mappedArgs);
        } catch (IllegalConstruction e) {
            throw new IllegalArgumentException("Cannot build operation '" + opType + "': invalid arguments.", e);
        }
    }

    private List<Expression> mapArgs(List<ExpressionRequest> args, int depth) {
        List<Expression> mapped = new ArrayList<>();
        for (ExpressionRequest arg : args) {
            if (arg == null) {
                throw new IllegalArgumentException("Operation arguments must not contain null elements.");
            }
            mapped.add(map(arg, depth));
        }
        return mapped;
    }

    private void enforceMaxDepth(int depth) {
        if (depth > maxExpressionDepth) {
            throw new RequestValidationException(
                    "Expression nesting exceeds the maximum depth of " + maxExpressionDepth + "."
            );
        }
    }

    private void enforceMaxArgs(String opType, int argCount) {
        if (argCount > maxOperationArgs) {
            throw new RequestValidationException(
                    "Operation '" + opType + "' exceeds the maximum of " + maxOperationArgs + " arguments."
            );
        }
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
