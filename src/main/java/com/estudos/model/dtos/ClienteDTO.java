package com.estudos.model.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.estudos.model.Cliente;
import com.estudos.model.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer id;
	@NotNull(message = "o campo NOME é obrigatório")
	protected String nome;
	@NotNull(message = "o campo CPF é obrigatório")
	protected String cpf;
	@NotNull(message = "o campo EMAIL é obrigatório")
	protected String email;
	@NotNull(message = "o campo SENHA é obrigatório")
	protected String senha;
	protected Set<Integer> perfis = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCricao = LocalDate.now();

	public ClienteDTO() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public ClienteDTO(Cliente tecnicoObj) {
		super();
		this.id = tecnicoObj.getId();
		this.nome = tecnicoObj.getNome();
		this.cpf = tecnicoObj.getCpf();
		this.email = tecnicoObj.getEmail();
		this.senha = tecnicoObj.getSenha();
		this.perfis = tecnicoObj.getPerfils().stream().map(perfil -> perfil.getCodigo()).collect(Collectors.toSet());
		this.dataCricao = tecnicoObj.getDataCricao();
		addPerfil(Perfil.CLIENTE);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public LocalDate getDataCricao() {
		return dataCricao;
	}

	public void setDataCricao(LocalDate dataCricao) {
		this.dataCricao = dataCricao;
	}

}
