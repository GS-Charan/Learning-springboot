package com.learning.demo.spring.model;
import jakarta.persistence.*;
@Entity
@Table(name = "Input")
public class Data {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	

}
