package com.bolsaideas.springboot.web.app.models;
public class Alumno {
	
	private int id;
	private String name;
	private String lastname;
	private int matricula;
	private double average;



	public Alumno() {
		
	}

	public Alumno(int id, String name, String lastname, int matricula, double average) {

		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.matricula = matricula;
		this.average = average;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

}
