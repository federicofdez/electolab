package es.upm.dit.isst.logica;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import es.upm.dit.isst.dao.electoLabDAOImpl;

public class calculos {
	 List<String[]> prov = new ArrayList<String[]>();
	 { //Nombre, identificado, comunidad autónoma, escaños, electores
	     prov.add(new String[]{"Álava","alava","País Vasco","5","248.456"});
	     prov.add(new String[]{"Albacete","albacete","Castilla La Mancha","5","304.089"});
	     prov.add(new String[]{"Alicante","alicante","Comunidad Valenciana","5","1.220.005"});
	     prov.add(new String[]{"Almería","almeria","Andalucía","5","452.589"});
	     prov.add(new String[]{"Asturias","asturias","Asturias","5","876.171"});
	     prov.add(new String[]{"Ávila","avila","Castilla León","5","132.575"});
	     prov.add(new String[]{"Badajoz","badajoz","Extremadura","5","548.707"});
	     prov.add(new String[]{"Barcelona","barcelona","Cataluña","5","3.974.408"});
	     prov.add(new String[]{"Burgos","burgos","Castilla León","5","284.916"});
	     prov.add(new String[]{"Cáceres","caceres","Extremadura","5","335.845"});
	     prov.add(new String[]{"Cádiz","cadiz","Andalucía","5","968.097"});
	     prov.add(new String[]{"Cantabria","cantabria","Cantabría","5","464.081"});
	     prov.add(new String[]{"Castellón","castellon","Comunidad Valenciana","5","409.474"});
	     prov.add(new String[]{"Ceuta","ceuta","Ceuta","5","59.213"});
	     prov.add(new String[]{"Ciudad Real","ciudadreal","Castilla La Mancha","5","396.293"});
	     prov.add(new String[]{"Córdoba","cordoba","Andalucía","5","635.086"});
	     prov.add(new String[]{"Cuenca","cuenca","Castilla La Mancha","5","156.088"});
	     prov.add(new String[]{"Gerona","gerona","Cataluña","5","496.127"});
	     prov.add(new String[]{"Granada","granada","Andalucía","5","702.887"});
	     prov.add(new String[]{"Guadalajara","guadalajara","Castilla La Mancha","5","178.749"});
	     prov.add(new String[]{"Guipúzcoa","guipuzcoa","País Vasco","5","555.417"});
	     prov.add(new String[]{"Huelva","huelva","Andalucía","5","389.811"});
	     prov.add(new String[]{"Huesca","huesca","Aragón","5","167.331"});
	     prov.add(new String[]{"Islas Baleares","islasbaleares","Islas Baleares","5","748.577"});
	     prov.add(new String[]{"Jaén","jaen","Andalucía","5","522.173"});
	     prov.add(new String[]{"La Coruña","lacoruña","Galicia","5","936.602"});
	     prov.add(new String[]{"La Rioja","larioja","La Rioja","5","233.087"});
	     prov.add(new String[]{"Las Palmas","laspalmas","Islas Canarias","5","798.145"});
	     prov.add(new String[]{"León","leon","Castilla León","5","398.416"});
	     prov.add(new String[]{"Lérida","lerida","Cataluña","5","299.069"});
	     prov.add(new String[]{"Lugo","lugo","Galicia","5","286.315"});
	     prov.add(new String[]{"Madrid","madrid","Comunidad de Madrid","5","4.658.397"});
	     prov.add(new String[]{"Málaga","malaga","Andalucía","5","1.113.952"});
	     prov.add(new String[]{"Melilla","melilla","Melilla","5","53.257"});
	     prov.add(new String[]{"Murcia","murcia","Murcia","5","1.003.799"});
	     prov.add(new String[]{"Navarra","navarra","Navarra","5","478.330"});
	     prov.add(new String[]{"Orense","orense","Galicia","5","267.704"});
	     prov.add(new String[]{"Palencia","palencia","Castilla León","5","137.517"});
	     prov.add(new String[]{"Pontevedra","pontevedra","Galicia","5","774.937"});
	     prov.add(new String[]{"Santa Cruz de Tenerife","tenerife","Islas Canarias","5","280.762"});
	     prov.add(new String[]{"Salamanca","salamanca","Castilla León","5","733.362"});
	     prov.add(new String[]{"Segovia","segovia","Castilla León","5","118.502"});
	     prov.add(new String[]{"Sevilla","sevilla","Andalucía","5","1.501.271"});
	     prov.add(new String[]{"Soria","soria","Castilla León","5","70.718"});
	     prov.add(new String[]{"Tarragona","tarragona","Cataluña","5","547.971"});
	     prov.add(new String[]{"Teruel","teruel","Aragón","5","105.453"});
	     prov.add(new String[]{"Toledo","toledo","Castilla La Mancha","5","509.333"});
	     prov.add(new String[]{"Valencia","valencia","Valencia","5","1.893.225"});
	     prov.add(new String[]{"Valladolid","valladolid","Castilla León","5","421.369"});
	     prov.add(new String[]{"Vizcaya","vizcaya","País Vasco","5","913.244"});
	     prov.add(new String[]{"Zamora","zamora","Castilla León","5","155.512"});
	     prov.add(new String[]{"Zaragoza","zaragoza","Aragón","5","714.370"});


	 }

	
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
	     partidos.add(new String[]{"PODEMOS","Podemos","img/logos/podemos.png","3"});
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
				System.out.println(paraName);
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
			if(datos[i] != "" && paraName.indexOf("PODEMOS") != -1 ){
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
if(votos[i] != "" && paraName.indexOf("PODEMOS") != -1 ){
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
