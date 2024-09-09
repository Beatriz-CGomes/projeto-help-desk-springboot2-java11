package com.estudos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.estudos.model.dtos.TecnicoDTO;
import com.estudos.model.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tecnico extends Pessoa {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
	}

	public Tecnico(TecnicoDTO tecnicoObj) {
		super();
		this.id = tecnicoObj.getId();
		this.nome = tecnicoObj.getNome();
		this.cpf = tecnicoObj.getCpf();
		this.email = tecnicoObj.getEmail();
		this.senha = tecnicoObj.getSenha();
		this.perfis = tecnicoObj.getPerfis().stream().map(perfil -> perfil.getCodigo()).collect(Collectors.toSet());
		this.dataCricao = tecnicoObj.getDataCricao();
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

}
