package es.upm.dit.isst.electolab.dao;

import java.util.List;
import java.util.Set;

import es.upm.dit.isst.electolab.model.Circunscripciones;
import es.upm.dit.isst.electolab.model.Comentario;
import es.upm.dit.isst.electolab.model.Escenario;
import es.upm.dit.isst.electolab.model.Grupo;
import es.upm.dit.isst.electolab.model.Partido;
import es.upm.dit.isst.electolab.model.Provincia;
import es.upm.dit.isst.electolab.model.Sistema;
import es.upm.dit.isst.electolab.model.Votos;

public interface ElectoLabDAO {
	
	// CREATE Escenario
	public Escenario createEscenario(String usuario, List<Votos> votos,
			List<Provincia> provincias, List<Partido> partidos,
			List<Comentario> comentarios, Sistema sistema,
			Circunscripciones circunscripciones, int mayoria_abs);
	public Escenario createEscenario(Escenario escenario);
	
	// READ Escenario
	public Escenario readEscenario(long id);
	public List<Escenario> readEscenarios();
	public List<Escenario> readEscenarios(String usuario);
	public List<Escenario> readEscenariosGrupo(String grupo);
	public boolean existsEscenario(String usuario);
	
	// UPDATE Escenario
	public void updateEscenario(Escenario escenario);
	
	// DELETE Escenario
	public void deleteEscenario(long id);
	
	// CREATE Comentario
	public Escenario createComentario(Long id, Comentario comentario);


	// CREATE Grupo
	public Grupo createGrupo(String nombre, String password, Set<String> usuarios);
	
	// READ Grupo
	public Grupo readGrupo(String nombre);
	public List<Grupo> readGrupos();
	public boolean existsGrupo(String nombre);
	
	//UPDATE Grupo
	public void updateGrupo(Grupo grupo);
	
	// DELETE Grupo
	public void deleteGrupo(String nombre);
		
	// CREATE Usuario
	public void createUsuario(String correo, String grupo);
	
	//READ Usuario
	public boolean existsUsuario(String correo);
	
	// DELETE Usuario
	public void deleteUsuario(String correo);

}
