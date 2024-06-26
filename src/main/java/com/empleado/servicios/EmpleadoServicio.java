package com.empleado.servicios;

import java.util.List;
import java.util.Optional;

import com.empleado.modelos.Empleado;

public interface EmpleadoServicio {

 Empleado registrar(Empleado emp) throws Exception;
 Optional<Empleado> modificar(int id, Empleado e) throws Exception;
 void BorrarRegistro(int id) throws Exception;
 Empleado listadoPorId(int id) throws Exception;
	
}
