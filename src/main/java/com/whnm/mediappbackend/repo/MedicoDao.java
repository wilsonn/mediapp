package com.whnm.mediappbackend.repo;

import org.springframework.stereotype.Repository;

import com.whnm.mediappbackend.entity.Medico;

@Repository
public interface MedicoDao extends GenericDao<Medico, Long> {

}
