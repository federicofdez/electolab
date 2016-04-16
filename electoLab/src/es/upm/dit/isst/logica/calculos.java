package es.upm.dit.isst.logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import es.upm.dit.isst.model.Partido;
import es.upm.dit.isst.model.Provincia;
import es.upm.dit.isst.dao.electoLabDAOImpl;

public class calculos {
		private List<Provincia> provincias = new ArrayList<Provincia>();
	 { //Nombre, identificado, comunidad autÃ³noma, escaÃ±os, electores
	     provincias.add(new Provincia("Ã�lava","alava","PaÃ­s Vasco",4,248456));
	     provincias.add(new Provincia("Albacete","albacete","Castilla La Mancha",4,304089));
	     provincias.add(new Provincia("Alicante","alicante","Comunidad Valenciana",12,1220005));
	     provincias.add(new Provincia("AlmerÃ­a","almeria","AndalucÃ­a",6,452589));
	     provincias.add(new Provincia("Asturias","asturias","Asturias",8,876171));
	     provincias.add(new Provincia("Ã�vila","avila","Castilla LeÃ³n",3,132575));
	     provincias.add(new Provincia("Badajoz","badajoz","Extremadura",6,548707));
	     provincias.add(new Provincia("Barcelona","barcelona","CataluÃ±a",31,3974408));
	     provincias.add(new Provincia("Burgos","burgos","Castilla LeÃ³n",4,284916));
	     provincias.add(new Provincia("CÃ¡ceres","caceres","Extremadura",4,335845));
	     provincias.add(new Provincia("CÃ¡diz","cadiz","AndalucÃ­a",9,968097));
	     provincias.add(new Provincia("Cantabria","cantabria","CantabrÃ­a",5,464081));
	     provincias.add(new Provincia("CastellÃ³n","castellon","Comunidad Valenciana",5,409474));
	     provincias.add(new Provincia("Ceuta","ceuta","Ceuta",1,59213));
	     provincias.add(new Provincia("Ciudad Real","ciudadreal","Castilla La Mancha",5,396293));
	     provincias.add(new Provincia("CÃ³rdoba","cordoba","AndalucÃ­a",6,635086));
	     provincias.add(new Provincia("Cuenca","cuenca","Castilla La Mancha",3,156088));
	     provincias.add(new Provincia("Gerona","gerona","CataluÃ±a",6,496127));
	     provincias.add(new Provincia("Granada","granada","AndalucÃ­a",7,702887));
	     provincias.add(new Provincia("Guadalajara","guadalajara","Castilla La Mancha",6,178749));
	     provincias.add(new Provincia("GuipÃºzcoa","guipuzcoa","PaÃ­s Vasco",6,555417));
	     provincias.add(new Provincia("Huelva","huelva","AndalucÃ­a",5,389811));
	     provincias.add(new Provincia("Huesca","huesca","AragÃ³n",3,167331));
	     provincias.add(new Provincia("Islas Baleares","islasbaleares","Islas Baleares",8,748577));
	     provincias.add(new Provincia("JaÃ©n","jaen","AndalucÃ­a",5,522173));
	     provincias.add(new Provincia("La CoruÃ±a","lacoruÃ±a","Galicia",8,936602));
	     provincias.add(new Provincia("La Rioja","larioja","La Rioja",4,233087));
	     provincias.add(new Provincia("Las Palmas","laspalmas","Islas Canarias",8,798145));
	     provincias.add(new Provincia("LeÃ³n","leon","Castilla LeÃ³n",5,398416));
	     provincias.add(new Provincia("LÃ©rida","lerida","CataluÃ±a",4,299069));
	     provincias.add(new Provincia("Lugo","lugo","Galicia",4,286315));
	     provincias.add(new Provincia("Madrid","madrid","Comunidad de Madrid",36,4658397));
	     provincias.add(new Provincia("MÃ¡laga","malaga","AndalucÃ­a",11,1113952));
	     provincias.add(new Provincia("Melilla","melilla","Melilla",1,53257));
	     provincias.add(new Provincia("Murcia","murcia","Murcia",10,1003799));
	     provincias.add(new Provincia("Navarra","navarra","Navarra",5,478330));
	     provincias.add(new Provincia("Orense","orense","Galicia",4,267704));
	     provincias.add(new Provincia("Palencia","palencia","Castilla LeÃ³n",3,137517));
	     provincias.add(new Provincia("Pontevedra","pontevedra","Galicia",7,774937));
	     provincias.add(new Provincia("Santa Cruz de Tenerife","tenerife","Islas Canarias",7,280762));
	     provincias.add(new Provincia("Salamanca","salamanca","Castilla LeÃ³n",4,733362));
	     provincias.add(new Provincia("Segovia","segovia","Castilla LeÃ³n",3,118502));
	     provincias.add(new Provincia("Sevilla","sevilla","AndalucÃ­a",12,1501271));
	     provincias.add(new Provincia("Soria","soria","Castilla LeÃ³n",2,70718));
	     provincias.add(new Provincia("Tarragona","tarragona","CataluÃ±a",6,547971));
	     provincias.add(new Provincia("Teruel","teruel","AragÃ³n",3,105453));
	     provincias.add(new Provincia("Toledo","toledo","Castilla La Mancha",5,509333));
	     provincias.add(new Provincia("Valencia","valencia","Valencia",15,1893225));
	     provincias.add(new Provincia("Valladolid","valladolid","Castilla LeÃ³n",5,421369));
	     provincias.add(new Provincia("Vizcaya","vizcaya","PaÃ­s Vasco",8,913244));
	     provincias.add(new Provincia("Zamora","zamora","Castilla LeÃ³n",7,155512));
	     provincias.add(new Provincia("Zaragoza","zaragoza","AragÃ³n",3,714370));


	 }
	 
	 private List<Partido> partidos = new ArrayList<Partido>();
	 {
	     partidos.add(new Partido("PP","Partido Popular","img/logos/pp.png","02CFF7"));
	     partidos.add(new Partido("PSOE","Partido Socialista","img/logos/psoe.png","FF0000"));
	     partidos.add(new Partido("PODEMOS","Podemos","img/logos/podemos.png","742DA1"));
	     partidos.add(new Partido("C's","Ciudadanos","img/logos/ciudadanos.png","F7771B"));
	     partidos.add(new Partido("EN COMÃš","En comÃº Podem","img/logos/podemosComun.png","998EEF"));
	     partidos.add(new Partido("COMPROMÃ�S","CompromÃ­s-Podemos-Ã‰s el moment","img/logos/podemosCompromis.png","4E05D3"));
	     partidos.add(new Partido("ERC-CATS","Esquerra Republicana de Catalunya-Catalunya SÃ­","img/logos/erc.png","FFCC00"));
	     partidos.add(new Partido("DL","DemocrÃ cia i Llibertat","img/logos/dl.png","0F178A"));
	     partidos.add(new Partido("En Marea-ANOVA-EU","En Marea","img/logos/podemosMarea.png","C920DD"));
	     partidos.add(new Partido("IU-UPeC","Unidad Popular: Izquierda Unida, Unidad Popular en ComÃºn","img/logos/iut.png","10812D"));
	     partidos.add(new Partido("EAJ-PNV","Euzko Alderdi Jeltzalea-Partido Nacionalista Vasco","img/logos/pnv.jpg","54A106"));
	     partidos.add(new Partido("CCa-PNC","CoaliciÃ³n Canaria-Partido Nacionalista Canario","img/logos/cca.png","DAE705"));
	     partidos.add(new Partido("UPN","UniÃ³n del Pueblo Navarro","img/logos/upn.png","17626A"));
	     partidos.add(new Partido("NC","Nueva Canarias","img/logos/nc.png","A4FF00"));
	     partidos.add(new Partido("FAC","Foro Asturias","img/logos/foro.jpg","626262"));
	 }


	 private String[] provincias_mapa = {"alava","albacete","alicante","almeria","asturias","avila","badajoz","barcelona","burgos","caceres","cadiz","cantabria","castellon","ciudadreal","cordoba","lacoruÃ±a","cuenca","gerona","granada","guadalajara", "guipuzcoa","huelva","huesca","islasbaleares","jaen","leon","lerida","lugo","madrid","malaga","murcia","navarra","orense","palencia","laspalmas","pontevedra","larioja","salamanca","segovia","sevilla","soria","tarragona","santacruzdetenerife","teruel","toledo","valencia","valladolid","vizcaya","zamora","zaragoza"};
	 private String[] partidos_mapa ={"PP", "PSOE", "PODEMOS", "C's", "EN COMÃš", "PODEMOS-COMPROMÃ�S", "ERC-CATS", "DL", "PODEMOS-En Marea-ANOVA-EU", "IU-UPeC", "EAJ-PNV", "CCa-PNC", "UPN", "FAC"};


	public List<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}

	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
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
	
	
	//MÃ©todo para comprobar porcentajes si son correctos
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
			if(datos[i] != "" && paraName.indexOf("PODEMOS-COMPROMÃ�S") != -1 ){
				porcentaje_total += Integer.parseInt(datos[i]);
				if(porcentaje_total > 100){
					return false; 
				}
			}
			if(datos[i] != "" && paraName.indexOf("EN COMÃš") != -1 ){
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
	

	
	
	public List<String[]> calculaEscanos(List<String[]> votos){
		
		
		
		return votos;
		
		
	}

	public List<Partido> getPartidos() {
		System.out.println(partidos);
		return partidos;
	}





	//Metodo que nos genera el diccionario (Partido:Provincia)->Porcentaje de votos

	  public HashMap<String, Integer> crea_mapa(Enumeration em, String[] datos){
		  int i = 0;
		  HashMap<String, Integer> datos_mapa = new HashMap<String, Integer>(); 
		  
		  //Ahora recorremos el enumerado de lo que nos envian para ir guardando lo que queremos
		  
		  while(em.hasMoreElements()){
				String paraName = (String) em.nextElement();
				
				//La linea mas importante del metodo, viendo lo que nos envia el servidor, filtramos lo que no queremos
				//asi nos quedamos solo con los partidos(el servidor envia que sistema es, los escaños/provincia,etc)
				
				if(datos[i] != "" && paraName.indexOf("sistema") == -1 && paraName.indexOf("escaÃ±os") == -1 && paraName.indexOf("circunscripciones") == -1 && paraName.indexOf("votosTable") == -1  && paraName.indexOf("votos") == -1 && paraName.indexOf("mayoria") == -1){
				 datos_mapa.put(paraName, Integer.parseInt(datos[i]));
					
				}
				i++;
		  }
		  //Esto nos sirve como comprobacion de que el diccionario se genera bien
		  
		  Iterator it= datos_mapa.keySet().iterator();
		  while(it.hasNext()){
			  String key = (String) it.next();
			 // System.out.println("Clave " + key + "-> Valor: " + datos_mapa.get(key));
		  }
		return datos_mapa;
		  
	  }
	  
	  //Mapa con (Provincia)-> Escaños de cada provincia
	  
	  public HashMap<String, Integer> esca_map(Enumeration em, String[] datos){
		  int i = 0;
		  HashMap<String, Integer> esc_mapa = new HashMap<String, Integer>(); 
		  while(em.hasMoreElements()){
				String paraName = (String) em.nextElement();
				
				//Al igual que antes, linea mas importante. Ahora solo queremos las lineas en las que el enumerado diga
				//escaños
				
				if(datos[i] != "" && paraName.indexOf("escaÃ±os") != -1 ){
				 esc_mapa.put(paraName, Integer.parseInt(datos[i]));
					
				}
				i++;
		  }
		  
		  //Comprobante de que efectivamente el diccionario guarda lo que buscabamos
		  
		  Iterator it= esc_mapa.keySet().iterator();
		  while(it.hasNext()){
			  String key = (String) it.next();
			  System.out.println("Clave " + key + "-> Valor: " + esc_mapa.get(key));
		  }
		return esc_mapa;
		  
	  }
	  
	  
	  //Metodo para calcular los escaños. Necesita:
	  //		mapa(partido:provincia)->porcentaje
	  //		mapa(provincia)->escaños
	  //		mapa(provincia)->electores HAY QUE HACER EL METODO QUE DEVUELVA ESTO
	  
	  public HashMap<String, Integer> calcula_esc(HashMap<String, Integer> datos_mapa, HashMap<String, Integer> esca_map, HashMap<String, Integer> votos_map){
		  
		  //Mapa que guardara los resultados de esta manera (Partido:Provincia)-> escaños obtenidos
		  
		 HashMap<String, Integer> resultados_total = new HashMap<String, Integer>();  
		 
		 for(int i=0; i <provincias_mapa.length-1; i++){
			 
			 //Mapa que guarda los partidos que se presentan en una provincia i (Partido)-> votos
			 
			 HashMap<String, Integer> datos_provincia = new HashMap<String, Integer>();
			 HashMap<String, Integer> esca_provincia = new HashMap<String, Integer>();
			  for(String key : datos_mapa.keySet()){
				  
				  //Dividimos Partido:Provincia en dos, asi podemos identificar la provincia y compararlo
				  
				  String[] partido_provincia = key.split(":");
				  
				  //Si la provincia en la que estamos, es la misma que la de la clave del mapa, lo guardamos en el
				  //diccionario para calcular los escaños
				  
				  if(partido_provincia[1].equals(provincias_mapa[i])){
					  datos_provincia.put(partido_provincia[0], datos_mapa.get(key));
				  }
			  }
			  //Ahora ya tenemos el mapa de provincias, por tanto, hay que saber cuantos escaños hay en esa provincia
			  int escprovincia = 0;
			  for(String key : esca_map.keySet()){
				  String[] escanos_provincia = key.split(":");
				  if(escanos_provincia[1].equals(provincias_mapa[i])){
					  escprovincia = esca_map.get(key);
					  break;
				  }
			  }
			  
			  //Cogemos los electores de la circunscripcion para hallar los votos de cada partido
			  
			  int electores = 0; 
			  for(String key : votos_map.keySet()){
				  String[] votos_provincia = key.split(":");
				  if(votos_provincia[1].equals(provincias_mapa[i])){
					  electores = votos_map.get(key);
					  break;
				  }
			  }
			  
			  //Pasamos los porcentajes de votos a votos
			  
			  for(String key : datos_provincia.keySet()){
				  int elec_partido= datos_provincia.get(key);
				  elec_partido = (elec_partido/100)*electores;
				  datos_provincia.put(key, elec_partido);
			  }
			  
			  
			  //A continuacion calculamos D'Hont
			  for(int z =0; z<escprovincia; z++){
				  String max = calculaMaxVotos(datos_provincia);
				  int votosPartido = datos_provincia.get(max);
				  
				  //Si el partido no fue incluido antes, lo guardamos en el mapa
				  if(!esca_provincia.containsKey(max)){
				  	esca_provincia.put(max, 1);
				  	votosPartido= votosPartido/2;
				  	datos_provincia.put(max, votosPartido);
				  }else{
					//Sino aplicamos D'Hont con un partido con mas de un escaño
					  esca_provincia.put(max, esca_provincia.get(max)+1);
					  votosPartido= votosPartido/(esca_provincia.get(max)+1);
					  datos_provincia.put(max, votosPartido);
				  }
			  }
			  //Una vez calculado D'Hont de una provincia, lo guardamos en los resultados
			  //totales
			  for(String key : esca_provincia.keySet()){
				  String partido_provincia = key + ":" + provincias_mapa[i];
				  resultados_total.put(key, esca_provincia.get(key));
			  }
			  
		 }
			 
		return resultados_total;
		  
	  }
	  
	  //Metodo para calcular que partido tiene el mayor numero de votos
	  
	public String calculaMaxVotos(HashMap<String, Integer> votos_provincia){
		String max = "";
		int votos = 0;
		//Cogemos el primer valor del diccionario
		Entry<String, Integer> entry=votos_provincia.entrySet().iterator().next();
		max = entry.getKey();
		votos = entry.getValue();
		//Vemos cual es el maximo numero de votos
		for(String key : votos_provincia.keySet()){
			  if(votos_provincia.get(key)> votos){
				  votos = votos_provincia.get(key);
				  max = key;
			  }
		  }
		return max;
	}
  



}






