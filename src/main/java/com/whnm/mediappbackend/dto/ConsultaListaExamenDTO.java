package com.whnm.mediappbackend.dto;

import java.util.List;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaListaExamenDTO {
	
	@NotNull
	private ConsultaDTO consulta;
	
	@NotNull
	private List<ExamenDTO> listaExamen;
	
}
