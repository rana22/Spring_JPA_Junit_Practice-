package com.howtodoinjava.demo.controller;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.howtodoinjava.demo.editors.DepartmentEditor;
import com.howtodoinjava.demo.model.DepartmentEntity;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.service.EmployeeManager;

@Controller
@RequestMapping("/employee-module")
@SessionAttributes("employee")
public class EmployeeController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	@SuppressWarnings("restriction")
	@Resource
    private MessageSource messageSource;
	
	@Autowired
	EmployeeManager manager;

	private Validator validator;
	

	//Bind custom validator for submitted form
	public EmployeeController()
	{
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
	/**
	 * Bind DepartmentEditor to DepartmentEntity; Look at JSP file for clearer picture
	 * */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(DepartmentEntity.class, new DepartmentEditor());
    }
	/**
	 * Bind all departments
	 * */
	@ModelAttribute("allDepartments")
    public List<DepartmentEntity> populateDepartments() 
    {
        List<DepartmentEntity> departments = manager.getAllDepartments();
        return departments;
    }
	
	/**
	 * Bind all employees list
	 * */
	@ModelAttribute("allEmployees")
    public List<EmployeeEntity> populateEmployees() 
    {
        List<EmployeeEntity> employees = manager.getAllEmployees();
        return employees;
    }
	
	/**
	 * Method will be called in initial page load at GET /employee-module
	 * */
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) 
	{
		EmployeeEntity employeeVO = new EmployeeEntity();
		model.addAttribute("employee", employeeVO);
		return "listEmployeeView";
	}

	/**
	 * Method will be called on submitting add employee form POST /employee-module
	 * */
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("employee") EmployeeEntity employeeVO,
			BindingResult result, SessionStatus status) {

		Set<ConstraintViolation<EmployeeEntity>> violations = validator.validate(employeeVO);
		
		for (ConstraintViolation<EmployeeEntity> violation : violations) 
		{
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            // Add JSR-303 errors to BindingResult
            // This allows Spring to display them in view via a FieldError
            result.addError(new FieldError("employee", propertyPath, "Invalid "+ propertyPath + "(" + message + ")"));
        }

		if (result.hasErrors()) {
			return "listEmployeeView";
		}
		// Store the employee information in database
		manager.addEmployee(employeeVO);
		
		// Mark Session Complete and redirect to URL so that page refresh do not re-submit the form
		status.setComplete();
		return "redirect:employee-module";
	}
	private String getMessage(String messageCode, Object... messageParameters) {
        Locale current = LocaleContextHolder.getLocale();
        LOGGER.debug("Current locale is {}", current);
        return messageSource.getMessage(messageCode, messageParameters, current);
    }

}