package com.learning.demo.spring.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
@Entity
@Table(name = "Input")
public class Data {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;
	@JsonCreator
	public Data(@JsonProperty("name")String name)
	{
		this.name=name;
		
	}
	public Data()
	{		
		
	}
	
	public String getName()
	{
		return name;
		
	}
	public void setName(String name)
	{
		this.name=name;
		
	}

}
