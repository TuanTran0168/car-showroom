package com.tuantran.CarShowroom.configurations.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setName("Trần Đăng Tuấn");
        contact.setUrl("https://www.facebook.com/TuanTran0168");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Car Showroom Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage Car Showroom.")
                .termsOfService("https://www.trandangtuan.com/terms")
                .license(mitLicense);

        // Configure JWT Bearer Authentication to be included in the Request Header
        SecurityScheme securityScheme = new SecurityScheme()
                .name("Bearer Authentication")
                .scheme("bearer")
                .bearerFormat("JWT")
                .type(Type.HTTP)
                .in(SecurityScheme.In.HEADER);

        return new OpenAPI()
                .info(info)
                .components(new Components().addSecuritySchemes("BearerAuth", securityScheme))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"));
    }
}
