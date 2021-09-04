package com.whnm.mediappbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whnm.mediappbackend.entity.Paciente;
import com.whnm.mediappbackend.repo.PacienteDao;

@Service
public class PacienteServiceImpl implements PacienteService {
	
	@Autowired
	private PacienteDao pacienteDao;
	
	@Override
	public Paciente registrar(Paciente p) {
		return pacienteDao.save(p);
	}

	@Override
	public Paciente modificar(Paciente p) {
		return pacienteDao.save(p);
	}

	@Override
	public List<Paciente> listar() {
		return pacienteDao.findAll();
	}

	@Override
	public Optional<Paciente> listarPorId(Long id) {
		return pacienteDao.findById(id);
	}

	@Override
	public void eliminar(Long id) {
		pacienteDao.deleteById(id);
	}

}
