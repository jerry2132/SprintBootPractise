package com.example.demo.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.StudentDao;
import com.example.demo.Dao.SubjectDao;
import com.example.demo.Entity.Student;
import com.example.demo.Entity.Subject;
import com.example.demo.Interface.StudentInterface;
import com.example.demo.ResponseApi.ResponseApi;
import com.example.demo.dto.StudentDto;

import jakarta.transaction.Transactional;

@Service
public class StudentImpl implements StudentInterface {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private SubjectDao subjectDao;

	@Autowired
	ModelMapper modelMapper;

	// model mapper pom
	@Transactional
	@Override
	public ResponseEntity<ResponseApi<List<Student>>> saveStudentAndSubject(List<StudentDto> studentDto) {
		// TODO Auto-generated method stub
		List<Student> studentList = new ArrayList<>();
		for (StudentDto studentDto2 : studentDto) {

			Student student = modelMapper.map(studentDto2, Student.class);

			studentList.add(student);

//			System.out.println(student);

		}

		studentDao.saveAll(studentList);

//		List<Student> studentList = studentDto.stream().map(this::saveStudent).collect(Collectors.toList());

		ResponseApi<List<Student>> response = ResponseApi.<List<Student>>builder().status("success")
				.message("saved successfully").data(studentList).build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseApi<List<StudentDto>>> saveOnlyStudents(List<StudentDto> studentDto) {
		// TODO Auto-generated method stub

		if (studentDto.isEmpty() || studentDto == null) {

			ResponseApi<List<StudentDto>> response = ResponseApi.<List<StudentDto>>builder().status("error")
					.message("null not accepted").data(null).build();
			return new ResponseEntity<ResponseApi<List<StudentDto>>>(response, HttpStatus.NOT_ACCEPTABLE);
		}

		List<Student> studentList = new ArrayList<>();

		for (StudentDto studentDto2 : studentDto) {

			Student student = modelMapper.map(studentDto2, Student.class);

			studentList.add(student);
//			studentDao.saveStudent(student);

			System.out.println(student);
		}

		studentDao.saveAll(studentList);

		ResponseApi<List<StudentDto>> response = ResponseApi.<List<StudentDto>>builder().status("success")
				.message("saved successfully").data(studentDto).build();
		return new ResponseEntity<ResponseApi<List<StudentDto>>>(response, HttpStatus.OK);
	}

	@Transactional
	@Override
	public ResponseEntity<ResponseApi<StudentDto>> saveStudentAndAssociateAllSubjects(StudentDto studentDto) {
		// TODO Auto-generated method stub

		Student student = modelMapper.map(studentDto, Student.class);

		List<Subject> subjectList = subjectDao.getAll();

		student.setSubject(subjectList);
		
		studentDao.saveStudent(student);
		
		StudentDto  studentDto2  = modelMapper.map(student, StudentDto.class);

		ResponseApi<StudentDto> response = ResponseApi.<StudentDto>builder().status("success")
				.message("saved successfully").data(studentDto2).build();
		return new ResponseEntity<ResponseApi<StudentDto>>(response, HttpStatus.OK);
	}

//	public Student saveStudent(StudentDto studentDto) {
//		
//	//Student student =	modelMapper.map(studentDto, Student.class);
//
//		List<Subject> subList = studentDto.getSubjectDto().stream().map(this::convertToEntity)
//				.collect(Collectors.toList());
//
//		StudentKey studentKey = StudentKey.builder().studentId(studentDto.getStudentId()).build();
//
//		Student student = Student.builder().studentKey(studentKey).name(studentDto.getName())
//				.email(studentDto.getEmail()).subject(subList).build();
//		System.out.println(student);
//
//		return studentDao.saveStudent(student);
//
//	}
//
//	private Subject convertToEntity(SubjectDto subjectDto) {
//
//		SubjectKey subjectKey = SubjectKey.builder().subjectId(subjectDto.getSubjectId()).build();
//		return Subject.builder().subjectKey(subjectKey).name(subjectDto.getName()).author(subjectDto.getAuthor())
//				.price(subjectDto.getPrice()).build();
//
//	}

}
