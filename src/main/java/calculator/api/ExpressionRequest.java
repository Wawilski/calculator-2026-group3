package calculator.api;


import java.util.List;

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
public class ExpressionRequest {

    // Type of node sent by the client: number, plus, minus, times or divides.
    private String type;
    // Used only when the node is a number.
    private Integer value;
    // Used only when the node is an operation.
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
}
