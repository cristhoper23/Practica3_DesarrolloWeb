package com.tecsup.gestion.dao.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tecsup.gestion.dao.EmployeeDAO;
import com.tecsup.gestion.exception.DAOException;
import com.tecsup.gestion.exception.EmptyResultException;
import com.tecsup.gestion.exception.LoginException;
import com.tecsup.gestion.mapper.EmployeeMapper;
import com.tecsup.gestion.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public Employee findEmployee(int employee_id) throws DAOException, EmptyResultException {

		String query = "SELECT employee_id, login, password, first_name, last_name, salary, department_id "
				+ " FROM employees WHERE employee_id = ?";

		Object[] params = new Object[] { employee_id };

		try {

			Employee emp = (Employee) jdbcTemplate.queryForObject(query, params, new EmployeeMapper());
			//
			return emp;
			//return null;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}


	@Override
	public void create(String login, String password, String lastname, String firstname, int salary, int dptId) throws DAOException {

		String query = "INSERT INTO employees (login, password, first_name, last_name, salary, department_id)  VALUES ( ?,?,?,?,?,? )";

		Object[] params = new Object[] { login, password, lastname, firstname, salary, dptId };

		//Employee emp = null;
		
		try {
			// create
			jdbcTemplate.update(query, params);

		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
		

	}

	@Override
	public void delete(String login) throws DAOException {

		String query = "DELETE FROM  employees WHERE login = ? ";

		Object[] params = new Object[] { login };

		try {
			jdbcTemplate.update(query, params);
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void update(String  login, String password, String lastname, String firstname, int salary, int dptId) throws DAOException {

		String query = "UPDATE employees SET password = ?, first_name =?, last_name = ?, salary = ? WHERE login = ?";

		Object[] params = new Object[] { password, lastname, firstname, salary, login };

		
		try {
			jdbcTemplate.update(query, params);
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}


	@Override
	public Employee findEmployeeByLogin(String login) throws DAOException, EmptyResultException {

		String query = "SELECT employee_id, login, password, first_name, last_name, salary, department_id "
				+ " FROM employees WHERE login = ? ";

		Object[] params = new Object[] { login };

		try {

			Employee employee = jdbcTemplate.queryForObject(query, params, new EmployeeMapper());
			//
			return employee;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}
	
	@Override
	public List<Employee> findAllEmployees() throws DAOException, EmptyResultException {

		String query = "SELECT employee_id, login, password, first_name, last_name, salary, department_id FROM employees ";

		try {

			List<Employee> employees = jdbcTemplate.query(query, new EmployeeMapper());
			//
			return employees;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<Employee> findEmployeesByName(String name) throws DAOException, EmptyResultException {

		String query = "SELECT employee_id, login, password, first_name, last_name, salary, department_id FROM employees WHERE upper(first_name) like upper(?) ";

		Object[] params = new Object[] { "%" + name + "%" };

		try {

			List<Employee> employees = jdbcTemplate.query(query, params, new EmployeeMapper());
			//
			return employees;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}


	public Employee validate(String login, String pwd) throws LoginException, DAOException {
	
		logger.info("validate(): login: " + login + ", clave: " + pwd);
	
		if ("".equals(login) && "".equals(pwd)) {
			throw new LoginException("Login and password incorrect");
		}
	
		String query = "SELECT login, password, employee_id, first_name, last_name, salary, department_id  "
				+ " FROM employees WHERE login=? AND password=?";
	
		Object[] params = new Object[] { login, pwd };
	
		try {
	
			Employee emp = (Employee) jdbcTemplate.queryForObject(query, params, new EmployeeMapper());
			//
			return emp;
	
		} catch (EmptyResultDataAccessException e) {
			logger.info("Employee y/o clave incorrecto");
			throw new LoginException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}


}