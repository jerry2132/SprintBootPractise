package com.example.demo.Implementation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.StudentDao;
import com.example.demo.Dao.SubjectDao;
import com.example.demo.Entity.Student;
import com.example.demo.Entity.Subject;
import com.example.demo.Interface.SubjectInterface;
import com.example.demo.ResponseApi.ResponseApi;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.SubjectDto;

import jakarta.transaction.Transactional;

@Service
public class SubjectImpl implements SubjectInterface {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SubjectDao subjectDao;

	@Autowired
	private StudentDao studentDao;

	@Override
	public ResponseEntity<ResponseApi<List<SubjectDto>>> saveMultipleSubjectsOnly(List<SubjectDto> subjectDto) {
		// TODO Auto-generated method stub

		if (subjectDto.isEmpty() || subjectDto == null) {

			ResponseApi<List<SubjectDto>> response = ResponseApi.<List<SubjectDto>>builder().status("error")
					.message("cann not save ").data(null).build();
			return new ResponseEntity<ResponseApi<List<SubjectDto>>>(response, HttpStatus.NOT_ACCEPTABLE);
		}

		Type listType = new TypeToken<List<Subject>>() {
		}.getType();

		List<Subject> subjectList = modelMapper.map(subjectDto, listType);

		subjectDao.saveMultipleSubject(subjectList);

		Type listStudentDto = new TypeToken<List<SubjectDto>>() {
		}.getType();

		List<SubjectDto> subjectDtoList = modelMapper.map(subjectList, listStudentDto);

		ResponseApi<List<SubjectDto>> response = ResponseApi.<List<SubjectDto>>builder().status("success")
				.message("subjects saved successfully ").data(subjectDtoList).build();
		return new ResponseEntity<ResponseApi<List<SubjectDto>>>(response, HttpStatus.OK);
	}

	@Transactional
	@Override
	public ResponseEntity<ResponseApi<List<StudentDto>>> assocaiteASubjectToAllStudents(SubjectDto subjectDto) {
		// TODO Auto-generated method stub

		if (subjectDto == null) {
			ResponseApi<List<StudentDto>> response = ResponseApi.<List<StudentDto>>builder().status("error")
					.message("cann not save ").data(null).build();
			return new ResponseEntity<ResponseApi<List<StudentDto>>>(response, HttpStatus.NOT_ACCEPTABLE);

		}

		Subject subject = modelMapper.map(subjectDto, Subject.class);

//		subjectDao.saveSubject(subject);
		System.out.println(subject);

		List<Student> studentList = studentDao.getAll();
		System.out.println(studentList);


		for (Student student : studentList) {
			
			List<Subject> subjectList = student.getSubject();

			System.out.println("before  "+subjectList);
			
			subjectList.add(subject);
			
			System.out.println("after  "+subjectList);
			
			student.setSubject(subjectList);

		}

		System.out.println(studentList);

		Type listType = new TypeToken<List<StudentDto>>() {
		}.getType();

		List<StudentDto> studentDtoList = modelMapper.map(studentList, listType);

		ResponseApi<List<StudentDto>> response = ResponseApi.<List<StudentDto>>builder().status("success")
				.message("subjects saved successfully ").data(studentDtoList).build();
		return new ResponseEntity<ResponseApi<List<StudentDto>>>(response, HttpStatus.OK);
	}
	
	
	

}
