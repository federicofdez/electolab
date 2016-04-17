package es.upm.dit.isst.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.model.Circunscripciones;
import es.upm.dit.isst.model.Comentario;
import es.upm.dit.isst.model.Escenario;
import es.upm.dit.isst.model.Grupo;
import es.upm.dit.isst.model.Partido;
import es.upm.dit.isst.model.Provincia;
import es.upm.dit.isst.model.Sistema;
import es.upm.dit.isst.model.Votos;
import es.upm.dit.isst.model.Usuario;

public class electoLabDAOImpl implements electoLabDAO {
	private static electoLabDAOImpl instance;

	public static electoLabDAOImpl getInstance() {
		if (instance == null)
			instance = new electoLabDAOImpl();
		return instance;
	}

	// Métodos de escenario

	@Override
	public Escenario create_escenario(String usuario, List<Votos> votos,
			List<Provincia> provincias, List<Partido> partidos,
			List<Comentario> comentarios, Sistema sistema,
			Circunscripciones circunscripciones, int mayoria_abs) {
		EntityManager em = EMFService.get().createEntityManager();
		Escenario escenario = new Escenario(usuario, votos, provincias,
				partidos, comentarios, sistema, circunscripciones, mayoria_abs);
		em.persist(escenario);
		em.close();
		return escenario;
	}
	
	@Override
	public Escenario create_escenario(Escenario escenario) {
		EntityManager em = EMFService.get().createEntityManager();
		em.persist(escenario);
		em.close();
		return escenario;
	}

	@Override
	public void delete_escenario(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Escenario escenarioBorrar = em.find(Escenario.class, id);
			em.remove(escenarioBorrar);
		} finally {
			em.close();
		}
	}

	public List<Escenario> read_escenarios() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Escenario m");
		List<Escenario> res = q.getResultList();
		em.close();
		return res;
	}

	public Escenario read_escenario(long id) {
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
	
	public Escenario read_escenario(String usuario) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Escenario t where t.usuario = :usuario");
		q.setParameter("usuario", usuario);
		Escenario res = null;
		List<Escenario> escenario = q.getResultList();
		if (escenario.size() > 0)
			res = (Escenario) (q.getResultList().get(0));
		em.close();
		return res;

	} 
	public boolean exist_escenario(String usuario) {
		if (usuario != "") {
			EntityManager em = EMFService.get().createEntityManager();
			Query q = em
					.createQuery("select t from Escenario t where t.usuario = :usuario");
			q.setParameter("usuario", usuario);
			List<Escenario> escenario = q.getResultList();
			if (escenario.size() > 0) {
				em.close();
				return true;
			}
		}
		return false;
	}


	// Métodos de Usuario

	@Override
	public Usuario create_usuario(String correo) {
		EntityManager em = EMFService.get().createEntityManager();
		Usuario usuario = null;
		usuario = new Usuario(correo);
		em.persist(usuario);
		em.close();
		return usuario;
	}

	@Override
	public void delete_usuario(String correo) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Usuario usuarioBorrar = em.find(Usuario.class, correo);
			em.remove(usuarioBorrar);
		} finally {
			em.close();
		}
	}

	@Override
	public boolean exist_usuario(String correo) {
		if (correo != "") {
			EntityManager em = EMFService.get().createEntityManager();
			Query q = em
					.createQuery("select t from Usuario t where t.correo = :correo");
			q.setParameter("correo", correo);
			List<Usuario> usuario = q.getResultList();
			if (usuario.size() > 0) {
				em.close();
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Usuario> read_usuarios() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Usuario m");
		List<Usuario> res = q.getResultList();
		em.close();
		return res;
	}

	// Metodos de Grupo
	@Override
	public Grupo create_grupo(String nombre, String password) {
		EntityManager em = EMFService.get().createEntityManager();
		Grupo grupo = null;
		grupo = new Grupo(nombre, password);
		em.persist(grupo);
		em.close();
		return grupo;
	}

	@Override
	public void delete_grupo(String nombre) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Grupo grupoBorrar = em.find(Grupo.class, nombre);
			em.remove(grupoBorrar);
		} finally {
			em.close();
		}
	}

	@Override
	public Grupo read_grupo(String nombre) {
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
	public List<Grupo> read_grupos() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Grupo m");
		List<Grupo> res = q.getResultList();
		em.close();
		return res;
	}

	@Override
	public boolean exist_grupo(String nombre) {
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
	
	

}
