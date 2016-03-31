package es.upm.dit.isst.model;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Escenario implements Serializable {
	
	@Id
	private String autor;
	private HashMap <String, String[]> partidos;
	private HashMap <String, int[]> votos;
	private String sistema;
	private String circunscripciones;
	private int escanos;
	
	public Escenario(String autor, HashMap<String, String[]> partidos, HashMap<String, int[]> votos, String sistema,
			String circunscripciones, int escanos) {
		super();
		this.autor = autor;
		this.partidos = partidos;
		this.votos = votos;
		this.sistema = sistema;
		this.circunscripciones = circunscripciones;
		this.escanos = escanos;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public HashMap<String, String[]> getPartidos() {
		return partidos;
	}

	public void setPartidos(HashMap<String, String[]> partidos) {
		this.partidos = partidos;
	}

	public HashMap<String, int[]> getVotos() {
		return votos;
	}

	public void setVotos(HashMap<String, int[]> votos) {
		this.votos = votos;
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

	public int getEscanos() {
		return escanos;
	}

	public void setEscanos(int escanos) {
		this.escanos = escanos;
	}


	@Override
	public String toString() {
		return "Escenario [autor=" + autor + ", partidos=" + partidos + ", votos=" + votos + ", sistema=" + sistema
				+ ", circunscripciones=" + circunscripciones + ", escanos=" + escanos + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}



}

