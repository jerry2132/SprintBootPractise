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
import com.example.demo.dao.ProjectDao;
import com.example.demo.dto.Employee;
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
	private ProjectDao projectDao;

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

		System.out.println(pageNumber + "  " + pageSize);

		List<Manager> managerList = managerDao.getAllManagers(pageNumber, pageSize);

		Response<List<Manager>> response = Response.<List<Manager>>builder().status("success")
				.message("manager deatails").data(managerList).build();

		return new ResponseEntity<Response<List<Manager>>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Response<Manager>> assignManagerAProject(int projectId, int managerId) {
		// TODO Auto-generated method stub

		if (managerDao.findById(managerId).isEmpty()) {
			throw new IdException("this manager id " + managerId + " is not present");
		}

		if (projectDao.findProject(projectId).isEmpty())
			throw new IdException("project id " + projectId + " is not present ");

		if (managerDao.findById(managerId).get().getProject() != null) {
			Response<Manager> response = Response.<Manager>builder().status("error")
					.message("the manager " + managerId + " is " + "already associated to a project ").data(null)
					.build();
			return new ResponseEntity<Response<Manager>>(response, HttpStatus.ALREADY_REPORTED);
		}

		Optional<Manager> manager = managerDao.findById(managerId).map(man -> {
			man.setProject(projectDao.findProject(projectId).get());

			return managerDao.saveManager(man);
		});

		System.out.println(manager.get());

//		
//		Manager man = managerDao.findById(managerId).get();
//		man.setProject(projectDao.findProject(projectId).get());
//
//		Manager manager = managerDao.saveManager(man);

		Response<Manager> response = Response.<Manager>builder().status("success").message("successfullyy associated")
				.data(manager.get()).build();
		return new ResponseEntity<Response<Manager>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Response<List<Manager>>> getAllFreeManagers() {
		// TODO Auto-generated method stub

		List<Manager> managerList = managerDao.getAllManager().stream().filter(e -> e.getProject() == null).toList();
		Response<List<Manager>> response = Response.<List<Manager>>builder().status("success")
				.message(" list of all managers doesnt have any project").data(managerList).build();
		return new ResponseEntity<Response<List<Manager>>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Response<Manager>> assignEmployeeManager(List<Employee> emp, int managerId) {
		// TODO Auto-generated method stub
		
		if(managerDao.findById(managerId).isEmpty()) {
			throw new IdException("manager not found by this id");
		}
		
		if(!managerDao.findById(managerId).get().getUserName().equals(SecurityContextHolder.getContext()
				.getAuthentication().getName())) {
			
			Response<Manager> response = Response.<Manager>builder().status("error")
					.message("cannot access other page").data(null).build();
			return new ResponseEntity<Response<Manager>>(response,HttpStatus.FORBIDDEN);
		}
		
		
		Manager manager = managerDao.findById(managerId).get();
		
		
		
		
		return null;
	}
	
	public boolean checkIfemployeeAlreadyHasAManager(int empId) {
		return managerDao.getAllManager().stream().flatMap(e -> e.getEmployee().stream())
				.anyMatch(e -> e.getEmployeeId() == empId);
	}

}
