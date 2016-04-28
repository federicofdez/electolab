package es.upm.dit.isst.electolab.model;

public abstract class AsociacionPartidoCircunscripcion {
	
	protected String circunscripcion;
	protected String partido;
	
	public AsociacionPartidoCircunscripcion(String circunscripcion, String partido){
		this.circunscripcion = circunscripcion;
		this.partido = partido;
	}
	
	public String getCircunscripcion() {
		return circunscripcion;
	}

	public String getPartido() {
		return partido;
	}
	
	public void setPartido(String partido) {
		this.partido = partido;
	}

	public void setCircunscripcion(String circunscripcion) {
		this.circunscripcion = circunscripcion;
	}

}
