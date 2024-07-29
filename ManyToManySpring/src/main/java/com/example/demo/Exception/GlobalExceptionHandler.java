package com.example.demo.Exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.ResponseApi.ResponseApi;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String , String>> fieldValidation(MethodArgumentNotValidException e){
		
		Map<String ,String> error = new HashMap<>();
		
		List<ObjectError> objectError = e.getBindingResult().getAllErrors();
		
		for (ObjectError objectError2 : objectError) {
			
			String field  = ((FieldError) error).getField();
			String errorMessage = objectError2.getDefaultMessage();
			
			error.put(field, errorMessage);
		}
		
		return new ResponseEntity<Map<String,String>>(error,HttpStatus.BAD_REQUEST);
		
	}
}
