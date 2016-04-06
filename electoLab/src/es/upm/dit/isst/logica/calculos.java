package es.upm.dit.isst.logica;

import java.util.Enumeration;

import es.upm.dit.isst.dao.electoLabDAOImpl;

public class calculos {
	private static calculos instance;
	
	public static calculos getInstance(){
		if(instance == null)
			instance = new calculos();
		return instance;
	}
	
	public int total_votos(Enumeration em, String[] votos){
		int total_votos = 0;
		int i = 0;
		while(em.hasMoreElements()){
			String paraName = (String) em.nextElement();
			if(votos[i] != "" && paraName.indexOf("PP") != -1 ){	
				total_votos += Integer.parseInt(votos[i]);
				i++;
		}
		}
		return total_votos;
	}

}
