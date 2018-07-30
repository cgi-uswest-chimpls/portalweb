package com.cgi.uswest.chimpls.portalweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@SpringBootApplication
@EnableFeignClients
@EnableOAuth2Sso
public class PortalwebApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(PortalwebApplication.class, args);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	    	.antMatchers("/login", "/login**", "/webjars/**", "/error**").permitAll()
	        .anyRequest().authenticated()
	        .and().logout().logoutSuccessUrl("/").permitAll()
	        .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	        .and().csrf().disable();
	}
	
}
