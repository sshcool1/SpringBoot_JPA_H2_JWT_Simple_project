package com.hellcow.testBook.config.jwt;

import com.hellcow.testBook.config.jwt.filter.JWTAuthenticationFilter;
import com.hellcow.testBook.config.jwt.filter.JWTAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.hellcow.testBook.config.jwt.SecurityConstants.*;

@EnableWebSecurity
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST).permitAll()
		.antMatchers(HttpMethod.GET, "/", SIGN_UP_URL).permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager()))
		.addFilter(new JWTAuthorizationFilter(authenticationManager()))
		.logout();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
}
