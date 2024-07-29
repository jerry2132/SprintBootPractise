package com.example.demo.ResponseApi;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseApi <T>{
	
	private String status;
	
	private String message;
	
	private T data;

}
