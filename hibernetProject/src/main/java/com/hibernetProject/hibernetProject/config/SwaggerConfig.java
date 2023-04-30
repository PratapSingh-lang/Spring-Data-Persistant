package com.hibernetProject.hibernetProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example"))
                .apis(RequestHandlerSelectors.basePackage("com.hibernetProject.hibernetProject.controller")) // put you base package Name here
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        		.title("API CREATED BY BHANU")
        	    .description("API for USER MANAGEMENT Spring Boot application")
        	    .version("1.0")
        	    .contact("Developer - BHANU PRATAP, email : bhanu.pratap@subex.com , domain : www.bel.com")
        	    .license("Apache 2.0")
        	    .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
        	    .termsOfServiceUrl("http://www.example.com/terms-of-service")
        	    .build();
    }
}

