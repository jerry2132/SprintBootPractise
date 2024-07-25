package com.example.demo.ResponseApi;

import lombok.Builder;

@Builder
public class ResponseApi <T>{
	
	private String status;
	
	private String message;
	
	private T data;

}
