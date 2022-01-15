package com.bugtracking.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bugtracking.bean.Bug;

import io.swagger.annotations.ApiOperation;

@RestController
public class EmployeeConsumer {
	RestTemplate rt =new RestTemplate();
	
	@ApiOperation("Used to fetch bugs by status(BY EMPLOYEE)")
	@GetMapping("/employees/bystatus/{bugStatus}")
	public List<Bug> bugsbystatus(@PathVariable("bugStatus") String bugStatus) {
		String endpointEmpBug = "http://localhost:8055/bugs/bystatus/" + bugStatus;
		List<Bug> m = Arrays.asList(rt.getForObject(endpointEmpBug, Bug[].class));
		return m;
	}

	@ApiOperation("Used to create bug(BY EMPLOYEE)")
	@PostMapping("/employee/bugs")
	public String createBug(@Valid @RequestBody Bug b) {
		String endpointEmp = "http://localhost:8055/bugs/";
		rt.postForLocation(endpointEmp, b);
		return "created successfully";
	}



}
