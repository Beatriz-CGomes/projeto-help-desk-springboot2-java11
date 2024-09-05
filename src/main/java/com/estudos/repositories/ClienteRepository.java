package com.estudos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudos.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
