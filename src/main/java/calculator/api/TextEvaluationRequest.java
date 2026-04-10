package calculator.api;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextEvaluationRequest {

    @NotBlank(message = "expression must not be blank")
    private String expression;

    @Min(value = 0, message = "scale must be between 0 and 16")
    @Max(value = 16, message = "scale must be between 0 and 16")
    private Integer scale;

    private Boolean angleUnitDegree;

    public TextEvaluationRequest() {
    }

    public TextEvaluationRequest(String expression, Integer scale, Boolean angleUnitDegree) {
        this.expression = expression;
        this.scale = scale;
        this.angleUnitDegree = angleUnitDegree;
    }
}
