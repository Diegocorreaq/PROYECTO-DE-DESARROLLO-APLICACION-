package com.consorcio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consorcio.entity.Cliente;

public interface ProveedorRepository extends JpaRepository<Cliente, Integer>{
	
	
	

}
