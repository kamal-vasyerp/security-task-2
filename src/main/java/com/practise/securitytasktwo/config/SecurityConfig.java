package com.practise.securitytasktwo.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.practise.securitytasktwo.constants.RoleConstants;

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
		request.requestMatchers("security/api/v2/admin").hasRole(RoleConstants.ADMIN)
		.requestMatchers("/security/api/v2/user").hasAnyRole(RoleConstants.ADMIN,RoleConstants.USER)
		.requestMatchers("/security/api/v2/visitor").hasAnyRole(RoleConstants.ADMIN,RoleConstants.USER ,RoleConstants.VISITOR)
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
				.roles(RoleConstants.ADMIN)
				.build();
		
		UserDetails user2 = User.builder()
				.username("user2")
				.password(passwordEncoder().encode("user2"))
				.roles(RoleConstants.USER)
				.build();
		
		UserDetails user3 = User.builder()
				.username("user3")
				.password(passwordEncoder().encode("user3"))
				.roles(RoleConstants.VISITOR)
				.build();
		
		return new InMemoryUserDetailsManager(user1,user2,user3);
	}
	
	@Bean
	AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomSuccessHandler();
	}
	
}
