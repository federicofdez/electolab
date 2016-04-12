package es.upm.dit.isst.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Grupo implements Serializable {

	@Id 
	private String nombre;
	private String password;
	
	
	
	
	public Grupo(String nombre, String password) {
		super();
		this.nombre = nombre;
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Grupo [nombre=" + nombre + ", password=" + password + "]";
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}


	
}
