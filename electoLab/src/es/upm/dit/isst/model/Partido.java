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
	private String provincia;
	private int id_escenario;
	private long votos;



	
	public Partido(String siglas, String nombre, String imagen, String color, String provincia, int id_escenario,
			long votos) {
		super();
		this.siglas = siglas;
		this.nombre = nombre;
		this.imagen = imagen;
		this.color = color;
		this.provincia = provincia;
		this.id_escenario = id_escenario;
		this.votos = votos;
	}




	@Override
	public String toString() {
		return "Partido [siglas=" + siglas + ", nombre=" + nombre + ", imagen=" + imagen + ", color=" + color
				+ ", provincia=" + provincia + ", id_escenario=" + id_escenario + ", votos=" + votos + "]";
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




	public String getProvincia() {
		return provincia;
	}




	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}




	public int getId_escenario() {
		return id_escenario;
	}




	public void setId_escenario(int id_escenario) {
		this.id_escenario = id_escenario;
	}




	public long getVotos() {
		return votos;
	}




	public void setVotos(long votos) {
		this.votos = votos;
	}




	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}

