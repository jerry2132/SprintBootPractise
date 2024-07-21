package com.example.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Response.ResponseApi;
import com.example.demo.ServiceInterface.SubjectInterface;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.SubjectDao;
import com.example.demo.dto.Student;
import com.example.demo.dto.Subject;

@Service
public class SubjectImpl implements SubjectInterface {

	@Autowired
	private SubjectDao subjectDao;

	@Autowired
	private StudentDao studentDao;

	@Override
	public ResponseEntity<ResponseApi<List<Subject>>> saveSubject(List<Subject> sub) {
		// TODO Auto-generated method stub

		if (sub.isEmpty() || sub == null) {

			ResponseApi<List<Subject>> response = ResponseApi.<List<Subject>>builder().status("error")
					.message("subject must not be null").data(null).build();
			return new ResponseEntity<ResponseApi<List<Subject>>>(response, HttpStatus.NOT_ACCEPTABLE);
		}

		subjectDao.saveSubject(sub);
		ResponseApi<List<Subject>> response = ResponseApi.<List<Subject>>builder().status("success")
				.message("subjects saved").data(sub).build();
		return new ResponseEntity<ResponseApi<List<Subject>>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseApi<List<Subject>>> getAllSubject(Integer offset, Integer pageSize) {
		// TODO Auto-generated method stub

		List<Subject> subjectList = subjectDao.getAllSubjects(offset, pageSize);

		if (subjectList.isEmpty()) {

			ResponseApi<List<Subject>> response = ResponseApi.<List<Subject>>builder().status("error")
					.message("no  subjects found").data(null).build();
			return new ResponseEntity<ResponseApi<List<Subject>>>(response, HttpStatus.NOT_ACCEPTABLE);
		}

		ResponseApi<List<Subject>> response = ResponseApi.<List<Subject>>builder().status("success")
				.message("found subject List").data(subjectList).build();
		return new ResponseEntity<ResponseApi<List<Subject>>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseApi<List<Subject>>> assocaiteSubjectToPresentStudent(List<Subject> sub) {

		if (sub == null || sub.isEmpty()) {
			ResponseApi<List<Subject>> response = ResponseApi.<List<Subject>>builder().status("error")
					.message("Empty list").data(null).build();
			return new ResponseEntity<ResponseApi<List<Subject>>>(response, HttpStatus.NOT_ACCEPTABLE);

		}

		List<Subject> invalid = new ArrayList<>();
		List<Subject> alreadAssocaited = new ArrayList<>();
		List<Subject> valid = new ArrayList<>();

		for (Subject subject : sub) {

			Optional<Subject> optionalSubject = subjectDao.findBySubjectId(subject.getId());

			Optional<Student> optionalStudent = studentDao
					.findByStudentKeyStudentId(subject.getStudent().getId().getStudentId());
			if (optionalSubject.isPresent() && optionalSubject != null && optionalStudent.isPresent()
					&& optionalStudent != null) {
				Subject subject2 = optionalSubject.get();

				Student student = optionalStudent.get();

				if (subject2.getStudent() == null) {
					subject2.setStudent(student);
					valid.add(subject2);
				} else {
					alreadAssocaited.add(subject2);
				}

			} else {

				invalid.add(subject);

			}

		}

		if (!valid.isEmpty()) {
			subjectDao.saveSubject(valid);
		}

		String successMessage = valid.isEmpty() ? ""
				: "Subjects sucessfully associaed with the students with Subjectid  "
						+ valid.stream().map(subject -> subject.getId() + " ").collect(Collectors.joining(" , "));

		String errorMessage = invalid.isEmpty() ? ""
				: "Subjects not present int the database with the Subjectid "
						+ invalid.stream().map(subject -> subject.getId() + " ").collect(Collectors.joining(" , "));

		String alreadyAssociated = alreadAssocaited.isEmpty() ? ""
				: "Subjects already associated with Students with Subjectid  " + alreadAssocaited.stream()
						.map(subject -> subject.getId() + " ").collect(Collectors.joining(" , "));

		String finalMessage = (successMessage.isEmpty() ? " " : " " + successMessage)
				+ (errorMessage.isEmpty() ? " " : " | " + errorMessage)
				+ (alreadyAssociated.isEmpty() ? " " : " | " + alreadyAssociated);

		ResponseApi<List<Subject>> response = ResponseApi.<List<Subject>>builder().status("success")
				.message(finalMessage).data(valid).build();
		return new ResponseEntity<ResponseApi<List<Subject>>>(response, HttpStatus.ACCEPTED);
	}

}
