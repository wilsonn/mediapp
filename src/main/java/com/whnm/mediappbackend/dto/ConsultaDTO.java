package com.whnm.mediappbackend.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaDTO {
	
	private Long idConsulta;
	private PacienteDTO paciente;
	private MedicoDTO medico;
	private EspecialidadDTO especilidad;
	private String numConsultorio;
	private LocalDateTime fecha;
	private List<DetalleConsultaDTO> detalleConsulta;
	
}
