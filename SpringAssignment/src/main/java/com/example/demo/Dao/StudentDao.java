package com.example.demo.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Student;
import com.example.demo.Repo.StudentRepo;

@Repository
public class StudentDao {

	@Autowired
	private StudentRepo studentRepo;
	
	
	public Student saveStudent(Student student) {
		
		return studentRepo.save(student);
	}
	
	public Optional<Student> findById(int studentId){
		
		return studentRepo.findById(studentId);
	}
}
