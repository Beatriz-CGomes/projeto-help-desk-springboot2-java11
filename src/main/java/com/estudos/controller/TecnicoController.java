package com.estudos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
