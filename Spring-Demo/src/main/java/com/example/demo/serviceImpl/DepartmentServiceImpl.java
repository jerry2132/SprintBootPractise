package com.example.demo.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.dto.Department;
import com.example.demo.response.Response;
import com.example.demo.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao deptDao;

	@Override
	public ResponseEntity<Response<Department>> saveDepartment(Department dept) {
		// TODO Auto-generated method stub

		Optional<Department> optionalDept = deptDao.findByDeptId(dept.getDepartmentId());

		if (optionalDept.isPresent()) {

			Response<Department> response = Response.<Department>builder().status("error")
					.message("id already present ").data(null).build();

			return new ResponseEntity<Response<Department>>(response, HttpStatus.ALREADY_REPORTED);
		}

		Department department = deptDao.saveDepartment(dept);

		Response<Department> response = Response.<Department>builder().status("success").message("saved successfully")
				.data(department).build();

		return new ResponseEntity<Response<Department>>(response, HttpStatus.OK);

	}

}
