package com.whnm.mediappbackend.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Paciente DTO")
public class PacienteDTO {
	private Long idPaciente;
	
	@Schema(description = "nombres del paciente")
	@Size(min=3, message= "{nombre.size}")
	private String nombres;
	
	@Schema(description = "apellidos del paciente")
	@Size(min=3, message= "{apellido.size}")
	private String apellidos;
	
	@Size(min=8, max=8, message= "{dni.size}")
	private String dni;
	
	@Size(min=8, max=150, message= "{direccion.size}")
	private String direccion;
	
	@Size(min=9, max=9, message= "{telefono.size}")
	private String telefono;
	
	@Email(message = "{email.formato}")
	private String email;
}
