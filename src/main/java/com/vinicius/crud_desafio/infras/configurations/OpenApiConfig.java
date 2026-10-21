package com.vinicius.crud_desafio.infras.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "CRUD Desafio API", version = "v1", description = "API com autenticação JWT"),
        security = @SecurityRequirement(name = "bearerAuth") // Aplica o token globalmente por padrão (opcional)
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "Insira o token JWT gerado no login no formato: Bearer <seu_token>"
)
public class OpenApiConfig {
}