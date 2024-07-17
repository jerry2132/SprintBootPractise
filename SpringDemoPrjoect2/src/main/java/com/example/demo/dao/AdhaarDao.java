package com.example.demo.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Adhaar;
import com.example.demo.repo.AdhaarRepo;

@Repository
public class AdhaarDao {
	
	@Autowired
	AdhaarRepo adhaarRepo;
	
	public Optional<Adhaar> findPersonById(Long adhaarNumber) {
		
		return adhaarRepo.findById(adhaarNumber);
	}
	
}
