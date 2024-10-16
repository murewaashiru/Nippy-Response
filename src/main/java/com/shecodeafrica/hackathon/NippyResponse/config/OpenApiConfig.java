package com.shecodeafrica.hackathon.NippyResponse.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;

@Configuration
@OpenAPIDefinition(servers = {@Server(url = "${springdoc.api-docs.path}", description = "${info.app.description}")})
public class OpenApiConfig {
    @Value("${info.app.version}")
    private String applicationVersion;
    @Value("${info.app.description}")
    private String applicationDescription;
    @Value("${info.app.name}")
    private String applicationName;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title(applicationName)).externalDocs(new ExternalDocumentation());
    }

    @Bean
    public ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }

}
