package com.bolsaideas.springboot.web.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsaideas.springboot.web.app.models.Profesor;

@Service
public class ProfesorServicio {
	//Listado Profesor//
	
			private List<Profesor> profesores;
			
			public ProfesorServicio() {
				profesores = new ArrayList<>();
				profesores.add(new Profesor(1, 2507,"Daniel","Gtuierrez",5.6));
				profesores.add(new Profesor(2, 2810,"Erik","Perez",5.6));
				profesores.add(new Profesor(3, 1308, "Sam","Rodriguez",5.6));
				
			}
			
			public List<Profesor> list(){
				return profesores;
			}
			
			//Buscar Profesor//
			
			public Profesor find(int id) {
				for(Profesor profesor: profesores) {
					if(profesor.getId()==id) {
						return profesor;
					}
				}
				return null;
			}
			
			//Crear Profesor//
			
			public Profesor save(Profesor profesor) {
				profesores.add(profesor);
				return profesor;
			}
			
			//Actualizar Profesor//
			public Profesor update(int id, Profesor profesor) {
				int index =0;
				for(Profesor l: profesores) {
					if(l.getId()==id) {
						profesor.setId(id);
						profesores.set(index, profesor);
					}
				}
				return profesor;
			}
			
			//ELiminar Profesor//
			
			public boolean delete(int id) {
				for(Profesor profesor : profesores) {
					if(profesor.getId()==id) {
						return profesores.remove(profesor);
					}
				}
				return false;
			}
}
