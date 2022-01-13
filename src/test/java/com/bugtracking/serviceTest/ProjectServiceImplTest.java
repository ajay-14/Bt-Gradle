package com.bugtracking.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.bugtracking.bean.Employee;
import com.bugtracking.bean.Project;
import com.bugtracking.dao.IProjectRepository;
import com.bugtracking.dto.ProjectDto;
import com.bugtracking.mapper.ProjectMapper;
import com.bugtracking.services.ProjectServiceImpl;

public class ProjectServiceImplTest {

	@Mock
	IProjectRepository pr;

	@InjectMocks
	ProjectServiceImpl psi;

	@Mock
	ProjectDto projectDto;

	@Mock
	Project project;

	@Mock
	List<ProjectDto> projectDtoList;

	@Mock
	List<Project> projectList;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		Employee employee1 = new Employee();
		Employee employee2 = new Employee();

		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee1);
		employeeList.add(employee2);

		projectDto.setProjId(12l);
		projectDto.setProjName("bt");
		projectDto.setProjStatus("open");
		projectDto.setMembers(employeeList);

	}

	@Test
	void testAddProject() {
		Mockito.when(pr.save(any(Project.class))).thenReturn(project);
		Project projectDto2 = psi.createproject(project);
		assertEquals(projectDto2.getProjId(), projectDto2.getProjId());
		Mockito.verify(pr, atLeast(1)).save(any(Project.class));
	}

	@Test
	void testProjectById() {
		when(pr.findById(12l)).thenReturn(Optional.of(project));
		ProjectDto projDto1 = ProjectMapper.toProjectDto(project);
		assertEquals(projDto1.getProjId(), psi.getproject(12l).getProjId());
		verify(pr, times(1)).findById(12l);
	}

	@Test
	void testGetProjectAllProject() {
		when(pr.findAll()).thenReturn(projectList);
		assertEquals(projectList, psi.getall());
		verify(pr, times(1)).findAll();
	}

	@Test
	void testUpdateProject() {
		when(pr.existsById(12l)).thenReturn(true);
		when(pr.save(any(Project.class))).thenReturn(project);
		assertEquals(project.getProjId(), psi.updateproject(12l, project).getProjId());
		verify(pr, atLeast(1)).save(any(Project.class));
	}

	@Test
	void testDeleteProjectbyId() {
		when(pr.existsById(12l)).thenReturn(true);
		when(pr.getById(12l)).thenReturn(project);
		assertEquals(project.getProjId(), psi.deleteproject(12l).getProjId());
		verify(pr, times(1)).existsById(12l);
	}

}