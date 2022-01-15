package com.bugtracking.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.bugtracking.bean.Employee;
import com.bugtracking.controller.EmployeeController;
import com.bugtracking.dto.EmployeeDto;
import com.bugtracking.services.IEmployeeService;

public class EmployeeControllerTest {
	long empId;
	@Mock
	public EmployeeDto employeeDto;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		employeeDto.setEmpId(1);
		employeeDto.setEmpName("aravi");
		employeeDto.setEmpEmail("aravicoder@gmail.com");
		employeeDto.setEmpContactNo("8123456789");
	}

	@InjectMocks
	EmployeeController employeeController;

	@Mock
	IEmployeeService employeeService;

	@Mock
	List<EmployeeDto> employeeDtolist;
	@Mock
	Employee employee;

	@Test
	void testAddEmployee() {
		Mockito.when(employeeService.createEmployee(employeeDto)).thenReturn(employeeDto);
		assertEquals(employeeDto, employeeController.createemployee(employeeDto));
		Mockito.verify(employeeService).createEmployee(employeeDto);
	}

	@Test
	void testGetEmployee() {
		when(employeeService.getEmployee(empId)).thenReturn(employeeDto);
		assertEquals(employeeDto, employeeController.getemployee(empId));
		verify(employeeService).getEmployee(empId);
	}

	@Test
	void testGetAllEmployee() {
		when(employeeService.getAllEmployees()).thenReturn(employeeDtolist);
		assertEquals(employeeDtolist, employeeController.allemployees());
		verify(employeeService).getAllEmployees();
	}

	@Test
	void testUpdateEmployee() {
		when(employeeService.updateEmployee(empId, employeeDto)).thenReturn(employeeDto);
		assertEquals(employeeDto, employeeController.updateemployee(empId, employeeDto));
		verify(employeeService).updateEmployee(empId, employeeDto);
	}

	@Test
	void testDeleteEmployee() {
		when(employeeService.deleteEmployee(12)).thenReturn(employeeDto);
		assertEquals(employeeDto, employeeController.deleteemployee(12));
		verify(employeeService).deleteEmployee(12);

	}

}