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

import com.whnm.mediappbackend.dto.EspecialidadDTO;
import com.whnm.mediappbackend.entity.Especialidad;
import com.whnm.mediappbackend.exception.ModeloNoFoundException;
import com.whnm.mediappbackend.service.EspecialidadService;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadRestController {

	@Autowired
	private EspecialidadService especialidadService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<EspecialidadDTO>> listar() throws Exception {
		List<EspecialidadDTO> listaEspecialidadsDTO = especialidadService.listar().stream()
				.map(
						especialidad -> modelMapper.map(especialidad, EspecialidadDTO.class)
				)
				.collect(Collectors.toList());
		return new ResponseEntity<>(listaEspecialidadsDTO, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EspecialidadDTO> listarPorId(@PathVariable("id") Long id) throws Exception {
		Optional<Especialidad> especialidad = especialidadService.listarPorId(id);
		if (!especialidad.isPresent()) {
			throw new ModeloNoFoundException("Especialidad con id: " + id + " no encontrado");
		}
		
		return new ResponseEntity<>(
				modelMapper.map(
						especialidad.get(), EspecialidadDTO.class
				), 
				HttpStatus.OK
		);
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody EspecialidadDTO especialidadDto) throws Exception {
		Especialidad especialidad = modelMapper.map(especialidadDto, Especialidad.class);
		Especialidad especialidadRegistro = especialidadService.registrar(especialidad);
		EspecialidadDTO especialidadDTORegistro = modelMapper.map(especialidadRegistro,EspecialidadDTO.class);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(especialidadDTORegistro.getIdEspecialidad()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EspecialidadDTO> modificar(@Valid @RequestBody EspecialidadDTO especialidadDTOMod) throws Exception {
		Optional<Especialidad> especialidad = especialidadService.listarPorId(especialidadDTOMod.getIdEspecialidad());
		if (especialidad.isPresent()) {
			especialidadDTOMod = modelMapper.map(
								especialidadService.modificar(
										modelMapper.map(especialidadDTOMod, Especialidad.class)
								),
								EspecialidadDTO.class
						  	);
		} else {
			throw new ModeloNoFoundException(
					"Especialidad con id: " + especialidadDTOMod.getIdEspecialidad() + " no encontrado"
			);
		}
		return new ResponseEntity<>(especialidadDTOMod, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception {
		Optional<Especialidad> especialidad = especialidadService.listarPorId(id);
		if (especialidad.isPresent()) {
			especialidadService.eliminar(id);
		} else {
			throw new ModeloNoFoundException(
					"Especialidad con id: " + id + " no encontrado"
			);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<EspecialidadDTO> listarHeteoasPorId(@PathVariable("id") Long id) throws Exception {
		Optional<Especialidad> especialidad = especialidadService.listarPorId(id);
		if (!especialidad.isPresent()) {
			throw new ModeloNoFoundException(
					"Especialidad con id: " + id + " no encontrado"
			);
		}
		
		EspecialidadDTO especialidadDTO = modelMapper.map(especialidad.get(), EspecialidadDTO.class);
		
		EntityModel<EspecialidadDTO> recurso = EntityModel.of(especialidadDTO);
		
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(link1.withRel("especialidadRecurso1"));
		
		return recurso;
	}
}
