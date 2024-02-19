package com.practise.securitytasktwo.config;


import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((request) -> {
		request.requestMatchers("security/api/v2/admin").hasRole("ADMIN")
		.requestMatchers("/security/api/v2/user").hasAnyRole("ADMIN","USER")
		.requestMatchers("/security/api/v2/visitor").hasAnyRole("ADMIN","USER","VISITOR")
		.anyRequest()
		.authenticated();
		})
		.httpBasic(Customizer.withDefaults())
		.formLogin((formLogin) ->
			formLogin
				.successHandler(authenticationSuccessHandler())
		);

		return http.build();
		
	}
	
	@Bean
	UserDetailsService setUpUsers() {
		UserDetails user1 = User.builder()
				.username("user1")
				.password(passwordEncoder().encode("user1"))
				.roles("ADMIN")
				.build();
		
		UserDetails user2 = User.builder()
				.username("user2")
				.password(passwordEncoder().encode("user2"))
				.roles("USER")
				.build();
		
		UserDetails user3 = User.builder()
				.username("user3")
				.password(passwordEncoder().encode("user3"))
				.roles("VISITOR")
				.build();
		
		return new InMemoryUserDetailsManager(user1,user2,user3);
	}
	
	@Bean
	AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}
	
	private static class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
				response.sendRedirect("/security/api/v2/admin");
			}else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
				response.sendRedirect("/security/api/v2/user");
			}else{
				response.sendRedirect("/security/api/v2/visitor");
			}
			
			
		}
		
	}
}
