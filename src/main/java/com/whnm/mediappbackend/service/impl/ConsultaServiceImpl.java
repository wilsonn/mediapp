package com.whnm.mediappbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whnm.mediappbackend.entity.Consulta;
import com.whnm.mediappbackend.entity.Examen;
import com.whnm.mediappbackend.repo.ConsultaDao;
import com.whnm.mediappbackend.repo.ConsultaExamenDao;
import com.whnm.mediappbackend.repo.GenericDao;
import com.whnm.mediappbackend.service.ConsultaService;

@Service
public class ConsultaServiceImpl extends CRUDImpl<Consulta, Long> implements ConsultaService {
	
	@Autowired
	private ConsultaDao consultaDao;
	
	@Autowired
	private ConsultaExamenDao consultaExamenDao;

	@Override
	protected GenericDao<Consulta, Long> getRepo() {
		return consultaDao;
	}

	@Transactional
	@Override
	public Consulta registrarConsultaCompleta(Consulta consulta, List<Examen> examenes) {
		
		consulta.getDetalleConsulta().forEach(det -> det.setConsulta(consulta));
		consultaDao.save(consulta);
		
		examenes.forEach(ex -> consultaExamenDao.registrar(consulta.getIdConsulta(), ex.getIdExamen()));
		
		return consulta;
		
	}

	

}
