package es.upm.dit.isst.model;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Comentario implements JSONAware {

	private String usuario;
	private String fecha;
	private String texto;

	public Comentario(String usuario, String fecha, String texto) {
		super();
		this.usuario = usuario;
		this.fecha = fecha;
		this.texto = texto;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getFecha() {
		return fecha;
	}

	public String getTexto() {
		return texto;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toJSONString() {
		JSONObject obj = new JSONObject();
		obj.put("usuario", this.usuario);
		obj.put("fecha", this.fecha);
		obj.put("texto", this.texto);
		return obj.toString();
	}

}
