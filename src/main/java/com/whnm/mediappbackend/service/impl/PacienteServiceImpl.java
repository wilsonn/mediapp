package com.whnm.mediappbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whnm.mediappbackend.entity.Paciente;
import com.whnm.mediappbackend.repo.GenericDao;
import com.whnm.mediappbackend.repo.PacienteDao;
import com.whnm.mediappbackend.service.PacienteService;

@Service
public class PacienteServiceImpl extends CRUDImpl<Paciente, Long> implements PacienteService {
	
	@Autowired
	private PacienteDao pacienteDao;

	@Override
	protected GenericDao<Paciente, Long> getRepo() {
		return pacienteDao;
	}
	
	
}
