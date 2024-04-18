package com.learning.demo.spring.Rest;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.demo.spring.repository.DataRepository;
import com.learning.demo.spring.model.Data;
@RestController
@RequestMapping("/api")
public class DataRestController {
	@Autowired
	DataRepository datarepository;
	@GetMapping("/names/fetchAll")
	  public ResponseEntity<List<Data>> getAllData(@RequestParam(required = false) String name) {
	    try {
	      List<Data> names = new ArrayList<Data>();
	      datarepository.findAll().forEach(names::add);
	      
	      if (names.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(names, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	@PostMapping("/name/send")
	public ResponseEntity<Data> addData(@RequestBody Data data)
	{
		try {
	      Data data1=datarepository.save(data);
	      
		return new ResponseEntity<>(data1, HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}