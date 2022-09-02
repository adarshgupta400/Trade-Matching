package com.osttra.trade_matching.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfigs {
	
	@Bean
	public Docket SwaggerDocBean() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.osttra.trade_matching.controller"))
				.paths(regex("/.*"))
				.build()
				.apiInfo(apiInfoGenerator());	
	}
	
	private ApiInfo apiInfoGenerator() {
		return new ApiInfoBuilder().title("Trade Matching")
				.description("Spring boot swagger based application")
				.version("1.0.0")
				.build();
	}
}
//http://localhost:8080/swagger-ui.html
//http://localhost:8080/v2/api-docs
