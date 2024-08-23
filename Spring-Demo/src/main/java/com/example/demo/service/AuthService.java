package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.response.Response;

@Service
public interface AuthService {

	public ResponseEntity<Response<String>> generateToken(AuthRequest authRequest);
	
}
