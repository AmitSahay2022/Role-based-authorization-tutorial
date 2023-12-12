package com.microsoft.tutorial.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SecurityConfiguration {	
	private UserDetailsService userDetailsService;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    @Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
    	security.authorizeHttpRequests(auth->{
    		auth.requestMatchers("/api/users/register").permitAll();
    		auth.requestMatchers(HttpMethod.GET,"/api/users").hasAuthority("Admin");
    		auth.requestMatchers(HttpMethod.GET,"/api/users/**").hasAnyAuthority("User","Admin");
    		auth.requestMatchers(HttpMethod.DELETE,"/api/users/**").hasAuthority("Admin");
    		
    		auth.requestMatchers("/api/test/admin").hasAuthority("Admin");
    		auth.requestMatchers("/api/test/user").hasAnyAuthority("User","Admin");
    		auth.requestMatchers("/api/test/any").permitAll();
    		auth.anyRequest().authenticated();    		
    		
    	});
    	security.csrf(csrf->csrf.disable());
    	security.httpBasic(Customizer.withDefaults());
    	security.formLogin(Customizer.withDefaults());
		return security.build();
	}
}
