package calculator.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Locale;

/**
 * This class represents the expression payload received by the API.
 *
 * Supported shapes:
 * - number node: {"type":"number","value":8}
 * - operation node: {"type":"plus","args":[...]}
 *
 * The mapping/validation logic is intentionally handled in ExpressionMapper.
 *
 * @author oussama hakik
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class ExpressionRequest {

    // I validate the type here so the controller can reject bad payloads before the mapper.
    @NotBlank(message = "type must not be blank")
    @Pattern(
            regexp = "(?i)number|plus|minus|times|divides",
            message = "type must be one of: number, plus, minus, times, divides"
    )
    private String type;

    private Integer value;

    // Recursive validation is needed because args can contain nested expressions.
    @Valid
    private List<ExpressionRequest> args;

    public ExpressionRequest() {
        // Needed by JSON binding libraries using default constructor + setters.
    }

    public ExpressionRequest(String type, Integer value, List<ExpressionRequest> args) {
        this.type = type;
        this.value = value;
        this.args = args;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<ExpressionRequest> getArgs() {
        return args;
    }

    public void setArgs(List<ExpressionRequest> args) {
        this.args = args;
    }

    // If the node is a number, I only accept a value and I reject args.
    @AssertTrue(message = "number expressions require value and must not define args")
    public boolean isNumberShapeValid() {
        String normalizedType = normalizedType();
        if (!"number".equals(normalizedType)) {
            return true;
        }
        return value != null && args == null;
    }

    // If the node is an operation, I do the opposite: args must exist and value must stay null.
    @AssertTrue(message = "operation expressions require args and must not define value")
    public boolean isOperationShapeValid() {
        String normalizedType = normalizedType();
        if (normalizedType == null || "number".equals(normalizedType)) {
            return true;
        }
        return args != null && value == null;
    }

    private String normalizedType() {
        if (type == null || type.isBlank()) {
            return null;
        }
        return type.trim().toLowerCase(Locale.ROOT);
    }
}
