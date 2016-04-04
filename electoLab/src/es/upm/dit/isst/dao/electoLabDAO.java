package es.upm.dit.isst.dao;

import java.util.List;

import es.upm.dit.isst.model.Partido;

public interface electoLabDAO {
	public Partido create(String siglas, String nombre,String imagen, int color, String provincias);
	public List<Partido> read();
	public void delete(String siglas);
}
