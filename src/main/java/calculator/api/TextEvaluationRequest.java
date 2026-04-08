package calculator.api;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextEvaluationRequest {

    @NotBlank(message = "expression must not be blank")
    private String expression;

    public TextEvaluationRequest() {
    }

    public TextEvaluationRequest(String expression) {
        this.expression = expression;
    }
}
