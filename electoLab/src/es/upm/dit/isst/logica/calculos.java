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
			}
			if(votos[i] != "" && paraName.indexOf("PSOE") != -1 ){
				total_votos += Integer.parseInt(votos[i]);
			}
			if(votos[i] != "" && paraName.indexOf("C's") != -1 ){
				total_votos += Integer.parseInt(votos[i]);
			}
			if(votos[i] != "" && paraName.indexOf("PODEMOS-COMPROMÍS") != -1 ){
				total_votos += Integer.parseInt(votos[i]);
			}
			if(votos[i] != "" && paraName.indexOf("EN COMÚ") != -1 ){
				total_votos += Integer.parseInt(votos[i]);
			}
			if(votos[i] != "" && paraName.indexOf("PODEMOS T") != -1 ){
				total_votos += Integer.parseInt(votos[i]);
			}
			if(votos[i] != "" && paraName.indexOf("CCa-PNC") != -1 ){
				total_votos += Integer.parseInt(votos[i]);
			}
			if(votos[i] != "" && paraName.indexOf("DL") != -1 ){
				total_votos += Integer.parseInt(votos[i]);
			}
			if(votos[i] != "" && paraName.indexOf("IU-UPeC") != -1 ){
				total_votos += Integer.parseInt(votos[i]);
			}
			if(votos[i] != "" && paraName.indexOf("FAC") != -1 ){
				total_votos += Integer.parseInt(votos[i]);
			}
			if(votos[i] != "" && paraName.indexOf("ERC-CATS") != -1 ){
				total_votos += Integer.parseInt(votos[i]);
			}
			if(votos[i] != "" && paraName.indexOf("PODEMOS-En Marea-ANOVA-EU") != -1 ){
				total_votos += Integer.parseInt(votos[i]);
			}
			if(votos[i] != "" && paraName.indexOf("EAJ-PNV") != -1 ){
				total_votos += Integer.parseInt(votos[i]);
			}
			if(votos[i] != "" && paraName.indexOf("UPN") != -1 ){
				total_votos += Integer.parseInt(votos[i]);
			}
			i++;
		}
		return total_votos;
	}

}
