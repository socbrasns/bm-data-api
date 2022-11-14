package com.cfg.bm.data.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cfg.bm.data.api.filter.TokenAuthenticationFilter;
import com.cfg.bm.data.api.service.security.AuthenticationService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@ComponentScan(basePackages = { "com.cfg.bm.data.api.*" })
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    UserDetailsPasswordService userDetailsPasswordService;

    @Autowired
    private PasswordEncoder encoder;

    private static final String[] AUTH_SWAGGER_WHITELIST = {
	    // -- Swagger UI v2
	    "/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
	    "/configuration/security", "/swagger-ui.html", "/webjars/**",
	    // -- Swagger UI v3 (OpenAPI)
	    "/v3/api-docs/**", "/swagger-ui/**" };

    private static final String[] AUTH_ACTUATOR_WHITELIST = { "/actuator/health" };

    private static final String[] AUTH_H2_WHITELIST = { "/h2-console/**" };

    private static final String[] AUTH_LOGIN_POST_WHITELIST = {
	    // AUtentication
	    "/auth",
	    // Account Creation
	    "/user/create-account" };

    // Configurations for authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(authenticationService).passwordEncoder(encoder)
		.userDetailsPasswordManager(userDetailsPasswordService);

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
    }

    // Configuration for authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests()
//	.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
		.antMatchers(AUTH_H2_WHITELIST).permitAll().antMatchers(AUTH_ACTUATOR_WHITELIST).permitAll()
		.antMatchers(AUTH_SWAGGER_WHITELIST).permitAll().antMatchers(HttpMethod.POST, AUTH_LOGIN_POST_WHITELIST)
		.permitAll().antMatchers("/error/**").permitAll()

		.anyRequest().authenticated().and()
		.cors().and()
		.csrf().disable()
		.rememberMe().key("rememberme").and()

		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.addFilterBefore(new TokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	http.headers().frameOptions().disable();

    }

    // Configuration for static resources
    @Override
    public void configure(WebSecurity web) throws Exception {

    }

}
