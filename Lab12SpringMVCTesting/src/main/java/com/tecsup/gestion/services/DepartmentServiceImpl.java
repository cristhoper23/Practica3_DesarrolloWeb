package com.tecsup.gestion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.gestion.dao.DepartmentDAO;
import com.tecsup.gestion.exception.DAOException;
import com.tecsup.gestion.exception.EmptyResultException;
import com.tecsup.gestion.model.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentDAO departmentDAO;
	
	@Override
	public Department find(int department_id) throws DAOException, EmptyResultException {
		Department dept = departmentDAO.findDepartment(department_id);

		return dept;
	}

	@Override
	public List<Department> findAll() throws DAOException, EmptyResultException {
		List<Department> depts = departmentDAO.findAllDepartments();
		
		return depts;
	}

	@Override
	public void update(String name, String description, String city) throws DAOException {
		// TODO Auto-generated method stub
		departmentDAO.update(name, description, city);
	}

	@Override
	public void delete(String name) throws DAOException {
		// TODO Auto-generated method stub
		departmentDAO.delete(name);
	}

	@Override
	public void create(String name, String description, String city) throws DAOException {
		// TODO Auto-generated method stub
		departmentDAO.create(name, description, city);
	}

}
