package es.upm.dit.isst.model;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Provincia implements JSONAware {

	private String nombre;
	private String comunidad;
	private int escanos;
	private int electores;

	public Provincia(String nombre, String comunidad,
			int escanos, int electores) {
		super();
		this.nombre = nombre;
		this.comunidad = comunidad;
		this.escanos = escanos;
		this.electores = electores;
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
		obj.put("comunidad", this.comunidad);
		obj.put("electores", this.electores);
		return obj.toString();
	}

}
