package es.upm.dit.isst.electolab.model;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Votos implements JSONAware {

	private String circunscripcion;
	private String partido;
	private int votos;

	public Votos(String circunscripcion, String partido, int votos) {
		super();
		this.circunscripcion = circunscripcion;
		this.partido = partido;
		this.votos = votos;
	}

	public String getCircunscripcion() {
		return circunscripcion;
	}

	public String getPartido() {
		return partido;
	}

	public int getVotos() {
		return votos;
	}

	public void setCircunscripcion(String circunscripcion) {
		this.circunscripcion = circunscripcion;
	}

	public void setPartido(String partido) {
		this.partido = partido;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

	@Override
	public String toJSONString() {
		JSONObject obj = new JSONObject();
		obj.put("circunscripcion", this.circunscripcion);
		obj.put("partido", this.partido);
		obj.put("votos", this.votos);
		return obj.toString();
	}

}
