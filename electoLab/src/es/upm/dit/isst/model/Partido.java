package es.upm.dit.isst.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Partido implements Serializable {
	
	@Id 
	private String siglas;
	private String nombre;
	private String imagen;
	private String color;
	
	
	public Partido(String siglas, String nombre, String imagen, String color) {
		super();
		this.siglas = siglas;
		this.nombre = nombre;
		this.imagen = imagen;
		this.color = color;
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


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}


	@Override
	public String toString() {
		return "Partido [siglas=" + siglas + ", nombre=" + nombre + ", imagen=" + imagen + ", color=" + color + "]";
	}

	
	
}

