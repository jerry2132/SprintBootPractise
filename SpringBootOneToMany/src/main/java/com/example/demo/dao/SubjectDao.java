package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Subject;
import com.example.demo.repo.SubjectRepo;

@Repository
public class SubjectDao {
	
	@Autowired
	private SubjectRepo subjectRepo;
	
	
	public List<Subject> saveSubject(List<Subject> subject) {
		return subjectRepo.saveAll(subject);
	}
	
	public Optional<Subject> findBySubjectId(int id){
		return subjectRepo.findById(id);
	}
	
	public Subject saveSingleSubject(Subject subject) {
		return subjectRepo.save(subject);
	}
	
	public List<Subject> getAllSubjects(int offset,int pageSize){
		
		Page<Subject> sub = subjectRepo.findAll(PageRequest.of(offset, pageSize));
		
		return sub.getContent();
	}

}
