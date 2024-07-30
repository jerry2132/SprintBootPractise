package com.example.demo.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Subject;
import com.example.demo.Repo.SubjectRepo;

@Repository
public class SubjectDao {

	@Autowired
	private SubjectRepo subjectRepo;

	public List<Subject> saveMultipleSubject(List<Subject> subjectList) {

		return subjectRepo.saveAll(subjectList);
	}

	
	public Subject saveSubject(Subject subject) {
		
		return subjectRepo.save(subject);
	}
	
	public List<Subject> getAll(){
		
		return subjectRepo.findAll();
	}
	
	public Optional<Subject> findBySubjectId(int subejctId) {
		
		return subjectRepo.findBySubjectKey_SubjectId(subejctId);
	}
	
}
