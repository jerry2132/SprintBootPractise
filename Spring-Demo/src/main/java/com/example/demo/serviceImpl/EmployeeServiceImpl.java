package com.example.demo.serviceImpl;

import java.time.Duration;
import java.util.Comparator;
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
import com.example.demo.dao.InquiryChannelDao;
import com.example.demo.dao.ManagerDao;
import com.example.demo.dao.TimeSheetDao;
import com.example.demo.dao.WeeklyFeedBackReportDao;
import com.example.demo.dto.Employee;
import com.example.demo.dto.FeedBackStatus;
import com.example.demo.dto.InquiryChannel;
import com.example.demo.dto.InquiryStatus;
import com.example.demo.dto.Manager;
import com.example.demo.dto.ManagerLim;
import com.example.demo.dto.TimeSheet;
import com.example.demo.dto.User;
import com.example.demo.dto.WeeklyFeedBackReport;
import com.example.demo.exception.IdException;
import com.example.demo.exception.NoDataFound;
import com.example.demo.exception.NotAuthorized;
import com.example.demo.repo.TimeSheetRepo;
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

	@Autowired
	private InquiryChannelDao inquiryChannelDao;

	@Autowired
	private WeeklyFeedBackReportDao weeklyFeedBackReportDao;

	@Autowired
	private TimeSheetDao timeSheetDao;

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

	/*****************************************************************************************************/

	@Override
	public ResponseEntity<Response<List<Employee>>> getAllemployee(int pageNumber, int size) {
		// TODO Auto-generated method stub

		List<Employee> employeeList = employeeDao.getAllEmployee(pageNumber, size);

		Response<List<Employee>> response = Response.<List<Employee>>builder().status("success")
				.message("employeee saved suucessfully").data(employeeList).build();

		return new ResponseEntity<Response<List<Employee>>>(response, HttpStatus.OK);

	}

	/*******************************************************************************************************/

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

	/*******************************************************************************************************/

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

	/************************************************************************************************************/

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
		ManagerLim managerLim = new ManagerLim(manager.getManagerId(), (manager.getFirstName() + manager.getLastName()),
				manager.getPhoneNumber(), manager.getEmployee());
		log.info("converted successfully");

		Response<ManagerLim> response = Response.<ManagerLim>builder().status("success").message("manager found")
				.data(managerLim).build();

		return new ResponseEntity<Response<ManagerLim>>(response, HttpStatus.OK);
	}

	/*******************************************************************************************************/

	@Override
	public ResponseEntity<Response<InquiryChannel>> raiseInquiryRequest(InquiryChannel inquiryChannel) {
		// TODO Auto-generated method stub

		if (getManagerDetails().getBody().getData().getManagerId() != inquiryChannel.getInquireToId()) {

			Response<InquiryChannel> response = Response.<InquiryChannel>builder().status("error")
					.message("the rquested inquiry manager is not assigned to you").data(null).build();
			return new ResponseEntity<Response<InquiryChannel>>(response, HttpStatus.NOT_FOUND);
		}

		Employee employee = employeeDao.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName())
				.get();

		inquiryChannel.setCreatedById(employee.getEmployeeId());
		inquiryChannel.setInquiryStatus(InquiryStatus.RAISED);

		inquiryChannelDao.saveInquiry(inquiryChannel);

		Response<InquiryChannel> response = Response.<InquiryChannel>builder().status("success")
				.message("request raised with id   " + inquiryChannel.getChannelId()).data(inquiryChannel).build();
		return new ResponseEntity<Response<InquiryChannel>>(response, HttpStatus.ACCEPTED);
	}

	/************************************************************************************************************/

	@Override
	public ResponseEntity<Response<List<InquiryChannel>>> getRequestStatus() {
		// TODO Auto-generated method stub

		int empId = employeeDao.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).get()
				.getEmployeeId();

		List<InquiryChannel> inquiryChannelList = inquiryChannelDao.getAll().stream()
				.filter(e -> e.getCreatedById() == empId)
				.sorted(Comparator.comparing(InquiryChannel::getCreatedOn).reversed()).toList();

		if (inquiryChannelList.isEmpty() || inquiryChannelList == null) {
			Response<List<InquiryChannel>> response = Response.<List<InquiryChannel>>builder().status("error")
					.message("no req available ").data(null).build();
			return new ResponseEntity<Response<List<InquiryChannel>>>(response, HttpStatus.NOT_FOUND);
		}

		Response<List<InquiryChannel>> response = Response.<List<InquiryChannel>>builder().status("success")
				.message("data found ").data(inquiryChannelList).build();
		return new ResponseEntity<Response<List<InquiryChannel>>>(response, HttpStatus.FOUND);
	}

	/************************************************************************************************************/

	@Override
	public ResponseEntity<Response<WeeklyFeedBackReport>> sendFeedBack(WeeklyFeedBackReport weeklyFeedBackReport) {
		// TODO Auto-generated method stub

		if (weeklyFeedBackReport.getManagerId() != getManagerDetails().getBody().getData().getManagerId()) {

			throw new IdException("no manager with this id");
		}

		Employee employee = employeeDao.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName())
				.get();

		weeklyFeedBackReport.setEmployeeId(employee.getEmployeeId());
		weeklyFeedBackReport.setFeedBackStatus(FeedBackStatus.PENDING);

		weeklyFeedBackReportDao.saveFeedBack(weeklyFeedBackReport);

		Response<WeeklyFeedBackReport> response = Response.<WeeklyFeedBackReport>builder().status("suucess")
				.message("feedback send suucessfully").data(weeklyFeedBackReport).build();
		return new ResponseEntity<Response<WeeklyFeedBackReport>>(response, HttpStatus.ACCEPTED);
	}

	/************************************************************************************************************/

	@Override
	public ResponseEntity<Response<List<WeeklyFeedBackReport>>> viewFeedbackStatus() {
		// TODO Auto-generated method stub

		int employeeId = employeeDao.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName())
				.get().getEmployeeId();

		List<WeeklyFeedBackReport> weeklyFeedBackReportList = weeklyFeedBackReportDao.findByEmployeeId(employeeId);

		if (weeklyFeedBackReportList.isEmpty())
			throw new NoDataFound("doesnt contain any report ");

		Response<List<WeeklyFeedBackReport>> response = Response.<List<WeeklyFeedBackReport>>builder().status("success")
				.message("report found").data(weeklyFeedBackReportList).build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/************************************************************************************************************/

	@Override
	public ResponseEntity<Response<TimeSheet>> fillTimeSheet(TimeSheet timeSheet) {
		// TODO Auto-generated method stub

		Employee employee = employeeDao.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName())
				.get();

		timeSheet.setManagerId(getManagerDetails().getBody().getData().getManagerId());

		timeSheet.setEmployeeId(employee.getEmployeeId());
		timeSheet.setEmployeeName(employee.getFirstName() + " " + employee.getLastName());

		timeSheet.getDailyEntries().stream().filter(e -> e.getCheckInTime() != null && e.getCheckOutTime() != null)
				.forEach(e -> {
					Duration duration = Duration.between(e.getCheckInTime(), e.getCheckOutTime());
					e.setHoursWorked(duration.toMinutes() / 60.0);
				});

		timeSheet.setTotalHoursWorked(timeSheet.getDailyEntries().stream().mapToDouble(e -> e.getHoursWorked()).sum());

		timeSheet.setStatus(FeedBackStatus.PENDING);

		timeSheetDao.saveTimeSheet(timeSheet);

		Response<TimeSheet> response = Response.<TimeSheet>builder().status("successs").message("timesheet filled")
				.data(timeSheet).build();

		return new ResponseEntity<Response<TimeSheet>>(response, HttpStatus.OK);
	}

	/***********************************************************************************************************/

	@Override
	public ResponseEntity<Response<List<TimeSheet>>> viewTimeSheet() {
		// TODO Auto-generated method stub

		List<TimeSheet> timeSheetList = timeSheetDao.findByEmployeeId(
				employeeDao.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).get()
					.getEmployeeId());

		Response<List<TimeSheet>> response = Response.<List<TimeSheet>>builder().status("success")
				.message("timesheet list")
				.data(timeSheetList).build();
		
		return new ResponseEntity<Response<List<TimeSheet>>>(response,HttpStatus.OK);
	}

	/************************************************************************************************************/

}
