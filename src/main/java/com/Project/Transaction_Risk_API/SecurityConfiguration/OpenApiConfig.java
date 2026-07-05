package com.Project.Transaction_Risk_API.SecurityConfiguration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Shivang Parmar",
                        email = "shivangparmar91@gmail.com"

                ),
                title = "Transaction Risk Detection API",
                description = "REST API for detecting transaction fraud risk and managing transactions.",
                version = "1.0"

        )
)
public class OpenApiConfig {
}
