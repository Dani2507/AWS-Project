package com.bolsaideas.springboot.web.app.controller;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsaideas.springboot.web.app.models.Alumno;
import com.bolsaideas.springboot.web.app.service.AlumnoServicio;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
private final AlumnoServicio service;

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
	    return ResponseEntity.badRequest().body("Error en la estructura del JSON: " + ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + ex.getMessage());
    }
	@ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticación: " + ex.getMessage());
    }
	
	public AlumnoController(AlumnoServicio service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Alumno>> list(){
		return new ResponseEntity<>(service.list(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Alumno> find (@PathVariable("id") int id) {
		return new ResponseEntity<>(service.find(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Alumno alumno) {
		if (alumno.getId() < 0) {
			return ResponseEntity.badRequest().body("El id no puede estar vacío o ser menor a 0");
		}
		
		if (alumno.getName() == null || alumno.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre no puede estar vacío");
	    
        }
		
		for (int i = 0; i < alumno.getName().length(); i++)
		{
			char caracter = alumno.getName().toUpperCase().charAt(i);
			int valorASCII = (int)caracter;
			if (valorASCII != 165 && (valorASCII < 65 || valorASCII > 90))
				return ResponseEntity.badRequest().body("El nombre solo puede contener letras");
		}

        if (alumno.getLastname() == null || alumno.getLastname().trim().isEmpty()) {
        	return ResponseEntity.badRequest().body("El apellido no puede estar vacío");
        }
        
        for (int i = 0; i < alumno.getLastname().length(); i++)
		{
			char caracter = alumno.getLastname().toUpperCase().charAt(i);
			int valorASCII = (int)caracter;
			if (valorASCII != 165 && (valorASCII < 65 || valorASCII > 90))
				return ResponseEntity.badRequest().body("El apellido solo puede contener letras");
		}

        if (alumno.getMatricula() < 0) {
            return ResponseEntity.badRequest().body("La matrícula no puede ser un número negativo");
        }

        if (alumno.getAverage() < 0 || alumno.getAverage() < 0.0 || alumno.getAverage() > 10.0) {
        	return ResponseEntity.badRequest().body("El promedio no puede estar vacio y debe estar entre 0.0 y 10.0");
        }
		return new ResponseEntity<>(service.save(alumno),HttpStatus.CREATED);
	}
	
	@PutMapping ("/{id}")
	public ResponseEntity<Alumno> update(@PathVariable("id") int id, @RequestBody Alumno alumno) {
		return new ResponseEntity<>(service.update(id, alumno),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") int id) {
		return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
	}
}
