package calculator.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ApiWebConfig implements WebMvcConfigurer {

    // I moved CORS here instead of leaving a global @CrossOrigin on the controller.
    private final List<String> allowedOrigins;

    // The property is a comma-separated string, so I split and clean it once here.
    public ApiWebConfig(
            @Value("${calculator.api.cors.allowed-origins:http://localhost:3000,http://127.0.0.1:3000}")
            String allowedOrigins
    ) {
        this.allowedOrigins = Arrays.stream(allowedOrigins.split(","))
                .map(String::trim)
                .filter(origin -> !origin.isEmpty())
                .toList();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(allowedOrigins.toArray(String[]::new))
                .allowedMethods("GET", "POST", "OPTIONS")
                .allowedHeaders("*");
    }
}
