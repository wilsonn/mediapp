package com.whnm.mediappbackend.repo;

import org.springframework.stereotype.Repository;

import com.whnm.mediappbackend.entity.Paciente;

@Repository
public interface PacienteDao extends GenericDao<Paciente, Long> {

}
