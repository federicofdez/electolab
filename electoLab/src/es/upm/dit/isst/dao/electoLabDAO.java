package es.upm.dit.isst.dao;

import java.util.HashMap;
import java.util.List;

import es.upm.dit.isst.model.Escenario;

public interface electoLabDAO {
	public Escenario create(String autor, HashMap<String, String[]> partidos, HashMap<String, int[]> votos, String sistema,
			String circunscripciones, int escanos);
	public void delete(String autor);
	public List<Escenario> read();
}
