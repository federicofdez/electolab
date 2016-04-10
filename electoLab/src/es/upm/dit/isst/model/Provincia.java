package es.upm.dit.isst.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
	@Entity
public class Provincia implements Serializable {
	@Id
	private String identificador;
	private String nombre;
	private String comunidad;
	private int escanos;
	private int electores;
	private long id_escenario;
	





	public Provincia(String identificador,String nombre, String comunidad, int escanos, int electores,
			long id_escenario) {
		super();
		this.identificador = identificador;
		this.nombre = nombre;
		this.comunidad = comunidad;
		this.escanos = escanos;
		this.electores = electores;
		this.id_escenario = id_escenario;
	}



	@Override
	public String toString() {
		return "Provincia [identificador=" + identificador + ", nombre=" + nombre + ", comunidad=" + comunidad
				+ ", escanos=" + escanos + ", electores=" + electores + ", id_escenario=" + id_escenario + "]";
	}







	public String getIdentificador() {
		return identificador;
	}



	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}



	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}











	public int getElectores() {
		return electores;
	}



	public void setElectores(int electores) {
		this.electores = electores;
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

	public long getId_escenario() {
		return id_escenario;
	}

	public void setId_escenario(long id_escenario) {
		this.id_escenario = id_escenario;
	}


	

	
	
	
}
