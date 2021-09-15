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
@Table(name="menu")
public class Menu {
	@Id
	private Long idMenu;
	
	@Column(name = "icono", nullable = false, length = 20)
	private String icono;
	
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;
	
	@Column(name = "url", nullable = false, length = 50)
	private String url;
	
	@ManyToMany
	@JoinTable(name="menu_rol",
			joinColumns=@JoinColumn(name="id_menu", referencedColumnName = "idMenu"),
			inverseJoinColumns = @JoinColumn(name="id_rol", referencedColumnName = "idRol"))
	private List<Rol> roles;
}
