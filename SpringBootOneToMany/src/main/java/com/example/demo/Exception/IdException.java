package com.example.demo.Exception;

public class IdException extends RuntimeException {

	String message;

	public IdException(String message) {
		super(message);
	}

}
