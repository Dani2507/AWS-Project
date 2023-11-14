package com.bolsaideas.springboot.web.app.models;

public class Profesor {
	private int id;
	private int employeeNumber;
	private String name;
	private String lastname;
	private double hoursOfClass;

	public Profesor() {

	}

	public Profesor(int id, int employeeNumber, String name, String lastname, double hoursOfClass) {

		this.id = id;
		this.employeeNumber = employeeNumber;
		this.name = name;
		this.lastname = lastname;
		this.hoursOfClass = hoursOfClass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public double getHoursOfClass() {
		return hoursOfClass;
	}

	public void setHoursOfClass(double hoursOfClass) {
		this.hoursOfClass = hoursOfClass;
	}
}
