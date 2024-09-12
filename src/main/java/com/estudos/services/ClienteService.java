package com.estudos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.estudos.model.Cliente;
import com.estudos.repositories.ClienteRepository;
import com.estudos.repositories.PessoaRepository;
import com.estudos.services.exceptions.ObjectNotFoundExceptions;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> clienteObj = clienteRepository.findById(id);
		return clienteObj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public void delete(Integer id) {
		Cliente clienteObj = findById(id);
		if (clienteObj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
		} else {
			clienteRepository.deleteById(id);
		}

	}
}
