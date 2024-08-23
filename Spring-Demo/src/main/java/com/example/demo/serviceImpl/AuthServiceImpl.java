package com.example.demo.serviceImpl;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.exception.NotAuthorized;
import com.example.demo.jwt.JwUtil;
import com.example.demo.response.Response;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwUtil jwtUtil;

	@Override
	public ResponseEntity<Response<String>> generateToken(AuthRequest authRequest) {
		// TODO Auto-generated method stub
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

//			System.out.println(authentication.getPrincipal());

			if (authentication.isAuthenticated()) {

				Response<String> response = Response.<String>builder().status("success").message("token genrated")
						.data(jwtUtil.generateToken(authRequest.getUserName())).build();

				return new ResponseEntity<Response<String>>(response, HttpStatus.ACCEPTED);
			}

		} catch (BadCredentialsException e) {
			throw new NotAuthorized("invalid Credentials");
		}
		
		Response<String> response = Response.<String>builder().status("errror").message("Authentication failed ")
				.data(null).build();

		return new ResponseEntity<Response<String>>(response, HttpStatus.ACCEPTED);

	}

}
