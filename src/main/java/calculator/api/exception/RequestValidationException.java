package calculator.api.exception;

/**
 * Exception thrown when the request body is invalid or cannot be processed by the server.
 * This can occur when the client sends a request with invalid JSON, missing required fields, or other issues that prevent the server from understanding the request.
 */
public class RequestValidationException extends IllegalArgumentException {

    public RequestValidationException(String message) {
        super(message);
    }
}
