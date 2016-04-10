package es.upm.dit.isst.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.model.Escenario;
import es.upm.dit.isst.model.Partido;
import es.upm.dit.isst.model.Provincia;


public class electoLabDAOImpl implements electoLabDAO {
	private static electoLabDAOImpl instance;
	
	public static electoLabDAOImpl getInstance(){
		if(instance == null)
			instance = new electoLabDAOImpl();
		return instance;
	}
	
	
	//Métodos de Partido
	@Override
	public Partido create_partido(String siglas, String nombre, String imagen, String color, List<String> provincia,
			List<Double> votos, long id_escenario) {
		Partido partido = null;
		EntityManager em = EMFService.get().createEntityManager();
		partido = new Partido(siglas,nombre,imagen,color,provincia,votos,id_escenario);
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
	public void delete_partido(String siglas, long id_escenario) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Partido t where t.siglas = :siglas");
		q.setParameter("siglas",siglas);
		//q.setParameter("id_escenario",id_escenario);
		System.out.println(siglas);
		Partido res = null;

			List<Partido> partidos = q.getResultList();
			System.out.println(partidos);
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
	public Provincia create_provincia(String identificador,String nombre, String comunidad, int escanos, int electores,
			long id_escenario){
		Provincia provincia = null;
		EntityManager em = EMFService.get().createEntityManager();
		provincia = new Provincia(identificador,nombre,comunidad, escanos,electores,
				id_escenario);
		em.persist(provincia);
		em.close();
		return provincia;		
		
	}
	
	@Override
	public void delete_provincia(String id, long id_escenario) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Partido provinciaBorrar = em.find(Partido.class, id);
			em.remove(provinciaBorrar);
		}
		finally {
			em.close();	
		}
	}
	
	public List<Provincia> read_provincias(){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Provincia m");
		List<Provincia> res = q.getResultList();
		em.close();
		return res;
		
	}
	public Provincia read_provincia(String id){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Provincia t where t.identificador = :id");
		q.setParameter("id",id);
		Provincia res = null;
		List<Provincia> provincia = q.getResultList();
		if(provincia.size()>0)
			res = (Provincia) (q.getResultList().get(0)); 
			em.close();
			return res;	
		
	}
		

	// Métodos de escenario
	
	public Escenario create_escenario(long id, int votos_totales, String sistema, String circunscripciones, int mayoria_abs,
			int total_escaños, int total_circuns){
		EntityManager em = EMFService.get().createEntityManager();
		Escenario escenario = null;
		escenario = new Escenario(votos_totales, sistema, circunscripciones,
				mayoria_abs, total_escaños, total_circuns);
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





}
