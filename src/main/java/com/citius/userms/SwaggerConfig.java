package com.citius.userms;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.service.*;
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.securityContexts(Arrays.asList(securityContext()))
        	      .securitySchemes(Arrays.asList(apiKey()))	
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Title: Users Resource APIs")
                .version("1.5")
                .description("APIs for managing Users")
                .contact(new Contact("Rod Stevenson", "http://rodstevenson.com", "rod.steve@swagger.com"))
                .license("Edu license version 1.2")
                .build();
    }
    
    private ApiKey apiKey() { 
        return new ApiKey("JWT", "Authorization", "header"); 
    }
    
    private SecurityContext securityContext() { 
        return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
    } 

    private List<SecurityReference> defaultAuth() { 
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
        authorizationScopes[0] = authorizationScope; 
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
    }
}

