package com.whnm.mediappbackend.restcontroller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

import com.whnm.mediappbackend.dto.ConsultaDTO;
import com.whnm.mediappbackend.dto.ConsultaListaExamenDTO;
import com.whnm.mediappbackend.entity.Consulta;
import com.whnm.mediappbackend.entity.Examen;
import com.whnm.mediappbackend.exception.ModeloNoFoundException;
import com.whnm.mediappbackend.service.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaRestController {

	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<ConsultaDTO>> listar() throws Exception {
		List<ConsultaDTO> listaConsultasDTO = consultaService.listar().stream()
				.map(
						consulta -> modelMapper.map(consulta, ConsultaDTO.class)
				)
				.collect(Collectors.toList());
		return new ResponseEntity<>(listaConsultasDTO, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ConsultaDTO> listarPorId(@PathVariable("id") Long id) throws Exception {
		Optional<Consulta> consulta = consultaService.listarPorId(id);
		if (!consulta.isPresent()) {
			throw new ModeloNoFoundException("Consulta con id: " + id + " no encontrado");
		}
		
		return new ResponseEntity<>(
				modelMapper.map(
						consulta.get(), ConsultaDTO.class
				), 
				HttpStatus.OK
		);
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody ConsultaListaExamenDTO consultaListaExamenDto) throws Exception {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Consulta consulta = modelMapper.map(consultaListaExamenDto, Consulta.class);
		List<Examen> examenes = modelMapper.map(consultaListaExamenDto.getListaExamen(), new TypeToken<List<Examen>>() {}.getType());
		
		Consulta consultaRegistro = consultaService.registrarConsultaCompleta(consulta, examenes);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(consultaRegistro.getIdConsulta()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ConsultaDTO> modificar(@Valid @RequestBody ConsultaDTO consultaDTOMod) throws Exception {
		Optional<Consulta> consulta = consultaService.listarPorId(consultaDTOMod.getIdConsulta());
		if (consulta.isPresent()) {
			consultaDTOMod = modelMapper.map(
								consultaService.modificar(
										modelMapper.map(consultaDTOMod, Consulta.class)
								),
								ConsultaDTO.class
						  	);
		} else {
			throw new ModeloNoFoundException(
					"Consulta con id: " + consultaDTOMod.getIdConsulta() + " no encontrado"
			);
		}
		return new ResponseEntity<>(consultaDTOMod, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception {
		Optional<Consulta> consulta = consultaService.listarPorId(id);
		if (consulta.isPresent()) {
			consultaService.eliminar(id);
		} else {
			throw new ModeloNoFoundException(
					"Consulta con id: " + id + " no encontrado"
			);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<ConsultaDTO> listarHeteoasPorId(@PathVariable("id") Long id) throws Exception {
		Optional<Consulta> consulta = consultaService.listarPorId(id);
		if (!consulta.isPresent()) {
			throw new ModeloNoFoundException(
					"Consulta con id: " + id + " no encontrado"
			);
		}
		
		ConsultaDTO consultaDTO = modelMapper.map(consulta.get(), ConsultaDTO.class);
		
		EntityModel<ConsultaDTO> recurso = EntityModel.of(consultaDTO);
		
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(link1.withRel("consultaRecurso1"));
		
		return recurso;
	}
}
