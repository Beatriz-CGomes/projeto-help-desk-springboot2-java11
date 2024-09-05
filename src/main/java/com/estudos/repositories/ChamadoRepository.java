package com.estudos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudos.model.Chamado;


public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
