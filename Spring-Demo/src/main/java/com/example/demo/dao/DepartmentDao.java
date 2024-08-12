package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Department;
import com.example.demo.repo.DepartmentRepo;

@Repository
public class DepartmentDao {

	@Autowired
	private DepartmentRepo departmentRepo;
	
	public Department saveDepartment(Department dept) {
		return departmentRepo.save(dept);
	}
	
	public Optional<Department> findByDeptId(int id){
		return departmentRepo.findById(id);
	}
	
	public List<Department> getAllDepartment(int pageNumber,int pageSize){
		return departmentRepo.findAll(PageRequest.of(pageNumber, pageSize)).toList();
	}
}
