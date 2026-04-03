package calculator.api;


/**
 * @author oussama hakik
 * This class represents the response payload sent by the API after evaluating an expression.
 * It can be extended in the future to include more information (e.g., evaluation time, expression tree, etc.).
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvaluationResponse {

    // Final numerical result of the evaluation.
    private Integer result;
    // Different string representations of the same expression.
    private String infix;
    private String pretty;
    private String prefix;


    public EvaluationResponse() {
    }

    public EvaluationResponse(Integer result, String infix, String pretty, String prefix) {
        this.result = result;
        this.infix = infix;
        this.pretty = pretty;
        this.prefix = prefix;
    }
}

