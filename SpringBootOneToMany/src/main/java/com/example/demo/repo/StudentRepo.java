package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{

	 @Query("SELECT s FROM Student s WHERE s.id.studentId = :studentId")
	 Optional<Student> findByStudentId(@Param("studentId") int studentId);
	
}
