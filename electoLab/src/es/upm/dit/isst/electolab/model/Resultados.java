package es.upm.dit.isst.electolab.model;

public class Resultados extends AsociacionPartidoCircunscripcion {

	private int escanos; // escaños obtenidos
	private int votos; // votos absolutos obtenidos

	public Resultados(String circunscripcion, String partido, int escanos, int votos) {
		super(circunscripcion, partido);
		this.escanos = escanos;
		this.votos = votos;
	}

	public int getEscanos() {
		return escanos;
	}

	public void setEscanos(int escanos) {
		this.escanos = escanos;
	}
	
	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}
	
	@Override
	public String toString() {
		return this.partido + "/" + this.escanos;
	}
}
