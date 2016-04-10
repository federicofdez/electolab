package es.upm.dit.isst.dao;

import java.util.ArrayList;
import java.util.List;

import es.upm.dit.isst.model.Escenario;
import es.upm.dit.isst.model.Partido;
import es.upm.dit.isst.model.Provincia;

public interface electoLabDAO {
	//Métodos de Partido
	public Partido create_partido(String siglas, String nombre, String imagen, String color, List<String> provincia,
			List<Double> votos, long id_escenario);
	public void delete_partido(String siglas, long id_escenario);
	public List<Partido> read_partidos();
	public Partido read_siglas(String siglas);
	
	//Métodos de Provincia
	public Provincia create_provincia(String identificador,String nombre, String comunidad, int escanos, int electores,
			long id_escenario);
	public void delete_provincia(String id, long id_escenario);
	public List<Provincia> read_provincias();
	public Provincia read_provincia(String id);
	
	//Métodos de escenario
	public Escenario create_escenario(long id, int votos_totales, String sistema, String circunscripciones, int mayoria_abs,
			int total_escaños, int total_circuns);
	public void delete_escenario(long id);
	public List<Escenario> read_escenarios();
	public Escenario read_escenario(long id);
}
