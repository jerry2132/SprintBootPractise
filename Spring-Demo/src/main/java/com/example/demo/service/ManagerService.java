package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Employee;
import com.example.demo.dto.Manager;
import com.example.demo.response.Response;

@Service
public interface ManagerService {

	public ResponseEntity<Response<Manager>> saveManager(Manager manager);
	
	public ResponseEntity<Response<Manager>> getDetails(int managerId);
	
	public ResponseEntity<Response<List<Manager>>> getAllManagers(int pageNumber,int pageSize);
	
	public ResponseEntity<Response<Manager>> assignManagerAProject(int projectId,int managerId);
	
	public ResponseEntity<Response<List<Manager>>> getAllFreeManagers();//those doesnt have any projects

	public ResponseEntity<Response<Manager>> assignEmployeeManager(List<Employee> emp, int managerId);
	
	public boolean checkIfemployeeAlreadyHasAManager(int empId);
	
}
