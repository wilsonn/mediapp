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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medico")
public class Medico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMedico;
	
	@Column(name = "nombres", nullable = false, length = 80)
	private String nombres;
	
	@Column(name = "apellidos", nullable = false, length = 80)
	private String apellidos;
	
	@Column(name = "cmp", nullable = true, length = 80)
	private String cmp;
	
	@Column(name = "foto_url", nullable = true, length = 80)
	private String fotoUrl;
}
