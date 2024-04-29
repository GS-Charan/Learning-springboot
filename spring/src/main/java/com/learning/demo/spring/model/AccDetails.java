package com.learning.demo.spring.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
@Entity
@Table(name="AccountDetails")
public class AccDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@Column(name = "Username")
	private String username;
	
	@Column(name = "Email id")
	private String email;
	
	@Column(name = "Password")
	private String password;
	
	@JsonCreator
	public AccDetails(@JsonProperty("username")String username,@JsonProperty("email")String email,@JsonProperty("password")String password)
	{
		this.username=username;
		this.email=email;
		this.password=password;


		
	}
	
	public AccDetails()
	{
		
		
	}

	
	public String getUsername()
	{
		return username;
		
	}
	public void setUsername(String username)
	{
		this.username=username;
		
	}
	
	public String getEmail()
	{
		return email;
		
	}
	public void setEmail(String email)
	{
		this.email=email;
		
	}
	public String getPassword()
	{
		return password;
		
	}
	public void setPassword(String password)
	{
		this.password=password;
		
	}
}
