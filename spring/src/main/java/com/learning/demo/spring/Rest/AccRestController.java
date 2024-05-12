package com.learning.demo.spring.Rest;

import java.util.ArrayList;
import java.util.List;
import com.learning.demo.spring.Config.SecurityConfiguration;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.learning.demo.spring.resources.UserCredentialsDto;
import com.learning.demo.spring.resources.UserInfoResponseDto;
import com.learning.demo.spring.resources.VerifyDto;
import com.learning.demo.spring.repository.AccDetailsRepository;
import com.learning.demo.spring.Config.JwtUtils;
import com.learning.demo.spring.Config.PasswordEncoder;
import com.learning.demo.spring.model.AccDetails;
import com.learning.demo.spring.model.Data;
import org.springframework.security.authentication.AuthenticationManager;

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
	
	@Autowired
	  AuthenticationManager authenticationManager;
	
	
	 @Autowired
	  JwtUtils jwtUtils;

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
              .roles("USER")
              .build();
    	accdetailsrepository.save(user);
    	System.out.println("successfull");
    	
    	return new ResponseEntity<>("Successfull",HttpStatus.CREATED);

    }
    
    @PostMapping("/verify")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void Verify( @RequestBody VerifyDto verifyDto)
    {
    	String username=verifyDto.getUsername();
    	
    	
        AccDetails userDetails1 = accdetailsrepository.findByUsername(username);
        
        if (userDetails1 == null) 
        {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        
        String encodedPassword = userDetails1.getPassword();
        if (PasswordEncoder.matches(verifyDto.getPassword(), encodedPassword)) 
        {
        	
        	
            

            Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(verifyDto.getUsername(), verifyDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            AccDetails userDetails = (AccDetails) authentication.getPrincipal();

            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

            
            
            String role = userDetails.getRoles();
;
			System.out.println(ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
	                .body(new UserInfoResponseDto(userDetails.getEmail(),
                            userDetails.getUsername(),
                            userDetails.getId(),
                            role)));

//            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//                .body(new UserInfoResponseDto(userDetails.getEmail(),
//                                           userDetails.getUsername(),
//                                           userDetails.getId(),
//                                           role));
            
            
        } else {
            // Passwords don't match, handle this scenario accordingly
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }
        
    	
    	
    }
}
