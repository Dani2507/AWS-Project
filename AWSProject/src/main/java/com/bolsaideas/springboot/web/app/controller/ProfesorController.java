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

import com.bolsaideas.springboot.web.app.models.Profesor;
import com.bolsaideas.springboot.web.app.service.ProfesorServicio;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {
private final ProfesorServicio service;
	
	public ProfesorController(ProfesorServicio service) {
		this.service = service;
	}
	
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
	
	@GetMapping
	public ResponseEntity<Iterable<Profesor>> list(){
		return new ResponseEntity<>(service.list(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Profesor> find (@PathVariable("id") int id) {
		return new ResponseEntity<>(service.find(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Profesor profesor) {
		if (profesor.getId() < 0) {
			return ResponseEntity.badRequest().body("El id no puede estar vacío o ser menor a 0");
		}
		
		if (profesor.getName() == null || profesor.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre no puede estar vacío");
	    
        }
		
		for (int i = 0; i < profesor.getName().length(); i++)
		{
			char caracter = profesor.getName().toUpperCase().charAt(i);
			int valorASCII = (int)caracter;
			if (valorASCII != 165 && (valorASCII < 65 || valorASCII > 90))
				return ResponseEntity.badRequest().body("El nombre solo puede contener letras");
		}

        if (profesor.getLastname() == null || profesor.getLastname().trim().isEmpty()) {
        	return ResponseEntity.badRequest().body("El apellido no puede estar vacío");
        }
        
        for (int i = 0; i < profesor.getLastname().length(); i++)
		{
			char caracter = profesor.getLastname().toUpperCase().charAt(i);
			int valorASCII = (int)caracter;
			if (valorASCII != 165 && (valorASCII < 65 || valorASCII > 90))
				return ResponseEntity.badRequest().body("El apellido solo puede contener letras");
		}

        if (profesor.getEmployeeNumber() < 0) {
            return ResponseEntity.badRequest().body("El numero de empleado no puede ser un número negativo");
        }

        if (profesor.getEmployeeNumber() < 0 || profesor.getEmployeeNumber() < 0.0) {
        	return ResponseEntity.badRequest().body("El numero de horas no puede estar vacio o ser menor a 0");
        }
		return new ResponseEntity<>(service.save(profesor), HttpStatus.CREATED);
	}
	
	@PutMapping ("/{id}")
	public ResponseEntity<Profesor> update(@PathVariable("id") int id, @RequestBody Profesor profesor) {
		return new ResponseEntity<>(service.update(id, profesor), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") int id) {
		return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
	}
}
