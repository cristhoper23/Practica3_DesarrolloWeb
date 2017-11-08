package com.tecsup.gestion.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.tecsup.gestion.model.Department;

public class DepartmentMapper implements RowMapper<Department>{
	
	public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
		Department dept = new Department();
		dept.setDepartmentId(rs.getInt("department_id"));
		dept.setName(rs.getString("name"));
		dept.setDescription(rs.getString("description"));
		dept.setCity(rs.getString("city"));
		return dept;
	}
}
