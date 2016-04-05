package es.upm.dit.isst.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
	@Entity
public class Provincia implements Serializable {
	@Id
	private String nombre;
	private int escaños;
	private String comunidad;
	private int id_escenario;
	
	@Override
	public String toString() {
		return "Provincia [nombre=" + nombre + ", escaños=" + escaños
				+ "]";
	}

	
	public Provincia(String nombre, int escaños) {
		this.nombre = nombre;
		this.escaños = escaños;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEscaños() {
		return escaños;
	}

	public void setEscaños(int escaños) {
		this.escaños = escaños;
	}

	
	
	
}
