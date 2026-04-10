package calculator.api.service;


import java.util.Objects;

import org.springframework.stereotype.Service;

import calculator.Calculator;
import calculator.Expression;
import calculator.ExpressionParser;
import calculator.Notation;
import calculator.api.EvaluationResponse;
import calculator.api.ExpressionMapper;
import calculator.api.ExpressionRequest;
import calculator.api.TextEvaluationResponse;
import calculator.numbers.BaseNumber;
import calculator.numbers.IntegerNumber;
import calculator.numbers.RealNumber;

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
    private final ExpressionParser expressionParser;

    public CalculatorApiService(ExpressionMapper expressionMapper) {
        this.calculator = new Calculator();
        this.expressionMapper = Objects.requireNonNull(expressionMapper, "expressionMapper must not be null");
        this.expressionParser = new ExpressionParser();
    }

    // This method connects the API layer to the calculator core.
    public EvaluationResponse evaluate(ExpressionRequest request){
        Expression expression = expressionMapper.map(request);
        BaseNumber evaluated = calculator.eval(expression);
        if (!(evaluated instanceof IntegerNumber integerResult)) {
            throw new IllegalStateException("API currently supports integer results only.");
        }
        // Build the response with the computed value and some string formats.
        EvaluationResponse response = new EvaluationResponse();
        response.setResult(integerResult.getValue());
        response.setInfix(calculator.format(expression, Notation.INFIX));
        response.setPretty(calculator.prettyFormat(expression));
        response.setPrefix(calculator.format(expression, Notation.PREFIX));
        return response;
    }

    public TextEvaluationResponse evaluateText(String rawExpression, Integer scale) {
        if (rawExpression == null || rawExpression.isBlank()) {
            throw new IllegalArgumentException("Expression text must not be blank.");
        }

        String normalizedExpression = normalizeExpression(rawExpression);
        int previousScale = RealNumber.getScale();
        if (scale != null) {
            RealNumber.setScale(scale);
        }
        try {
            Expression expression = expressionParser.parse(normalizedExpression);
            BaseNumber evaluated = calculator.eval(expression);
            TextEvaluationResponse response = new TextEvaluationResponse();
            response.setResult(evaluated.toString());
            response.setInfix(calculator.format(expression, Notation.INFIX));
            response.setPretty(calculator.prettyFormat(expression));
            response.setPrefix(calculator.format(expression, Notation.PREFIX));
            response.setPostfix(calculator.format(expression, Notation.POSTFIX));
            return response;
        } catch (RuntimeException exception) {
            throw new IllegalArgumentException("Invalid expression syntax.");
        } finally {
            if (scale != null) {
                RealNumber.setScale(previousScale);
            }
        }
    }

    private String normalizeExpression(String expression) {
        return expression.replace("^", "**").trim();
    }
}
