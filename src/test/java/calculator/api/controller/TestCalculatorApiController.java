package calculator.api.controller;

import calculator.api.ApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApiApplication.class, properties = {
    "calculator.api.max-expression-depth=4",
    "calculator.api.max-operation-args=3",
    "calculator.api.max-request-size-bytes=1024",
    "calculator.api.cors.allowed-origins=http://localhost:3000"
})
@AutoConfigureMockMvc
class TestCalculatorApiController {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testHealthEndpoint() throws Exception {
    mockMvc.perform(get("/api/health"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.status").value("UP"));
  }

  @Test
  void testEvaluateEndpoint() throws Exception {
    String payload = """
        {
          "type": "times",
          "args": [
            {
              "type": "plus",
              "args": [
                { "type": "number", "value": 3 },
                { "type": "number", "value": 4 }
              ]
            },
            { "type": "number", "value": 5 }
          ]
        }
        """;

    mockMvc.perform(post("/api/evaluate")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.result").value(35))
        .andExpect(jsonPath("$.infix").exists())
        .andExpect(jsonPath("$.pretty").exists())
        .andExpect(jsonPath("$.prefix").exists());
  }

  @Test
  void testEvaluateEndpointReturnsStructuredValidationError() throws Exception {
    String payload = """
        {
          "type": "number"
        }
        """;

    mockMvc.perform(post("/api/evaluate")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.status").value(400))
        .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"))
        .andExpect(jsonPath("$.message").value("Request validation failed."))
        .andExpect(jsonPath("$.path").value("/api/evaluate"))
        .andExpect(jsonPath("$.timestamp").exists())
        .andExpect(jsonPath("$.details", hasItem("number expressions require value and must not define args")));
  }

  @Test
  void testEvaluateEndpointRejectsMalformedJson() throws Exception {
    mockMvc.perform(post("/api/evaluate")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"type\":\"number\",\"value\":1"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value("MALFORMED_JSON"))
        .andExpect(jsonPath("$.path").value("/api/evaluate"));
  }

  @Test
  void testEvaluateEndpointRejectsEmptyBody() throws Exception {
    mockMvc.perform(post("/api/evaluate")
        .contentType(MediaType.APPLICATION_JSON)
        .content(""))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value("REQUEST_BODY_MISSING"));
  }

  @Test
  void testEvaluateEndpointRejectsUnsupportedContentType() throws Exception {
    mockMvc.perform(post("/api/evaluate")
        .contentType(MediaType.TEXT_PLAIN)
        .content("{\"type\":\"number\",\"value\":1}"))
        .andExpect(status().isUnsupportedMediaType())
        .andExpect(jsonPath("$.code").value("UNSUPPORTED_MEDIA_TYPE"));
  }

  @Test
  void testEvaluateEndpointRejectsDivisionByZero() throws Exception {
    String payload = """
        {
          "type": "divides",
          "args": [
            { "type": "number", "value": 8 },
            { "type": "number", "value": 0 }
          ]
        }
        """;

    mockMvc.perform(post("/api/evaluate")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value("ARITHMETIC_ERROR"))
        .andExpect(jsonPath("$.message").value("Division by zero is not allowed for integer numbers"));
  }

  @Test
  void testEvaluateEndpointRejectsTooDeepPayload() throws Exception {
    String payload = """
        {
          "type": "plus",
          "args": [
            {
              "type": "plus",
              "args": [
                {
                  "type": "plus",
                  "args": [
                    {
                      "type": "plus",
                      "args": [
                        { "type": "number", "value": 1 },
                        { "type": "number", "value": 2 }
                      ]
                    },
                    { "type": "number", "value": 3 }
                  ]
                }
                """.formatted("{\"type\":\"number\",\"value\":1},".repeat(80));

        mockMvc.perform(post("/api/evaluate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isPayloadTooLarge())
                .andExpect(jsonPath("$.code").value("REQUEST_TOO_LARGE"));
    }

    @Test
    void testEvaluateTextEndpoint() throws Exception {
        String payload = """
                {
                  "expression": "(3+4)*5"
                }
                """;

        mockMvc.perform(post("/api/evaluate-text")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value("35"))
                .andExpect(jsonPath("$.infix").exists())
                .andExpect(jsonPath("$.pretty").exists())
                .andExpect(jsonPath("$.prefix").exists());
    }

    @Test
    void testEvaluateTextEndpointRejectsBlankExpression() throws Exception {
        String payload = """
                {
                  "expression": "   "
                }
                """;

        mockMvc.perform(post("/api/evaluate-text")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"))
                .andExpect(jsonPath("$.details", hasItem("expression: expression must not be blank")));
    }

    @Test
    void testEvaluateTextEndpointRejectsInvalidSyntax() throws Exception {
        String payload = """
                {
                  "expression": "3++"
                }
                """;

        mockMvc.perform(post("/api/evaluate-text")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("INVALID_REQUEST"))
                .andExpect(jsonPath("$.message").value("Invalid expression syntax."));
    }
}
