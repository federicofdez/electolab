package es.upm.dit.isst.model;

import java.io.Serializable;

public class Relacion implements Serializable {

	private String provincia;
	private int escanosProvincia;
	private String partido;
	private int votos ;
	
	
	
	
	
	public Relacion(String provincia, int escanosProvincia, String partido, int votos) {
		super();
		this.provincia = provincia;
		this.escanosProvincia = escanosProvincia;
		this.partido = partido;
		this.votos = votos;
	}



	public String getProvincia() {
		return provincia;
	}



	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}



	public int getEscanosProvincia() {
		return escanosProvincia;
	}



	public void setEscanosProvincia(int escanosProvincia) {
		this.escanosProvincia = escanosProvincia;
	}



	public String getPartido() {
		return partido;
	}



	public void setPartido(String partido) {
		this.partido = partido;
	}



	public int getVotos() {
		return votos;
	}



	public void setVotos(int votos) {
		this.votos = votos;
	}



	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}



	@Override
	public String toString() {
		return "Relacion [provincia=" + provincia + ", escanosProvincia=" + escanosProvincia + ", partido=" + partido
				+ ", votos=" + votos + "]";
	}

	
}
