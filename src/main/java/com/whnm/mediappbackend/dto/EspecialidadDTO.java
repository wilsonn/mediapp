package com.whnm.mediappbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EspecialidadDTO {
	
	private Long idEspecialidad;
	private String nombre;
	private String descripcion;
	
}
