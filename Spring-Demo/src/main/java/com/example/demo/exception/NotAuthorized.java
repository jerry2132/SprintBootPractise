package com.example.demo.exception;

public class NotAuthorized extends RuntimeException {

//	private String message;

	public NotAuthorized(String message) {
		super(message);

	}

}
