package com.cfg.bm.data.api.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public LocaleResolver localeResolver() {
	SessionLocaleResolver slr = new SessionLocaleResolver();
	var defaultLocale = new Locale.Builder()
//		.setExtension(Locale.UNICODE_LOCALE_EXTENSION, "")
		.setLanguage("en-US")
//		.setLanguageTag("")
		.setLocale(Locale.US)
//		.setRegion("")
//		.setScript("")
//		.setVariant("")
		.build();
	slr.setDefaultLocale(defaultLocale);
	return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
	LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	lci.setParamName("lang");
	return lci;
    }
}
