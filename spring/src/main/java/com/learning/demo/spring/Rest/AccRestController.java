package com.learning.demo.spring.Rest;

import java.util.ArrayList;
import java.util.List;

import java.util.Set;
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
import com.learning.demo.spring.resources.UserCredentialsDto;
import com.learning.demo.spring.repository.AccDetailsRepository;
import com.learning.demo.spring.Config.PasswordEncoder;
import com.learning.demo.spring.model.AccDetails;
import com.learning.demo.spring.model.Data;

import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class AccRestController {
	@Autowired
	private final AccDetailsRepository accdetailsrepository;
	
	
	
	
	

    public AccRestController(AccDetailsRepository accdetailsrepository) {
        this.accdetailsrepository=accdetailsrepository;
    }
	
    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> register(@RequestBody UserCredentialsDto userCredentialsDto) {
    	AccDetails user =
    			AccDetails.builder()
              .enabled(true)
              .username(userCredentialsDto.getUsername())
              .password(PasswordEncoder.encode(userCredentialsDto.getPassword()))
              .Email(userCredentialsDto.getEmail())
              .roles(Set.of("USER"))
              .build();
    	accdetailsrepository.save(user);
    	System.out.println("successfull");
    	
    	return new ResponseEntity<>("Successfull",HttpStatus.CREATED);

}
}
