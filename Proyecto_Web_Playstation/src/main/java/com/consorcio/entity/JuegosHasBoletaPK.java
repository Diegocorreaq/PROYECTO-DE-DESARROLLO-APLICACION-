package com.consorcio.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class JuegosHasBoletaPK implements Serializable{
	
	@Column(name = "num_bol")
	private int numeroBoleta;
	@Column(name = "cod_juegos")
	private int codigoJuegos;
	
	public int getNumeroBoleta() {
		return numeroBoleta;
	}
	public void setNumeroBoleta(int numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}
	public int getCodigoJuegos() {
		return codigoJuegos;
	}
	public void setCodigoJuegos(int codigoJuego) {
		this.codigoJuegos = codigoJuego;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoJuegos;
		result = prime * result + numeroBoleta;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JuegosHasBoletaPK other = (JuegosHasBoletaPK) obj;
		if (codigoJuegos != other.codigoJuegos)
			return false;
		if (numeroBoleta != other.numeroBoleta)
			return false;
		return true;
	}
	
}
