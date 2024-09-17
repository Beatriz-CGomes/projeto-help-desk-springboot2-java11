package com.estudos.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.estudos.model.Cliente;
import com.estudos.model.Pessoa;
import com.estudos.model.dtos.ClienteDTO;
import com.estudos.repositories.ClienteRepository;
import com.estudos.repositories.PessoaRepository;
import com.estudos.services.exceptions.InvalidDataAccessResourceUsageException;
import com.estudos.services.exceptions.ObjectNotFoundExceptions;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
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

	public Cliente post(ClienteDTO clienteDTO) {
		clienteDTO.setId(null);
		clienteDTO.setSenha(encoder.encode(clienteDTO.getSenha()));
		validaPorCpfEEmail(clienteDTO);
		Cliente clienteNovo = new Cliente(clienteDTO);
		return clienteRepository.save(clienteNovo);

	}

	private void validaPorCpfEEmail(ClienteDTO objDTO) {
		Optional<Pessoa> objPessoa = pessoaRepository.findByCpf(objDTO.getCpf());
		if (objPessoa.isPresent() && objPessoa.get().getId() != objDTO.getId()) {
			throw new InvalidDataAccessResourceUsageException("CPF já cadastrado no sistema");
		}

		objPessoa = pessoaRepository.findByEmail(objDTO.getEmail());
		if (objPessoa.isPresent() && objPessoa.get().getId() != objDTO.getId()) {
			throw new InvalidDataAccessResourceUsageException("E-mail já cadastrado no sistema");
		}
	}

	public Cliente put(Integer id, @Valid ClienteDTO clienteDTO) {
		clienteDTO.setId(id);
		Cliente clienteObj = findById(id);
		validaPorCpfEEmail(clienteDTO);
		clienteObj = new Cliente(clienteDTO);
		return clienteRepository.save(clienteObj);
	}
}
