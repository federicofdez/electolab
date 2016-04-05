package es.upm.dit.isst.dao;

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

	@Override
	public Partido create(String siglas, String nombre, String imagen, int color, String provincias) {
		Partido partido = null;
		EntityManager em = EMFService.get().createEntityManager();
		partido = new Partido(siglas,nombre,color,provincias);
		em.persist(partido);
		em.close();
		return partido;
	}

	@Override
	public List<Partido> read() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Partido m");
		List<Partido> res = q.getResultList();
		em.close();
		return res;
	}

	@Override
	public void delete(String siglas) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Partido partidoBorrar = em.find(Partido.class, siglas);
			em.remove(partidoBorrar);
		}
		finally {
			em.close();	
		}
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
	public Provincia create_provincia(String nombre, int escaños, int votos){
		Provincia provincia = null;
		EntityManager em = EMFService.get().createEntityManager();
		provincia = new Provincia(nombre, escaños);
		em.persist(provincia);
		em.close();
		return provincia;		
		
	}
	public List<Provincia> read_provincias(){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Provincia m");
		List<Provincia> res = q.getResultList();
		em.close();
		return res;
		
	}
	public Provincia read_provincia(String nombre){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Provincia t where t.nombre = :nombre");
		q.setParameter("nombre",nombre);
		Provincia res = null;
		List<Provincia> provincia = q.getResultList();
		if(provincia.size()>0)
			res = (Provincia) (q.getResultList().get(0)); 
			em.close();
			return res;	
		
	}
	

	// Métodos de escenario
	
	public Escenario create_escenario(int votos_totales, String sistema, String circunscripciones,
			int mayoria_abs, int total_escaños, int total_circuns){
		EntityManager em = EMFService.get().createEntityManager();
		Escenario escenario = null;
		escenario = new Escenario(votos_totales, sistema, circunscripciones,
				mayoria_abs, total_escaños, total_circuns);
		em.persist(escenario);
		em.close();
		return escenario;		
	}
	public List<Escenario> read_escenarios(){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Escenario m");
		List<Escenario> res = q.getResultList();
		em.close();
		return res;
	}

}
