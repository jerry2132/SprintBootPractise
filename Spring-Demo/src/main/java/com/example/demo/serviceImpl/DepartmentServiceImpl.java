package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.dao.ManagerDao;
import com.example.demo.dto.Department;
import com.example.demo.dto.Employee;
import com.example.demo.exception.IdException;
import com.example.demo.response.Response;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.ManagerService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao deptDao;

	@Autowired
	private ManagerDao managerDao;

	@Autowired
	private EmployeeDao employeeDao;

	

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

	@Override
	public ResponseEntity<Response<List<Department>>> getAllDepartment(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub

		List<Department> departmentList = deptDao.getAllDepartment(pageNumber, pageSize);

		Response<List<Department>> response = Response.<List<Department>>builder().status("success")
				.message("saved successfully").data(departmentList).build();

		return new ResponseEntity<Response<List<Department>>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Response<Department>> associateDepartmentWithManager(int deptId, int managerId) {
		// TODO Auto-generated method stub

		if (managerDao.findById(managerId).isEmpty())
			throw new IdException("manager id not present");
		if (deptDao.findByDeptId(deptId).isEmpty())
			throw new IdException("dept is not  present");

		System.out.println(deptDao.getAllDepartment().stream().filter(e -> e.getManager() != null)
				.anyMatch(m -> m.getManager().getManagerId() == 50023));

		if (deptDao.findByDeptId(deptId).get().getManager() != null) {
			Response<Department> response = Response.<Department>builder().status("error")
					.message("department aleady has a manager").data(null).build();
			return new ResponseEntity<Response<Department>>(response, HttpStatus.ALREADY_REPORTED);
		}

		if (deptDao.getAllDepartment().stream().map(Department::getManager).filter(Objects::nonNull)
				.anyMatch(e -> e.getManagerId() == managerId)) {
			Response<Department> response = Response.<Department>builder().status("error")
					.message("manager is already associated to some other department").data(null).build();
			return new ResponseEntity<Response<Department>>(response, HttpStatus.ALREADY_REPORTED);
		}

		Optional<Department> department = deptDao.findByDeptId(deptId).map(e -> {
			e.setManager(managerDao.findById(managerId).get());

			return deptDao.saveDepartment(e);
		});

		Response<Department> response = Response.<Department>builder().status("success")
				.message("successfully associated").data(department.get()).build();
		return new ResponseEntity<Response<Department>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Response<Department>> assignEmployeeToDepartment(int deptId, List<Integer> empList) {
		// TODO Auto-generated method stub

		if (deptDao.findByDeptId(deptId).isEmpty())
			throw new IdException("department id is not present");
		
		Department department = deptDao.findByDeptId(deptId).get();
		List<Integer> invalid = new ArrayList<>();
		List<Integer> alreadyPresent = new ArrayList<>();
		List<Employee> validEmployee = new ArrayList<>();

		for (Integer empId : empList) {

			if (employeeDao.findEmployee(empId).isEmpty())
				invalid.add(empId);

			else if(checkIfemployeeAlreadyHasADepartment(empId))
				alreadyPresent.add(empId);
			else
				validEmployee.add(employeeDao.findEmployee(empId).get());

		}
		
		if(!validEmployee.isEmpty()) {
			department.setEmployee(validEmployee);
			deptDao.saveDepartment(department);
			
		}
//		
//		System.out.println(invalid);
//		System.out.println(alreadyPresent);
//		System.out.println(validEmployee);
		
		String validMessage = validEmployee.isEmpty()?"":"id saved "+validEmployee.stream()
			.map(e -> e.getEmployeeId()+" ").collect(Collectors.joining(" | "));
		
		String invalidMessage = invalid.isEmpty()?"":" || id's not present "+invalid.stream()
			.map(e -> e.intValue()+" ")
			.collect(Collectors.joining(" | "));
		
		String alreadyMessage = alreadyPresent.isEmpty()?"":" || already present "+alreadyPresent.stream()
			.map(e -> e.intValue()+" ").collect(Collectors.joining(" | "));
		
		String finalMessage = (validMessage.isEmpty()?"":validMessage) + (invalidMessage.isEmpty()?"":invalidMessage)
				+(alreadyMessage.isEmpty()?"":alreadyMessage);

		Response<Department> response = Response.<Department>builder().status("success")
				.message(finalMessage)
				.data(department).build();
		
		return new ResponseEntity<Response<Department>>(response,HttpStatus.OK);
	}
	
	public boolean checkIfemployeeAlreadyHasADepartment(int empId) {
		return deptDao.getAllDepartment().stream().flatMap(e -> e.getEmployee().stream())
				.anyMatch(m -> m.getEmployeeId() == empId);
	}

}
