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
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.Response.ResponseApi;
import com.example.demo.dto.Student;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalExeptionHandler {

	@ExceptionHandler(IdException.class)
	public ResponseEntity<ResponseApi<Student>> idExceptionHandler(IdException e) {

		ResponseApi<Student> response = ResponseApi.<Student>builder().status("error").message(e.getMessage())
				.data(null).build();
		return new ResponseEntity<ResponseApi<Student>>(response, HttpStatus.ALREADY_REPORTED);
	}
//
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errorMap = new HashMap<>();
	        ex.getBindingResult().getAllErrors().forEach((error) -> {
	            String fieldName = ((FieldError) error).getField();
	            String errorMessage = error.getDefaultMessage();
	            errorMap.put(fieldName, errorMessage);
	            System.out.println(errorMap);
	        });
	        
	       

	        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
	    }
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<ResponseApi<Map<String, String>>> classFieldValidation(MethodArgumentNotValidException e) {
//
//		Map<String, String> errors = new HashMap<>();
//
//		System.out.println(e.getFieldErrorCount());
//
//		e.getBindingResult().getAllErrors().forEach((error) -> {
//			String fieldName = ((FieldError) error).getField();
//			String errorMessage = error.getDefaultMessage();
//			errors.put(fieldName, errorMessage);
//		});
//
//		// Build response with only field name and error message
//		ResponseApi<Map<String, String>> response = ResponseApi.<Map<String, String>>builder().status("error")
//				.message("validation failed").data(errors).build();
//
//		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//
//	}

}
