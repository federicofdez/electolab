package es.upm.dit.isst.logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import es.upm.dit.isst.model.Partido;
import es.upm.dit.isst.model.Provincia;
import es.upm.dit.isst.dao.electoLabDAOImpl;

public class calculos {
		private List<Provincia> provincias = new ArrayList<Provincia>();
	 { //Nombre, identificado, comunidad autónoma, escaños, electores
	     provincias.add(new Provincia("Álava","alava","País Vasco",5,248456,0));
	     provincias.add(new Provincia("Albacete","albacete","Castilla La Mancha",5,304089,0));
	     provincias.add(new Provincia("Alicante","alicante","Comunidad Valenciana",5,1220005,0));
	     provincias.add(new Provincia("Almería","almeria","Andalucía",5,452589,0));
	     provincias.add(new Provincia("Asturias","asturias","Asturias",5,876171,0));
	     provincias.add(new Provincia("Ávila","avila","Castilla León",5,132575,0));
	     provincias.add(new Provincia("Badajoz","badajoz","Extremadura",5,548707,0));
	     provincias.add(new Provincia("Barcelona","barcelona","Cataluña",5,3974408,0));
	     provincias.add(new Provincia("Burgos","burgos","Castilla León",5,284916,0));
	     provincias.add(new Provincia("Cáceres","caceres","Extremadura",5,335845,0));
	     provincias.add(new Provincia("Cádiz","cadiz","Andalucía",5,968097,0));
	     provincias.add(new Provincia("Cantabria","cantabria","Cantabría",5,464081,0));
	     provincias.add(new Provincia("Castellón","castellon","Comunidad Valenciana",5,409474,0));
	     provincias.add(new Provincia("Ceuta","ceuta","Ceuta",5,59213,0));
	     provincias.add(new Provincia("Ciudad Real","ciudadreal","Castilla La Mancha",5,396293,0));
	     provincias.add(new Provincia("Córdoba","cordoba","Andalucía",5,635086,0));
	     provincias.add(new Provincia("Cuenca","cuenca","Castilla La Mancha",5,156088,0));
	     provincias.add(new Provincia("Gerona","gerona","Cataluña",5,496127,0));
	     provincias.add(new Provincia("Granada","granada","Andalucía",5,702887,0));
	     provincias.add(new Provincia("Guadalajara","guadalajara","Castilla La Mancha",5,178749,0));
	     provincias.add(new Provincia("Guipúzcoa","guipuzcoa","País Vasco",5,555417,0));
	     provincias.add(new Provincia("Huelva","huelva","Andalucía",5,389811,0));
	     provincias.add(new Provincia("Huesca","huesca","Aragón",5,167331,0));
	     provincias.add(new Provincia("Islas Baleares","islasbaleares","Islas Baleares",5,748577,0));
	     provincias.add(new Provincia("Jaén","jaen","Andalucía",5,522173,0));
	     provincias.add(new Provincia("La Coruña","lacoruña","Galicia",5,936602,0));
	     provincias.add(new Provincia("La Rioja","larioja","La Rioja",5,233087,0));
	     provincias.add(new Provincia("Las Palmas","laspalmas","Islas Canarias",5,798145,0));
	     provincias.add(new Provincia("León","leon","Castilla León",5,398416,0));
	     provincias.add(new Provincia("Lérida","lerida","Cataluña",5,299069,0));
	     provincias.add(new Provincia("Lugo","lugo","Galicia",5,286315,0));
	     provincias.add(new Provincia("Madrid","madrid","Comunidad de Madrid",5,4658397,0));
	     provincias.add(new Provincia("Málaga","malaga","Andalucía",5,1113952,0));
	     provincias.add(new Provincia("Melilla","melilla","Melilla",5,53257,0));
	     provincias.add(new Provincia("Murcia","murcia","Murcia",5,1003799,0));
	     provincias.add(new Provincia("Navarra","navarra","Navarra",5,478330,0));
	     provincias.add(new Provincia("Orense","orense","Galicia",5,267704,0));
	     provincias.add(new Provincia("Palencia","palencia","Castilla León",5,137517,0));
	     provincias.add(new Provincia("Pontevedra","pontevedra","Galicia",5,774937,0));
	     provincias.add(new Provincia("Santa Cruz de Tenerife","tenerife","Islas Canarias",5,280762,0));
	     provincias.add(new Provincia("Salamanca","salamanca","Castilla León",5,733362,0));
	     provincias.add(new Provincia("Segovia","segovia","Castilla León",5,118502,0));
	     provincias.add(new Provincia("Sevilla","sevilla","Andalucía",5,1501271,0));
	     provincias.add(new Provincia("Soria","soria","Castilla León",5,70718,0));
	     provincias.add(new Provincia("Tarragona","tarragona","Cataluña",5,547971,0));
	     provincias.add(new Provincia("Teruel","teruel","Aragón",5,105453,0));
	     provincias.add(new Provincia("Toledo","toledo","Castilla La Mancha",5,509333,0));
	     provincias.add(new Provincia("Valencia","valencia","Valencia",5,1893225,0));
	     provincias.add(new Provincia("Valladolid","valladolid","Castilla León",5,421369,0));
	     provincias.add(new Provincia("Vizcaya","vizcaya","País Vasco",5,913244,0));
	     provincias.add(new Provincia("Zamora","zamora","Castilla León",5,155512,0));
	     provincias.add(new Provincia("Zaragoza","zaragoza","Aragón",5,714370,0));


	 }
	 
	 private List<Partido> partidos = new ArrayList<Partido>();
	 {
	     partidos.add(new Partido("PP","Partido Popular","img/logos/pp.png","1",new ArrayList<String>(),new ArrayList<Double>(),0));
	     partidos.add(new Partido("PSOE","Partido Socialista","img/logos/psoe.png","2",new ArrayList<String>(),new ArrayList<Double>(),0));
	     partidos.add(new Partido("PODEMOS","Podemos","img/logos/podemos.png","3",new ArrayList<String>(),new ArrayList<Double>(),0));
	     partidos.add(new Partido("C's","Ciudadanos","img/logos/ciudadanos.png","4",new ArrayList<String>(),new ArrayList<Double>(),0));
	     partidos.add(new Partido("EN COMÚ","En comú Podem","img/logos/podemosComun.png","5",new ArrayList<String>(),new ArrayList<Double>(),0));
	     partidos.add(new Partido("PODEMOS-COMPROMÍS","Compromís-Podemos-És el moment","img/logos/podemosCompromis.png","6",new ArrayList<String>(),new ArrayList<Double>(),0));
	     partidos.add(new Partido("ERC-CATS","Esquerra Republicana de Catalunya-Catalunya Sí","img/logos/erc.png","7",new ArrayList<String>(),new ArrayList<Double>(),0));
	     partidos.add(new Partido("DL","Democràcia i Llibertat","img/logos/dl.png","8",new ArrayList<String>(),new ArrayList<Double>(),0));
	     partidos.add(new Partido("PODEMOS-En Marea-ANOVA-EU","En Marea","img/logos/podemosMarea.png","9",new ArrayList<String>(),new ArrayList<Double>(),0));
	     partidos.add(new Partido("IU-UPeC","Unidad Popular: Izquierda Unida, Unidad Popular en Común","img/logos/iut.png","10",new ArrayList<String>(),new ArrayList<Double>(),0));
	     partidos.add(new Partido("EAJ-PNV","Euzko Alderdi Jeltzalea-Partido Nacionalista Vasco","img/logos/pnv.jpg","11",new ArrayList<String>(),new ArrayList<Double>(),0));
	     partidos.add(new Partido("CCa-PNC","Coalición Canaria-Partido Nacionalista Canario","img/logos/cca.png","12",new ArrayList<String>(),new ArrayList<Double>(),0));
	     partidos.add(new Partido("UPN","Unión del Pueblo Navarro","img/logos/upn.png","13",new ArrayList<String>(),new ArrayList<Double>(),0));
	     partidos.add(new Partido("FAC","Foro Asturias","img/logos/foro.jpg","14",new ArrayList<String>(),new ArrayList<Double>(),0));
	 }






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
	
	public List<Partido> calculaVotos(Enumeration em, String[] datos){
		int i = 0;
		//Variables de resultados finales
		List<Partido> votosTabla = new ArrayList<Partido>();
		List<Partido> votosTablaOrder = new ArrayList<Partido>();
		//Bucle por los nombres de los parametros del formulario
		while(em.hasMoreElements()){
			String paraName = (String) em.nextElement();
			//Lista para guardar los votos de un partido en las distintas provincias
			List<Double> votosList = new ArrayList<Double>();
			//Iterador de partidos
			Iterator<Partido> partidosIterator = partidos.iterator();
			while (partidosIterator.hasNext()) {
				//Variable de un partido, siglas
				Partido partido = partidosIterator.next();
				String siglas = partido.getSiglas();
				if(paraName.indexOf(siglas) != -1){
					//Lista para guardar las provincias de un mismo partido
					List<String> provinciasList = new ArrayList<String>();
					//Fijamos el valor de los votos
					Iterator<Provincia> provinciasIterator = provincias.iterator();
					while (provinciasIterator.hasNext()) {
						//Variables de una provincia, provinciaId y electores
						Provincia arrayProvincia = provinciasIterator.next();
						String provinciaId = arrayProvincia.getIdentificador();
						int electores = arrayProvincia.getElectores();
						if(datos[i] != "" && paraName.indexOf(provinciaId) != -1 ){
						//Calculo de votos a partir de porcentaje y electores
						Double votosDouble = Double.parseDouble(datos[i])/100 * electores;
						Double votos =  (double) Math.round(votosDouble);
						//Añadimos a la lista de votos estos votos y esta provincia
						votosList.add(votos);
						provinciasList.add(provinciaId);

					}
						//
						if(!partidosIterator.hasNext()){
							
							//Partido party = new Partido(siglas,partido.getNombre(),partido.getImagen(),partido.getColor(),provincia,partido.getId_escenario(),votosList );
							//votosTabla.add(party);

							
						}
						}
				}
			}
			i++;
	}
		
		//Ordenar la lista
		/*Iterator<String[]> provinciasIterator = provincias.iterator();
		while (provinciasIterator.hasNext()) {
			String provincia = provinciasIterator.next()[1];
			Iterator<Partido> votosIterator = votosTabla.iterator();
			List<Partido> temporal = new ArrayList<Partido>();
			while(votosIterator.hasNext()){
				Partido parametros = votosIterator.next();
				if(parametros.getProvincia() == provincia){
					System.out.println("provincia: " + parametros.getProvincia() + ", partido: " + parametros.getNombre());
					temporal.add(new Partido(parametros.getSiglas(),parametros.getNombre(),parametros.getImagen(),parametros.getColor(),parametros.getProvincia(),parametros.getId_escenario(),parametros.getVotos()));
				}
				if(!votosIterator.hasNext()){
					Iterator<Partido> temporalIterator = temporal.iterator();
					while(temporalIterator.hasNext()){
						System.out.println(temporalIterator.next().getVotos());
					}
					Comparator<Partido> comp = new Comparator<Partido>() {
					      public int compare(Partido p1, Partido p2) {					    	  
					        return Long.compare(p1.getVotos(), p2.getVotos());
					      }
					      };
						    Collections.sort(temporal, comp);
						    Collections.reverse(temporal);
					votosTablaOrder.addAll(temporal);
				}
			}
		}*/
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
