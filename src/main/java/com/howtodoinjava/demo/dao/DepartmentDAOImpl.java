package com.howtodoinjava.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.howtodoinjava.demo.model.DepartmentEntity;


@Repository
@Transactional
public class DepartmentDAOImpl implements DepartmentDAO{
	
	private DepartmentEntity dept;
	
	 @PersistenceContext
	 private EntityManager manager;

	public DepartmentEntity getDepartmentById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addDepartment(DepartmentEntity dept) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeDepartment(DepartmentEntity remove) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeAllDepartment(DepartmentEntity removeAll) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<DepartmentEntity> getAllDeparment() {
		List<DepartmentEntity> depts = 
        		manager.createQuery("Select a From department a", DepartmentEntity.class).getResultList();
        return depts;
	}

}
