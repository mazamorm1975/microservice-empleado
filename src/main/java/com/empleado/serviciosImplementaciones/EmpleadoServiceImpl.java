package com.empleado.serviciosImplementaciones;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.empleado.modelos.Empleado;
import com.empleado.repositorio.EmpleadoRepository;
import com.empleado.servicios.EmpleadoServicio;


@Service
public class EmpleadoServiceImpl implements EmpleadoServicio {

	
	@Autowired
	private EmpleadoRepository empRepo;
	
	@Override
	public Empleado registrar(Empleado emp) throws Exception {
		
	 	Empleado em = empRepo.save(emp);
		
		return em;
	}

	@Override
	public Optional<Empleado> modificar(int id, Empleado em) throws Exception {
		
	  Optional<Empleado> optionalEmpleado = empRepo.findById(id);
	  
	  optionalEmpleado.ifPresentOrElse(obj -> { em.setId(id); empRepo.save(em); } , () -> { System.out.println("No fue posible la modificación");});
	  
	 return optionalEmpleado; 
	
	}
	

	@Override
	public void BorrarRegistro(int id) {
		
		Optional<Empleado> emp = empRepo.findById(id);
		
		/*
		emp.ifPresentOrElse(
				//Si encuentra el id del empleado lo regresa
				obj -> obj.getId(),
				
				//Si no se encuentra nada arroja una excepción de no encontrado
				() -> {
					
					new Exception("No se encontro ningun registro");
					
				});*/
		
		//Todo en una sola linea
		emp.ifPresentOrElse(obj -> { empRepo.deleteById(obj.getId());  }, () -> { new Exception("No Se Encontro Ningun Registro");});
		
				
	}

	@Override
	public Empleado listadoPorId(int id) throws Exception {
		
		Optional<Empleado> empPorId =  empRepo.findById(id);
		
		return empPorId.isPresent() ? empPorId.get(): new Empleado();
	}

}
