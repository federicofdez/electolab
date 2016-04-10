package es.upm.dit.isst.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

	@Entity
public class Escenario implements Serializable {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	private int votos_totales;
	private String sistema;
	private String circunscripciones;
	private int mayoria_abs;
	private int total_escaños;
	private int total_circuns;
	
	

	public Escenario(Long id, int votos_totales, String sistema, String circunscripciones, int mayoria_abs,
			int total_escaños, int total_circuns) {
		super();
		this.id = id;
		this.votos_totales = votos_totales;
		this.sistema = sistema;
		this.circunscripciones = circunscripciones;
		this.mayoria_abs = mayoria_abs;
		this.total_escaños = total_escaños;
		this.total_circuns = total_circuns;
	}
	@Override
	public String toString() {
		return "Escenario [id=" + id + ", votos_totales=" + votos_totales + ", sistema=" + sistema
				+ ", circunscripciones=" + circunscripciones + ", mayoria_abs=" + mayoria_abs + ", total_escaños="
				+ total_escaños + ", total_circuns=" + total_circuns + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getVotos_totales() {
		return votos_totales;
	}
	public void setVotos_totales(int votos_totales) {
		this.votos_totales = votos_totales;
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
	public int getTotal_escaños() {
		return total_escaños;
	}
	public void setTotal_escaños(int total_escaños) {
		this.total_escaños = total_escaños;
	}
	public int getTotal_circuns() {
		return total_circuns;
	}
	public void setTotal_circuns(int total_circuns) {
		this.total_circuns = total_circuns;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}





		

}
