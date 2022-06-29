package com.consorcio.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_tipo_juegos")
public class TipoJuego {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_tipo")
	private int codigoTipo;
	@Column(name="nom_tipo")
	private String nombreTipo;
	
	//relacion de uno "tipo juego" a muchos "JUEGOS"
	@OneToMany(mappedBy = "tipo")//asignar el asociacion 
	@JsonIgnore//evitar json de este atributo
	private List<Juegos> listaJuegos;

	public int getCodigoTipo() {
		return codigoTipo;
	}

	public void setCodigoTipo(int codigoTipo) {
		this.codigoTipo = codigoTipo;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public List<Juegos> getListaJuegos() {
		return listaJuegos;
	}

	public void setListaJuegos(List<Juegos> listaJuegos) {
		this.listaJuegos = listaJuegos;
	}

	
	
	
	
	

}
