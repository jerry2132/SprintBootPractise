package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.Student;
import com.example.demo.dto.StudentKey;

public interface StudentRepo extends JpaRepository<Student, StudentKey>{

	 @Query("SELECT s FROM Student s WHERE s.id.studentId = :studentId")
	 Optional<Student> findByStudentId(@Param("studentId") int studentId);
	
}
