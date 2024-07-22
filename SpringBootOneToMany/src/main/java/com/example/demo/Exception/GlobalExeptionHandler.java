package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.Response.ResponseApi;
import com.example.demo.dto.Student;

@ControllerAdvice
public class GlobalExeptionHandler {

	@ExceptionHandler(IdException.class)
	public ResponseEntity<ResponseApi<Student>> idExceptionHandler(IdException e){
		
		ResponseApi<Student> response = ResponseApi.<Student>builder().status("error").message(e.getMessage())
				.data(null).build();
		return new ResponseEntity<ResponseApi<Student>>(response,HttpStatus.ALREADY_REPORTED);
	}
	
	
}
