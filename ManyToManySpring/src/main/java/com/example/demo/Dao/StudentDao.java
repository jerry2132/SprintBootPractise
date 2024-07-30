package com.example.demo.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Student;
import com.example.demo.Repo.StudentRepo;

@Repository
public class StudentDao {

	@Autowired
	StudentRepo studentRepo;
	
	
	
	public Student saveStudent(Student student) {
		
		return studentRepo.save(student);
	}
	
	public List<Student> saveAll(List<Student> student){
		
		return studentRepo.saveAll(student);
	}
	
	public List<Student> getAll(){
		
		return studentRepo.findAll();
	}
	
	public Optional<Student> findByStudentId(int studentId) {
		return studentRepo.findByStudentKey_StudentId(studentId);
	}
	
}
