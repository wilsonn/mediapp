package com.whnm.mediappbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whnm.mediappbackend.entity.Especialidad;
import com.whnm.mediappbackend.repo.GenericDao;
import com.whnm.mediappbackend.repo.EspecialidadDao;
import com.whnm.mediappbackend.service.EspecialidadService;

@Service
public class EspecialidadServiceImpl extends CRUDImpl<Especialidad, Long> implements EspecialidadService {
	
	@Autowired
	private EspecialidadDao especialidadDao;

	@Override
	protected GenericDao<Especialidad, Long> getRepo() {
		return especialidadDao;
	}

	

}
