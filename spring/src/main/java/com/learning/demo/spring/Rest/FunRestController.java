package com.learning.demo.spring.Rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
	@Value("${my.name}")
	String name;
	@GetMapping("/")
	public String hello()
	{
		
		return name;
	}

}
