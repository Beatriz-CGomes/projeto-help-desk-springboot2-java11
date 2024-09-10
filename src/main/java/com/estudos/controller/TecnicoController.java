package com.estudos.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estudos.model.Tecnico;
import com.estudos.model.dtos.TecnicoDTO;
import com.estudos.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		Tecnico tecnicoObj = tecnicoService.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(tecnicoObj));

	}

	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		List<Tecnico> listTecnico = tecnicoService.findById();
		List<TecnicoDTO> listDTO = listTecnico.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<TecnicoDTO> post(@Validated @RequestBody TecnicoDTO objDTO) {
		Tecnico newObj = tecnicoService.post(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<TecnicoDTO> put(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO dto) {
		Tecnico objTecnico = tecnicoService.put(id, dto);
		return ResponseEntity.ok().body(new TecnicoDTO(objTecnico));
	}

}
