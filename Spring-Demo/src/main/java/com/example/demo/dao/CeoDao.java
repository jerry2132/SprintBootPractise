package com.example.demo.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Ceo;
import com.example.demo.repo.CeoRepo;

@Repository
public class CeoDao {

	@Autowired
	private CeoRepo ceoRepo;
	
	public Ceo saveCeo(Ceo ceo) {
		return ceoRepo.save(ceo);
	}
	
	public Optional<Ceo> findByCeo(int ceoId){
		return ceoRepo.findById(ceoId);
	}
	
}
