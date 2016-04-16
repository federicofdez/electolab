package es.upm.dit.isst.model;

import java.io.Serializable;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Partido implements JSONAware, Serializable{

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
	public String toJSONString() {
		JSONObject obj = new JSONObject();
		obj.put("siglas", this.siglas);
		obj.put("nombre", this.nombre);
		obj.put("imagen", this.imagen);
		obj.put("color", this.color);
		return obj.toString();
	}

}
