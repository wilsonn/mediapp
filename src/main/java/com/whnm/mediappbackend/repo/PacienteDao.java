package com.whnm.mediappbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whnm.mediappbackend.entity.Paciente;

@Repository
public interface PacienteDao extends JpaRepository<Paciente, Long> {

}
