package com.pakt.example.socialgoogle1.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	//configure Spring Security - defining how to protect the application and how to authenticate users
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//define what should/shouldn`t be protected 
		//how the authentication must be performed (which is defined to use form basis authentication)
		http.authorizeRequests()
			.antMatchers("/connect/google?*").permitAll()
			.anyRequest().authenticated().and()
			.formLogin().and()
			.logout().permitAll().and()
			.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws 	Exception {
		//predefined users
		auth.inMemoryAuthentication()
			.withUser("adolfo").password("123").authorities("USER")
			.and()
			.withUser("jujuba").password("123").authorities("USER");
	}
}
