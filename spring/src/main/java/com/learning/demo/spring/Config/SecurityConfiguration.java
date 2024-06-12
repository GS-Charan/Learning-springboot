package com.learning.demo.spring.Config;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import com.learning.demo.spring.Config.AuthTokenFilter;
import com.learning.demo.spring.Config.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	  UserDetailsService userDetailsService;
	
	@Bean
	  public AuthTokenFilter authenticationJwtTokenFilter() {
	    return new AuthTokenFilter();
	  }

	  @Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(userDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }

	  @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/api/verify").permitAll()
				.anyRequest().permitAll()
				
				
			)
			.csrf(csrf -> csrf.disable());

		return http.build();
	}
	
	 
	 
	 @Bean
	 public AuthenticationManager authenticationManager(List<AuthenticationProvider> myAuthenticationProviders) {
	     return new ProviderManager(myAuthenticationProviders);
	 }
}
	
	
