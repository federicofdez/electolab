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

import es.upm.dit.isst.model.Circunscripciones;
import es.upm.dit.isst.model.Comentario;
import es.upm.dit.isst.model.Escenario;
import es.upm.dit.isst.model.Partido;
import es.upm.dit.isst.model.Provincia;
import es.upm.dit.isst.model.Sistema;
import es.upm.dit.isst.model.Votos;
import es.upm.dit.isst.dao.electoLabDAO;
import es.upm.dit.isst.dao.electoLabDAOImpl;

public class calculos {
	private static calculos instance;
	electoLabDAO dao = electoLabDAOImpl.getInstance();

	public static calculos getInstance(){
		if(instance == null)
			instance = new calculos();
		return instance;
	}

	private int[] getArrayElectores(){
		List<Integer> res_list= new ArrayList<Integer>();
		List<Provincia> provincias_list = dao.read_escenario("admin").getProvincias();
		Iterator provinciasIterator= provincias_list.iterator();
		  while(provinciasIterator.hasNext()){
			  Provincia provincia = (Provincia) provinciasIterator.next();
			  res_list.add(provincia.getElectores());
		  }
			int[] res_Array = new int[ res_list.size() ];
			
			for(int i = 0; i < res_list.size(); i++)
				res_Array[i] = res_list.get(i);
			
		return res_Array;
		
	}
	
	private String[] getArrayProvincias(){
		List<String> res_list= new ArrayList<String>();
		List<Provincia> provincias_list = dao.read_escenario("admin").getProvincias();
		Iterator provinciasIterator= provincias_list.iterator();
		  while(provinciasIterator.hasNext()){
			  Provincia provincia = (Provincia) provinciasIterator.next();
			  res_list.add(provincia.getId());
		  }
			String[] res_Array = new String[ res_list.size() ];
			
			for(int i = 0; i < res_list.size(); i++)
				res_Array[i] = res_list.get(i);
			
		return res_Array;
		
	}
	
	private String[] getArrayPartidos(){
		List<String> res_list= new ArrayList<String>();
		List<Partido> partidos_list = dao.read_escenario("admin").getPartidos();
		Iterator partidosIterator= partidos_list.iterator();
		  while(partidosIterator.hasNext()){
			  Partido partido = (Partido) partidosIterator.next();
			  res_list.add(partido.getNombre());
		  }
			String[] res_Array = new String[ res_list.size() ];
			
			for(int i = 0; i < res_list.size(); i++)
				res_Array[i] = res_list.get(i);
			
		return res_Array;
		
	}
	
	 private String[] provincias_mapa = {"alava","albacete","alicante","almeria","asturias","avila","badajoz","barcelona","burgos","caceres","cadiz","cantabria","castellon","ciudadreal","cordoba","lacoruÃ±a","cuenca","gerona","granada","guadalajara", "guipuzcoa","huelva","huesca","islasbaleares","jaen","leon","lerida","lugo","madrid","malaga","murcia","navarra","orense","palencia","laspalmas","pontevedra","larioja","salamanca","segovia","sevilla","soria","tarragona","santacruzdetenerife","teruel","toledo","valencia","valladolid","vizcaya","zamora","zaragoza"};
	 private String[] partidos_mapa ={"PP", "PSOE", "PODEMOS", "C's", "EN COMÃš", "PODEMOS-COMPROMÃ�S", "ERC-CATS", "DL", "PODEMOS-En Marea-ANOVA-EU", "IU-UPeC", "EAJ-PNV", "CCa-PNC", "UPN", "FAC"};


	 

	
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



	//Metodo que nos genera el diccionario (Partido:Provincia)->Porcentaje de votos

	  public HashMap<String, Integer> crea_mapa(Enumeration em, String[] datos){
		  int i = 0;
		  HashMap<String, Integer> datos_mapa = new HashMap<String, Integer>(); 
		  
		  //Ahora recorremos el enumerado de lo que nos envian para ir guardando lo que queremos
		  
		  while(em.hasMoreElements()){
				String paraName = (String) em.nextElement();
				
				//La linea mas importante del metodo, viendo lo que nos envia el servidor, filtramos lo que no queremos
				//asi nos quedamos solo con los partidos(el servidor envia que sistema es, los escaños/provincia,etc)
				
				if(datos[i] != "" && paraName.indexOf("sistema") == -1 && paraName.indexOf("escaños") == -1 && paraName.indexOf("circunscripciones") == -1 && paraName.indexOf("votosTable") == -1  && paraName.indexOf("votos") == -1 && paraName.indexOf("mayoria") == -1){
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
				
				if(datos[i] != "" && paraName.indexOf("escaños") != -1 ){
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
  
	public Escenario crearEscenario(Enumeration em, String[] datos){
		//*************************Listas para la salida y variables**********************
		//Lista para guardar los nombres de los parametros
		List<String> paramNames = new ArrayList<String>();
		//Lista de partidos, provincias
		List<Provincia> provinciasList = new ArrayList<Provincia>();
		List<Partido> partidosList = new ArrayList<Partido>();	
		List<Votos> votosList = new ArrayList<Votos>();
		
		//Pasar enumeracion a lista para conservar los nombres de los datos
		while(em.hasMoreElements()){
			paramNames.add((String) em.nextElement());
		}
		
		//*************************Calculo de los parametros individuales**********************
		//Inicializacion parametros individuales
		String usuario;
		Sistema sistema = null;
		Circunscripciones circunscripciones = null;
		int mayoria;
		
		
		//Calculo de los valores de los parametros individuales
		int index = paramNames.indexOf("usuario");
		usuario = datos[index];
		index = paramNames.indexOf("sistema");
		String sistemaString = datos[index];
		if(sistemaString.equals("dhont"))
             sistema = Sistema.DHONDT;
        index = paramNames.indexOf("circunscripciones");
		String circunscripcionesString = datos[index];
		if(circunscripcionesString.equals("provincias"))
             circunscripciones = Circunscripciones.PROVINCIAS;
		if(circunscripcionesString.equals("comunidades"))
             circunscripciones = Circunscripciones.COMUNIDADES;
		if(circunscripcionesString.equals("spain"))
          circunscripciones = Circunscripciones.PAIS;
		index = paramNames.indexOf("mayoria");
		mayoria = Integer.parseInt(datos[index]);

//---------------------------PRUEBA------------------------------------
//System.out.println("usuario: " + usuario + ", sistema: " + sistema + ", circunscripciones: " + circunscripciones + ", mayoria_abs: " + mayoria);
//---------------------------------------------------------------------
		

		//*************************Calculo de provincias**********************
		//Iterator de provincias
		Iterator<Provincia> provinciasIterator = dao.read_escenario("admin").getProvincias().iterator();
		//Bucle de provincias
		while (provinciasIterator.hasNext()) {
			Provincia provincia = provinciasIterator.next();
			index = paramNames.indexOf("escaños " + provincia.getId());
			int escanos = 0;
			if(index != -1){
				escanos = Integer.parseInt(datos[index]);
				provincia = new Provincia(provincia.getNombre(), provincia.getId(), provincia.getComunidad(), escanos, provincia.getElectores());
				//---------------------------PRUEBA------------------------------------
				//System.out.println("nombre: " + provincia.getNombre() + ", id: " + provincia.getId() + ", comunidad: " + provincia.getComunidad() + ", escanos: " + provincia.getEscanos() + ", electores: " + provincia.getElectores());
				//---------------------------------------------------------------------
				provinciasList.add(provincia);
				}
		}
		
		//*************************Calculo de partidos y votos**********************	
		
		//Iterator de partidos	
		Iterator<Partido> partidosIterator = dao.read_escenario("admin").getPartidos().iterator();
		//Bucle de partidos si hay siguiente elemento
		while (partidosIterator.hasNext()) {
				Partido partido = partidosIterator.next();
				if(!partidosList.contains(partido)){
					//---------------------------PRUEBA------------------------------------
					//System.out.println("siglas: " + partido.getSiglas() + ", nombre: " + partido.getNombre() + ", color: " + partido.getColor() + ", imagen: " + partido.getImagen());
					//---------------------------------------------------------------------
					partidosList.add(partido);

				//Iterator de provincias
				Iterator<Provincia> provinciasIterator2 = dao.read_escenario("admin").getProvincias().iterator();
				//Bucle de provincias
				while (provinciasIterator2.hasNext()) {
					Provincia provincia = provinciasIterator2.next();
					//Buscamos el indice que tiene las siglas y la provincia concreta que esta iterando
						index = paramNames.indexOf(partido.getSiglas()+":"+provincia.getId());
						//System.out.println("index: " + index + ", id: " + partido.getSiglas()+""+provincia.getId() /*+ ", valorDatos: " + datos[index]*/);
						if(index != -1){
							
							//Si es NP no lo metemos en el array de votos
							if(datos[index] != "NP"){		
								Votos votos = new Votos(provincia.getId(), partido.getSiglas(), Integer.parseInt(datos[index]));
								//---------------------------PRUEBA------------------------------------
								//System.out.println("provincia: " + votos.getProvincia()+ ", partido: " + votos.getPartido() + ", porcentajes: " + votos.getVotos());
								//---------------------------------------------------------------------
								votosList.add(votos);
							}
						}
						}

							
						}
						
				}
		List<Comentario> comentariosList = new ArrayList<Comentario>();
		Escenario escenario = new Escenario(usuario, votosList, provinciasList, partidosList, comentariosList, sistema, circunscripciones, mayoria);
		return escenario;

	}
		 }






