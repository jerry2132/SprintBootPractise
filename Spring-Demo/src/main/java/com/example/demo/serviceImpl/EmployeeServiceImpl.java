package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.Employee;
import com.example.demo.dto.Manager;
import com.example.demo.dto.User;
import com.example.demo.response.Response;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.RegistrationService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private RegistrationService registrationService;

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

}
