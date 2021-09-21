package com.whnm.mediappbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Medico DTO")
public class MedicoDTO {
	
	public Long idMedico;
	private String nombres;
	private String apellidos;
	private String cmp;
	private String fotoUrl;
	
	
}
