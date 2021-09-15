package com.whnm.mediappbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whnm.mediappbackend.entity.Medico;
import com.whnm.mediappbackend.repo.GenericDao;
import com.whnm.mediappbackend.repo.MedicoDao;
import com.whnm.mediappbackend.service.MedicoService;

@Service
public class MedicoServiceImpl extends CRUDImpl<Medico, Long> implements MedicoService {
	
	@Autowired
	private MedicoDao medicoDao;

	@Override
	protected GenericDao<Medico, Long> getRepo() {
		return medicoDao;
	}

	

}
