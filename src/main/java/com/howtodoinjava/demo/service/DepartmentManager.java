package com.howtodoinjava.demo.service;

import java.util.List;

import com.howtodoinjava.demo.model.DepartmentEntity;

public interface DepartmentManager {
	
	public List<DepartmentEntity> getAllDepartments();
	public void addDepartment(DepartmentEntity dept);

}
