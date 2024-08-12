package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.Manager;
import com.example.demo.response.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IdException.class)
	public ResponseEntity<Response<Manager>> handleIdException(IdException e){
		
		Response<Manager> response = Response.<Manager>builder().status("error").message(e.getMessage())
				.data(null).build();
		return new ResponseEntity<Response<Manager>>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotAuthorized.class)
	public ResponseEntity<Response<String>> handleNotAuthorizedException(NotAuthorized e){
		
		Response<String> response = Response.<String>builder().status("error").message(e.getMessage())
				.data(null).build();
		return new ResponseEntity<Response<String>>(response, HttpStatus.NOT_FOUND);
		
	}
	
}
