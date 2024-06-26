package com.empleado.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.empleado.modelos.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

	
	
}
