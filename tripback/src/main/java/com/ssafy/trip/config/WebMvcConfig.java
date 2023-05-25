package com.ssafy.trip.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.trip.exceptionresolver.JwtHandlerExceptionResolver;
import com.ssafy.trip.interceptor.JwtInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
	
	private final long MAX_AGE_SECS = 3600;
	
	private final List<String> patterns = Arrays.asList(
			"/tripapi/board/**",
			"/tripapi/plan/**"
			);
	
	private final JwtInterceptor jwtInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor)
			.addPathPatterns(patterns);
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:8081")
		.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
		.allowedHeaders("*")
		.allowCredentials(true)
		.maxAge(MAX_AGE_SECS);
	}
	
	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		resolvers.add(new JwtHandlerExceptionResolver());
	}
	
	@Override 
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/* '/js/**'로 호출하는 자원은 '/static/js/' 폴더 아래에서 찾는다. */
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/")
				.setCachePeriod(60 * 60 * 24 * 365);
		/* '/css/**'로 호출하는 자원은 '/static/css/' 폴더 아래에서 찾는다. */
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/")
				.setCachePeriod(60 * 60 * 24 * 365);
		/* '/img/**'로 호출하는 자원은 '/static/img/' 폴더 아래에서 찾는다. */
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/")
				.setCachePeriod(60 * 60 * 24 * 365);
		/* '/font/**'로 호출하는 자원은 '/static/font/' 폴더 아래에서 찾는다. */
		registry.addResourceHandler("/font/**").addResourceLocations("classpath:/static/font/")
				.setCachePeriod(60 * 60 * 24 * 365);
		
		registry.addResourceHandler("/tripapi/userimg/**").addResourceLocations("classpath:/static/userimg/")
		.setCachePeriod(1);
	}
}
