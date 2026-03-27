package calculator.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Oussama HAKIK
 * @description: Configuration class to set up CORS for the API endpoints. It reads allowed origins from application properties and applies them to all /api/** endpoints.
 */
@Configuration
public class ApiWebConfig implements WebMvcConfigurer {

    // List of allowed origins for CORS, read from application properties and processed in the constructor.
    private final List<String> allowedOrigins;

    // Constructor that takes the allowed origins as a comma-separated string, splits it, trims whitespace, and filters out empty entries to create a list of allowed origins.
    public ApiWebConfig(
            @Value("${calculator.api.cors.allowed-origins:http://localhost:3000,http://127.0.0.1:3000}")
            String allowedOrigins
    ) {
        this.allowedOrigins = Arrays.stream(allowedOrigins.split(","))
                .map(String::trim)
                .filter(origin -> !origin.isEmpty())
                .toList();
    }

    /**
     * Configures CORS mappings for the API endpoints. It applies the allowed origins, HTTP methods, and headers to all endpoints under /api/**.
     * @param registry The CorsRegistry to which the CORS configuration will be applied.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(allowedOrigins.toArray(String[]::new))
                .allowedMethods("GET", "POST", "OPTIONS")
                .allowedHeaders("*");
    }
}
