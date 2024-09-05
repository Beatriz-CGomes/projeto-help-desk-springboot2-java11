package com.estudos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudos.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
