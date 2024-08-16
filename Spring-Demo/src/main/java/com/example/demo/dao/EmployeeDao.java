package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
	
	public List<Employee> getAllEmployee(int pageNumber,int size){
		return employeeRepo.findAll(PageRequest.of(pageNumber, size)).toList();
	}
	
	public void deleteEmployee(Employee emp) {
		 employeeRepo.delete(emp);
	}
	
	public Optional<Employee> findEmployee(int empId){
		return employeeRepo.findById(empId);
	}
	
	public List<Employee> saveAll(List<Employee> emplList) {
		return employeeRepo.saveAll(emplList);
	}
}
