package com.tecsup.gestion.dao;

import java.util.List;

import com.tecsup.gestion.exception.DAOException;
import com.tecsup.gestion.exception.EmptyResultException;
import com.tecsup.gestion.model.Department;

public interface DepartmentDAO {

	Department findDepartment(int department_id) throws DAOException, EmptyResultException;

	void create(String name, String description, String city) throws DAOException;

	void delete(String name) throws DAOException;

	void update(String name, String description, String city) throws DAOException;

	Department findDepartmentByName(String name) throws DAOException, EmptyResultException;

	List<Department> findAllDepartments() throws DAOException, EmptyResultException;

	List<Department> findDepartmentsByCity(String city) throws DAOException, EmptyResultException;
}
