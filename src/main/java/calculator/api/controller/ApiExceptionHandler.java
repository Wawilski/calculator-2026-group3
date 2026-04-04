package calculator.api.controller;

import calculator.api.exception.RequestTooLargeException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    // I group validation errors here to return one consistent JSON format for the frontend.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        List<String> details = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    if (error instanceof FieldError fieldError) {
                        // The synthetic fields from @AssertTrue are not useful for the client,
                        // so I only keep the plain message in that case.
                        if (fieldError.getField().endsWith("ShapeValid")) {
                            return error.getDefaultMessage();
                        }
                        return fieldError.getField() + ": " + error.getDefaultMessage();
                    }
                    return error.getDefaultMessage();
                })
                .toList();
        return buildResponse(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", "Request validation failed.", request, details);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleUnreadableBody(
            HttpMessageNotReadableException exception,
            HttpServletRequest request
    ) {
        String detail = exception.getMostSpecificCause() != null
                ? exception.getMostSpecificCause().getMessage()
                : exception.getMessage();
        boolean bodyMissing = detail != null
                && (detail.contains("Required request body is missing") || detail.contains("No content to map"));
        // I separate empty body from malformed JSON because the client should not get the same code.
        String message = bodyMissing ? "Request body is required." : "Malformed JSON request.";
        String code = bodyMissing ? "REQUEST_BODY_MISSING" : "MALFORMED_JSON";
        return buildResponse(HttpStatus.BAD_REQUEST, code, message, request, List.of(detail));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedMediaType(
            HttpMediaTypeNotSupportedException exception,
            HttpServletRequest request
    ) {
        return buildResponse(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                "UNSUPPORTED_MEDIA_TYPE",
                "Content-Type must be application/json.",
                request,
                List.of("Supported content type: application/json")
        );
    }

    @ExceptionHandler(RequestTooLargeException.class)
    public ResponseEntity<ErrorResponse> handlePayloadTooLarge(
            RequestTooLargeException exception,
            HttpServletRequest request
    ) {
        return buildResponse(
                HttpStatus.PAYLOAD_TOO_LARGE,
                "REQUEST_TOO_LARGE",
                "Request payload is too large.",
                request,
                List.of(exception.getMessage())
        );
    }

    @ExceptionHandler({IllegalArgumentException.class, ArithmeticException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest(
            RuntimeException exception,
            HttpServletRequest request
    ) {
        String code = exception instanceof ArithmeticException ? "ARITHMETIC_ERROR" : "INVALID_REQUEST";
        return buildResponse(HttpStatus.BAD_REQUEST, code, exception.getMessage(), request, List.of(exception.getMessage()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            NoResourceFoundException exception,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.NOT_FOUND, "NOT_FOUND", "Resource not found.", request, List.of(exception.getMessage()));
    }

    private ResponseEntity<ErrorResponse> buildResponse(
            HttpStatus status,
            String code,
            String message,
            HttpServletRequest request,
            List<String> details
    ) {
        // All API errors go through this helper so the response shape stays the same everywhere.
        ErrorResponse body = new ErrorResponse(
                Instant.now(),
                status.value(),
                code,
                message,
                request.getRequestURI(),
                details
        );
        return ResponseEntity.status(status).body(body);
    }
}
