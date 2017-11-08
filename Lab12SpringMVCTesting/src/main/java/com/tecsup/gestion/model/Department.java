package com.tecsup.gestion.model;

public class Department {

	int departmentId;
	String name;
	String description;
	String city;
	
	/**
	 * 
	 * @param name
	 * @param description
	 * @param city
	 */
	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(int departmentId, String name, String description, String city) {
		super();
		this.departmentId = departmentId;
		this.name = name;
		this.description = description;
		this.city = city;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", name=" + name + ", description=" + description
				+ ", city=" + city + "]";
	}
}
