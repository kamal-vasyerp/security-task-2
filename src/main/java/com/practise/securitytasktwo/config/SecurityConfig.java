package com.practise.securitytasktwo.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.practise.securitytasktwo.constants.RoleConstants;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests((request) -> {
		request.requestMatchers("security/api/v3/home").permitAll()
		.requestMatchers("security/api/v3/admin").hasRole(RoleConstants.ADMIN)
		.requestMatchers("/security/api/v3/user").hasAnyRole(RoleConstants.ADMIN,RoleConstants.USER)
		.requestMatchers("/security/api/v3/visitor").hasAnyRole(RoleConstants.ADMIN,RoleConstants.USER ,RoleConstants.VISITOR)
		.anyRequest()
		.permitAll();
		})
		.httpBasic(Customizer.withDefaults())
		.formLogin((formLogin) ->
			formLogin
				.successHandler(authenticationSuccessHandler())
		);

		return http.build();
		
	}
	
	@Bean
	AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomSuccessHandler();
	}
	
}
