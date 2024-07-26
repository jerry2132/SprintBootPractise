package com.example.demo.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	
	public List<Student> findAllStudents(){
		return studentRepo.findAll();
	}
	
//	public Student findSubjec(String subject1, String subject2) {
//		
//		subject1 = "maths";
//		subject2="science";
//		Student stu= studentRepo.findBySubjectName(subject1, subject2);
//		System.out.println("maths and scienc are  "+stu);
//		return stu;
//	}
	
	public List<Student> findStudensBySubjects(String subject1,String subject2,Pageable pageable){
		return studentRepo.findStudentsBySubjects(subject1, subject2,pageable);
	}
	
	public List<Student> findBySubjectName(String subjectName){
		return studentRepo.findBySubject_SubjectName(subjectName);
	}
	
	public void deleteStudent(Student student) {
		 studentRepo.delete(student);
	}
}
