package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Employee;
import com.example.demo.repo.EmployeeRepo;

@Repository
public class EmployeeDao {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	public Employee saveEmployee(Employee emp) {
		return employeeRepo.save(emp);
	}
}
