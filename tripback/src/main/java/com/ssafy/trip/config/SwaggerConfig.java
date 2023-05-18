package com.ssafy.trip.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		final ApiInfo apiInfo = new ApiInfoBuilder()
				.title("Enjoy Trip API")
				.description("<h3>Enjoy Trip에서 사용하는 RestAPI에 대한 문서를 제공합니다.</h3>")
				.contact(new Contact("SSAFY", "http://edu.ssafy.com", "ssafy@email.com"))
				.license("MIT License")
				.version("0.1")
				.build();
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo)
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.trip.controller"))
				.paths(PathSelectors.ant("/**/tripapi/**"))
				.build();
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
	}

	private ApiKey apiKey() {
		return new ApiKey("Authorization", "access-token", "header");
	}
}
