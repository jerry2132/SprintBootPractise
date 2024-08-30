package com.example.demo.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.dao.InquiryChannelDao;
import com.example.demo.dao.ManagerDao;
import com.example.demo.dao.ProjectDao;
import com.example.demo.dao.WeeklyFeedBackReportDao;
import com.example.demo.dto.Employee;
import com.example.demo.dto.InquiryChannel;
import com.example.demo.dto.InquiryStatus;
import com.example.demo.dto.Manager;
import com.example.demo.dto.Project;
import com.example.demo.dto.ProjectStatus;
import com.example.demo.dto.User;
import com.example.demo.dto.WeeklyFeedBackReport;
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
	private EmployeeDao employeeDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private InquiryChannelDao inquiryChannelDao;

	@Autowired
	private WeeklyFeedBackReportDao weeklyFeedBackReportDao;

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

	/*******************************************************************************************/

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

		if (projectAlreadyAssociatedToManager(projectId)) {
			throw new IdException("this project is alredy associated to this project");
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
	public ResponseEntity<Response<Manager>> assignEmployeeManager(int managerId, List<Integer> empList) {
		// TODO Auto-generated method stub

//		if (managerDao.findById(managerId).isEmpty()) {
//			throw new IdException("manager id not found");
//		}

		if (managerDao.findById(managerId).isEmpty() ? true
				: !managerDao.findById(managerId).get().getUserName()
						.equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
			throw new NotAuthorized("access denied");
		}

		Manager manager = managerDao.findById(managerId).get();

//		List<Employee> employeeList = manager.getEmployee();

		List<Integer> invalid = new ArrayList<>();
		List<Integer> alreadyAssociated = new ArrayList<>();
		List<Employee> validEmployee = new ArrayList<>();
//		List<Employee> check  = new ArrayList<>();

		for (Integer empId : empList) {

			if (employeeDao.findEmployee(empId).isEmpty())
				invalid.add(empId);

			else if (checkIfemployeeAlreadyHasAManager(empId))
				alreadyAssociated.add(empId);
			else {

				validEmployee.add(employeeDao.findEmployee(empId).get());
			}

		}

//		System.out.println(check);
		System.out.println(invalid);
		System.out.println(alreadyAssociated);
		System.out.println(validEmployee);

		Project project = manager.getProject();

		if (!validEmployee.isEmpty()) {
			validEmployee.addAll(manager.getEmployee());

			for (Employee employee : validEmployee) {

				if (project != null)
					employee.setProject(project);
			}

			manager.setEmployee(validEmployee);
			managerDao.saveManager(manager);
		}

		String finalMessage = getFinalMessage(validEmployee, invalid, alreadyAssociated);

		Response<Manager> response = Response.<Manager>builder().status("success").message(finalMessage).data(manager)
				.build();
		return new ResponseEntity<Response<Manager>>(response, HttpStatus.OK);

	}

	public boolean checkIfemployeeAlreadyHasAManager(int empId) {
		return managerDao.getAllManager().stream().flatMap(e -> e.getEmployee().stream())
				.anyMatch(e -> e.getEmployeeId() == empId);
	}

	public String getFinalMessage(List<Employee> validEmployee, List<Integer> invalid,
			List<Integer> alreadyAssociated) {
		String validMessage = validEmployee.isEmpty() ? ""
				: "id saved "
						+ validEmployee.stream().map(e -> e.getEmployeeId() + " ").collect(Collectors.joining(" | "));

		String invalidMessage = invalid.isEmpty() ? ""
				: " || id's not present "
						+ invalid.stream().map(e -> e.intValue() + " ").collect(Collectors.joining(" | "));

		String alreadyMessage = alreadyAssociated.isEmpty() ? ""
				: " || already associcated "
						+ alreadyAssociated.stream().map(e -> e.intValue() + " ").collect(Collectors.joining(" | "));

		String finalMessage = (validMessage.isEmpty() ? "" : validMessage)
				+ (invalidMessage.isEmpty() ? "" : invalidMessage) + (alreadyMessage.isEmpty() ? "" : alreadyMessage);

		return finalMessage;
	}

	public boolean projectAlreadyAssociatedToManager(int projectId) {
		return managerDao.getAllManager().stream().filter(e -> e.getProject() != null)
				.anyMatch(m -> m.getProject().getProjectId() == projectId);
	}

	/**************************************************************************************************/

	@Override
	public ResponseEntity<Response<Manager>> assignEmployeeProject(int managerId) {
		// TODO Auto-generated method stub

		if (managerDao.findById(managerId).isEmpty())
			throw new IdException("manager Id is not present");

		if (!managerDao.findById(managerId).get().getUserName()
				.equals(SecurityContextHolder.getContext().getAuthentication().getName())) {

			throw new NotAuthorized("access denied");
		}

		Project project = managerDao.findById(managerId).get().getProject();

		if (project == null) {

			Response<Manager> response = Response.<Manager>builder().status("error")
					.message("manager doesnt have any project").data(null).build();
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

		}

		List<Employee> empList = managerDao.findById(managerId).get().getEmployee();

		for (Employee employee : empList) {

			if (employee.getProject() == null)
				employee.setProject(project);
		}

		employeeDao.saveAll(empList);

		Response<Manager> response = Response.<Manager>builder().status("success").message("successfully assocaited ")
				.data(managerDao.findById(managerId).get()).build();

		return new ResponseEntity<Response<Manager>>(response, HttpStatus.OK);
	}

	/******************************************************************************************************/

	@Override
	public ResponseEntity<Response<Employee>> removeEmployeeFromProject(int employeeId) {
		// TODO Auto-generated method stub

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();

		List<Employee> empList = managerDao.findByUserName(userName).get().getEmployee();

		if (!empList.isEmpty()) {

			boolean found = empList.stream().anyMatch(e -> e.getEmployeeId() == employeeId);

			if (found) {
				Employee employee = employeeDao.findEmployee(employeeId).get();
				employee.setProject(null);
				employeeDao.saveEmployee(employee);
			}

			else
				throw new IdException("the manager doesnt contain any employee by this id");
		} else
			throw new IdException("manager doesnt contain any employee");

		Response<Employee> response = Response.<Employee>builder().status("successs").message("employees found")
				.data(employeeDao.findEmployee(employeeId).get()).build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/*******************************************************************************************************/

	public Manager getCurrentManager() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();

		return managerDao.findByUserName(userName).get();
	}

	@Override
	public ResponseEntity<Response<Manager>> changeProjectStatus(String status) {
		// TODO Auto-generated method stub

		Manager manager = getCurrentManager();

		if (manager.getProject() == null)
			throw new IdException("manager doesnt have any project");

		manager.getProject().setStatus(ProjectStatus.valueOf(status));
		managerDao.saveManager(manager);

		Response<Manager> response = Response.<Manager>builder().status("success").message("project statud chnaged")
				.data(manager).build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/*****************************************************************************************************/

	@Override
	public ResponseEntity<Response<List<Employee>>> getAllEmployeeWithGivenRating(int rating) {
		// TODO Auto-generated method stub
		if (rating > 10)
			throw new IdException("searching for rating below 11");

		List<Employee> employeeList = employeeDao.getAllEmployee().stream().filter(e -> e.getEmployeeRating() >= rating)
				.toList();

		Response<List<Employee>> response = Response.<List<Employee>>builder().status("success")
				.message("employees found").data(employeeList).build();

		return new ResponseEntity<Response<List<Employee>>>(response, HttpStatus.OK);
	}

	/*************************************************************************************************************/

	@Override
	public ResponseEntity<Response<List<InquiryChannel>>> getInquiryRequest() {
		// TODO Auto-generated method stub

		int managerId = managerDao.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName())
				.get().getManagerId();

		List<InquiryChannel> inquiryChannelsList = inquiryChannelDao.getAll().stream()
				.filter(e -> e.getInquireToId() == managerId)
				.sorted(Comparator.comparing(InquiryChannel::getCreatedOn).reversed()).toList();

		if (inquiryChannelsList.isEmpty() || inquiryChannelsList == null) {
			Response<List<InquiryChannel>> response = Response.<List<InquiryChannel>>builder().status("error")
					.message("no request").data(null).build();
			return new ResponseEntity<Response<List<InquiryChannel>>>(response, HttpStatus.NOT_FOUND);
		}

		Response<List<InquiryChannel>> response = Response.<List<InquiryChannel>>builder().status("success")
				.message("found ").data(inquiryChannelsList).build();
		return new ResponseEntity<Response<List<InquiryChannel>>>(response, HttpStatus.FOUND);
	}

	/**********************************************************************************************************/

	@Override
	public ResponseEntity<Response<InquiryChannel>> updateInquiryStatus(int channelId, String status) {
		// TODO Auto-generated method stub

		if (inquiryChannelDao.findById(channelId).isEmpty() || channelId < 0) {
			Response<InquiryChannel> response = Response.<InquiryChannel>builder().status("error")
					.message("no data found").data(null).build();
			return new ResponseEntity<Response<InquiryChannel>>(response, HttpStatus.NOT_FOUND);
		}

		InquiryChannel inquiryChannel = inquiryChannelDao.findById(channelId).get();

//	InquiryStatus inquiryStatus = InquiryStatus.va

		inquiryChannel.setInquiryStatus(InquiryStatus.valueOf(status.toUpperCase()));

		inquiryChannelDao.saveInquiry(inquiryChannel);

		Response<InquiryChannel> response = Response.<InquiryChannel>builder().status("success")
				.message("status changed").data(inquiryChannel).build();
		return new ResponseEntity<Response<InquiryChannel>>(response, HttpStatus.OK);

	}

	/***************************************************************************************************************/

	@Override
	public ResponseEntity<Response<List<WeeklyFeedBackReport>>> getWeeklyFeedBackReport() {
		// TODO Auto-generated method stub

		List<WeeklyFeedBackReport> weeklyFeedBackReportList = weeklyFeedBackReportDao.findByManagerIdOrderByCreatedOn(
				managerDao.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).get()
						.getManagerId());

		if (weeklyFeedBackReportList.isEmpty())
			throw new IdException("no report found");

		Response<List<WeeklyFeedBackReport>> response = Response.<List<WeeklyFeedBackReport>>builder()
				.status("successs").message("report found successfully").data(weeklyFeedBackReportList).build();

		return new ResponseEntity<Response<List<WeeklyFeedBackReport>>>(response, HttpStatus.FOUND);
	}

	/*************************************************************************************************************/

	@Override
	public ResponseEntity<Response<WeeklyFeedBackReport>> respondFeedbackReport(int feedbackId,
			WeeklyFeedBackReport weeklyFeedBackReport) {
		// TODO Auto-generated method stub

		WeeklyFeedBackReport weeklyFeedBackReportDb = weeklyFeedBackReportDao.findByFeedbackId(feedbackId)
				.orElseThrow(() -> new IdException("not found " + feedbackId));

		if (managerDao.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).get()
				.getManagerId() != weeklyFeedBackReportDb.getManagerId())
			throw new NotAuthorized("access denied");

		weeklyFeedBackReportDb.setApprovedOn(LocalDateTime.now());
		weeklyFeedBackReportDb.setComments(weeklyFeedBackReport.getComments());
		weeklyFeedBackReportDb.setFeedBackStatus(weeklyFeedBackReport.getFeedBackStatus());
		weeklyFeedBackReportDb.setRating(weeklyFeedBackReport.getRating());
		
		weeklyFeedBackReportDao.saveFeedBack(weeklyFeedBackReportDb);
		
		
		Response<WeeklyFeedBackReport> response = Response.<WeeklyFeedBackReport>builder()
				.status("success").message("updated successfully")
				.data(weeklyFeedBackReportDb).build();
		
		return new ResponseEntity<Response<WeeklyFeedBackReport>>(response,HttpStatus.OK);
	}

	/********************************************************************************************************/

}
