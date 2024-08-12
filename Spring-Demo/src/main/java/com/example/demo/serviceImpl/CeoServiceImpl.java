package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CeoDao;
import com.example.demo.dao.DepartmentDao;
import com.example.demo.dto.Ceo;
import com.example.demo.dto.User;
import com.example.demo.response.Response;
import com.example.demo.service.CeoService;
import com.example.demo.service.RegistrationService;

@Service
public class CeoServiceImpl implements CeoService {

	@Autowired
	private CeoDao ceoDao;

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private DepartmentDao deptDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<Response<Ceo>> addCeo(Ceo ceo) {
		// TODO Auto-generated method stub

		if (ceoDao.findByCeo(ceo.getCeoId()).isPresent()) {
			Response<Ceo> response = Response.<Ceo>builder().status("error").message("already present").data(null)
					.build();
			return new ResponseEntity<Response<Ceo>>(response, HttpStatus.ALREADY_REPORTED);
		}
		

		if (registrationService.registerUser(extractUser(ceo)).getStatusCode()
				.isSameCodeAs(HttpStatus.ALREADY_REPORTED)) {

			Response<Ceo> response = Response.<Ceo>builder().status("error").message("already present").data(ceo)
					.build();
			return new ResponseEntity<Response<Ceo>>(response, HttpStatus.ALREADY_REPORTED);
		}

		ceo.setDepartment(deptDao.getAllDepartment());

		ceo.setPassword(passwordEncoder.encode(ceo.getPassword()));

		ceoDao.saveCeo(ceo);

		Response<Ceo> response = Response.<Ceo>builder().status("success").message("saved successfully").data(ceo)
				.build();

		return new ResponseEntity<Response<Ceo>>(response, HttpStatus.OK);
	}

	public static User extractUser(Ceo ceo) {

		User user = new User(ceo.getCeoId(), ceo.getUserName(), ceo.getEmail(), ceo.getPassword(), ceo.getRole());
		return user;
	}

}
