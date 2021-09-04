package com.whnm.mediappbackend.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whnm.mediappbackend.entity.Paciente;
import com.whnm.mediappbackend.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteRestController {

	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping
	public List<Paciente> listar() throws Exception {
		return pacienteService.listar();
	}
	
	@GetMapping("/{id}")
	public Paciente listarPorId(@PathVariable("id") Long id) throws Exception {
		return pacienteService.listarPorId(id).get();
	}
	
	@PostMapping
	public Paciente registrar(@RequestBody Paciente p) throws Exception {
		return pacienteService.registrar(p);
	}
	
	@PutMapping("/{id}")
	public Paciente modificar(@PathVariable("id") Long id, @RequestBody Paciente pacienteMod) throws Exception {
		Optional<Paciente> paciente = pacienteService.listarPorId(id);
		if (paciente.isPresent()) {
			pacienteMod.setIdPaciente(id);
			pacienteMod = pacienteService.modificar(pacienteMod);
		} else {
			pacienteMod = null;
		}
		return pacienteMod;
	}
	
}
