package es.upm.dit.isst.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Partido implements Serializable {
	
	@Id
	private String siglas;
	private String nombre;
	private String imagen;
	private int color;
	private String provincias;
	private int id_escenario;



	public Partido(String siglas, String nombre, int color, String provincias) {
		super();
		this.siglas = siglas;
		this.nombre = nombre;
		this.color = color;
		this.provincias = provincias;
	}
	@Override
	public String toString() {
		return "Partidos [siglas=" + siglas + ", nombre=" + nombre + ", color=" + color + ", provincias=" + provincias
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}


	public String getSiglas() {
		return siglas;
	}


	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getColor() {
		return color;
	}


	public void setColor(int color) {
		this.color = color;
	}


	public String getProvincias() {
		return provincias;
	}


	public void setProvincias(String provincias) {
		this.provincias = provincias;
	}



}

