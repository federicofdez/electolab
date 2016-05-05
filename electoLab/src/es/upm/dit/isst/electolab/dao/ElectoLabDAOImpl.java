package es.upm.dit.isst.electolab.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.electolab.model.Circunscripciones;
import es.upm.dit.isst.electolab.model.Comentario;
import es.upm.dit.isst.electolab.model.Escenario;
import es.upm.dit.isst.electolab.model.Grupo;
import es.upm.dit.isst.electolab.model.Partido;
import es.upm.dit.isst.electolab.model.Provincia;
import es.upm.dit.isst.electolab.model.Sistema;
import es.upm.dit.isst.electolab.model.Votos;

public class ElectoLabDAOImpl implements ElectoLabDAO {
	private static ElectoLabDAOImpl instance;

	public static ElectoLabDAOImpl getInstance() {
		if (instance == null)
			instance = new ElectoLabDAOImpl();
		return instance;
	}

	// CREATE Escenario

	@Override
	public Escenario createEscenario(String usuario,String titulo, List<Votos> votos,
			List<Provincia> provincias, List<Partido> partidos,
			List<Comentario> comentarios, Sistema sistema,
			Circunscripciones circunscripciones, int mayoria_abs, String fecha) {
		EntityManager em = EMFService.get().createEntityManager();
		Escenario escenario = new Escenario(usuario,titulo, votos, provincias,
				partidos, comentarios, sistema, circunscripciones, mayoria_abs,fecha);
		em.persist(escenario);
		em.close();
		return escenario;
	}
	
	@Override
	public Escenario createEscenario(Escenario escenario) {
		EntityManager em = EMFService.get().createEntityManager();
		em.persist(escenario);
		em.close();
		return escenario;
	}
	
	// READ Escenario
	
	public List<Escenario> readEscenarios() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Escenario m");
		List<Escenario> res = q.getResultList();
		em.close();
		return res;
	}
	
	public List<Escenario> readEscenarios(String usuario) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Escenario t where t.usuario = :usuario");
		q.setParameter("usuario", usuario);
		List<Escenario> res = q.getResultList();
		em.close();
		return res;
	}
	
	@Override
	public List<Escenario> readEscenariosGrupo(String grupo) {
		List<Escenario> escenarios = new ArrayList();
		Grupo group = readGrupo(grupo);
		Set<String> usuarios = group.getUsuarios();
		for (String usuario : usuarios) {
			escenarios.addAll(readEscenarios(usuario));
		}
		return escenarios;		
	}

	public Escenario readEscenario(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Escenario t where t.id = :id");
		q.setParameter("id", id);
		Escenario res = null;
		List<Escenario> escenario = q.getResultList();
		if (escenario.size() > 0)
			res = (Escenario) (q.getResultList().get(0));
		em.close();
		return res;
	}
	
	public boolean existsEscenario(String usuario) {
		if (usuario != "") {
			EntityManager em = EMFService.get().createEntityManager();
			Query q = em
					.createQuery("select t from Escenario t where t.usuario = :usuario");
			q.setParameter("usuario", usuario);
			List<Escenario> escenarios = q.getResultList();
			if (escenarios.size() > 0) {
				em.close();
				return true;
			}
		}
		return false;
	}
	
	// UPDATE Escenario
	
	@Override
	public void updateEscenario(Escenario escenario) {
		EntityManager em = EMFService.get().createEntityManager();
		em.merge(escenario);
		em.close();
	}
	
	// DELETE Escenario

	@Override
	public void deleteEscenario(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Escenario escenarioBorrar = em.find(Escenario.class, id);
			em.remove(escenarioBorrar);
		} finally {
			em.close();
		}
	}
	
	// CREATE Comentario
	public Escenario createComentario(Long id, Comentario comentario){
		Escenario escenario = null;
			EntityManager em = EMFService.get().createEntityManager();
			Query q = em
					.createQuery("select t from Escenario t where t.id = :id");
			q.setParameter("id", id);
			List<Escenario> escenarios = q.getResultList();
			if (escenarios.size() > 0) {
				escenario = escenarios.get(0);
				List<Comentario> comentarios = escenario.getComentarios();
				comentarios.add(comentario);
				escenario.setComentarios(comentarios);
				escenario = em.merge(escenario);
				em.close();
			}
		return escenario;		
	}


	// CREATE Grupo
	
	@Override
	public Grupo createGrupo(String nombre, String password, Set<String> usuarios) {
		EntityManager em = EMFService.get().createEntityManager();
		Grupo grupo = null;
		grupo = new Grupo(nombre, password, usuarios);
		em.persist(grupo);
		em.close();
		return grupo;
	}
	
	// READ Grupo
	
	@Override
	public Grupo readGrupo(String nombre) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Grupo t where t.nombre = :nombre");
		q.setParameter("nombre", nombre);
		Grupo res = null;
		List<Grupo> grupo = q.getResultList();
		if (grupo.size() > 0)
			res = (Grupo) (q.getResultList().get(0));
		em.close();
		return res;
	}

	@Override
	public List<Grupo> readGrupos() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Grupo m");
		List<Grupo> res = q.getResultList();
		em.close();
		return res;
	}

	@Override
	public boolean existsGrupo(String nombre) {
		if (nombre != "") {
			EntityManager em = EMFService.get().createEntityManager();
			Query q = em
					.createQuery("select t from Grupo t where t.nombre = :nombre");
			q.setParameter("nombre", nombre);
			List<Grupo> grupo = q.getResultList();
			if (grupo.size() > 0) {
				em.close();
				return true;
			}
		}
		return false;
	}
	
	// UPDATE Grupo
	
	@Override
	public void updateGrupo(Grupo grupo) {
		EntityManager em = EMFService.get().createEntityManager();
		em.merge(grupo);
		em.close();
	}
	
	// DELETE Grupo

	@Override
	public void deleteGrupo(String nombre) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Grupo grupoBorrar = em.find(Grupo.class, nombre);
			em.remove(grupoBorrar);
		} finally {
			em.close();
		}
	}
	
	// FIND Usuario's group
	@Override
	public Grupo findGroup (String usuario) {
		Grupo grupo = null;
		if (usuario != "")
			for (Grupo g : this.readGrupos())
				if (g.getUsuarios().contains(usuario))
					grupo = g;
		return grupo;
	}

	
	
	// CREATE Usuario
	
	@Override
	public void createUsuario(String correo, String grupo) {
		Grupo g = this.readGrupo(grupo);
		Set<String> usuarios = g.getUsuarios();
		usuarios.add(correo);
		g.setUsuarios(usuarios);
		this.updateGrupo(g);
	}
	
	// READ Usuario
	
	@Override
	public boolean existsUsuario(String correo) {
		if (correo != "")
			for (Grupo g : this.readGrupos())
				if (g.getUsuarios().contains(correo))
					return true;
		return false;
	}
	
	
	// DELETE Usuario
	
	@Override
	public void deleteUsuario(String correo) {
		List<Grupo> grupos = this.readGrupos();
		for (Grupo g : grupos)
			if (g.getUsuarios().contains(correo)){
				Set<String> usuarios = g.getUsuarios();
				usuarios.remove(correo);
				this.updateGrupo(g);
			}
	}


}
