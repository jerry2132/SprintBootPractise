package com.example.demo.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Subject;
import com.example.demo.Entity.SubjectKey;

public interface SubjectRepo extends JpaRepository<Subject, SubjectKey>{
	

	public Optional<Subject> findBySubjectKey_SubjectId(int subjectId);

}
