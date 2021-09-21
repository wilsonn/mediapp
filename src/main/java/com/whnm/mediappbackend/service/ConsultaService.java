package com.whnm.mediappbackend.service;

import java.util.List;

import com.whnm.mediappbackend.entity.Consulta;
import com.whnm.mediappbackend.entity.Examen;

public interface ConsultaService extends ICRUD<Consulta, Long>{

	Consulta registrarConsultaCompleta(Consulta consulta, List<Examen> examenes);

}
