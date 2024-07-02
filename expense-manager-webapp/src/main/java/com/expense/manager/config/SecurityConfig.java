package com.expense.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.expense.manager.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final CustomUserDetailsService customUserDetailsService;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeRequests()
                // add your resources here. By default, spring security blocks all resources that is not under /resources/**
                .antMatchers(HttpMethod.GET, "/","/resources/**", "/js/**", "/css/**", "/images/**").permitAll()
                // prevent spring security from blocking some pages that doesn't require authentication to be access here.
                .antMatchers("/", "/login","/register").permitAll()
                .anyRequest().authenticated()
            .and()
            // login configuration
            .formLogin()
                .loginPage("/login")// can either be mapping or file
                .permitAll()
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/expenses")
                .usernameParameter("email")
                .passwordParameter("password")
           .and()
           .csrf().disable();
    }
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
	}
	
	
	
	
}
