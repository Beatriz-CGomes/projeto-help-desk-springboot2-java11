package com.estudos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		List<ChamadoDTO> listChamadoDTO = listChamado.stream().map(obj -> new ChamadoDTO())
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listChamadoDTO);
	}
}
