package calculator.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Main class for the Spring Boot application.
 * It starts the embedded server and loads the API beans.
 **/
@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        // Start the Spring Boot application.
        SpringApplication.run(ApiApplication.class, args);
    }
}
