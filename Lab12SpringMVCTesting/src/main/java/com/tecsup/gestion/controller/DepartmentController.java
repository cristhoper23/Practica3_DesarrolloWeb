package com.tecsup.gestion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tecsup.gestion.exception.DAOException;
import com.tecsup.gestion.model.Department;
import com.tecsup.gestion.services.DepartmentService;

@Controller
public class DepartmentController {

	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/admin/dept/list_dept")
	public String list(@ModelAttribute("SpringWeb") Department department, ModelMap model) {

		try {
			model.addAttribute("departments", departmentService.findAll());
		} catch (Exception e) {
			logger.info(e.getMessage());
			model.addAttribute("message", e.getMessage());
		}

		return "admin/dept/list_dept";
	}
	
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping("/{department_id}")
	public ModelAndView home(@PathVariable int department_id, ModelMap model) {

		ModelAndView modelAndView = null;
		Department dept = new Department();
		try {
			dept = departmentService.find(department_id);
			logger.info(dept.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		modelAndView = new ModelAndView("home", "command", dept);

		return modelAndView;
	}
	
	@GetMapping("/admin/dept/{action}/{department_id}")
	public ModelAndView form(@PathVariable String action, @PathVariable int department_id, ModelMap model) {

		// action = {"editform","deleteform"}
		logger.info("action = " + action);
		ModelAndView modelAndView = null;

		try {
			Department dept = departmentService.find(department_id);
			logger.info(dept.toString());
			modelAndView = new ModelAndView("admin/dept/" + action, "command", dept);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("admin/dept/" + action, "command", new Department());
		}

		return modelAndView;
	}
	
	@PostMapping("/admin/dept/editsave")
	public ModelAndView editsave(@ModelAttribute("SpringWeb") Department dept, ModelMap model) {

		
		logger.info("dept = " + dept);
		
		ModelAndView modelAndView = null;

		try {
			departmentService.update(dept.getName(), dept.getDescription(), dept.getCity());

			modelAndView = new ModelAndView("redirect:/admin/dept/list_dept");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("redirect:/admin/dept/list_dept");
		}

		return modelAndView;
	}
	
	//------------------------------------------- Semana12 (miercoles)----------------------------------------------
	
	@PostMapping("/admin/dept/delete")
	public ModelAndView delete(@ModelAttribute("SpringWeb") Department dept, ModelMap model) {

		ModelAndView modelAndView = null;

		try {
			departmentService.delete(dept.getName());
			modelAndView = new ModelAndView("redirect:/admin/dept/list_dept");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("redirect:/admin/dept/list_dept");
		}

		return modelAndView;
	}


	@GetMapping("/admin/dept/createform")
	public ModelAndView createform() {

		Department dept = new Department();

		ModelAndView modelAndView = new ModelAndView("admin/dept/createform", "command", dept);

		return modelAndView;
	}


	@PostMapping("/admin/dept/create")
	public ModelAndView create(@ModelAttribute("SpringWeb") Department dept, ModelMap model) {

		
		ModelAndView modelAndView = null;
		
		try {
			departmentService.create(dept.getName(), dept.getDescription(), dept.getCity());
			logger.info("new Department name = " + dept.getName());
			modelAndView = new ModelAndView("redirect:/admin/dept/list_dept");
		} catch (DAOException e) {
			logger.error(e.getMessage());
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("admin/dept/createform","command", dept);
		}

		return modelAndView;
	}
}
