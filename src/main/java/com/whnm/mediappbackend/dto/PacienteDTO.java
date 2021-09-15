package com.whnm.mediappbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {
	private Long idPaciente;
	private String nombres;
	private String apellidos;
	private String dni;
	private String direccion;
	private String telefono;
	private String email;
}
