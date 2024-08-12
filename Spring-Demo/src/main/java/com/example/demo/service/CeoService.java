package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Ceo;
import com.example.demo.response.Response;

@Service
public interface CeoService {

	public ResponseEntity<Response<Ceo>> addCeo(Ceo ceo);

}
