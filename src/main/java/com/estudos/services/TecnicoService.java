package com.estudos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.model.Tecnico;
import com.estudos.repositories.TecnicoRepository;
import com.estudos.services.exceptions.ObjectNotFoundExceptions;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnicoObj = tecnicoRepository.findById(id);
		return tecnicoObj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado! Id: " + id));
	}

}
