package com.howtodoinjava.demo.test.employee.mode;

import org.springframework.test.util.ReflectionTestUtils;

import com.howtodoinjava.demo.model.DepartmentEntity;
import com.howtodoinjava.demo.model.EmployeeEntity;

public class EmployeeBuilder {

	 private EmployeeEntity model;
	 
	 public EmployeeBuilder() {
		 model = new EmployeeEntity();
	 }

	 public EmployeeBuilder id(Integer id){
		 ReflectionTestUtils.setField(model, "id", id);
		 return this;
	 }
	 
	 public EmployeeBuilder firstName(String firstName){
		 ReflectionTestUtils.setField(model, "firstName", firstName);
		 return this;
	 }

	 public EmployeeBuilder lastName(String lastName){
		 ReflectionTestUtils.setField(model, "lastName", lastName);
		 return this;
	 }
	 
	 public EmployeeBuilder email(String email){
		 ReflectionTestUtils.setField(model, "email", email);
		 return this;
	 }
	 
	 public EmployeeBuilder department(DepartmentEntity department){
		 ReflectionTestUtils.setField(model, "department", department);
		 return this;
	 }
	 
	 public EmployeeEntity build() {
	        return model;
	    }
}
