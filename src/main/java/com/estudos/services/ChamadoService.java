package com.estudos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.model.Chamado;
import com.estudos.repositories.ChamadoRepository;
import com.estudos.services.exceptions.ObjectNotFoundExceptions;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;

	public Chamado findById(Integer id) {
		Optional<Chamado> chamadoObj = chamadoRepository.findById(id);
		return chamadoObj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado! ID: " + id));
	}
}
