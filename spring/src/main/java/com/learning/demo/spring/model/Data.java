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
	
	
	@Column(name = "time")
	private String time;
	
	
	@JsonCreator
	public Data(@JsonProperty("name")String name,@JsonProperty("time")String time)
	{
		this.name=name;
		this.time=time;
		
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
	
	public String getTime()
	{
		return time;
		
	}
	public void setTime(String time)
	{
		this.time=time;
		
	}

}
