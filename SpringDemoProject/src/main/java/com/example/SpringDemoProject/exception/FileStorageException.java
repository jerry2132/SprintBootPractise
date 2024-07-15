package com.example.SpringDemoProject.exception;

public class FileStorageException extends RuntimeException{

	
	String message;

	public FileStorageException(String message) {
		super(message);
	}
	
	
}
