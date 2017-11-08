package com.tecsup.gestion.services;

import java.util.List;

import com.tecsup.gestion.exception.DAOException;
import com.tecsup.gestion.exception.EmptyResultException;
import com.tecsup.gestion.model.Department;

public interface DepartmentService {

	Department find(int department_id) throws DAOException, EmptyResultException;

	List<Department> findAll() 
			throws DAOException, EmptyResultException;

	void update(String name, String description, String city)
			throws DAOException;
	
	void delete(String name) throws DAOException;

	void create(String name, String description, String city) throws DAOException;
}
