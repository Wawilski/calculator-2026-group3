package calculator.api.controller;

import calculator.api.ApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureMockMvc
class TestCalculatorApiController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHealthEndpoint() throws Exception {
        // Health endpoint should answer with a simple JSON status.
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    void testEvaluateEndpoint() throws Exception {
        // This request simulates what web UI will send to the API.
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
    void testEvaluateEndpointReturnsBadRequestForInvalidPayload() throws Exception {
        // Invalid payloads should be translated into a 400 error response.
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
                .andExpect(jsonPath("$.error").value("Number expression must provide a non-null value."));
    }
}
