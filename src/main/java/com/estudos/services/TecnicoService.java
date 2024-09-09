package com.estudos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.model.Tecnico;
import com.estudos.repositories.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnicoObj = tecnicoRepository.findById(id);
		return tecnicoObj.orElse(null);
	}
}
