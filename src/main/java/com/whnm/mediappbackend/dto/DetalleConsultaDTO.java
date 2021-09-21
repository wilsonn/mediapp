package com.whnm.mediappbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleConsultaDTO {
	private Integer idDetalleConsulta;
	@JsonIgnore
	private ConsultaDTO consulta;
	private String diagnostico;
	private String tratamiento;
}
