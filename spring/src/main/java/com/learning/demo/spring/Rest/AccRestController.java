package com.learning.demo.spring.Rest;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.demo.spring.repository.AccDetailsRepository;
import com.learning.demo.spring.Config.PasswordEncoder;
import com.learning.demo.spring.model.AccDetails;


@RestController
@RequestMapping("/acc")
public class AccRestController {
	@Autowired
	AccDetailsRepository accdetailsrepository;
	@Lazy
	@Autowired
	PasswordEncoder passwordencoder;
	
	@PostMapping("/Details/send")
	public ResponseEntity<AccDetails> addAccDetails(@RequestBody AccDetails accdetails)
	{
		try {
			
			String password = accdetails.getPassword();
			
			String Encryptedpassword=passwordencoder.passwordEncode(password);
			AccDetails acc=accdetailsrepository.save(new AccDetails(accdetails.getUsername(), accdetails.getEmail(), Encryptedpassword));
	      
	      System.out.println("successfully created");  
		return new ResponseEntity<>(acc, HttpStatus.CREATED);
		
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
