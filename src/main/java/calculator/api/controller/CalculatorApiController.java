package calculator.api.controller;

import calculator.api.EvaluationResponse;
import calculator.api.ExpressionRequest;
import calculator.api.service.CalculatorApiService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CalculatorApiController {

    private final CalculatorApiService calculatorApiService;

    public CalculatorApiController(CalculatorApiService calculatorApiService) {
        this.calculatorApiService = calculatorApiService;
    }

    // Main REST endpoint used by the web client to evaluate an expression.
    @PostMapping("/evaluate")
    public EvaluationResponse evaluate(@Valid @RequestBody ExpressionRequest request) {
        return calculatorApiService.evaluate(request);
    }

    // Small endpoint to quickly check if the API is running.
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}
