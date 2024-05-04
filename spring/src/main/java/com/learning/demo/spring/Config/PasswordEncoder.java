package com.learning.demo.spring.Config;

import java.security.SecureRandom;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordEncoder {
    @Bean
	public String passwordEncode(String plainPassword)
	{
		
		 BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder(10, new SecureRandom());
		 
		return bCryptPasswordEncoder.encode(plainPassword);
		
	}
	

}
