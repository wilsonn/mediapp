package com.whnm.mediappbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "paciente")
//@Table(name = "paciente", schema = "protegido"), para indicar esquema de base de datos
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPaciente;
	
	@Column(name ="nombres", nullable = false, length = 70)
	private String nombres;
	
	@Column(name ="apellidos", nullable = false, length = 70)
	private String apellidos;

	@Column(name ="dni", nullable = false, length = 8)
	private String dni;
	
	@Column(name ="direccion", nullable = true, length = 150)
	private String direccion;
	
	@Column(name ="telefono", nullable = true, length = 9)
	private String telefono;
	
	@Column(name ="email", nullable = false, length = 55)
	private String email;
	
	
}
