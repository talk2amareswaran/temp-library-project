package com.example.librarydemo.oauth;

import static com.example.librarydemo.oauth.Constants.SIGN_UP_URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@EnableWebSecurity
@Component
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll().anyRequest()
				.authenticated().and().addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager()));
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(bCryptPasswordEncoder);
		return authProvider;
	}
}
