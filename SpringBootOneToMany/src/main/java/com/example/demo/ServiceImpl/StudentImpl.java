package com.example.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.IdException;
import com.example.demo.Response.ResponseApi;
import com.example.demo.ServiceInterface.StudentInterface;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.SubjectDao;
import com.example.demo.dto.Student;
import com.example.demo.dto.Subject;

@Service
public class StudentImpl implements StudentInterface {

	@Autowired
	private StudentDao studentDao;
	@Autowired
	private SubjectDao subjectDao;

	@Override
	public ResponseEntity<ResponseApi<Student>> saveStudent(Student student) {
		// TODO Auto-generated method stub

		if (student != null) {

			if (student.getSubject() != null) {
				List<Subject> sub = student.getSubject();

				for (Subject subject : sub) {

					subject.setStudent(student);
				}
			}

			studentDao.saveStudent(student);

			ResponseApi<Student> response = ResponseApi.<Student>builder().status("success")
					.message("student and subject associated" + "and saved successfully").data(student).build();

			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		ResponseApi<Student> response = ResponseApi.<Student>builder().status("error").message("not saved ").data(null)
				.build();

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<ResponseApi<Student>> associateStudentToSubject(Student student) {
		// TODO Auto-generated method stub

		int studentId = student.getId().getStudentId();
		System.out.println("Student id = " + studentId);
		System.out.println(student.getSubject());
		Optional<Student> optionalStudent = studentDao.findByStudentKeyStudentId(studentId);

		if (optionalStudent.isEmpty() || optionalStudent == null) {
			ResponseApi<Student> response = ResponseApi.<Student>builder().status("error")
					.message("no student found by this id").data(null).build();

			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		List<Subject> invalid = new ArrayList<>();
		List<Subject> alreadyAssocaited = new ArrayList<>();
		List<Subject> valid = new ArrayList<>();

		Student student2 = optionalStudent.get();

		System.out.println("sutdent is ==  " + student2);

		List<Subject> subjectList = student.getSubject();

		if (!subjectList.isEmpty() && subjectList != null) {

			for (Subject subject2 : subjectList) {

				Optional<Subject> optionalSubject = subjectDao.findBySubjectId(subject2.getId());

				if (optionalSubject.isEmpty() || optionalSubject == null) {
//					ResponseApi<Student> response = ResponseApi.<Student>builder().status("error")
//							.message("no subject found by this id").data(null).build();
//
//					return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

					invalid.add(subject2);

				}

				else {

					Subject subject = optionalSubject.get();
					System.out.println(subject);

					if (subject.getStudent() != null) {

						alreadyAssocaited.add(subject);
//						ResponseApi<Student> response = ResponseApi.<Student>builder().status("error")
//								.message("subject id "+subject.getId()+"is already associated").data(null).build();
//
//						return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);

//						throw new IdException("Id is already associated to some other student " + subject.getId());

					} else {

						subject.setStudent(student2);
						subjectDao.saveSingleSubject(subject);
						System.out.println("Subject is " + subject);
						valid.add(subject2);
					}

				}

			}

			// subjectDao.saveSubject(subjectList);

			// System.out.println(subjectList);

			String errrorMessage = invalid.isEmpty() ? ""
					: "Following subject id is not present in the database "
							+ invalid.stream().map(subject -> subject.getId() + " ").collect(Collectors.joining(" , "));

			String alreadyPresent = alreadyAssocaited.isEmpty() ? ""
					: "Following subjectId is already associated to some other student " + alreadyAssocaited.stream()
							.map(subject -> subject.getId() + " ").collect(Collectors.joining(" , "));

			String finalMessage = (errrorMessage.isEmpty() ? "" : errrorMessage)
					+ (alreadyPresent.isEmpty() ? "" : " | "+alreadyPresent);
			
			

			if (!valid.isEmpty()) {
				ResponseApi<Student> response = ResponseApi.<Student>builder().status("success").message(finalMessage)
						.data(student2).build();

				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {

				ResponseApi<Student> response = ResponseApi.<Student>builder().status("success").message(finalMessage)
						.data(null).build();

				return new ResponseEntity<>(response, HttpStatus.OK);
			}

		}

		ResponseApi<Student> response = ResponseApi.<Student>builder().status("error").message("subject list is empty")
				.data(null).build();

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

	}

	@Override
	public ResponseEntity<ResponseApi<List<Student>>> getAllStudents(int offset, int pageSize) {
		// TODO Auto-generated method stub

		List<Student> studentList = studentDao.getAllStudent(offset, pageSize);
		ResponseApi<List<Student>> response = ResponseApi.<List<Student>>builder().status("success")
				.message("Student List ").data(studentList).build();

		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}

}
