package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.dao.ManagerDao;
import com.example.demo.dto.Employee;
import com.example.demo.dto.Manager;
import com.example.demo.dto.ManagerLim;
import com.example.demo.dto.User;
import com.example.demo.exception.IdException;
import com.example.demo.exception.NotAuthorized;
import com.example.demo.response.Response;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private ManagerDao managerDao;

	@Override
	public ResponseEntity<Response<Employee>> saveEmployee(Employee employee) {
		// TODO Auto-generated method stub

		User user = extractUser(employee);
//		ResponseEntity<Response<User>> usernw = registrationService.registerUser(user);
//		
//		System.out.println(usernw);
//		
//		System.out.println("code value"+usernw.getStatusCodeValue());
//		System.out.println("has body"+usernw.hasBody());
//		System.out.println("get origin"+usernw.getHeaders().getOrigin());
//		System.out.println("paragma "+usernw.getHeaders().getPragma());
//		System.out.println(" status value "+usernw.getStatusCode().value());
//		System.out.println("getstatus code  "+usernw.getStatusCode());
//		
//		System.out.println("check "+usernw.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(208)));
//		
//		System.out.println(usernw.getStatusCode().value() == 208);
//		System.out.println(usernw.);

		if (registrationService.registerUser(user).getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(208))) {

			Response<Employee> response = Response.<Employee>builder().status("error")
					.message("employee aready present").data(null).build();

			return new ResponseEntity<Response<Employee>>(response, HttpStatus.ALREADY_REPORTED);

		}

		employee.setPassword(passwordEncoder.encode(employee.getPassword()));

		employeeDao.saveEmployee(employee);

		Response<Employee> response = Response.<Employee>builder().status("success")
				.message("employeee saved suucessfully").data(employee).build();

		return new ResponseEntity<Response<Employee>>(response, HttpStatus.OK);
	}

	public static User extractUser(Employee employee) {

		User user = new User(employee.getEmployeeId(), employee.getUserName(), employee.getEmail(),
				employee.getPassword(), employee.getRole());
		return user;
	}

	@Override
	public ResponseEntity<Response<List<Employee>>> getAllemployee(int pageNumber, int size) {
		// TODO Auto-generated method stub

		List<Employee> employeeList = employeeDao.getAllEmployee(pageNumber, size);

		Response<List<Employee>> response = Response.<List<Employee>>builder().status("success")
				.message("employeee saved suucessfully").data(employeeList).build();

		return new ResponseEntity<Response<List<Employee>>>(response, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Response<Employee>> deleteemployee(int empId) {
		// TODO Auto-generated method stub

		Optional<Employee> optionalEmployee = employeeDao.findEmployee(empId);

		if (optionalEmployee.isEmpty() || optionalEmployee == null) {
			Response<Employee> response = Response.<Employee>builder().status("error").message("employeee not found")
					.data(null).build();

			return new ResponseEntity<Response<Employee>>(response, HttpStatus.NOT_FOUND);
		}

		employeeDao.deleteEmployee(optionalEmployee.get());

		Response<Employee> response = Response.<Employee>builder().status("success").message("employeee deleted")
				.data(optionalEmployee.get()).build();

		return new ResponseEntity<Response<Employee>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Response<Employee>> getProfile(int empId) {
		// TODO Auto-generated method stub

		if (employeeDao.findEmployee(empId).isEmpty())
			throw new IdException("id not present");

		if (!employeeDao.findEmployee(empId).get().getUserName()
				.equals(SecurityContextHolder.getContext().getAuthentication().getName()))
			throw new NotAuthorized("access denied");

		Response<Employee> response = Response.<Employee>builder().status("success").message("details found ")
				.data(employeeDao.findEmployee(empId).get()).build();

		return new ResponseEntity<Response<Employee>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Response<ManagerLim>> getManagerDetails() {
		// TODO Auto-generated method stu
		
		log.info("entering service implementation");
		
		log.info("trying to get meployee");
		Employee employee = employeeDao.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName())
				.get();

		log.info("employee got");
//		System.out.println(employee);
		
//		Manager manager = employee.getProject().getManager();   can do this also 
		
		log.info("getting optional Managerr");
		Optional<Manager> optionalManager = managerDao.getAllManager().stream()
				.filter(e -> e.getEmployee().contains(employee)).findAny();
		log.info("optional employee got");
		
		
		if (optionalManager.isEmpty()) {
			throw new IdException("manager is  not assigned to you");
		}

		log.info("getting manager");
		Manager manager = optionalManager.get();
		log.info("manager got");
		
		log.info("converting manager to managerLim");
		ManagerLim managerLim = new ManagerLim((manager.getFirstName() + manager.getLastName()),
				manager.getPhoneNumber(), manager.getEmployee());
		log.info("converted successfully");
		
		Response<ManagerLim> response = Response.<ManagerLim>builder().status("success")
				.message("manager found")
				.data(managerLim).build();
		
		return new ResponseEntity<Response<ManagerLim>>(response,HttpStatus.OK);
	}

}
