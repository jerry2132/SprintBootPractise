package com.example.SpringDemoProject.exception;

public class IdAlreadyPresent extends RuntimeException{

	String message;

	public IdAlreadyPresent(String message) {
		super(message);
		
	}
	
	
	
}
