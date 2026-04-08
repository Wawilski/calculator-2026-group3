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

@Component
public class RequestSizeLimitFilter extends OncePerRequestFilter {

    // This limit is checked before the request reaches the controller.
    private final long maxRequestSizeBytes;

    public RequestSizeLimitFilter(
            @Value("${calculator.api.max-request-size-bytes:8192}") long maxRequestSizeBytes
    ) {
        this.maxRequestSizeBytes = maxRequestSizeBytes;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        long contentLength = request.getContentLengthLong();
        if (contentLength > maxRequestSizeBytes) {
            // I return directly here because there is no reason to deserialize a request that is already too big.
            writePayloadTooLargeResponse(request, response, contentLength);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        String uri = request.getRequestURI();
        return !"/api/evaluate".equals(uri) && !"/api/evaluate-text".equals(uri);
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
        // I keep the same JSON structure as the rest of the API errors.
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
        // Small helper to avoid breaking the JSON error body.
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
