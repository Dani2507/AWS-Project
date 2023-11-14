package com.bolsaideas.springboot.web.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsaideas.springboot.web.app.models.Alumno;

@Service
public class AlumnoServicio {

	//Listado ALumnos//
	
	private List<Alumno> alumnos;
	
	public AlumnoServicio() {
		alumnos = new ArrayList<>();
			alumnos.add(new Alumno(1,"Daniel","Gtuierrez",1801,5.6));
			alumnos.add(new Alumno(2,"Erik","Perez",1801,5.6));
			alumnos.add(new Alumno(3,"Sam","Rodriguez",1801,5.6));
		
	}
	
	public List<Alumno> list(){
		return alumnos;
	}
	
	//Buscar Alumno//
	
	public Alumno find(int id) {
		for(Alumno alumno: alumnos) {
			if(alumno.getId()==id) {
				return alumno;
			}
		}
		return null;
	}
	
	//Crear Alumno//
	
	public Alumno save(Alumno alumno) {
		System.out.print("segundo punto");
		alumnos.add(alumno);
		return alumno;
	}
	
	//Actualizar alumno//
	public Alumno update(int id, Alumno alumno) {
		int index =0;
		for(Alumno l: alumnos) {
			if(l.getId()==id) {
				alumno.setId(id);
				alumnos.set(index, alumno);
			}
		}
		return alumno;
	}
	
	//ELiminar Alumno//
	
	public boolean delete(int id) {
		for(Alumno alumno : alumnos) {
			if(alumno.getId()==id) {
				return alumnos.remove(alumno);
			}
		}
		return false;
	}
}
