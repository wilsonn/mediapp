package com.whnm.mediappbackend.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ConsultaExamenPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_consulta", nullable = false, foreignKey = @ForeignKey(name="FK_consultaexamen_consulta"))
	private Consulta consulta;
	
	@ManyToOne
	@JoinColumn(name = "id_examen", nullable = false, foreignKey = @ForeignKey(name="FK_consultaexamen_examen"))
	private Examen examen;
}
