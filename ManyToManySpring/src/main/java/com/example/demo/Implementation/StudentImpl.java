package com.example.demo.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.StudentDao;
import com.example.demo.Entity.Student;
import com.example.demo.Entity.StudentKey;
import com.example.demo.Entity.Subject;
import com.example.demo.Entity.SubjectKey;
import com.example.demo.Interface.StudentInterface;
import com.example.demo.ResponseApi.ResponseApi;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.SubjectDto;

import jakarta.transaction.Transactional;

@Service
public class StudentImpl implements StudentInterface {

	@Autowired
	StudentDao studentDao;

	@Transactional
	@Override
	public ResponseEntity<ResponseApi<List<Student>>> saveStudentAndSubject(List<StudentDto> studentDto) {
		// TODO Auto-generated method stub

		List<Student> studentList = studentDto.stream().map(this::saveStudent).collect(Collectors.toList());

		ResponseApi<List<Student>> response = ResponseApi.<List<Student>>builder().status("success")
				.message("saved successfully").data(studentList).build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public Student saveStudent(StudentDto studentDto) {

		List<Subject> subList = studentDto.getSubjectDto().stream().map(this::convertToEntity)
				.collect(Collectors.toList());

		StudentKey studentKey = StudentKey.builder().studentId(studentDto.getStudentId()).build();

		Student student = Student.builder().studentKey(studentKey).name(studentDto.getName())
				.email(studentDto.getEmail()).subject(subList).build();
		System.out.println(student);

		return studentDao.saveStudent(student);

	}

	private Subject convertToEntity(SubjectDto subjectDto) {

		SubjectKey subjectKey = SubjectKey.builder().subjectId(subjectDto.getSubjectId()).build();
		return Subject.builder().subjectKey(subjectKey).name(subjectDto.getName()).author(subjectDto.getAuthor())
				.price(subjectDto.getPrice()).build();

	}

}
