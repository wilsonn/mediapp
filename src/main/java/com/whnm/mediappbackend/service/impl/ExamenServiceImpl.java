package com.whnm.mediappbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whnm.mediappbackend.entity.Examen;
import com.whnm.mediappbackend.repo.GenericDao;
import com.whnm.mediappbackend.repo.ExamenDao;
import com.whnm.mediappbackend.service.ExamenService;

@Service
public class ExamenServiceImpl extends CRUDImpl<Examen, Long> implements ExamenService {
	
	@Autowired
	private ExamenDao examenDao;

	@Override
	protected GenericDao<Examen, Long> getRepo() {
		return examenDao;
	}

	

}
