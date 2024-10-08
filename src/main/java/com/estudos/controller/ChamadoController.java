package com.estudos.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estudos.model.Chamado;
import com.estudos.model.dtos.ChamadoDTO;
import com.estudos.services.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {

	@Autowired
	private ChamadoService chamadoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
		Chamado obj = chamadoService.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll() {
		List<Chamado> listChamado = chamadoService.findAll();
		List<ChamadoDTO> listChamadoDTO = listChamado.stream().map(obj -> new ChamadoDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listChamadoDTO);
	}

	@PostMapping
	public ResponseEntity<ChamadoDTO> post(@Valid @RequestBody ChamadoDTO chamadoDTO) {
		Chamado chamadoNovo = chamadoService.post(chamadoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamadoNovo.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<ChamadoDTO> pit(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO chamadoDTO) {
		Chamado chamadoObj = chamadoService.put(id, chamadoDTO);
		return ResponseEntity.ok().body(new ChamadoDTO(chamadoObj));
	}

}
