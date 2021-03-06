package com.whnm.mediappbackend.restcontroller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.whnm.mediappbackend.dto.PacienteDTO;
import com.whnm.mediappbackend.entity.Paciente;
import com.whnm.mediappbackend.exception.ModeloNoFoundException;
import com.whnm.mediappbackend.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteRestController {

	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<PacienteDTO>> listar() throws Exception {
		List<PacienteDTO> listaPacientesDTO = pacienteService.listar().stream()
				.map(
						paciente -> modelMapper.map(paciente, PacienteDTO.class)
				)
				.collect(Collectors.toList());
		return new ResponseEntity<>(listaPacientesDTO, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PacienteDTO> listarPorId(@PathVariable("id") Long id) throws Exception {
		Optional<Paciente> paciente = pacienteService.listarPorId(id);
		if (!paciente.isPresent()) {
			throw new ModeloNoFoundException("Paciente con id: " + id + " no encontrado");
		}
		
		return new ResponseEntity<>(
				modelMapper.map(
						paciente.get(), PacienteDTO.class
				), 
				HttpStatus.OK
		);
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody PacienteDTO pacienteDto) throws Exception {
		Paciente paciente = modelMapper.map(pacienteDto, Paciente.class);
		Paciente pacienteRegistro = pacienteService.registrar(paciente);
		PacienteDTO pacienteDTORegistro = modelMapper.map(pacienteRegistro,PacienteDTO.class);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pacienteDTORegistro.getIdPaciente()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PacienteDTO> modificar(@Valid @RequestBody PacienteDTO pacienteDTOMod) throws Exception {
		Optional<Paciente> paciente = pacienteService.listarPorId(pacienteDTOMod.getIdPaciente());
		if (paciente.isPresent()) {
			pacienteDTOMod = modelMapper.map(
								pacienteService.modificar(
										modelMapper.map(pacienteDTOMod, Paciente.class)
								),
								PacienteDTO.class
						  	);
		} else {
			throw new ModeloNoFoundException(
					"Paciente con id: " + pacienteDTOMod.getIdPaciente() + " no encontrado"
			);
		}
		return new ResponseEntity<>(pacienteDTOMod, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception {
		Optional<Paciente> paciente = pacienteService.listarPorId(id);
		if (paciente.isPresent()) {
			pacienteService.eliminar(id);
		} else {
			throw new ModeloNoFoundException(
					"Paciente con id: " + id + " no encontrado"
			);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<PacienteDTO> listarHeteoasPorId(@PathVariable("id") Long id) throws Exception {
		Optional<Paciente> paciente = pacienteService.listarPorId(id);
		if (!paciente.isPresent()) {
			throw new ModeloNoFoundException(
					"Paciente con id: " + id + " no encontrado"
			);
		}
		
		PacienteDTO pacienteDTO = modelMapper.map(paciente.get(), PacienteDTO.class);
		
		EntityModel<PacienteDTO> recurso = EntityModel.of(pacienteDTO);
		
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(link1.withRel("pacienteRecurso1"));
		
		return recurso;
	}
}
