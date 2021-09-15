package com.whnm.mediappbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="detalle_consulta")
public class DetalleConsulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetalleConsulta;
	
	@ManyToOne
	@JoinColumn(name="id_consulta", nullable=false,foreignKey = @ForeignKey(name = "FK_detalleconsulta_consulta"))
	private Consulta consulta;
	
	@Column(name="diagnostico", nullable=false, length = 150)
	private String diagnostico;
	
	@Column(name="tratamiento", nullable=false, length = 150)
	private String tratamiento;
}
