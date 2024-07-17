package com.example.demo.exception;

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

import com.example.demo.entity.Person;
import com.example.demo.response.ResponseApi;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IdError.class)
	public ResponseEntity<ResponseApi<Person>> idErrorHandler(IdError e){
		
		ResponseApi<Person> response = ResponseApi.<Person>builder().status("error").message(e.getMessage())
				.data(null).build();
		
		return new ResponseEntity<ResponseApi<Person>>(response,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseApi<Map<String ,String>>> handleValidationExceptions(MethodArgumentNotValidException e){
		
		Map<String ,String> errors  = new HashMap<>();
		List<ObjectError> objectError = e.getBindingResult().getAllErrors();
		String errorMessage = null;
		
		for (ObjectError  error : objectError) {
			
			String fieldName = ((FieldError) error).getField();
			errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
			
		}
		
		ResponseApi<Map<String ,String>> response = ResponseApi.<Map<String ,String>>builder().status("error").message("validation failed")
				.data(errors).build();
		
		return new ResponseEntity<ResponseApi<Map<String ,String>>>(response,HttpStatus.BAD_REQUEST);
	}
	
}
