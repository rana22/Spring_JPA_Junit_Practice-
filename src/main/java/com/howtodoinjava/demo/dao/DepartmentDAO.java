package com.howtodoinjava.demo.dao;

import java.util.List;

import com.howtodoinjava.demo.model.DepartmentEntity;
import com.howtodoinjava.demo.model.EmployeeEntity;

public interface DepartmentDAO {
	
	public List<DepartmentEntity> getAllDeparment();
	public DepartmentEntity getDepartmentById(Integer id);
	public boolean addDepartment(DepartmentEntity dept);
	public boolean removeDepartment(DepartmentEntity remove);
	public boolean removeAllDepartment(DepartmentEntity removeAll);

}
