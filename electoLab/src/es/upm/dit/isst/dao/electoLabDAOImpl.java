package es.upm.dit.isst.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.model.Partido;

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



	

}
