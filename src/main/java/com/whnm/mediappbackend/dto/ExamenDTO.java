package com.whnm.mediappbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Examen DTO")
public class ExamenDTO {
	private Long idExamen;
	private String nombre;
	private String descripcion;
	
}
