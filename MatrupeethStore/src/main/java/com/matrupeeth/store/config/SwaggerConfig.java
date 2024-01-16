package com.matrupeeth.store.config;

import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket()
    {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.apiInfo(getApiInfo());

        docket.securityContexts(Arrays.asList(getSecurityContext()));
        docket.securitySchemes(Arrays.asList(getSchemes()));

        ApiSelectorBuilder select = docket.select();
        select.apis(RequestHandlerSelectors.any());
        select.paths(PathSelectors.any());
        Docket build = select.build();
        return build;

    }

    private SecurityContext getSecurityContext() {

        SecurityContext context=SecurityContext.builder().securityReferences(getSecurityReferences()).build();
        return  context;

    }

    private List<SecurityReference> getSecurityReferences() {
        AuthorizationScope[] scopes = {
                new AuthorizationScope("Globale","Access every thing ")
        };
        return  Arrays.asList(new SecurityReference("JWT",scopes));
    }

    private SecurityScheme getSchemes() {
        return new ApiKey("JWT","Authorization","header");
    }


    private ApiInfo getApiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Matrupeeth Store Backend APIS",
                "This is backend project create by arun pal",
                "1.0.0V",
                "https://www.matrupeeth.com",
                new Contact("Arun","http://www.matrupeeth.in","arunpal@gmail.com"),
                "License of Api",
                        "https://www.matrupeeth.com",
        new ArrayDeque<>()
        );
        return apiInfo;
    }
}
