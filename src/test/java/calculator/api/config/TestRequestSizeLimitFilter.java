package calculator.api.config;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestRequestSizeLimitFilter {

    @Test
    void testRejectsTooLargeEvaluatePayload() throws Exception {
        RequestSizeLimitFilter filter = new RequestSizeLimitFilter(8);
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/evaluate");
        request.setContent("123456789".getBytes(StandardCharsets.UTF_8));
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilter(request, response, chain);

        assertEquals(413, response.getStatus());
        assertTrue(response.getContentAsString().contains("\"code\":\"REQUEST_TOO_LARGE\""));
        assertNull(chain.getRequest());
    }

    @Test
    void testAllowsPayloadAtLimit() throws Exception {
        RequestSizeLimitFilter filter = new RequestSizeLimitFilter(8);
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/evaluate");
        request.setContent("12345678".getBytes(StandardCharsets.UTF_8));
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilter(request, response, chain);

        assertNotNull(chain.getRequest());
        assertEquals(200, response.getStatus());
    }

    @Test
    void testSkipsFilterForNonEvaluatePath() throws Exception {
        RequestSizeLimitFilter filter = new RequestSizeLimitFilter(1);
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/health");
        request.setContent("123456789".getBytes(StandardCharsets.UTF_8));
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilter(request, response, chain);

        assertNotNull(chain.getRequest());
        assertEquals(200, response.getStatus());
    }

    @Test
    void testRejectsTooLargeEvaluateTextPayload() throws Exception {
        RequestSizeLimitFilter filter = new RequestSizeLimitFilter(8);
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/evaluate-text");
        request.setContent("123456789".getBytes(StandardCharsets.UTF_8));
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilter(request, response, chain);

        assertEquals(413, response.getStatus());
        assertTrue(response.getContentAsString().contains("\"code\":\"REQUEST_TOO_LARGE\""));
        assertNull(chain.getRequest());
    }

    @Test
    void testSkipsFilterForNonPostMethod() throws Exception {
        RequestSizeLimitFilter filter = new RequestSizeLimitFilter(1);
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/evaluate");
        request.setContent("123456789".getBytes(StandardCharsets.UTF_8));
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilter(request, response, chain);

        assertNotNull(chain.getRequest());
        assertEquals(200, response.getStatus());
    }
}
