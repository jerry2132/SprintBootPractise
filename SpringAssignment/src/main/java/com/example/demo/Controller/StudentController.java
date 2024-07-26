package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.StudentDao;
import com.example.demo.Entity.Student;
import com.example.demo.ResponseApi.ResponseApi;
import com.example.demo.Service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping(value = "/saveStudentOnly", produces = "application/json")
	public ResponseEntity<ResponseApi<Student>> onlySaveStudent(@RequestBody Student student) {

		return studentService.saveStudentsOnly(student);
	}

	@GetMapping("/studentsWithSubjectMathsScience/{offset}/{pageSize}")
	public ResponseEntity<ResponseApi<List<Student>>> getAllStudentsWithMathsAndScience(@PathVariable int offset,@PathVariable
			int pageSize) {
		return studentService.findAllWithSubjectMathsAndScience(offset,pageSize);
	}
	
	@GetMapping("/deleteFisrt5StudentsFromScience")
	public ResponseEntity<ResponseApi<List<Student>>> findBySubjectName(){
		return studentService.deleteFirstFiveScienceStudents();
	}

}
