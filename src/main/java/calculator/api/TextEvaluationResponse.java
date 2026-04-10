package calculator.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextEvaluationResponse {

    private String result;
    private String infix;
    private String pretty;
    private String prefix;
    private String postfix;

    public TextEvaluationResponse() {
    }

    public TextEvaluationResponse(String result, String infix, String pretty, String prefix, String postfix) {
        this.result = result;
        this.infix = infix;
        this.pretty = pretty;
        this.prefix = prefix;
        this.postfix = postfix;
    }
}
