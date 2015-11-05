package com.howtodoinjava.demo.controller.test;

/**
 author: Ambar Rana
*/

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Set;

import javax.annotation.*;

import junit.framework.Assert;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.howtodoinjava.demo.controller.EmployeeController;
import com.howtodoinjava.demo.model.DepartmentEntity;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.service.EmployeeManager;
import com.howtodoinjava.demo.test.employee.mode.EmployeeBuilder;


@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application-context-text.xml",
"classpath*:/WEB-INF/spring-servlet.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    /*DbUnitTestExecutionListener.class */})
@WebAppConfiguration
//@DatabaseSetup("toDoData.xml")

public class EmployeeControllerTest {

	private EmployeeController controller;

	private MessageSource messageSourceMock;

	private EmployeeManager serviceMock;
	
	private DepartmentEntity dept;
	
	private BindingResult result;
	
	private SessionStatus status;
	
	private MockMvc mockMvc;
	
	@Resource
    private WebApplicationContext webApplicationContext;

//	@SuppressWarnings("restriction")
//	@Resource
//	private Validator validator;
	
//	@Autowired
//    private WebApplicationContext wac;


	@Before
    public void setUp() {
        controller = new EmployeeController();

        messageSourceMock = mock(MessageSource.class);
        ReflectionTestUtils.setField(controller, "messageSource", messageSourceMock);

        serviceMock = mock(EmployeeManager.class);
        ReflectionTestUtils.setField(controller, "manager", serviceMock);
        
//        ReflectionTestUtils.setField(controller, "validator", validator);
        
        result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        
        status = mock(SessionStatus.class);
        when(status.isComplete()).thenReturn(true);
        
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

	@SuppressWarnings("deprecation")
	@Test
    public void showAddEmployeeList() throws Exception{

		String str= "apple";
		Assert.assertEquals(str, "apple");

		dept = new DepartmentEntity(1, "Department of Molecular Biology");

		//Mock adding new row
		EmployeeEntity added = new EmployeeBuilder()
        .firstName("Tom") 
        .lastName("Delong")
        .email("DTom@gmail.com")
        .department(dept).build();
        when(serviceMock.getAllEmployees()).thenReturn(Arrays.asList(added));

        //Mock test for rest call and url path
        mockMvc.perform(get("/employee-module"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("/WEB-INF/views/listEmployeeView.jsp"));
       System.out.println(added.toString());

       controller = new EmployeeController();
       controller.submitForm(added, result, status);

		
	}
}
