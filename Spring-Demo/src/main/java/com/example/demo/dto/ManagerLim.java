package com.example.demo.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ManagerLim {

	private String managerName;
	
	private long mobile;
	
	private List<Employee> empList;
}
