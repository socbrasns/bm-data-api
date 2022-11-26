package com.cfg.bm.data.api;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
//@EnableWebMvc
//@EnableWebFlux
//@EnableEurekaClient
//@OpenAPIDefinition(info = @Info(title = "Benchmarker API", version = "1.0", description = "Documentation Benchmarker API v1.0"))
public class BmDataApiApplication {

    public static void main(String[] args) {

	SpringApplication.run(BmDataApiApplication.class, args);
    }
}
