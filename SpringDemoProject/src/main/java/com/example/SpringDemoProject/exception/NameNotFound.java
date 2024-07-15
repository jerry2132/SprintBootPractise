package com.example.SpringDemoProject.exception;




public class NameNotFound extends RuntimeException {

	
    public NameNotFound() {
        super();
    }

    public NameNotFound(String message) {
        super(message);
    }
}

