package com.whnm.mediappbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paciente")
//@Table(name = "paciente", schema = "protegido"), para indicar esquema de base de datos
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPaciente;
	
	@Size(min=3, message= "{nombre.size}")
	@Column(name ="nombres", nullable = false, length = 70)
	private String nombres;
	
	@Size(min=3, message= "{apellido.size}")
	@Column(name ="apellidos", nullable = false, length = 70)
	private String apellidos;

	@Size(min=8, max=8, message= "{dni.size}")
	@Column(name ="dni", nullable = false, length = 8)
	private String dni;
	
	@Size(min=8, max=150, message= "{direccion.size}")
	@Column(name ="direccion", nullable = true, length = 150)
	private String direccion;
	
	@Size(min=9, max=9, message= "{telefono.size}")
	@Column(name ="telefono", nullable = true, length = 9)
	private String telefono;
	
	@Email(message = "{email.formato}")
	@Column(name ="email", nullable = false, length = 55)
	private String email;
	
	
}
