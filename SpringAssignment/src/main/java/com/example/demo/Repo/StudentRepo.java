package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

//	public Student findBySubjectName(String subject1,String subject2);

	@Query(value = "SELECT s.* FROM student s WHERE s.id IN " + "(SELECT s.id FROM student s "
			+ "JOIN student_subject ss ON s.id = ss.student_id " + "JOIN subject sj ON ss.subject_id = sj.id "
			+ "WHERE sj.subject_name IN (:subject1, :subject2) " + "GROUP BY s.id "
			+ "HAVING COUNT(DISTINCT sj.subject_name) = 2)", nativeQuery = true)
	List<Student> findStudentsBySubjects(@Param("subject1") String subject1, @Param("subject2") String subject2,
			Pageable pageable);
	
	
	  List<Student> findBySubject_SubjectName(String subjectName);
}
