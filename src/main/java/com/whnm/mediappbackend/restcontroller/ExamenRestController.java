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

import com.whnm.mediappbackend.dto.ExamenDTO;
import com.whnm.mediappbackend.entity.Examen;
import com.whnm.mediappbackend.exception.ModeloNoFoundException;
import com.whnm.mediappbackend.service.ExamenService;

@RestController
@RequestMapping("/examenes")
public class ExamenRestController {

	@Autowired
	private ExamenService examenService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<ExamenDTO>> listar() throws Exception {
		List<ExamenDTO> listaExamensDTO = examenService.listar().stream()
				.map(
						examen -> modelMapper.map(examen, ExamenDTO.class)
				)
				.collect(Collectors.toList());
		return new ResponseEntity<>(listaExamensDTO, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ExamenDTO> listarPorId(@PathVariable("id") Long id) throws Exception {
		Optional<Examen> examen = examenService.listarPorId(id);
		if (!examen.isPresent()) {
			throw new ModeloNoFoundException("Examen con id: " + id + " no encontrado");
		}
		
		return new ResponseEntity<>(
				modelMapper.map(
						examen.get(), ExamenDTO.class
				), 
				HttpStatus.OK
		);
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody ExamenDTO examenDto) throws Exception {
		Examen examen = modelMapper.map(examenDto, Examen.class);
		Examen examenRegistro = examenService.registrar(examen);
		ExamenDTO examenDTORegistro = modelMapper.map(examenRegistro,ExamenDTO.class);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(examenDTORegistro.getIdExamen()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ExamenDTO> modificar(@Valid @RequestBody ExamenDTO examenDTOMod) throws Exception {
		Optional<Examen> examen = examenService.listarPorId(examenDTOMod.getIdExamen());
		if (examen.isPresent()) {
			examenDTOMod = modelMapper.map(
								examenService.modificar(
										modelMapper.map(examenDTOMod, Examen.class)
								),
								ExamenDTO.class
						  	);
		} else {
			throw new ModeloNoFoundException(
					"Examen con id: " + examenDTOMod.getIdExamen() + " no encontrado"
			);
		}
		return new ResponseEntity<>(examenDTOMod, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception {
		Optional<Examen> examen = examenService.listarPorId(id);
		if (examen.isPresent()) {
			examenService.eliminar(id);
		} else {
			throw new ModeloNoFoundException(
					"Examen con id: " + id + " no encontrado"
			);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<ExamenDTO> listarHeteoasPorId(@PathVariable("id") Long id) throws Exception {
		Optional<Examen> examen = examenService.listarPorId(id);
		if (!examen.isPresent()) {
			throw new ModeloNoFoundException(
					"Examen con id: " + id + " no encontrado"
			);
		}
		
		ExamenDTO examenDTO = modelMapper.map(examen.get(), ExamenDTO.class);
		
		EntityModel<ExamenDTO> recurso = EntityModel.of(examenDTO);
		
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(link1.withRel("examenRecurso1"));
		
		return recurso;
	}
}
