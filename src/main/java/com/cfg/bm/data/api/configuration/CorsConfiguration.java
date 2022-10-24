package com.cfg.bm.data.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class CorsConfiguration implements WebFluxConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//			.allowedOriginPatterns("*")
//			.allowedMethods(HttpMethod.GET.name(), 
//					HttpMethod.POST.name(), 
//					HttpMethod.PUT.name(), 
//					HttpMethod.DELETE.name(),
//					HttpMethod.OPTIONS.name(), 
//					HttpMethod.HEAD.name(), 
//					HttpMethod.TRACE.name())
//			.allowCredentials(false)
//			.allowedHeaders("*")
//			.exposedHeaders("*")
//		;
	}

}
