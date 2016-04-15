package es.upm.dit.isst.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.model.Escenario;
import es.upm.dit.isst.model.Grupo;
import es.upm.dit.isst.model.Partido;
import es.upm.dit.isst.model.Provincia;
import es.upm.dit.isst.model.Relacion;
import es.upm.dit.isst.model.Usuario;


public class electoLabDAOImpl implements electoLabDAO {
	private static electoLabDAOImpl instance;
	
	public static electoLabDAOImpl getInstance(){
		if(instance == null)
			instance = new electoLabDAOImpl();
		return instance;
	}
	
	
	//Métodos de Partido
	@Override
	public Partido create_partido(String siglas, String nombre, String imagen, String color) {
		Partido partido = null;
		EntityManager em = EMFService.get().createEntityManager();
		partido = new Partido(siglas, nombre, imagen, color);
		em.persist(partido);
		em.close();
		return partido;
	}

	@Override
	public List<Partido> read_partidos() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Partido m");
		List<Partido> res = q.getResultList();
		em.close();
		return res;
	}

	@Override
	public void delete_partido(String siglas) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Partido t where t.siglas = :siglas");
		q.setParameter("siglas",siglas);
		Partido res = null;
        List<Partido> partidos = q.getResultList();
		if(partidos.size()>0){
			res = (Partido) (q.getResultList().get(0)); 
			em.remove(res);
		}
		em.close();	

		}
	
	public Partido read_siglas(String siglas){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Partido t where t.siglas = :siglas");
		q.setParameter("siglas",siglas);
		Partido res = null;
		List<Partido> partidos = q.getResultList();
		if(partidos.size()>0)
			res = (Partido) (q.getResultList().get(0)); 
		em.close();
		return res;				
	}
	
	//Métodos de provincias
	public Provincia create_provincia(String identificador, String nombre, String comunidad, int electores){
		Provincia provincia = null;
		EntityManager em = EMFService.get().createEntityManager();
		provincia = new Provincia(identificador, nombre,comunidad, electores);
		em.persist(provincia);
		em.close();
		return provincia;		
		
	}
	
	@Override
	public void delete_provincia(String identificador) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Provincia t where t.identificador = :identificador ");
		q.setParameter("identificador",identificador);
		Provincia res = null;
        List<Provincia> provincias = q.getResultList();
		if(provincias.size()>0){
			res = (Provincia) (q.getResultList().get(0)); 
			em.remove(res);
		}
		em.close();	

	}
	
	public List<Provincia> read_provincias(){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Provincia m");
		List<Provincia> res = q.getResultList();
		em.close();
		return res;
		
	}
	public Provincia read_provincia(String identificador){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Provincia t where t.identificador = :identificador");
		q.setParameter("identificador",identificador);
		Provincia res = null;
		List<Provincia> provincia = q.getResultList();
		if(provincia.size()>0)
			res = (Provincia) (q.getResultList().get(0)); 
			em.close();
			return res;	
		
	}
		

	// Métodos de escenario
	
	public Escenario create_escenario( String usuario, Map<String, Integer> votos, Map<String, Integer> escProv, String sistema,
			String circunscripciones, int mayoria_abs, List<String> comentarios){
		EntityManager em = EMFService.get().createEntityManager();
		Escenario escenario = null;
		escenario = new Escenario( usuario,  votos, escProv,  sistema,
				circunscripciones, mayoria_abs,  comentarios);
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
		}
		finally {
			em.close();	
		}
	}
	
	public List<Escenario> read_escenarios(){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Escenario m");
		List<Escenario> res = q.getResultList();
		em.close();
		return res;
	}

	public Escenario read_escenario(long id){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Escenario t where t.id = :id");
		q.setParameter("id",id);
		Escenario res = null;
		List<Escenario> escenario = q.getResultList();
		if(escenario.size()>0)
			res = (Escenario) (q.getResultList().get(0)); 
			em.close();
			return res;	
		
	}

	
//Métodos de Usuario
	
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
		}
		finally {
			em.close();	
		}
	}


	@Override
	public boolean exist_usuario(String correo) {
		if(correo != ""){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Usuario t where t.correo = :correo");
		q.setParameter("correo",correo);
		List<Usuario> usuario = q.getResultList();
		if(usuario.size()>0){
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


	//Metodos de Grupo
	@Override
	public Grupo create_grupo(String nombre, String password) {
		EntityManager em = EMFService.get().createEntityManager();
		Grupo grupo = null;
		grupo = new Grupo(nombre,password);
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
		}
		finally {
			em.close();	
		}		
	}


	@Override
	public Grupo read_grupo(String nombre) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Grupo t where t.nombre = :nombre");
		q.setParameter("nombre",nombre);
		Grupo res = null;
		List<Grupo> grupo = q.getResultList();
		if(grupo.size()>0)
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
		if(nombre != ""){
			EntityManager em = EMFService.get().createEntityManager();
			Query q = em.createQuery("select t from Grupo t where t.nombre = :nombre");
			q.setParameter("nombre",nombre);
			List<Grupo> grupo = q.getResultList();
			if(grupo.size()>0){
				em.close();
				return true;
			}
		}
		return false;
	}




}
