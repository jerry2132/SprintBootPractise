package com.example.demo.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Subject;
import com.example.demo.Repo.SubjectRepo;

@Repository
public class Subjectdao {

	@Autowired
	private SubjectRepo subjectRepo;
	
	
	public Optional<Subject> findSubjectById(int subjectId){
		return subjectRepo.findById(subjectId);
	}
	
	public Subject saveSubject(Subject subject) {
		return subjectRepo.save(subject);
	}
	
}
