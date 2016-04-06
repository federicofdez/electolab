package es.upm.dit.isst.logica;

import java.util.Enumeration;

public class calculos {
	
	
	public static int total_votos(Enumeration em, String[] votos){
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
