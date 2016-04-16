package es.upm.dit.isst.model;

import java.io.Serializable;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Provincia implements JSONAware,Serializable{

	private String nombre;
	private String id;
	private String comunidad;
	private int escanos;
	private int electores;

	public Provincia(String nombre, String id, String comunidad,
			int escanos, int electores) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.comunidad = comunidad;
		this.escanos = escanos;
		this.electores = electores;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getComunidad() {
		return comunidad;
	}

	public int getEscanos() {
		return escanos;
	}

	public int getElectores() {
		return electores;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}

	public void setEscanos(int escanos) {
		this.escanos = escanos;
	}

	public void setElectores(int electores) {
		this.electores = electores;
	}

	@Override
	public String toJSONString() {
		JSONObject obj = new JSONObject();
		obj.put("nombre", this.nombre);
		obj.put("id", this.id);
		obj.put("comunidad", this.comunidad);
		obj.put("electores", this.electores);
		return obj.toString();
	}

}
