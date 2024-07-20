package com.example.demo.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Response.ResponseApi;
import com.example.demo.ServiceInterface.SubjectInterface;
import com.example.demo.dao.SubjectDao;
import com.example.demo.dto.Subject;

@Service
public class SubjectImpl implements SubjectInterface{

	@Autowired
	private SubjectDao subjectDao;
	
	@Override
	public ResponseEntity<ResponseApi<List<Subject>>> saveSubject(List<Subject> sub) {
		// TODO Auto-generated method stub
		
		if(sub.isEmpty() || sub == null) {
			
			ResponseApi<List<Subject>> response = ResponseApi.<List<Subject>>builder().status("error").message("subject must not be null")
					.data(null).build();
			return new ResponseEntity<ResponseApi<List<Subject>>>(response,HttpStatus.NOT_ACCEPTABLE);
		}
		
		subjectDao.saveSubject(sub);
		ResponseApi<List<Subject>> response = ResponseApi.<List<Subject>>builder().status("success").message("subjects saved")
				.data(sub).build();
		return new ResponseEntity<ResponseApi<List<Subject>>>(response,HttpStatus.OK);
	}

}
