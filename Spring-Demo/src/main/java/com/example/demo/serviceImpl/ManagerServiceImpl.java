package com.example.demo.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ManagerDao;
import com.example.demo.dto.Manager;
import com.example.demo.dto.User;
import com.example.demo.exception.IdException;
import com.example.demo.response.Response;
import com.example.demo.service.ManagerService;
import com.example.demo.service.RegistrationService;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerDao managerDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RegistrationService registrationService;

	@Override
	public ResponseEntity<Response<Manager>> saveManager(Manager manager) {
		// TODO Auto-generated method stub

		manager.setPassword(passwordEncoder.encode(manager.getPassword()));
		User user = extractUser(manager);
		if (registrationService.registerUser(user).getStatusCode().isSameCodeAs(HttpStatus.ALREADY_REPORTED)) {

			Response<Manager> response = Response.<Manager>builder().status("error").message("already present")
					.data(null).build();

			return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
		}

		Manager man = managerDao.saveManager(manager);

		Response<Manager> response = Response.<Manager>builder().status("success").message("manager saved suucesfully")
				.data(man).build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public static User extractUser(Manager manager) {

		User user = new User(manager.getManagerId(), manager.getUserName(), manager.getEmail(), manager.getPassword(),
				manager.getRole());
		return user;
	}

	
	
	@Override
	public ResponseEntity<Response<Manager>> getDetails(int managerId) {
		// TODO Auto-generated method stub
		
	Optional<Manager> optionalManager = 	managerDao.findById(managerId);
	
	if(optionalManager == null || optionalManager.isEmpty()) {
		throw new IdException("id not present in the database");
	}
		
	Manager manager  = optionalManager.get();
	
	Response<Manager> response = Response.<Manager>builder().status("success").message("manager deatails")
			.data(manager).build();
	
		return new ResponseEntity<Response<Manager>>(response,HttpStatus.OK);
	}

}
