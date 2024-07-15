package com.example.SpringDemoProject.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.SpringDemoProject.apiResponse.ApiResponse;
import com.example.SpringDemoProject.dao.CollegeDao;
import com.example.SpringDemoProject.dto.College;
import com.example.SpringDemoProject.exception.FileStorageException;
import com.example.SpringDemoProject.exception.IdAlreadyPresent;
import com.example.SpringDemoProject.exception.NameNotFound;

import jakarta.annotation.PostConstruct;

@Service
public class CollegeImpl implements CollegeInterface {

	// List<College> col = new ArrayList<>();

	@Autowired
	private CollegeDao collegeDao;

	@Value("${project.image}")
	private String injectedpath;

	private static String path;

	@PostConstruct
	private void initStaticPath() {
		CollegeImpl.path = this.injectedpath;
	}

//	@Autowired
//	private ApiResponse<List<College>> apiResponseList;

	@Override
	public ResponseEntity<ApiResponse<College>> saveCollege(College college, MultipartFile file) {

		String uniqueFileName = null;

		if (college != null && hasNullOrZeroFields(college) && !file.isEmpty()) {

			Optional<College> col = collegeDao.findCollegeById(college.getId());

			if (col.isPresent()) {
				ApiResponse<College> response = new ApiResponse<>("error", "id already present", null);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

			}

			uniqueFileName = storeImage(file);

			// throw new FileStorageException("file name can not be null");

			college.setImageUrl(uniqueFileName);

			collegeDao.saveCollegeDao(college);

			ApiResponse<College> response = new ApiResponse<>("success", "data and file stored successfully", college);
			return new ResponseEntity<>(response, HttpStatus.CREATED);

		} else {

			ApiResponse<College> response = new ApiResponse<>("error", "some data is null or file is empty", null);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
	}

	public static String storeImage(MultipartFile file) {

		String formattedDateTime = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss").format(LocalDateTime.now());
		String uniqueFilename = formattedDateTime + "_" + file.getOriginalFilename();

		// System.out.println("");

		String filePath = path + File.separator + uniqueFilename;

		//System.out.println("original path from application file === " + path);

		File directory = new File(path);

		//System.out.println("file destination === " + directory);

		if (!directory.exists()) {

			boolean b = directory.mkdir();

			if (!b)
				throw new FileStorageException("failed to create file");
		}

		try {
			Files.copy(file.getInputStream(), Paths.get(filePath));
		} catch (IOException e) {
			throw new FileStorageException("failed to store file ");
		}

		return uniqueFilename;

	}

	private boolean hasNullOrZeroFields(College college) {
		// TODO Auto-generated method stub
		return college.getId() != 0 && !(college.getCollegeName() == null || college.getCollegeName().equals("null"))
				&& !(college.getType() == null || college.getType().equals("null"))
				&& !(college.getAddress() == null || college.getAddress().equals("null"));
	}

	@Override
	public ResponseEntity<ApiResponse<List<College>>> getCollegeDetails() {
		// TODO Auto-generated method stub
		List<College> college = collegeDao.getAllCollege();

		if (!college.isEmpty()) {
			ApiResponse<List<College>> response = new ApiResponse<>("success", "all colege details ", college);

			return new ResponseEntity<>(response, HttpStatus.FOUND);
		}

		ApiResponse<List<College>> response = new ApiResponse<>("error ", "no college to show", college);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	

	@Override
	public ResponseEntity<ApiResponse<List<College>>> saveMultipleCollege(List<College> college, MultipartFile[] file) {
		// TODO Auto-generated method stub
		String uniquefileName = null;
		
//		System.out.println(file.length);
		
		
		if (college.isEmpty() || file == null || file.length != college.size()) {
			ApiResponse<List<College>> response = new ApiResponse<>("error ",
					"something wrong file or college is empty or" + "length of files and college data is not equal",
					null);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		
		List<College> college2 = new ArrayList<>();

		for (int i = 0; i < college.size(); i++) {

			College col = college.get(i);
			Optional<College> optionalCollege = collegeDao.findCollegeById(col.getId());
			
			if(optionalCollege.isPresent()) {
				
				throw new IdAlreadyPresent("This college id is already present");
			}
			
			MultipartFile tempFile = file[i];

			uniquefileName = storeImage(tempFile);

			col.setImageUrl(uniquefileName);
			college2.add(col);

			collegeDao.saveCollegeDao(col);

		}
		
		ApiResponse<List<College>> response = new ApiResponse<>("success","data saved suucessfully",college);
		return new ResponseEntity<>(response , HttpStatus.CREATED);

	}
	
	

	@Override
	public ResponseEntity<ApiResponse<List<College>>> saveMultipleCollegeWithValidation(List<College> college) {
		// TODO Auto-generated method stub

		List<College> college3 = new ArrayList<>();
		for (College college2 : college) {

			if (college2 != null && hasNullOrZeroFields(college2)) {
				college3.add(college2);
			} else {

				ApiResponse<List<College>> response = new ApiResponse<>("error",
						"some data is null, can not save null value", null);
				return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
			}
		}

		collegeDao.saveMultipleCollege(college3);

		ApiResponse<List<College>> response = new ApiResponse<>("success", "daata saved suucessfully", college3);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ApiResponse<College>> findCollegeById(int collegeId) {
		// TODO Auto-generated method stub

		if (collegeId != 0) {
			Optional<College> optionalCollege = collegeDao.findCollegeById(collegeId);

			if (optionalCollege.isPresent()) {

				College college = optionalCollege.get();
				ApiResponse<College> response = new ApiResponse<>("success", "id found ", college);

				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				ApiResponse<College> response = new ApiResponse<>("error",
						"no data found on this id , id doesnt exist ", null);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		}
		ApiResponse<College> response = new ApiResponse<>("errror", "id found can not be 0", null);
		return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
	}
	
	

	@Override
	public ResponseEntity<ApiResponse<List<College>>> findAllByName(String name) {
		// TODO Auto-generated method stub

		if (!name.isEmpty()) {
			List<College> college = collegeDao.findByName(name);

			if (!college.isEmpty()) {
				ApiResponse<List<College>> response = new ApiResponse<>("success", "data found by this name", college);

				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {

				throw new NameNotFound("no such name exist in the database");
//				ApiResponse<List<College>> response = new ApiResponse<>("error", "no data found by this name", null);
//				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}

		} else {

			ApiResponse<List<College>> response = new ApiResponse<>("error", "name can not be empty", null);
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

		}

	}
	
	

	public boolean fieldMatch(String field) {

		return field.equals("collegeName") || field.equals("id") || field.equals("address") || field.equals("type");
	}
	
	

	@Override
	public ResponseEntity<ApiResponse<List<College>>> sortByField(String field) {
		// TODO Auto-generated method stub

		if (field == null || field.isEmpty() || !fieldMatch(field)) {
			ApiResponse<List<College>> response = new ApiResponse<>("error", "No such field: " + field, null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		List<College> colleges = collegeDao.finCollegeBySorting(field);
		if (colleges.isEmpty()) {
			ApiResponse<List<College>> response = new ApiResponse<>("error", "No data found for field: " + field, null);
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}

		ApiResponse<List<College>> response = new ApiResponse<>("success", "Data sorted by " + field, colleges);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<List<College>>> sortAndPaginationGetAll(String field, Integer offSet,
			Integer pageSize) {
		// TODO Auto-generated method stub

		if (field == null || field.isEmpty() || !fieldMatch(field) || offSet < 0 || pageSize <= 0) {

			ApiResponse<List<College>> response = new ApiResponse<>("error", "incorrect url ", null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		List<College> college = collegeDao.paginationAndSorting(offSet, pageSize, field);

		if (college.isEmpty()) {
			ApiResponse<List<College>> response = new ApiResponse<>("error", "no  data available ", null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		ApiResponse<List<College>> response = new ApiResponse<>("success", "Data sorted by " + field, college);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ApiResponse<College>> updateCollege(Integer id , String name) {
		// TODO Auto-generated method stub
		
		Optional<College> col = collegeDao.findCollegeById(id);
		
		if(col.isPresent()) {
			
			College college = col.get();
			
			college.setCollegeName(name);
			collegeDao.saveCollegeDao(college);
			
			
			ApiResponse<College> response = new ApiResponse<>("success", "name updated ", college);
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		}
		
		ApiResponse<College> response = new ApiResponse<>("error", "id not found", null);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

}
