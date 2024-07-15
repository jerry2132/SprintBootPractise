package com.example.SpringDemoProject.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.example.SpringDemoProject.apiResponse.ApiResponse;
import com.example.SpringDemoProject.dto.College;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(NameNotFound.class)
	public ResponseEntity<ApiResponse<List<College>>> handleNameNotFoundException(NameNotFound e){
		
		ApiResponse<List<College>> response = new ApiResponse<>("error",e.getMessage(),null);
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(FileStorageException.class)
	public ResponseEntity<ApiResponse<College>> handleFileStorageException(FileStorageException e){
		
		ApiResponse<College> response = new ApiResponse<>("error",e.getMessage(),null);
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IdAlreadyPresent.class)
	public ResponseEntity<ApiResponse<College>> handleIdAlreadyPresentException(IdAlreadyPresent e){
		
		ApiResponse<College> response = new ApiResponse<>("error",e.getMessage(),null);
		
		return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
	}
	
	
}
