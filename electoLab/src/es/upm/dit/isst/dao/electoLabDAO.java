package es.upm.dit.isst.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import es.upm.dit.isst.model.Escenario;
import es.upm.dit.isst.model.Grupo;
import es.upm.dit.isst.model.Partido;
import es.upm.dit.isst.model.Provincia;
import es.upm.dit.isst.model.Relacion;
import es.upm.dit.isst.model.Usuario;

public interface electoLabDAO {
	//Métodos de Partido
	public Partido create_partido(String siglas, String nombre, String imagen, String color);
	public void delete_partido(String siglas);
	public List<Partido> read_partidos();
	public Partido read_siglas(String siglas);
	
	//Métodos de Provincia
	public Provincia create_provincia(String identificador, String nombre, String comunidad, int electores);
	public void delete_provincia(String identificador);
	public List<Provincia> read_provincias();
	public Provincia read_provincia(String identificador);
	
	//Métodos de Escenario
	public Escenario create_escenario( String usuario, Map<String, Integer> votos, Map<String, Integer> escProv, String sistema,
			String circunscripciones, int mayoria_abs, List<String> comentarios);
	public void delete_escenario(long id);
	public List<Escenario> read_escenarios();
	public Escenario read_escenario(long id);
	
	//Métodos de Usuario
	public Usuario create_usuario(String correo);
	public void delete_usuario(String correo);
	public boolean exist_usuario(String correo);
	public List<Usuario> read_usuarios();
	
	//Métodos de Grupo
	public Grupo create_grupo(String nombre, String password);
	public void delete_grupo(String nombre);
	public Grupo read_grupo(String nombre);
	public List<Grupo> read_grupos();
	public boolean exist_grupo(String nombre);


}
