package com.whnm.mediappbackend.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="usuario")
public class Usuario {
	
	@Id
	private Long idUsuario;
	
	@Column(name = "usuario", nullable = false, unique = true, length = 80)
	private String usuario;
	
	@Column(name = "clave", nullable = false, length = 50)
	private String clave;
	
	@Column(name = "estado", nullable = false)
	private boolean estado;
	
	@ManyToMany
	@JoinTable(name="usuario_rol",
			joinColumns=@JoinColumn(name="id_usuario", referencedColumnName = "idUsuario"),
			inverseJoinColumns = @JoinColumn(name="id_rol", referencedColumnName = "idRol"))
	private List<Rol> roles;
}
