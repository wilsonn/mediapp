package com.whnm.mediappbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="rol")
public class Rol {
	@Id
	private Long idRol;
	
	@Column(name = "nombre", nullable = false, length = 80)
	private String nombre;
	
	@Column(name = "descripcion", nullable = true, length = 150)
	private String descripcion;
}
