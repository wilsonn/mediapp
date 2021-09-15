package com.whnm.mediappbackend.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="consulta")
public class Consulta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConsulta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name="FK_consulta_paciente"))
	private Paciente paciente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_medico", nullable = false, foreignKey = @ForeignKey(name="FK_consulta_medico"))
	private Medico medico;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_especialidad", nullable = false, foreignKey = @ForeignKey(name="FK_consulta_especialidad"))
	private Especialidad especilidad;
	
	@Column(name = "num_consultorio", nullable = false, length = 3)
	private String numConsultorio;
	
	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;
	//@Temporal
	//private Date fecha;
	//Spring boot 1.5 agregar al pom.xml dependencia JSR310
	
	@OneToMany(mappedBy = "consulta", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<DetalleConsulta> detalleConsulta;
	
	
	
}
