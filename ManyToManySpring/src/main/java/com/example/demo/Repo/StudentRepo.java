package com.example.demo.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Student;
import com.example.demo.Entity.StudentKey;

public interface StudentRepo extends JpaRepository<Student, StudentKey>{

	public Optional<Student> findByStudentKey_StudentId(int studentId);
	
}
