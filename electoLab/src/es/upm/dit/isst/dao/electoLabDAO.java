package es.upm.dit.isst.dao;

import java.util.List;

import es.upm.dit.isst.model.Escenario;
import es.upm.dit.isst.model.Partido;
import es.upm.dit.isst.model.Provincia;

public interface electoLabDAO {
	public Partido create(String siglas, String nombre,String imagen, int color, String provincias);
	public List<Partido> read();
	public void delete(String siglas);
	public Partido read_siglas(String siglas);
	
	//Métodos de provincias
	public Provincia create_provincia(String nombre, int escaños, int votos);
	public List<Provincia> read_provincias();
	public Provincia read_provincia(String nombre);
	
	//Métodos de escenario
	public Escenario create_escenario(int votos_totales, String sistema, String circunscripciones,
			int mayoria_abs, int total_escaños, int total_circuns);
	public List<Escenario> read_escenarios();
}
