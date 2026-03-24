package calculator.api;


/**
 * @author oussama hakik
 * This class represents the response payload sent by the API after evaluating an expression.
 * It can be extended in the future to include more information (e.g., evaluation time, expression tree, etc.).
 */

public class EvaluationResponse {

    // Final numerical result of the evaluation.
    private Integer result;
    // Different string representations of the same expression.
    private String infix;
    private String pretty;
    private String prefix;

    public EvaluationResponse(){}

    public EvaluationResponse(Integer result, String infix, String pretty, String prefix){
        this.result = result;
        this.infix = infix;
        this.pretty = pretty;
        this.prefix = prefix;
    }

    public Integer getResult() {
        return result;
    }

    public String getInfix() {
        return infix;
    }

    public String getPretty() {
        return pretty;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public void setInfix(String infix) {
        this.infix = infix;
    }

    public void setPretty(String pretty) {
        this.pretty = pretty;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}

