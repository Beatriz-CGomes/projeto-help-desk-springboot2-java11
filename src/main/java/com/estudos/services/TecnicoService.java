package com.estudos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.model.Pessoa;
import com.estudos.model.Tecnico;
import com.estudos.model.dtos.TecnicoDTO;
import com.estudos.repositories.PessoaRepository;
import com.estudos.repositories.TecnicoRepository;
import com.estudos.services.exceptions.InvalidDataAccessResourceUsageException;
import com.estudos.services.exceptions.ObjectNotFoundExceptions;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnicoObj = tecnicoRepository.findById(id);
		return tecnicoObj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado! Id: " + id));
	}

	public List<Tecnico> findById() {
		return tecnicoRepository.findAll();
	}

	public Tecnico post(TecnicoDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return tecnicoRepository.save(newObj);
	}

	private void validaPorCpfEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> objPessoa = pessoaRepository.findByCpf(objDTO.getCpf());
		if (objPessoa.isPresent() && objPessoa.get().getId() != objDTO.getId()) {
			throw new InvalidDataAccessResourceUsageException("CPF já cadastrado no sistema");
		}

		objPessoa = pessoaRepository.findByEmail(objDTO.getEmail());
		if (objPessoa.isPresent() && objPessoa.get().getId() != objDTO.getId()) {
			throw new InvalidDataAccessResourceUsageException("E-mail já cadastrado no sistema");
		}

	}

}
