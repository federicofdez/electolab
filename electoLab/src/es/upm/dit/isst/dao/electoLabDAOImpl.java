package es.upm.dit.isst.dao;

import java.util.HashMap;
import java.util.List;

import es.upm.dit.isst.model.Escenario;

public class electoLabDAOImpl implements electoLabDAO {
	private static electoLabDAOImpl instance;

	public electoLabDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static electoLabDAOImpl getInstance(){
		if(instance == null)
			instance = new electoLabDAOImpl();
		return instance;
	}

	@Override
	public Escenario create(String autor, HashMap<String, String[]> partidos, HashMap<String, int[]> votos,
			String sistema, String circunscripciones, int escanos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String autor) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Escenario> read() {
		// TODO Auto-generated method stub
		return null;
	}

}
