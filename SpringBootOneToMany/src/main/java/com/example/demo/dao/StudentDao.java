package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Student;
import com.example.demo.repo.StudentRepo;

@Repository
public class StudentDao {
	
	
	private StudentRepo studentRepo;

	public StudentDao(StudentRepo studentRepo) {
		this.studentRepo = studentRepo;
	}
	
	
	public Student saveStudent(Student student) {

		return studentRepo.save(student);

	}
	
	
//	public Optional<Student> findByIdStudent(int id){
//		return studentRepo.findById(id);
//	}
//	
	
	public Optional<Student> findByStudentKeyStudentId(int studentId){
		
		return studentRepo.findByStudentId(studentId);
	}
	
	public List<Student> getAllStudent(int offset , int pageSize){
		
		Page<Student> stu = studentRepo.findAll(PageRequest.of(offset, pageSize));
		return stu.getContent();
	}
	
	public void deleteStudent(Student student) {
		studentRepo.delete(student);
	}

}
