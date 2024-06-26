package com.empleado.controlador;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.empleado.modelos.Empleado;
import com.empleado.serviciosImplementaciones.EmpleadoServiceImpl;

@RestController
@RequestMapping("/empleado")
public class Controlador {

	@Autowired
	private EmpleadoServiceImpl emplServiceImpl;

		
	@PostMapping("/guardar")
	public ResponseEntity<Empleado> guardarRegistroEmpleado(@RequestBody Empleado emp) throws Exception{
		
	  Empleado empRegistrado = emplServiceImpl.registrar(emp);
		
		return new ResponseEntity<Empleado>(empRegistrado, HttpStatus.CREATED);
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<Optional<Empleado>> modificarRegistroEmpleado(@PathVariable("id") int id, @RequestBody Empleado emp) throws Exception{
			
	  Optional<Empleado> empResult = emplServiceImpl.modificar(id, emp);
	  
	 if(!empResult.isEmpty()) {
		 return new ResponseEntity<Optional<Empleado>>(empResult, HttpStatus.OK);
	 }
		
	 return new ResponseEntity<>(empResult, HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<Void> borradoRegistroEmpleado(@PathVariable("id") int id) throws Exception{
		
		Empleado em = emplServiceImpl.listadoPorId(id);
	
		if(em.getId()== 0) {
			 emplServiceImpl.BorrarRegistro(id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		 emplServiceImpl.BorrarRegistro(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/consultaPorId/{id}")
	public ResponseEntity<Empleado> busquedaPorId(@PathVariable("id") int id) throws Exception{
		
		Empleado empConsultaId = emplServiceImpl.listadoPorId(id);
		
		if(empConsultaId.getId() == 0) {
			emplServiceImpl.listadoPorId(id);
			return new ResponseEntity<Empleado>(empConsultaId, HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<Empleado>(empConsultaId, HttpStatus.OK);
	}

}
