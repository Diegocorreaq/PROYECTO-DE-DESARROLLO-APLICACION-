package com.consorcio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.consorcio.entity.Boleta;
import com.consorcio.entity.JuegosHasBoleta;

public interface BoletaRepository  extends JpaRepository<Boleta,Integer>{
	@Query("select b from Boleta b where b.numeroBoleta=?1")
	public Boleta buscarBoletaPorNumero(int num);
	
	@Query("select mhb from JuegosHasBoleta mhb where mhb.boleta.numeroBoleta=?1")
	public List<JuegosHasBoleta> buscarDetalleBoletaPorNumero(int num);

}
