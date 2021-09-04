package com.whnm.mediappbackend.service;

import java.util.List;
import java.util.Optional;

import com.whnm.mediappbackend.entity.Paciente;



public interface PacienteService {
	Paciente registrar (Paciente p);
	Paciente modificar (Paciente p);
	List<Paciente> listar ();
	Optional<Paciente> listarPorId (Long id);
	void eliminar (Long id);
}
