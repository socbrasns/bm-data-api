package com.cfg.bm.data.api.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.EndpointLinksResolver;
import org.springframework.boot.actuate.endpoint.web.EndpointMapping;
import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes;
import org.springframework.boot.actuate.endpoint.web.ExposableWebEndpoint;
import org.springframework.boot.actuate.endpoint.web.WebEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
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

@Configuration
@OpenAPIDefinition(info = @Info(title = "Benchmarker API", version = "1.0", description = "Documentation Benchmarker API v1.0"))
public class SwaggerConfiguration {

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

    @Bean
    public Docket api() {
	return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo())
		.securityContexts(Arrays.asList(securityContext())).securitySchemes(Arrays.asList(apiKey())).select()
		.apis(RequestHandlerSelectors.basePackage("com.cfg.bm.data.api"))
		.paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
	return new ApiInfo("Benchmarker REST API", "RESTFull API for Benchmarker Application", "1.0",
		"Terms of service",
		new Contact("CFG Soluções Corporativas", "cfgsolucoes.com", "contato@cfgsolucoes.com"),
		"License of API", "API license URL", Collections.emptyList());
    }

    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier,
	    ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier,
	    EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties,
	    WebEndpointProperties webEndpointProperties, Environment environment) {
	List<ExposableEndpoint<?>> allEndpoints = new ArrayList<ExposableEndpoint<?>>();
	Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
	allEndpoints.addAll(webEndpoints);
	allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
	allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
	String basePath = webEndpointProperties.getBasePath();
	EndpointMapping endpointMapping = new EndpointMapping(basePath);
	boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment,
		basePath);
	return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes,
		corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath),
		shouldRegisterLinksMapping, null);
    }

    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment,
	    String basePath) {
	return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath)
		|| ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }
}
