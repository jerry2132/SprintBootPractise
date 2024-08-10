package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.User;
import com.example.demo.response.Response;

@Service
public interface RegistrationService {

	public ResponseEntity<Response<User>> registerUser(User user);
}
