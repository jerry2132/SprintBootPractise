package com.example.demo.Implementation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

			System.out.println("before  " + subjectList);

			subjectList.add(subject);

			System.out.println("after  " + subjectList);

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

	@Transactional
	@Override
	public ResponseEntity<ResponseApi<SubjectDto>> updatePriceBySubjectId(int subjectId, double price) {
		// TODO Auto-generated method stub

		Optional<Subject> subjectOptional = subjectDao.findBySubjectId(subjectId);

		if (subjectOptional == null || subjectOptional.isEmpty()) {

			ResponseApi<SubjectDto> response = ResponseApi.<SubjectDto>builder().status("error")
					.message("cann not save ").data(null).build();
			return new ResponseEntity<ResponseApi<SubjectDto>>(response, HttpStatus.NOT_ACCEPTABLE);

		}

		Subject subject = subjectOptional.get();

		subject.setPrice(price);

		SubjectDto subjectDto = modelMapper.map(subject, SubjectDto.class);
		ResponseApi<SubjectDto> response = ResponseApi.<SubjectDto>builder().status("success")
				.message("subject updated successfully").data(subjectDto).build();
		return new ResponseEntity<ResponseApi<SubjectDto>>(response, HttpStatus.OK);
	}

	@Transactional
	@Override
	public ResponseEntity<ResponseApi<List<StudentDto>>> associateSingleSubjectToMultipleStudents(int subjectId,
			List<StudentDto> studentDto) {
		// TODO Auto-generated method stub

		Optional<Subject> subjectOptional = subjectDao.findBySubjectId(subjectId);

		if (subjectOptional == null || subjectOptional.isEmpty()) {

			ResponseApi<List<StudentDto>> response = ResponseApi.<List<StudentDto>>builder().status("error")
					.message("no data found" + "by this subjectId ").data(null).build();

			return new ResponseEntity<ResponseApi<List<StudentDto>>>(response, HttpStatus.NOT_FOUND);
		}

		List<StudentDto> notFound = new ArrayList<>();
		List<Student> valid = new ArrayList<>();
		List<Student> alreadyAssociated = new ArrayList<>();
		Subject subject = subjectOptional.get();
		for (StudentDto studentDto2 : studentDto) {

			Optional<Student> studentOptional = studentDao.findByStudentId(studentDto2.getStudentKey().getStudentId());

			if (studentOptional.isEmpty() || studentOptional == null) {
				notFound.add(studentDto2);
				continue;
			}

			Student student = studentOptional.get();

//			List<Subject> subjectList = student.getSubject();

			if (student.getSubject().contains(subject)) {
				alreadyAssociated.add(student);
			} else {

				student.getSubject().add(subject);
				valid.add(student);
			}

		}

		if (!valid.isEmpty()) {

//			List<Student> studentList = subject.getStudent();
//			System.out.println(studentList);
//			studentList.addAll(valid);
//			subject.setStudent(studentList);
//			System.out.println(subject);
//			subjectDao.saveSubject(subject);

//			subject.getStudent().addAll(valid);
//
//			subjectDao.saveSubject(subject);
			
			for (Student student : valid) {
				
				studentDao.saveStudent(student);
			}
		}

		Type listType = new TypeToken<List<StudentDto>>() {
		}.getType();

		List<StudentDto> studentDtoList = modelMapper.map(valid, listType);

		String notfoundMessage = (notFound.isEmpty() ? "" : "these students are not found in the db")
				+ notFound.stream().map(e -> e.getStudentKey().getStudentId() + " ").collect(Collectors.joining(" , "));

		String validMessage = (valid.isEmpty() ? "" : "these students are successfully associated in the database")
				+ valid.stream().map(e -> e.getStudentKey().getStudentId() + " ").collect(Collectors.joining(" , "));

		String alreadyMessage = (alreadyAssociated.isEmpty() ? "" : "these students are already associated")
				+ alreadyAssociated.stream().map(e -> e.getStudentKey().getStudentId() + " ")
						.collect(Collectors.joining(" , "));

		String finalMessage = (notfoundMessage.isEmpty() ? "" : " | " + notfoundMessage)
				+ (validMessage.isEmpty() ? "" : " | " + validMessage)
				+ (alreadyMessage.isEmpty() ? " " : " | " + alreadyMessage);

		if (!valid.isEmpty() && valid != null) {

			ResponseApi<List<StudentDto>> response = ResponseApi.<List<StudentDto>>builder().status("success")
					.message(finalMessage).data(studentDtoList).build();

			return new ResponseEntity<ResponseApi<List<StudentDto>>>(response, HttpStatus.OK);
		}

		ResponseApi<List<StudentDto>> response = ResponseApi.<List<StudentDto>>builder().status("error")
				.message(finalMessage).data(null).build();

		return new ResponseEntity<ResponseApi<List<StudentDto>>>(response, HttpStatus.NOT_ACCEPTABLE);

	}

}
