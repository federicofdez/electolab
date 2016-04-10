package es.upm.dit.isst.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Partido implements Serializable {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE)	
	private long id_partido;
	private String siglas;
	private String nombre;
	private String imagen;
	private String color;
	private List<String> provincia;
	private List<Double> votos;
	private long id_escenario;



	public long getId_partido() {
		return id_partido;
	}



	public void setId_partido(long id_partido) {
		this.id_partido = id_partido;
	}






	@Override
	public String toString() {
		return "Partido [id_partido=" + id_partido + ", siglas=" + siglas + ", nombre=" + nombre + ", imagen=" + imagen
				+ ", color=" + color + ", provincia=" + provincia + ", votos=" + votos + ", id_escenario="
				+ id_escenario + "]";
	}






	public Partido(String siglas, String nombre, String imagen, String color, List<String> provincia,
			List<Double> votos, long id_escenario) {
		super();
		this.siglas = siglas;
		this.nombre = nombre;
		this.imagen = imagen;
		this.color = color;
		this.provincia = provincia;
		this.votos = votos;
		this.id_escenario = id_escenario;
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






	public List<String> getProvincia() {
		return provincia;
	}






	public void setProvincia(List<String> provincia) {
		this.provincia = provincia;
	}






	public List<Double> getVotos() {
		return votos;
	}






	public void setVotos(List<Double> votos) {
		this.votos = votos;
	}






	public long getId_escenario() {
		return id_escenario;
	}






	public void setId_escenario(long id_escenario) {
		this.id_escenario = id_escenario;
	}






	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}



}

