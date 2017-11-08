package com.tecsup.gestion.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tecsup.gestion.exception.DAOException;
import com.tecsup.gestion.exception.EmptyResultException;
import com.tecsup.gestion.model.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
@WebAppConfiguration
public class EmployeeDAOTest {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOTest.class);

	@Autowired
	private EmployeeDAO employeeDAO;

	
	@BeforeClass
	public static void beforeClass() {
		logger.info("Antes de todos los metodos");

	}
	
	@Before
	public  void before() {
		logger.info("Antes de cada metodo");
	}
	
	@Test
	public void testFindEmployeeById() {

		try {
			//
			Employee emp = employeeDAO.findEmployee(100);

			Assert.assertNotNull(emp);

			Assert.assertEquals("jgomez", emp.getLogin());
			Assert.assertEquals("123456", emp.getPassword());
			Assert.assertEquals("Jaime", emp.getFirstname());
			Assert.assertEquals("Gomez", emp.getLastname());
			Assert.assertEquals(2505, emp.getSalary());

			logger.info(emp.toString());

		} catch (EmptyResultException e) {
			fail(e.getMessage());
		} catch (DAOException e) {
			fail(e.getMessage());
		}

	}

//	@Test
	public void testFindAllEmployees() {

		try {
			//
			List<Employee> emps = employeeDAO.findAllEmployees();

			//Assert.assertEquals(emps.size(), 4);

			logger.info(emps.toString());

		} catch (EmptyResultException e) {
			fail(e.getMessage());
		} catch (DAOException e) {
			fail(e.getMessage());
		}

		
	}

//	@Test
	public void testFindEmployeeByLogin() {

		try {
			//
			Employee emp = employeeDAO.findEmployeeByLogin("jgomez");

			Assert.assertEquals("123456", emp.getPassword());
			Assert.assertEquals("Jaime", emp.getFirstname());
			Assert.assertEquals("Gomez", emp.getLastname());
			Assert.assertEquals(2505, emp.getSalary());

			logger.info(emp.toString());
			
		} catch (EmptyResultException e) {
			fail(e.getMessage());
		} catch (DAOException e) {
			fail(e.getMessage());
		}

	}

//	@Test
	public void testCreateEmployee() {

		logger.info("--");
		
		String LOGIN = "aromero" + (int) (Math.random() * 100);
		//logger.info(LOGIN);
		String PWD = "123";
		String FIRSTNAME = "Alberto";
		String LASTNAME = "Romero";
		int SALARY = 3000;
		int DEPT_ID = 14;

		try {
			//
			employeeDAO.create(LOGIN, PWD, FIRSTNAME, LASTNAME, SALARY, DEPT_ID);

			//
			Employee emp = employeeDAO.findEmployeeByLogin(LOGIN);

			Assert.assertEquals(PWD, emp.getPassword());
			Assert.assertEquals(FIRSTNAME, emp.getFirstname());
			Assert.assertEquals(LASTNAME, emp.getLastname());
			Assert.assertEquals(SALARY, emp.getSalary());

		} catch (DAOException e) {
			fail(e.getMessage());
		} catch (EmptyResultException e) {
			fail(e.getMessage());
		}

	}

	
	
//	@Test
	public void testUpdateEmployee() {

		String LOGIN = "jgomez" + (int)(Math.random() * 100);
		String PWD = "123";
		String FIRSTNAME = "Jaime";
		String LASTNAME = "Gomez";
		int SALARY = 3000;
		int DEPT_ID = 14;
		
		String UP_PWD = "456";
		String UP_FIRSTNAME = "Jaime5";
		String UP_LASTNAME = "Gomez6";
		int UP_SALARY = 3500;
		int UP_DEPT_ID = 14;
		
		try {
			//
			employeeDAO.create(LOGIN, PWD, FIRSTNAME, LASTNAME, SALARY, DEPT_ID);
			//
			employeeDAO.update(LOGIN, UP_PWD, UP_FIRSTNAME, UP_LASTNAME, UP_SALARY, UP_DEPT_ID);
			//
			Employee emp = employeeDAO.findEmployeeByLogin(LOGIN);

			Assert.assertEquals(UP_PWD, emp.getPassword());
			Assert.assertEquals(UP_FIRSTNAME, emp.getFirstname());
			Assert.assertEquals(UP_LASTNAME, emp.getLastname());
			Assert.assertEquals(UP_SALARY, emp.getSalary());

		} catch (DAOException e) {
			fail(e.getMessage());
		} catch (EmptyResultException e) {
			fail(e.getMessage());
		}

	}
	
	
//	@Test
	public void testDeleteEmployee() {

		String LOGIN = "jromero";
		String PWD = "123";
		String FIRSTNAME = "Juan";
		String LASTNAME = "Romero";
		int SALARY = 3000;
		int DEPT_ID = 14;

		try {
			//
			employeeDAO.create(LOGIN, PWD, FIRSTNAME, LASTNAME, SALARY, DEPT_ID);

		} catch (DAOException e) {
			fail(e.getMessage());
		}

		try {

			employeeDAO.delete(LOGIN);

			employeeDAO.findEmployeeByLogin(LOGIN);

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyResultException e) {
			// Usuario borrado 
			return;
		}

	}
	
	@After
	public void after() {
		logger.info("Despues de cada metodo");
	}
	
	@AfterClass
	public static void afterClass() {
		logger.info("Despues de todos los metodos");
	}
}
