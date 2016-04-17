package es.upm.dit.isst.dao;

import java.util.List;

import es.upm.dit.isst.model.Circunscripciones;
import es.upm.dit.isst.model.Comentario;
import es.upm.dit.isst.model.Escenario;
import es.upm.dit.isst.model.Grupo;
import es.upm.dit.isst.model.Partido;
import es.upm.dit.isst.model.Provincia;
import es.upm.dit.isst.model.Sistema;
import es.upm.dit.isst.model.Usuario;
import es.upm.dit.isst.model.Votos;

public interface electoLabDAO {
	// Métodos de Escenario
	public Escenario create_escenario(String usuario, List<Votos> votos,
			List<Provincia> provincias, List<Partido> partidos,
			List<Comentario> comentarios, Sistema sistema,
			Circunscripciones circunscripciones, int mayoria_abs);
	public Escenario create_escenario(Escenario escenario);
	public void delete_escenario(long id);
	public List<Escenario> read_escenarios();
	public Escenario read_escenario(long id);
	public Escenario read_escenario(String usuario);
	public boolean exist_escenario(String usuario);


	// Métodos de Usuario
	public Usuario create_usuario(String correo);
	public void delete_usuario(String correo);
	public boolean exist_usuario(String correo);
	public List<Usuario> read_usuarios();

	// Métodos de Grupo
	public Grupo create_grupo(String nombre, String password);
	public void delete_grupo(String nombre);
	public Grupo read_grupo(String nombre);
	public List<Grupo> read_grupos();
	public boolean exist_grupo(String nombre);

}
