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

import com.whnm.mediappbackend.dto.MedicoDTO;
import com.whnm.mediappbackend.entity.Medico;
import com.whnm.mediappbackend.exception.ModeloNoFoundException;
import com.whnm.mediappbackend.service.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoRestController {

	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<MedicoDTO>> listar() throws Exception {
		List<MedicoDTO> listaMedicosDTO = medicoService.listar().stream()
				.map(
						medico -> modelMapper.map(medico, MedicoDTO.class)
				)
				.collect(Collectors.toList());
		return new ResponseEntity<>(listaMedicosDTO, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicoDTO> listarPorId(@PathVariable("id") Long id) throws Exception {
		Optional<Medico> medico = medicoService.listarPorId(id);
		if (!medico.isPresent()) {
			throw new ModeloNoFoundException("Medico con id: " + id + " no encontrado");
		}
		
		return new ResponseEntity<>(
				modelMapper.map(
						medico.get(), MedicoDTO.class
				), 
				HttpStatus.OK
		);
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody MedicoDTO medicoDto) throws Exception {
		Medico medico = modelMapper.map(medicoDto, Medico.class);
		Medico medicoRegistro = medicoService.registrar(medico);
		MedicoDTO medicoDTORegistro = modelMapper.map(medicoRegistro,MedicoDTO.class);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(medicoDTORegistro.getIdMedico()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MedicoDTO> modificar(@Valid @RequestBody MedicoDTO medicoDTOMod) throws Exception {
		Optional<Medico> medico = medicoService.listarPorId(medicoDTOMod.getIdMedico());
		if (medico.isPresent()) {
			medicoDTOMod = modelMapper.map(
								medicoService.modificar(
										modelMapper.map(medicoDTOMod, Medico.class)
								),
								MedicoDTO.class
						  	);
		} else {
			throw new ModeloNoFoundException(
					"Medico con id: " + medicoDTOMod.getIdMedico() + " no encontrado"
			);
		}
		return new ResponseEntity<>(medicoDTOMod, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception {
		Optional<Medico> medico = medicoService.listarPorId(id);
		if (medico.isPresent()) {
			medicoService.eliminar(id);
		} else {
			throw new ModeloNoFoundException(
					"Medico con id: " + id + " no encontrado"
			);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<MedicoDTO> listarHeteoasPorId(@PathVariable("id") Long id) throws Exception {
		Optional<Medico> medico = medicoService.listarPorId(id);
		if (!medico.isPresent()) {
			throw new ModeloNoFoundException(
					"Medico con id: " + id + " no encontrado"
			);
		}
		
		MedicoDTO medicoDTO = modelMapper.map(medico.get(), MedicoDTO.class);
		
		EntityModel<MedicoDTO> recurso = EntityModel.of(medicoDTO);
		
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(link1.withRel("medicoRecurso1"));
		
		return recurso;
	}
}
