package com.example.demo.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Manager;
import com.example.demo.repo.ManagerRepo;

@Repository
public class ManagerDao {
	
	@Autowired
	private ManagerRepo managerRepo;
	
	public Manager saveManager(Manager manager) {
		return managerRepo.save(manager);
	}

	public Optional<Manager> findById(int id){
		return managerRepo.findById(id);
	}
	
}
