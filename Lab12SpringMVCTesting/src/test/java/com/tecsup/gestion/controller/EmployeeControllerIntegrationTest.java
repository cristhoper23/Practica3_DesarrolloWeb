package com.tecsup.gestion.controller;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
@WebAppConfiguration
public class EmployeeControllerIntegrationTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void givenWac_whenServletContext_thenItProvidesGreetController() {
		ServletContext servletContext = wac.getServletContext();

		Assert.assertNotNull(servletContext);
		Assert.assertTrue(servletContext instanceof MockServletContext);
		Assert.assertNotNull(wac.getBean("employeeController"));
	}

	@Test
	public void list() throws Exception {
		mockMvc.perform(get("/admin/emp/list"))
				.andExpect(status().isOk())
				.andExpect(view().name("admin/emp/list"))
				.andExpect(forwardedUrl("/WEB-INF/views/admin/emp/list.jsp"))
				//.andExpect(model().attribute("employees", hasSize(43)))
				.andExpect(model().attribute("employees",
						hasItem(allOf(  
										hasProperty("employeeId", is(100)),
										hasProperty("login", is("jgomez")),
										hasProperty("password", is("123456")),
										hasProperty("firstname", is("Jaime")),
										hasProperty("lastname", is("Gomez")), 
										hasProperty("salary", is(2505)) //,
										//hasProperty("department",hasProperty("departmentId", is(12)))
									))));
	}

	
	@Test
    public void editForm() throws Exception {
		
        mockMvc.perform(get("/admin/emp/editform/100"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/emp/editform"))
                .andExpect(forwardedUrl("/WEB-INF/views/admin/emp/editform.jsp"))
                .andExpect(model().attribute("command", hasProperty("employeeId", is(100))))
                .andExpect(model().attribute("command", hasProperty("login", is("jgomez"))))
                .andExpect(model().attribute("command", hasProperty("password", is("123456"))))
                .andExpect(model().attribute("command", hasProperty("firstname", is("Jaime"))))
                .andExpect(model().attribute("command", hasProperty("lastname", is("Gomez"))))
                .andExpect(model().attribute("command", hasProperty("salary", is(2505))))
                //.andExpect(model().attribute("command", 
                //		hasProperty("department", hasProperty("departmentId",is(12)))))
            ;
	}

	
}
