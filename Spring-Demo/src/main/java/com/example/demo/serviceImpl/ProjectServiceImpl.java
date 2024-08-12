package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProjectDao;
import com.example.demo.dto.Project;
import com.example.demo.response.Response;
import com.example.demo.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;

	@Override
	public ResponseEntity<Response<List<Project>>> addProject(List<Project> project) {
		// TODO Auto-generated method stub

		List<Project> valid = new ArrayList<>();

		List<Project> alreadyPresent = new ArrayList<>();

		for (Project project2 : project) {

			if (projectDao.findProject(project2.getProjectId()).isPresent())
				alreadyPresent.add(project2);
			else
				valid.add(project2);

		}

		if (!valid.isEmpty()) {
			projectDao.saveAllProjects(valid);
		}

		String validMessage = (valid.isEmpty() ? ""
				: "these id's are succeessfully saved in the database"
						+ valid.stream().map(e -> e.getProjectId() + " ").collect(Collectors.joining(" | ")));

		String alreadyMessage = (alreadyPresent.isEmpty() ? ""
				: "these id's are already present in the database "
						+ alreadyPresent.stream().map(e -> e.getProjectId() + " ").collect(Collectors.joining(" | ")));

		String finalMessage = (validMessage.isEmpty() ? "" : validMessage)
				+ (alreadyMessage.isEmpty() ? "" : alreadyMessage);

		Response<List<Project>> response = Response.<List<Project>>builder().status("success").message(finalMessage)
				.data(valid).build();

		return new ResponseEntity<Response<List<Project>>>(response, HttpStatus.OK);
	}

}
