package com.example.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.StudentDao;
import com.example.demo.Entity.Student;
import com.example.demo.ResponseApi.ResponseApi;
import com.example.demo.Service.StudentService;

@Service
public class StudentImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public ResponseEntity<ResponseApi<Student>> saveStudentsOnly(Student student) {
		// TODO Auto-generated method stub

		if (student == null) {

			ResponseApi<Student> response = ResponseApi.<Student>builder().status("error")
					.message("can not save null values").data(null).build();
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		studentDao.saveStudent(student);
		ResponseApi<Student> response = ResponseApi.<Student>builder().status("success")
				.message("student saved successfully").data(student).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseApi<List<Student>>> findAllWithSubjectMathsAndScience(int offset, int pageSize) {
		// TODO Auto-generated method stub

//		List<Student> studentList = studentDao.findAllStudents();
//		
//		studentList.stream()
//        .filter(student -> {
//            List<String> subjectNames = student.getSubject().stream()
//                    .map(Subject::getSubjectName)
//                    .collect(Collectors.toList());
//            return subjectNames.contains("Maths") && subjectNames.contains("Science");
//        })
//        .collect(Collectors.toList());
//		
//		return null;

		Pageable pageable = PageRequest.of(offset, pageSize, Sort.Direction.ASC, "name");

		List<Student> studentList = studentDao.findStudensBySubjects("Maths", "Science", pageable);

//		students.stream().sorted(Comparator.comparing(Student::getName)) .collect(Collectors.toList());

		ResponseApi<List<Student>> response = ResponseApi.<List<Student>>builder().status("success")
				.message("students found").data(studentList).build();
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ResponseApi<List<Student>>> deleteFirstFiveScienceStudents() {
		// TODO Auto-generated method stub
		List<Student> studentList = studentDao.findBySubjectName("Science");
		
	List<Student> sortedStudents = 	studentList.stream().sorted(Comparator.comparing(Student :: getId)).toList();

		List<Student> deletedStudents = new ArrayList<>();

		if (studentList.size() > 5) {

			int i = 0;
			for (Student student : sortedStudents) {

				if (i < 5) {
					deletedStudents.add(student);
					student.setSubject(null);
					studentDao.deleteStudent(student);
				}

				i++;
			}

		} else {

			ResponseApi<List<Student>> response = ResponseApi.<List<Student>>builder().status("error")
					.message("cannot delete 5 students , there are less than 5 students havind science as subject").
					data(null).build();
			return new ResponseEntity<>(response, HttpStatus.OK);

		}

		System.out.println(studentList.size());

		ResponseApi<List<Student>> response = ResponseApi.<List<Student>>builder().status("success")
				.message("first five students deleted ").data(deletedStudents).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
