package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ManagerDao;
import com.example.demo.dto.Manager;
import com.example.demo.dto.User;
import com.example.demo.exception.IdException;
import com.example.demo.exception.NotAuthorized;
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

		User user = extractUser(manager);
		if (registrationService.registerUser(user).getStatusCode().isSameCodeAs(HttpStatus.ALREADY_REPORTED)) {

			Response<Manager> response = Response.<Manager>builder().status("error").message("already present")
					.data(null).build();

			return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
		}

		manager.setPassword(passwordEncoder.encode(manager.getPassword()));

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

		Optional<Manager> optionalManager = managerDao.findById(managerId);

		if (optionalManager == null || optionalManager.isEmpty()) {
			throw new IdException("id not present in the database");
		}

//		if (!optionalManager.get().getUserName().equals(req.getUserPrincipal().getName())) {
//
//			throw new NotAuthorized("you are not authorized to access this url");
//
//		}

		if (!optionalManager.get().getUserName()
				.equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
			throw new NotAuthorized("you are not authorized to access this url");
		}

		Manager manager = optionalManager.get();

		Response<Manager> response = Response.<Manager>builder().status("success").message("manager deatails")
				.data(manager).build();

		return new ResponseEntity<Response<Manager>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Response<List<Manager>>> getAllManagers(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		
		System.out.println(pageNumber+"  "+pageSize);
		
		List<Manager> managerList = managerDao.getAllManagers(pageNumber,pageSize);

		Response<List<Manager>> response = Response.<List<Manager>>builder().status("success")
				.message("manager deatails").data(managerList).build();

		return new ResponseEntity<Response<List<Manager>>>(response, HttpStatus.OK);
	}

}
