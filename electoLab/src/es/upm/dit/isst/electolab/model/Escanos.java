package es.upm.dit.isst.electolab.model;

public class Escanos extends AsociacionPartidoCircunscripcion {

	private int escanos;

	public Escanos(String circunscripcion, String partido, int escanos) {
		super(circunscripcion, partido);
		this.escanos = escanos;
	}

	public int getEscanos() {
		return escanos;
	}

	public void setEscanos(int escanos) {
		this.escanos = escanos;
	}
	
	@Override
	public String toString() {
		return this.partido + "/" + this.escanos;
	}
}
