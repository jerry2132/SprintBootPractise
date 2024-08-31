package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.response.Response;

import lombok.extern.slf4j.Slf4j;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IdException.class)
	public ResponseEntity<Response<String>> handleIdException(IdException e){
		
		Response<String> response = Response.<String>builder().status("error").message(e.getMessage())
				.data(null).build();
		return new ResponseEntity<Response<String>>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotAuthorized.class)
	public ResponseEntity<Response<String>> handleNotAuthorizedException(NotAuthorized e){
		
		Response<String> response = Response.<String>builder().status("error").message(e.getMessage())
				.data(null).build();
		return new ResponseEntity<Response<String>>(response, HttpStatus.NOT_FOUND);
		
	}

	
	@ExceptionHandler(NoDataFound.class)
	public ResponseEntity<Response<String>> handleDataNotFoundException(NoDataFound e){
		
		Response<String> response = Response.<String>builder().status("error").message(e.getMessage())
				.data(null).build();
		return new ResponseEntity<Response<String>>(response, HttpStatus.NOT_FOUND);
		
	}
}
