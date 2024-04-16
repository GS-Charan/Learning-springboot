package com.learning.demo.spring.repository;
import java.util.List;
import com.learning.demo.spring.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Long> {
	List<Data> findById(long id);
	List<Data> findByNameContaining(String name);

}
