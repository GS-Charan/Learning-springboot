package com.learning.demo.spring.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="AccountDetails")
public class AccDetails {
	@Id 
	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
	private String username;

	  private String password;
	  private String Email;

	  boolean enabled;
	
	  
	  
	  @Column(name = "authority")
	    private String roles;


		

	
	
}
