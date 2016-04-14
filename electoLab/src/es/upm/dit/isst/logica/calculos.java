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
	     provincias.add(new Provincia("Álava","alava","País Vasco",4,248456,0));
	     provincias.add(new Provincia("Albacete","albacete","Castilla La Mancha",4,304089,0));
	     provincias.add(new Provincia("Alicante","alicante","Comunidad Valenciana",12,1220005,0));
	     provincias.add(new Provincia("Almería","almeria","Andalucía",6,452589,0));
	     provincias.add(new Provincia("Asturias","asturias","Asturias",8,876171,0));
	     provincias.add(new Provincia("Ávila","avila","Castilla León",3,132575,0));
	     provincias.add(new Provincia("Badajoz","badajoz","Extremadura",6,548707,0));
	     provincias.add(new Provincia("Barcelona","barcelona","Cataluña",31,3974408,0));
	     provincias.add(new Provincia("Burgos","burgos","Castilla León",4,284916,0));
	     provincias.add(new Provincia("Cáceres","caceres","Extremadura",4,335845,0));
	     provincias.add(new Provincia("Cádiz","cadiz","Andalucía",9,968097,0));
	     provincias.add(new Provincia("Cantabria","cantabria","Cantabría",5,464081,0));
	     provincias.add(new Provincia("Castellón","castellon","Comunidad Valenciana",5,409474,0));
	     provincias.add(new Provincia("Ceuta","ceuta","Ceuta",1,59213,0));
	     provincias.add(new Provincia("Ciudad Real","ciudadreal","Castilla La Mancha",5,396293,0));
	     provincias.add(new Provincia("Córdoba","cordoba","Andalucía",6,635086,0));
	     provincias.add(new Provincia("Cuenca","cuenca","Castilla La Mancha",3,156088,0));
	     provincias.add(new Provincia("Gerona","gerona","Cataluña",6,496127,0));
	     provincias.add(new Provincia("Granada","granada","Andalucía",7,702887,0));
	     provincias.add(new Provincia("Guadalajara","guadalajara","Castilla La Mancha",6,178749,0));
	     provincias.add(new Provincia("Guipúzcoa","guipuzcoa","País Vasco",6,555417,0));
	     provincias.add(new Provincia("Huelva","huelva","Andalucía",5,389811,0));
	     provincias.add(new Provincia("Huesca","huesca","Aragón",3,167331,0));
	     provincias.add(new Provincia("Islas Baleares","islasbaleares","Islas Baleares",8,748577,0));
	     provincias.add(new Provincia("Jaén","jaen","Andalucía",5,522173,0));
	     provincias.add(new Provincia("La Coruña","lacoruña","Galicia",8,936602,0));
	     provincias.add(new Provincia("La Rioja","larioja","La Rioja",4,233087,0));
	     provincias.add(new Provincia("Las Palmas","laspalmas","Islas Canarias",8,798145,0));
	     provincias.add(new Provincia("León","leon","Castilla León",5,398416,0));
	     provincias.add(new Provincia("Lérida","lerida","Cataluña",4,299069,0));
	     provincias.add(new Provincia("Lugo","lugo","Galicia",4,286315,0));
	     provincias.add(new Provincia("Madrid","madrid","Comunidad de Madrid",36,4658397,0));
	     provincias.add(new Provincia("Málaga","malaga","Andalucía",11,1113952,0));
	     provincias.add(new Provincia("Melilla","melilla","Melilla",1,53257,0));
	     provincias.add(new Provincia("Murcia","murcia","Murcia",10,1003799,0));
	     provincias.add(new Provincia("Navarra","navarra","Navarra",5,478330,0));
	     provincias.add(new Provincia("Orense","orense","Galicia",4,267704,0));
	     provincias.add(new Provincia("Palencia","palencia","Castilla León",3,137517,0));
	     provincias.add(new Provincia("Pontevedra","pontevedra","Galicia",7,774937,0));
	     provincias.add(new Provincia("Santa Cruz de Tenerife","tenerife","Islas Canarias",7,280762,0));
	     provincias.add(new Provincia("Salamanca","salamanca","Castilla León",4,733362,0));
	     provincias.add(new Provincia("Segovia","segovia","Castilla León",3,118502,0));
	     provincias.add(new Provincia("Sevilla","sevilla","Andalucía",12,1501271,0));
	     provincias.add(new Provincia("Soria","soria","Castilla León",2,70718,0));
	     provincias.add(new Provincia("Tarragona","tarragona","Cataluña",6,547971,0));
	     provincias.add(new Provincia("Teruel","teruel","Aragón",3,105453,0));
	     provincias.add(new Provincia("Toledo","toledo","Castilla La Mancha",5,509333,0));
	     provincias.add(new Provincia("Valencia","valencia","Valencia",15,1893225,0));
	     provincias.add(new Provincia("Valladolid","valladolid","Castilla León",5,421369,0));
	     provincias.add(new Provincia("Vizcaya","vizcaya","País Vasco",8,913244,0));
	     provincias.add(new Provincia("Zamora","zamora","Castilla León",7,155512,0));
	     provincias.add(new Provincia("Zaragoza","zaragoza","Aragón",3,714370,0));


	 }
	 
	 private List<Partido> partidos = new ArrayList<Partido>();
	 {
	     partidos.add(new Partido("PP","Partido Popular","img/logos/pp.png","1",new ArrayList<String>(),new ArrayList<Double>(),new ArrayList<Integer>(),0));
	     partidos.add(new Partido("PSOE","Partido Socialista","img/logos/psoe.png","2",new ArrayList<String>(),new ArrayList<Double>(),new ArrayList<Integer>(),0));
	     partidos.add(new Partido("PODEMOS","Podemos","img/logos/podemos.png","3",new ArrayList<String>(),new ArrayList<Double>(),new ArrayList<Integer>(),0));
	     partidos.add(new Partido("C's","Ciudadanos","img/logos/ciudadanos.png","4",new ArrayList<String>(),new ArrayList<Double>(),new ArrayList<Integer>(),0));
	     partidos.add(new Partido("EN COMÚ","En comú Podem","img/logos/podemosComun.png","5",new ArrayList<String>(),new ArrayList<Double>(),new ArrayList<Integer>(),0));
	     partidos.add(new Partido("PODEMOS-COMPROMÍS","Compromís-Podemos-És el moment","img/logos/podemosCompromis.png","6",new ArrayList<String>(),new ArrayList<Double>(),new ArrayList<Integer>(),0));
	     partidos.add(new Partido("ERC-CATS","Esquerra Republicana de Catalunya-Catalunya Sí","img/logos/erc.png","7",new ArrayList<String>(),new ArrayList<Double>(),new ArrayList<Integer>(),0));
	     partidos.add(new Partido("DL","Democràcia i Llibertat","img/logos/dl.png","8",new ArrayList<String>(),new ArrayList<Double>(),new ArrayList<Integer>(),0));
	     partidos.add(new Partido("PODEMOS-En Marea-ANOVA-EU","En Marea","img/logos/podemosMarea.png","9",new ArrayList<String>(),new ArrayList<Double>(),new ArrayList<Integer>(),0));
	     partidos.add(new Partido("IU-UPeC","Unidad Popular: Izquierda Unida, Unidad Popular en Común","img/logos/iut.png","10",new ArrayList<String>(),new ArrayList<Double>(),new ArrayList<Integer>(),0));
	     partidos.add(new Partido("EAJ-PNV","Euzko Alderdi Jeltzalea-Partido Nacionalista Vasco","img/logos/pnv.jpg","11",new ArrayList<String>(),new ArrayList<Double>(),new ArrayList<Integer>(),0));
	     partidos.add(new Partido("CCa-PNC","Coalición Canaria-Partido Nacionalista Canario","img/logos/cca.png","12",new ArrayList<String>(),new ArrayList<Double>(),new ArrayList<Integer>(),0));
	     partidos.add(new Partido("UPN","Unión del Pueblo Navarro","img/logos/upn.png","13",new ArrayList<String>(),new ArrayList<Double>(),new ArrayList<Integer>(),0));
	     partidos.add(new Partido("FAC","Foro Asturias","img/logos/foro.jpg","14",new ArrayList<String>(),new ArrayList<Double>(),new ArrayList<Integer>(),0));
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
	/*
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
	
	// método calcula máximo
	
	public int calcula_max(double votos[]){
		double max= votos[0];
		int ind = 0;
		for(int i=0; i< votos.length; i++){
			if(votos[i] > max){
				max=votos[i];
				ind = i;
			}
		}
	return ind;
	}
	
	// Método de D'hont para calculo de escaños 
		public int[] metodo_dhont_Avila(Enumeration em, String[] datos){
			int i = 0;
			int x=0;
			double [] votos = new double[10];
			String[] partidos = new String[10];
			int total_esc=0;
			int [] esca = new int[100];
			int [] repartidor = {2,2,2,2,2,2,2,2,2,2};
			while(em.hasMoreElements()){
				String paraName = (String) em.nextElement();
				if(datos[i] != "" && paraName.indexOf("avila") != -1 && paraName.indexOf("escaños") == -1) {
				//System.out.println("datos i " + datos[i]);
				partidos[x]= paraName;	
				votos[x] = (Integer.parseInt(datos[i])/100.0) *132575;
				System.out.println("Los votos son " + partidos[x] + votos[x]);
				x++;
				}
				i++;
			} 
			for(int j=0; j < 3; j++){
				int ind_max = calcula_max(votos);
				System.out.println("El indice es " + Integer.toString(ind_max));
				esca[ind_max] += 1;
				//System.out.println("Escaños indice " + Integer.toString(esca[ind_max]));
				votos[ind_max]= votos[ind_max]/repartidor[ind_max];	
				repartidor[ind_max] += 1;
				total_esc++;
			}
			//System.out.println("Escaños repartidos " + Integer.toString(total_esc));
			return esca;
	}

		// Método de Saint para calculo de escaños 
		public int[] metodo_saint(Enumeration em, String[] datos){
			return null;	
		}
	
	//Método de calculo de escaños 
	public int calcula_esca(Enumeration em, String[] datos){
		int i = 0;
		while(em.hasMoreElements()){
			String paraName = (String) em.nextElement();
		if(paraName.indexOf("sistema") != -1 && datos[i].equals("dhont")){
			return 10;//metodo_dhont(em, datos); 
		}else if(paraName.indexOf("sistema") != -1 && datos[i].equals("sainte")){
			return 4;//metodo_saint(em, datos);
		}
		i++;
		}
		return 0;
	}
	
	*/
	
	
	// Método calcula escaños con iterator
	public List<int[]> calulo_es_it(Enumeration em, String[] datos){
	
		//Lista para guardar los nombres de los parametros
				List<String> paramNames = new ArrayList<String>();

				//Variables de resultados finales
				List<Partido> votosTabla = new ArrayList<Partido>();
				List<Partido> votosTablaOrder = new ArrayList<Partido>();
						
				//Pasar enumeracion a lista para conservar los nombres de los datos
				while(em.hasMoreElements()){
					paramNames.add((String) em.nextElement());
				}
	
	
	
	//Lista con escaños
	List<Integer> esca = new ArrayList<Integer>();
	List<String> provincia_partido = new ArrayList<String>();
	List<Double> votos_provincia = new ArrayList<Double>(); 
			
	
	//Iterator de provincias	
	Iterator<Provincia> provinciasIterator = provincias.iterator();
	int i= 0;
	//Bucle de partidos si hay siguiente elemento
	while (provinciasIterator.hasNext()) {
			//Siguiente elemento del iterator(NO hacer dos veces en la misma iteracion)
			Provincia provincia = provinciasIterator.next();
			
			//Iterator de partidos
			Iterator<Partido> partidosIterator = partidos.iterator();
			
			//Bucle de provincias
			while (partidosIterator.hasNext()) {
			
				Partido partido = partidosIterator.next();
				
				if(partido.getProvincia().contains(provincia.getIdentificador())){
					provincia_partido.add(partido.getSiglas());
					//votos_provincia.add(partido.getVotos());
				}
			
						}
					}
				return null;
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
		//Lista para guardar los nombres de los parametros
		List<String> paramNames = new ArrayList<String>();

		//Variables de resultados finales
		List<Partido> votosTabla = new ArrayList<Partido>();
		List<Partido> votosTablaOrder = new ArrayList<Partido>();
				
		//Pasar enumeracion a lista para conservar los nombres de los datos
		while(em.hasMoreElements()){
			paramNames.add((String) em.nextElement());
		}
		//Iterator de partidos	
		Iterator<Partido> partidosIterator = partidos.iterator();
		//Bucle de partidos si hay siguiente elemento
		while (partidosIterator.hasNext()) {
				//Lista de Doubles para guardar los votos en cada iteracion
				ArrayList<Double> votosList = new ArrayList<Double>();
				//Siguiente elemento del iterator(NO hacer dos veces en la misma iteracion)
				Partido partido = partidosIterator.next();
				//Cogemos las siglas del partido iterado
				String siglas = partido.getSiglas();
				//Creamos la lista de provincias
				ArrayList<String> provinciasList = new ArrayList<String>();

				//Iterator de provincias
				Iterator<Provincia> provinciasIterator = provincias.iterator();
				//Bucle de provincias
				while (provinciasIterator.hasNext()) {
					//Cogemos el siguiente elemento del iterator de provincias(NO hacer dos veces en el mismo bucle)
					//Primero cogemos un array del siguiente elemento y de el cogemos el identificador
					Provincia elementoProvincia = provinciasIterator.next();
					//Cogemos el id de la provicia y sus electores
					String provinciaId = elementoProvincia.getIdentificador();
					int electores = elementoProvincia.getElectores();
					//Buscamos el indice que tiene las siglas y la provincia concreta que esta iterando
						int index = paramNames.indexOf(siglas+""+provinciaId);
						//System.out.println("index: " + index + ", id: " + siglas+""+provinciaId + ", valorDatos: " + datos[index]);
						//Si esta el elemento en la lista
						if(index != -1){
							//Si esta en blanco no lo metemos en el array de provincias
							if(datos[index] == ""){		
							}
							else{
								//Sino, calculamos sus votos y lo metemos en las listas temporales de cada iteracion
							Double votosDouble = Double.parseDouble(datos[index])/100 * electores;
							Double votos =  (double) Math.round(votosDouble);
							votosList.add(votos);
							provinciasList.add(provinciaId);
							}
						}
						//Al iterar partidos por fuera, vamos a recorrer todas las provincias de un partido
						//y vamos a guardar todos los valores de ese(por como estan inicializadas las listas)
						//Si sabemos que estamos en la ultima provincia de la iteracion
						if(!provinciasIterator.hasNext()){
							///Guardamos el partido como sus parametros y las listas que tenia
							Partido party = new Partido(siglas,partido.getNombre(),partido.getImagen(),partido.getColor(),provinciasList,votosList,partido.getEsca(),partido.getId_escenario());
							//Lo añadimos a la salida
							votosTabla.add(party);
							System.out.println(party);
						}
							
						}
						
				}
		//Esto era para despues ordenar, se puede hacer return de votosTabla directamente
			votosTablaOrder.addAll(votosTabla);
		
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
