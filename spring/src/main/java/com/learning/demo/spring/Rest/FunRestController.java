package com.learning.demo.spring.Rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
	
	@PostMapping("/api/data")
	   public ResponseEntity<String> receiveData(@RequestBody String data) {
        // Process the received data
        System.out.println("Received data: " + data);
        return new ResponseEntity<>("recived data from springboot "+data, HttpStatus.OK);
    }
	//@RequestParam("data") String data
}
