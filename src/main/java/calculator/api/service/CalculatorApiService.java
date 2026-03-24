package calculator.api.service;


import calculator.Calculator;
import calculator.Expression;
import calculator.Notation;
import calculator.api.EvaluationResponse;
import calculator.api.ExpressionMapper;
import calculator.api.ExpressionRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author oussama hakik
 * This class is responsible for handling the API endpoint for evaluating expressions.
 * It will receive an ExpressionRequest payload, use the ExpressionMapper to convert it to the internal Expression model, evaluate the expression using the calculator logic, and return an EvaluationResponse with the result and different string representations of the expression.
 * The API service will also handle any exceptions that may occur during the mapping or evaluation process, and return appropriate HTTP status codes and error messages to the client.
 * The API endpoint will be designed to be simple and intuitive, allowing clients to easily send complex expressions in a structured format and receive clear results.
 */
@Service
public class CalculatorApiService {

    private final Calculator calculator;
    private final ExpressionMapper expressionMapper;

    public CalculatorApiService(ExpressionMapper expressionMapper) {
        this.calculator = new Calculator();
        this.expressionMapper = Objects.requireNonNull(expressionMapper, "expressionMapper must not be null");
    }

    // This method connects the API layer to the calculator core.
    public EvaluationResponse evaluate(ExpressionRequest request){
        Expression expression = expressionMapper.map(request);
        int result = calculator.eval(expression);
        // Build the response with the computed value and some string formats.
        EvaluationResponse response = new EvaluationResponse();
        response.setResult(result);
        response.setInfix(calculator.format(expression, Notation.INFIX));
        response.setPretty(calculator.prettyFormat(expression));
        response.setPrefix(calculator.format(expression, Notation.PREFIX));
        return response;
    }
}
