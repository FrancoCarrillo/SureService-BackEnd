package com.sureservice_backend.shared.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean(name = "questOpenApi")
    public OpenAPI questOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Quest API")
                        .description("Quest API implemented with Spring Boot"));

    }
}
