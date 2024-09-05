package com.estudos.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.model.Chamado;
import com.estudos.model.Cliente;
import com.estudos.model.Tecnico;
import com.estudos.model.enums.Perfil;
import com.estudos.model.enums.Prioridade;
import com.estudos.model.enums.Status;
import com.estudos.repositories.ChamadoRepository;
import com.estudos.repositories.ClienteRepository;
import com.estudos.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {
		Tecnico tec1 = new Tecnico(null, "Valdir Cesar", "53342075031", "valdir@email.com", "senha123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Rosa Maria", "39631856003", "rosamaria@email.com", "senha123");

		Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1,
				cli1);
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(chamado1));
	}
}
