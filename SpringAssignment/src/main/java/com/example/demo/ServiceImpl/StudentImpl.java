package com.example.demo.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.StudentDao;
import com.example.demo.Entity.Student;
import com.example.demo.ResponseApi.ResponseApi;
import com.example.demo.Service.StudentService;

@Service
public class StudentImpl implements StudentService{

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public ResponseEntity<ResponseApi<Student>> saveStudentsOnly(Student student) {
		// TODO Auto-generated method stub
		
		if(student == null) {
			
			ResponseApi<Student> response = ResponseApi.<Student>builder().status("error").message("can not save null values")
					.data(null).build();
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
		studentDao.saveStudent(student);
		ResponseApi<Student> response = ResponseApi.<Student>builder().status("success").message("student saved successfully")
				.data(student).build();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
