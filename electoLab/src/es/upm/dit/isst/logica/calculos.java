package es.upm.dit.isst.logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import es.upm.dit.isst.model.Partido;
import es.upm.dit.isst.dao.electoLabDAOImpl;

public class calculos {
		private List<String[]> provincias = new ArrayList<String[]>();
	 { //Nombre, identificado, comunidad autónoma, escaños, electores
	     provincias.add(new String[]{"Álava","alava","País Vasco","5","248456"});
	     provincias.add(new String[]{"Albacete","albacete","Castilla La Mancha","5","304089"});
	     provincias.add(new String[]{"Alicante","alicante","Comunidad Valenciana","5","1220005"});
	     provincias.add(new String[]{"Almería","almeria","Andalucía","5","452589"});
	     provincias.add(new String[]{"Asturias","asturias","Asturias","5","876171"});
	     provincias.add(new String[]{"Ávila","avila","Castilla León","5","132575"});
	     provincias.add(new String[]{"Badajoz","badajoz","Extremadura","5","548707"});
	     provincias.add(new String[]{"Barcelona","barcelona","Cataluña","5","3974408"});
	     provincias.add(new String[]{"Burgos","burgos","Castilla León","5","284916"});
	     provincias.add(new String[]{"Cáceres","caceres","Extremadura","5","335845"});
	     provincias.add(new String[]{"Cádiz","cadiz","Andalucía","5","968097"});
	     provincias.add(new String[]{"Cantabria","cantabria","Cantabría","5","464081"});
	     provincias.add(new String[]{"Castellón","castellon","Comunidad Valenciana","5","409474"});
	     provincias.add(new String[]{"Ceuta","ceuta","Ceuta","5","59.213"});
	     provincias.add(new String[]{"Ciudad Real","ciudadreal","Castilla La Mancha","5","396293"});
	     provincias.add(new String[]{"Córdoba","cordoba","Andalucía","5","635086"});
	     provincias.add(new String[]{"Cuenca","cuenca","Castilla La Mancha","5","156088"});
	     provincias.add(new String[]{"Gerona","gerona","Cataluña","5","496127"});
	     provincias.add(new String[]{"Granada","granada","Andalucía","5","702887"});
	     provincias.add(new String[]{"Guadalajara","guadalajara","Castilla La Mancha","5","178749"});
	     provincias.add(new String[]{"Guipúzcoa","guipuzcoa","País Vasco","5","555417"});
	     provincias.add(new String[]{"Huelva","huelva","Andalucía","5","389811"});
	     provincias.add(new String[]{"Huesca","huesca","Aragón","5","167331"});
	     provincias.add(new String[]{"Islas Baleares","islasbaleares","Islas Baleares","5","748577"});
	     provincias.add(new String[]{"Jaén","jaen","Andalucía","5","522173"});
	     provincias.add(new String[]{"La Coruña","lacoruña","Galicia","5","936602"});
	     provincias.add(new String[]{"La Rioja","larioja","La Rioja","5","233087"});
	     provincias.add(new String[]{"Las Palmas","laspalmas","Islas Canarias","5","798145"});
	     provincias.add(new String[]{"León","leon","Castilla León","5","398416"});
	     provincias.add(new String[]{"Lérida","lerida","Cataluña","5","299069"});
	     provincias.add(new String[]{"Lugo","lugo","Galicia","5","286315"});
	     provincias.add(new String[]{"Madrid","madrid","Comunidad de Madrid","5","4658397"});
	     provincias.add(new String[]{"Málaga","malaga","Andalucía","5","1113952"});
	     provincias.add(new String[]{"Melilla","melilla","Melilla","5","53257"});
	     provincias.add(new String[]{"Murcia","murcia","Murcia","5","1003799"});
	     provincias.add(new String[]{"Navarra","navarra","Navarra","5","478330"});
	     provincias.add(new String[]{"Orense","orense","Galicia","5","267704"});
	     provincias.add(new String[]{"Palencia","palencia","Castilla León","5","137517"});
	     provincias.add(new String[]{"Pontevedra","pontevedra","Galicia","5","774937"});
	     provincias.add(new String[]{"Santa Cruz de Tenerife","tenerife","Islas Canarias","5","280762"});
	     provincias.add(new String[]{"Salamanca","salamanca","Castilla León","5","733362"});
	     provincias.add(new String[]{"Segovia","segovia","Castilla León","5","118502"});
	     provincias.add(new String[]{"Sevilla","sevilla","Andalucía","5","1501271"});
	     provincias.add(new String[]{"Soria","soria","Castilla León","5","70718"});
	     provincias.add(new String[]{"Tarragona","tarragona","Cataluña","5","547971"});
	     provincias.add(new String[]{"Teruel","teruel","Aragón","5","105453"});
	     provincias.add(new String[]{"Toledo","toledo","Castilla La Mancha","5","509333"});
	     provincias.add(new String[]{"Valencia","valencia","Valencia","5","1893225"});
	     provincias.add(new String[]{"Valladolid","valladolid","Castilla León","5","421369"});
	     provincias.add(new String[]{"Vizcaya","vizcaya","País Vasco","5","913244"});
	     provincias.add(new String[]{"Zamora","zamora","Castilla León","5","155512"});
	     provincias.add(new String[]{"Zaragoza","zaragoza","Aragón","5","714370"});


	 }
	 
	 private List<Partido> partidos = new ArrayList<Partido>();
	 {
	     partidos.add(new Partido("PP","Partido Popular","img/logos/pp.png","1","Todas",0,0));
	     partidos.add(new Partido("PSOE","Partido Socialista","img/logos/psoe.png","2","Todas",0,0));
	     partidos.add(new Partido("PODEMOS","Podemos","img/logos/podemos.png","3","Todas",0,0));
	     partidos.add(new Partido("C's","Ciudadanos","img/logos/ciudadanos.png","4","Todas",0,0));
	     partidos.add(new Partido("EN COMÚ","En comú Podem","img/logos/podemosComun.png","5","Cataluña",0,0));
	     partidos.add(new Partido("PODEMOS-COMPROMÍS","Compromís-Podemos-És el moment","img/logos/podemosCompromis.png","6","Comunidad Valenciana",0,0));
	     partidos.add(new Partido("ERC-CATS","Esquerra Republicana de Catalunya-Catalunya Sí","img/logos/erc.png","7","Cataluña",0,0));
	     partidos.add(new Partido("DL","Democràcia i Llibertat","img/logos/dl.png","8","Barcelona,Tarragona,Lérida,Gerona",0,0));
	     partidos.add(new Partido("PODEMOS-En Marea-ANOVA-EU","En Marea","img/logos/podemosMarea.png","9","A Coruña,Lugo,Orense,Pontevedra",0,0));
	     partidos.add(new Partido("IU-UPeC","Unidad Popular: Izquierda Unida, Unidad Popular en Común","img/logos/iut.png","10","Todas",0,0));
	     partidos.add(new Partido("EAJ-PNV","Euzko Alderdi Jeltzalea-Partido Nacionalista Vasco","img/logos/pnv.jpg","11","Álava,Guipúzcoa,Vizcaya",0,0));
	     partidos.add(new Partido("CCa-PNC","Coalición Canaria-Partido Nacionalista Canario","img/logos/cca.png","12","Las Palmas,Santa Cruz de Tenerife",0,0));
	     partidos.add(new Partido("UPN","Unión del Pueblo Navarro","img/logos/upn.png","13","Navarra",0,0));
	     partidos.add(new Partido("FAC","Foro Asturias","img/logos/foro.jpg","14","Asturias",0,0));
	 }





	public List<String[]> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<String[]> provincias) {
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
	
	public List<String[]> calculaVotos(Enumeration em, String[] datos){
		int i = 0;
		List<String[]> votosTabla = new ArrayList<String[]>();
		List<String[]> votosTablaOrder = new ArrayList<String[]>();
		while(em.hasMoreElements()){
			String paraName = (String) em.nextElement();
			Iterator<String[]> provinciasIterator = provincias.iterator();
			while (provinciasIterator.hasNext()) {
				String[] arrayProvincia = provinciasIterator.next();
				String provincia = arrayProvincia[1];
				String electores = arrayProvincia[4];
				if(paraName.indexOf(provincia) != -1){
					Iterator<Partido> partidosIterator = partidos.iterator();
					while (partidosIterator.hasNext()) {
						String partido = partidosIterator.next().getSiglas();
						if(datos[i] != "" && paraName.indexOf(partido) != -1 ){
						double votosDouble = Double.parseDouble(datos[i])/100 * Double.parseDouble(electores);
						String votos = String.valueOf((int) votosDouble);
						votosTabla.add(new String[]{provincia,partido,votos});
					}
				}
			}
				}
			i++;
	}
		
		//Ordenar la lista
		Iterator<String[]> provinciasIterator = provincias.iterator();
		while (provinciasIterator.hasNext()) {
			String provincia = provinciasIterator.next()[1];
			Iterator<String[]> votosIterator = votosTabla.iterator();
			List<String[]> temporal = new ArrayList<String[]>();
			while(votosIterator.hasNext()){
				String[] parametros = votosIterator.next();
				if(parametros[0] == provincia){
					System.out.println("provincia: " + parametros[0] + ", partido: " + parametros[1]);
					temporal.add(new String[]{parametros[0],parametros[1],parametros[2]});
				}
				if(!votosIterator.hasNext()){
					Collections.sort(temporal);
					votosTablaOrder.addAll(temporal);
				}
			}
		}
		return votosTablaOrder;
}
	
	
	public List<String[]> calculaEscanos(List<String[]> votos){
		
		
		
		return votos;
		
		
	}

	public List<Partido> getPartidos() {
		System.out.println(partidos);
		return partidos;
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
