package com.example.demo.dao;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Person;
import com.example.demo.repo.PersonRepo;

@Repository
public class PersonDao {
	
	@Autowired
	PersonRepo personRepo;
	
	

	public Person savePerson(Person person) {
		return personRepo.save(person);
	}
	

	
}
