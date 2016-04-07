package es.upm.dit.isst.logica;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import es.upm.dit.isst.dao.electoLabDAOImpl;

public class calculos {
	
	private String[] provincias = {"Alava","Albacete","Alicante","Almería","Asturias","Avila","Badajoz","Barcelona","Burgos","Cáceres",
        	"Cádiz","Cantabria","Castellón","Ceuta","Ciudad Real","Córdoba","Cuenca","Gerona","Granada","Guadalajara",
        	"Guipúzcoa","Huelva","Huesca","Islas Baleares","Jaén","La Coruña","La Rioja","Las Palmas","León","Lérida","Lugo","Madrid","Málaga","Melilla","Murcia","Navarra",
        	"Orense","Palencia","Pontevedra","Santa Cruz de Tenerife","Salamanca","Segovia","Sevilla","Soria","Tarragona",
        	"Teruel","Toledo","Valencia","Valladolid","Vizcaya","Zamora","Zaragoza"};
	
	 private String[] electores = {"248.456","304.089","1.220.005","452.589","876.171","132.575","548.707","3.974.408","284.916","335.845",
	         	"968.097","464.081","409.474","59.213","396.293","635.086","156.088","496.127","702.887","178.749",
	         	"555.417","389.811","167.331","748.577","522.173","936.602","233.087","798.145","398.416","299.069","286.315","4.658.397","1.113.952","53.257","1.003.799","478.330",
	         	"267.704","137.517","774.937","280.762","733.362","118.502","1.501.271","70.718","547.971",
	         	"105.453","509.333","1.893.225","421.369","913.244","155.512","714.370"};
	 
	 List<String[]> partidos = new ArrayList<String[]>();
	 {
	     partidos.add(new String[]{"PP","Partido Popular","img/logos/pp.png","1"});
	     partidos.add(new String[]{"PSOE","Partido Socialista","img/logos/psoe.png","2"});
	     partidos.add(new String[]{"PODEMOS T","Podemos","img/logos/podemos.png","3"});
	     partidos.add(new String[]{"C's","Ciudadanos","img/logos/ciudadanos.png","4"});
	     partidos.add(new String[]{"EN COMÚ","En comú Podem","img/logos/podemosComun.png","5","Barcelona,Tarragona,Lérida,Gerona"});
	     partidos.add(new String[]{"PODEMOS-COMPROMÍS","Compromís-Podemos-És el moment","img/logos/podemosCompromis.png","6","Alicante,Castellón,Valencia"});
	     partidos.add(new String[]{"ERC-CATS","Esquerra Republicana de Catalunya-Catalunya Sí","img/logos/erc.png","7","Barcelona,Tarragona,Lérida,Gerona"});
	     partidos.add(new String[]{"DL","Democràcia i Llibertat","img/logos/dl.png","8","Barcelona,Tarragona,Lérida,Gerona"});
	     partidos.add(new String[]{"PODEMOS-En Marea-ANOVA-EU","En Marea","img/logos/podemosMarea.png","9","A Coruña,Lugo,Orense,Pontevedra"});
	     partidos.add(new String[]{"IU-UPeC","Unidad Popular: Izquierda Unida, Unidad Popular en Común","img/logos/iut.png","10"});
	     partidos.add(new String[]{"EAJ-PNV","Euzko Alderdi Jeltzalea-Partido Nacionalista Vasco","img/logos/pnv.jpg","11","Álava,Guipúzcoa,Vizcaya"});
	     partidos.add(new String[]{"CCa-PNC","Coalición Canaria-Partido Nacionalista Canario","img/logos/cca.png","12","Las Palmas,Santa Cruz de Tenerife"});
	     partidos.add(new String[]{"UPN","Unión del Pueblo Navarro","img/logos/upn.png","13","Navarra"});
	     partidos.add(new String[]{"FAC","Foro Asturias","img/logos/foro.jpg","14","Asturias"});
	 }

	 
	public String[] getElectores() {
		return electores;
	}

	public void setElectores(String[] electores) {
		this.electores = electores;
	}

	public List<String[]> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<String[]> partidos) {
		this.partidos = partidos;
	}

	public String[] getProvincias() {
		return provincias;
	}

	public void setProvincias(String[] provincias) {
		this.provincias = provincias;
	}
	private static calculos instance;
	
	public static calculos getInstance(){
		if(instance == null)
			instance = new calculos();
		return instance;
	}
	
	/**
	 * @param em
	 * @param votos
	 * @return
	 */
	// Calculo de los votos totales
	public int total_votos(Enumeration em, String[] datos){
		int total_votos = 0;
		int i = 0;
		while(em.hasMoreElements()){
			String paraName = (String) em.nextElement();
			
			if(datos[i] != "" && paraName.indexOf("votos ") != -1 ){	
				total_votos += Integer.parseInt(datos[i]);
			}
			
			i++;
		}
		return total_votos;
	}
	//Método para comprobar porcentajes si son correctos
	public boolean porcentaje_correctos(Enumeration em, String[] datos){
		int porcentaje_total= 0;
		int i = 0;
		while(em.hasMoreElements()){
			String paraName = (String) em.nextElement();
			if(datos[i] != "" && paraName.indexOf("PP") != -1 ){	
				porcentaje_total += Integer.parseInt(datos[i]);
				if(porcentaje_total > 100){
					return false; 
				}
			}
			if(datos[i] != "" && paraName.indexOf("PSOE") != -1 ){
				porcentaje_total += Integer.parseInt(datos[i]);
				if(porcentaje_total > 100){
					return false; 
				}
			}
			if(datos[i] != "" && paraName.indexOf("C's") != -1 ){
				porcentaje_total += Integer.parseInt(datos[i]);
				if(porcentaje_total > 100){
					return false; 
				}
			}
			if(datos[i] != "" && paraName.indexOf("PODEMOS-COMPROMÍS") != -1 ){
				porcentaje_total += Integer.parseInt(datos[i]);
				if(porcentaje_total > 100){
					return false; 
				}
			}
			if(datos[i] != "" && paraName.indexOf("EN COMÚ") != -1 ){
				porcentaje_total += Integer.parseInt(datos[i]);
				if(porcentaje_total > 100){
					return false; 
				}
			}
			if(datos[i] != "" && paraName.indexOf("PODEMOS T") != -1 ){
				porcentaje_total += Integer.parseInt(datos[i]);
				if(porcentaje_total > 100){
					return false; 
				}
			}
			if(datos[i] != "" && paraName.indexOf("CCa-PNC") != -1 ){
				porcentaje_total += Integer.parseInt(datos[i]);
				if(porcentaje_total > 100){
					return false; 
				}
			}
			if(datos[i] != "" && paraName.indexOf("DL") != -1 ){
				porcentaje_total += Integer.parseInt(datos[i]);
				if(porcentaje_total > 100){
					return false; 
				}
			}
			if(datos[i] != "" && paraName.indexOf("IU-UPeC") != -1 ){
				porcentaje_total += Integer.parseInt(datos[i]);
				if(porcentaje_total > 100){
					return false; 
				}
			}
			if(datos[i] != "" && paraName.indexOf("FAC") != -1 ){
				porcentaje_total += Integer.parseInt(datos[i]);
				if(porcentaje_total > 100){
					return false; 
				}
			}
			if(datos[i] != "" && paraName.indexOf("ERC-CATS") != -1 ){
				porcentaje_total += Integer.parseInt(datos[i]);
				if(porcentaje_total > 100){
					return false; 
				}
			}
			if(datos[i] != "" && paraName.indexOf("PODEMOS-En Marea-ANOVA-EU") != -1 ){
				porcentaje_total += Integer.parseInt(datos[i]);
				if(porcentaje_total > 100){
					return false; 
				}
			}
			if(datos[i] != "" && paraName.indexOf("EAJ-PNV") != -1 ){
				porcentaje_total += Integer.parseInt(datos[i]);
				if(porcentaje_total > 100){
					return false; 
				}
			}
			if(datos[i] != "" && paraName.indexOf("UPN") != -1 ){
				porcentaje_total += Integer.parseInt(datos[i]);
				if(porcentaje_total > 100){
					return false; 
				}
			}
			i++;
		}
		if(porcentaje_total > 100){
			return false;
		}else {
			return true;
		}
	}

}


/*if(votos[i] != "" && paraName.indexOf("PP") != -1 ){	
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
}*/
