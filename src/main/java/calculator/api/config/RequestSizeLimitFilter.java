package calculator.api.config;

import calculator.api.exception.RequestTooLargeException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/**
 * @author Oussama HAKIK
 * @description:
 * Filter to enforce a maximum request size for specific API endpoints. It checks the Content-Length header of incoming requests and rejects those that exceed the configured limit with a 413 Payload Too Large response.
 * This helps protect the API from excessively large requests that could lead to performance issues or resource exhaustion.
 * The filter is applied only to the /api/evaluate endpoint for POST requests, as this is where the main expression evaluation occurs and where large payloads are most likely to be a concern.
 */
@Component
public class RequestSizeLimitFilter extends OncePerRequestFilter {

    private final long maxRequestSizeBytes; // Define the maximum request size

    public RequestSizeLimitFilter(
            @Value("${calculator.api.max-request-size-bytes:8192}") long maxRequestSizeBytes
    ) {
        this.maxRequestSizeBytes = maxRequestSizeBytes;
    }


    /**
     * This method is called for each incoming request. It checks the Content-Length header to determine the size of the request payload. If the payload exceeds the configured maximum size, it writes a 413 Payload Too Large response and does not proceed with the filter chain.
     * Otherwise, it allows the request to continue through the filter chain for normal processing.
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        long contentLength = request.getContentLengthLong();
        if (contentLength > maxRequestSizeBytes) {
            writePayloadTooLargeResponse(request, response, contentLength);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !"/api/evaluate".equals(request.getRequestURI()) || !"POST".equalsIgnoreCase(request.getMethod());
    }

    private void writePayloadTooLargeResponse(
            HttpServletRequest request,
            HttpServletResponse response,
            long contentLength
    ) throws IOException {
        RequestTooLargeException exception = new RequestTooLargeException(
                "Payload size of " + contentLength + " bytes exceeds the maximum of "
                        + maxRequestSizeBytes + " bytes."
        );
        response.setStatus(413);
        response.setContentType("application/json");
        response.getWriter().write("""
                {"timestamp":"%s","status":413,"code":"REQUEST_TOO_LARGE","message":"Request payload is too large.","path":"%s","details":["%s"]}
                """.formatted(
                java.time.Instant.now(),
                escapeJson(request.getRequestURI()),
                escapeJson(exception.getMessage())
        ));
    }

    private String escapeJson(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
