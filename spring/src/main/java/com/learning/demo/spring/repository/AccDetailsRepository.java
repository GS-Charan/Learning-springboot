package com.learning.demo.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.demo.spring.model.AccDetails;

public interface AccDetailsRepository extends JpaRepository<AccDetails, Long> {
	AccDetails findByUsername(String username);

}
