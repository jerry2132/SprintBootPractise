package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Manager;
import com.example.demo.response.Response;
import com.example.demo.service.ManagerService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@GetMapping("/home")
	public String home() {
		return "manager home";
	}

//	@PreAuthorize("principal.username == #username")
	@GetMapping("/details/{mangerId}")
	public ResponseEntity<Response<Manager>> getDetails(@PathVariable("mangerId") int managerId) {

		return managerService.getDetails(managerId);
	}

}
