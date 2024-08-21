package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

	public  Optional<Employee> findByUserName(String userName);
	
}
