package es.upm.dit.isst.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
	@Entity
public class Provincia implements Serializable {
	@Id
	private String nombre;
	private int escanos;
	private String comunidad;
	private int id_escenario;
	
	public Provincia(String nombre, int escanos, String comunidad, int id_escenario) {
		super();
		this.nombre = nombre;
		this.escanos = escanos;
		this.comunidad = comunidad;
		this.id_escenario = id_escenario;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Provincia [nombre=" + nombre + ", escanos=" + escanos + ", comunidad=" + comunidad + ", id_escenario="
				+ id_escenario + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEscanos() {
		return escanos;
	}

	public void setEscanos(int escanos) {
		this.escanos = escanos;
	}

	public String getComunidad() {
		return comunidad;
	}

	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}

	public int getId_escenario() {
		return id_escenario;
	}

	public void setId_escenario(int id_escenario) {
		this.id_escenario = id_escenario;
	}


	

	
	
	
}
