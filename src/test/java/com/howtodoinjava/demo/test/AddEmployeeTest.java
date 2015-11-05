package com.howtodoinjava.demo.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.howtodoinjava.demo.dao.EmployeeDAO;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.service.EmployeeManager;

import javax.persistence.criteria.*;

import junit.framework.Assert;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:application-context-text.xml")
public class AddEmployeeTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private EmployeeDAO employeeDOA;
	
	@Autowired
	EmployeeManager manager;
	
	//	Test for spring junit set up 
	@SuppressWarnings("deprecation")
	@Test
	public void getEmployee(){
		
		String str="ambar";
		
		Assert.assertEquals("There are matched", "ambar", str);
	}
	
	//show employee list test
	
	
	@Before
	
	
	
	@Test
	public void showEmployeeList() throws Exception{
		List<EmployeeEntity> employees = manager.getAllEmployees();
		System.out.println(employees.get(0).getFirstName());
		
		
	}
	
	public void testAddEmployee() throws Exception{
		EmployeeEntity employee = new EmployeeEntity();
//		List<EmployeeEntity> employees = manager.addEmployee(employee);
		
	}

}
