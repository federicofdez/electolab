package es.upm.dit.isst.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
	@Entity
public class Provincia implements Serializable {
	
	@Id 
	private String identificador;
	private String nombre;
	private String comunidad;
	private int electores;
	
	
	public Provincia(String identificador, String nombre, String comunidad, int electores) {
		super();
		this.identificador = identificador;
		this.nombre = nombre;
		this.comunidad = comunidad;
		this.electores = electores;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "Provincia [identificador=" + identificador + ", nombre=" + nombre + ", comunidad=" + comunidad
				+ ", electores=" + electores + "]";
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getComunidad() {
		return comunidad;
	}
	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}
	public int getElectores() {
		return electores;
	}
	public void setElectores(int electores) {
		this.electores = electores;
	}
	
	
}
