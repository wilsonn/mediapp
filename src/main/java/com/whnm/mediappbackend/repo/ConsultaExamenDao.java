package com.whnm.mediappbackend.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whnm.mediappbackend.entity.ConsultaExamen;
import com.whnm.mediappbackend.entity.ConsultaExamenPK;

@Repository
public interface ConsultaExamenDao extends GenericDao<ConsultaExamen, ConsultaExamenPK> {

	@Modifying
	@Query(value="INSERT INTO consulta_examen(id_consulta, id_examen) VALUES (:idConsulta, :idExamen)", nativeQuery = true)
	Integer registrar(@Param("idConsulta") Long idConsulta, @Param("idExamen") Long idExamen);
}
