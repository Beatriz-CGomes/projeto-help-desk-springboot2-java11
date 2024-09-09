package com.estudos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.model.Tecnico;
import com.estudos.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Tecnico> findById(@PathVariable Integer id) {
		Tecnico tecnicoObj = tecnicoService.findById(id);
		return ResponseEntity.ok().body(tecnicoObj);

	}

}
