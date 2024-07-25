package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.StudentDao;
import com.example.demo.Dao.Subjectdao;
import com.example.demo.Entity.Student;
import com.example.demo.Entity.Subject;
import com.example.demo.ResponseApi.ResponseApi;
import com.example.demo.Service.SubjectInterface;

@Service
public class SubjectImpl implements SubjectInterface {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private Subjectdao subjectDao;

	@Override
	public ResponseEntity<ResponseApi<Student>> saveSubjectAssociateStudent(int studentId, List<Subject> subject) {
		// TODO Auto-generated method stub

		Optional<Student> optionalStudent = studentDao.findById(studentId);

		if (optionalStudent == null || optionalStudent.isEmpty()) {

			ResponseApi<Student> response = ResponseApi.<Student>builder().status("error")
					.message("no studentId found ").data(null).build();
			return new ResponseEntity<ResponseApi<Student>>(response, HttpStatus.NOT_FOUND);

		}

		if (subject == null || subject.isEmpty()) {
			ResponseApi<Student> response = ResponseApi.<Student>builder().status("error").message("subjet is  null")
					.data(null).build();
			return new ResponseEntity<ResponseApi<Student>>(response, HttpStatus.NOT_FOUND);

		}

		Student student = optionalStudent.get();

		List<Subject> subjectList = student.getSubject();

		subjectList.addAll(subject);

		student.setSubject(subjectList);

		studentDao.saveStudent(student);

		ResponseApi<Student> response = ResponseApi.<Student>builder().status("success")
				.message("successfully saved and " + "assocaited subject with student").data(student).build();

		return new ResponseEntity<ResponseApi<Student>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseApi<Subject>> updateSingleSubject(Subject subject) {
		// TODO Auto-generated method stub
		
		Optional<Subject> optionalSubject = subjectDao.findSubjectById(subject.getId());
		
		if(optionalSubject.isEmpty() || optionalSubject == null) {
			ResponseApi<Subject> response = ResponseApi.<Subject>builder().status("error").message("Subject with this id is not found"
					+ "to update")
					.data(null).build();
			return new ResponseEntity<ResponseApi<Subject>>(response, HttpStatus.NOT_FOUND);

		}
		
		Subject subject2 = optionalSubject.get();
		
		subject2.setSubjectName(subject.getSubjectName());
		
		subjectDao.saveSubject(subject2);
		
		ResponseApi<Subject> response = ResponseApi.<Subject>builder().status("success")
				.message("successfully saved and " + "assocaited subject with student").data(subject2).build();

		return new ResponseEntity<ResponseApi<Subject>>(response, HttpStatus.OK);
	}

}
