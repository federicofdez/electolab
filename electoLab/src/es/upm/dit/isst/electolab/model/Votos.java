package es.upm.dit.isst.electolab.model;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Votos extends AsociacionPartidoCircunscripcion implements JSONAware {
	
	/**
	 * Votos en porcentaje
	 */
	private int votos;

	public Votos(String circunscripcion, String partido, int votos) {
		super(circunscripcion, partido);
		this.votos = votos;
	}

	public int getVotos() {
		return votos;
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
