package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

	public Optional<Manager> findById(int id) {
		return managerRepo.findById(id);
	}

	public List<Manager> getAllManagers(int pageNumber, int pageSize) {
		return managerRepo.findAll(PageRequest.of(pageNumber, pageSize)).toList();
	}

	public List<Manager> getAllManager() {
		return managerRepo.findAll();
	}

	public List<Manager> saveAll(List<Manager> manager) {
		return managerRepo.saveAll(manager);
	}
	
	public Optional<Manager> findByUserName(String userName){
		return managerRepo.findByUserName(userName);
	}
}
