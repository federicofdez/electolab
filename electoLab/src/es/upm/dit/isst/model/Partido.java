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
	private String color;
	private String comunidad;
	private int id_escenario;
	private int votos;



	
	public Partido(String siglas, String nombre, String imagen, String color, String comunidad, int id_escenario,
			int votos) {
		super();
		this.siglas = siglas;
		this.nombre = nombre;
		this.imagen = imagen;
		this.color = color;
		this.comunidad = comunidad;
		this.id_escenario = id_escenario;
		this.votos = votos;
	}

	@Override
	public String toString() {
		return "Partidos [siglas=" + siglas + ", nombre=" + nombre + ", color=" + color + ", comunidad=" + comunidad
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


	public String getColor() {
		return color;
	}


	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getId_escenario() {
		return id_escenario;
	}

	public void setId_escenario(int id_escenario) {
		this.id_escenario = id_escenario;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public String getComunidad() {
		return comunidad;
	}


	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}



}

