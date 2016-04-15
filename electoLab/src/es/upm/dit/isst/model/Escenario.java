package es.upm.dit.isst.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

	@Entity
public class Escenario implements Serializable {
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE)	
	private Long id;
	private String usuario;
	private Map<String, Integer> votos;
	private Map<String, Integer> escProv;
	private String sistema;
	private String circunscripciones;
	private int mayoria_abs;
	private List<String> comentarios;
	
	
	
	
	public Escenario( String usuario, Map<String, Integer> votos, Map<String, Integer> escProv, String sistema,
			String circunscripciones, int mayoria_abs, List<String> comentarios) {
		super();
		this.usuario = usuario;
		this.votos = votos;
		this.escProv = escProv;
		this.sistema = sistema;
		this.circunscripciones = circunscripciones;
		this.mayoria_abs = mayoria_abs;
		this.comentarios = comentarios;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Map<String, Integer> getVotos() {
		return votos;
	}
	public void setVotos(Map<String, Integer> votos) {
		this.votos = votos;
	}
	public Map<String, Integer> getEscProv() {
		return escProv;
	}
	public void setEscProv(Map<String, Integer> escProv) {
		this.escProv = escProv;
	}
	public String getSistema() {
		return sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public String getCircunscripciones() {
		return circunscripciones;
	}
	public void setCircunscripciones(String circunscripciones) {
		this.circunscripciones = circunscripciones;
	}
	public int getMayoria_abs() {
		return mayoria_abs;
	}
	public void setMayoria_abs(int mayoria_abs) {
		this.mayoria_abs = mayoria_abs;
	}

	public List<String> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<String> comentarios) {
		this.comentarios = comentarios;
	}
	@Override
	public String toString() {
		return "Escenario [id=" + id + ", usuario=" + usuario + ", votos=" + votos + ", escProv=" + escProv
				+ ", sistema=" + sistema + ", circunscripciones=" + circunscripciones + ", mayoria_abs=" + mayoria_abs
				+ ", comentarios=" + comentarios + "]";
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	
}
