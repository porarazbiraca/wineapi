package x5.ppln.Swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
    @OpenAPIDefinition(
            info = @Info(
                    title = "Wine Recommendation Api",
                    description = "Wine Recommendation", version = "1.0.0",
                    contact = @Contact(
                            name = "Aleksey Galkin",
                            email = "Aleksey.Galkin@x5.ru",
                            url = "http://localhost:8080/api/"
                    )
            )
    )
    public class OpenApiConfig {

    }
