package com.example.demo.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.User;
import com.example.demo.response.Response;
import com.example.demo.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public ResponseEntity<Response<User>> registerUser(User user) {
		// TODO Auto-generated method stub
		
		Optional<User> optionalUser = userDao.findById(user.getUserId());
		
		if(optionalUser.isPresent()) {
			Response<User> response = Response.<User>builder().status("error").message("user already present").data(null).build();
			
			return new ResponseEntity<>(response,HttpStatus.ALREADY_REPORTED);
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User newUser = userDao.saveUser(user);
		
		Response<User> response = Response.<User>builder().status("success").message("user saved").data(newUser).build();
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
