package com.whnm.mediappbackend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="consulta_examen")
@IdClass(ConsultaExamenPK.class)
public class ConsultaExamen {
	
	@Id
	private Consulta consulta;
	
	@Id
	private Examen examen;
	
}
