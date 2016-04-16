package es.upm.dit.isst.model;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Votos implements JSONAware {

	private String provincia;
	private String partido;
	private int votos;

	public Votos(String provincia, String partido, int votos) {
		super();
		this.provincia = provincia;
		this.partido = partido;
		this.votos = votos;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getPartido() {
		return partido;
	}

	public int getVotos() {
		return votos;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
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
		obj.put("provincia", this.provincia);
		obj.put("partido", this.partido);
		obj.put("votos", this.votos);
		return obj.toString();
	}

}
