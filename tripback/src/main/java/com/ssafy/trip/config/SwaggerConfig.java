package com.ssafy.trip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
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
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.trip.controller"))
				.paths(PathSelectors.ant("/**/tripapi/**"))
				.build();
	}
}
