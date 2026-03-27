package calculator.api.exception;


/**
 * Exception thrown when the request body is too large to be processed by the server.
 * This can occur when the client sends a request that exceeds the server's configured maximum request size
 */
public class RequestTooLargeException extends RuntimeException {

    public RequestTooLargeException(String message) {
        super(message);
    }
}
