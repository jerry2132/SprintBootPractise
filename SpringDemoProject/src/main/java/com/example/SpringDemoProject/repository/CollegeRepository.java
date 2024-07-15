package com.example.SpringDemoProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.SpringDemoProject.dto.College;

public interface CollegeRepository extends JpaRepository<College , Integer>{

	
	 public List<College> findByCollegeName(String name);

}
