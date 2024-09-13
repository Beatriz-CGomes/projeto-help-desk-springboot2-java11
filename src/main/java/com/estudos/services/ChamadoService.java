package com.estudos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.model.Chamado;
import com.estudos.model.Cliente;
import com.estudos.model.Tecnico;
import com.estudos.model.dtos.ChamadoDTO;
import com.estudos.model.enums.Prioridade;
import com.estudos.model.enums.Status;
import com.estudos.repositories.ChamadoRepository;
import com.estudos.services.exceptions.ObjectNotFoundExceptions;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	public Chamado findById(Integer id) {
		Optional<Chamado> chamadoObj = chamadoRepository.findById(id);
		return chamadoObj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado! ID: " + id));
	}

	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}

	public Chamado post(ChamadoDTO chamadoDTO) {
		return chamadoRepository.save(chamadoNovo(chamadoDTO));
	}

	private Chamado chamadoNovo(ChamadoDTO chamadoObj) {
		Tecnico tecnico = tecnicoService.findById(chamadoObj.getTecnico());
		Cliente cliente = clienteService.findById(chamadoObj.getCliente());

		Chamado chamado = new Chamado();
		if (chamadoObj.getId() != null) {
			chamado.setId(chamadoObj.getId());
		}

		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnumStatus(chamadoObj.getPrioridade()));
		chamado.setStatus(Status.toEnumStatus(chamadoObj.getStatus()));
		chamado.setTitulo(chamadoObj.getTitulo());
		chamado.setObservacoes(chamadoObj.getObservacoes());
		return chamado;

	}
}
