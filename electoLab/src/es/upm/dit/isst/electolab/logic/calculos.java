package es.upm.dit.isst.electolab.logic;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import es.upm.dit.isst.electolab.dao.ElectoLabDAO;
import es.upm.dit.isst.electolab.dao.ElectoLabDAOImpl;
import es.upm.dit.isst.electolab.model.Circunscripciones;
import es.upm.dit.isst.electolab.model.Comentario;
import es.upm.dit.isst.electolab.model.Comunidades;
import es.upm.dit.isst.electolab.model.Escenario;
import es.upm.dit.isst.electolab.model.Partido;
import es.upm.dit.isst.electolab.model.Provincia;
import es.upm.dit.isst.electolab.model.Sistema;
import es.upm.dit.isst.electolab.model.Votos;

public class calculos {
	private static calculos instance;
	ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();

	public static calculos getInstance() {
		if (instance == null)
			instance = new calculos();
		return instance;
	}

	private String[] provincias_mapa = { "alava", "albacete", "alicante",
			"almeria", "asturias", "avila", "badajoz", "barcelona", "burgos",
			"caceres", "cadiz", "cantabria", "castellon", "ciudadreal",
			"cordoba", "lacoruÃ±a", "cuenca", "gerona", "granada",
			"guadalajara", "guipuzcoa", "huelva", "huesca", "islasbaleares",
			"jaen", "leon", "lerida", "lugo", "madrid", "malaga", "murcia",
			"navarra", "orense", "palencia", "laspalmas", "pontevedra",
			"larioja", "salamanca", "segovia", "sevilla", "soria", "tarragona",
			"santacruzdetenerife", "teruel", "toledo", "valencia",
			"valladolid", "vizcaya", "zamora", "zaragoza" };
	private String[] partidos_mapa = { "PP", "PSOE", "PODEMOS", "C's",
			"EN COMÃš", "PODEMOS-COMPROMÃ�S", "ERC-CATS", "DL",
			"PODEMOS-En Marea-ANOVA-EU", "IU-UPeC", "EAJ-PNV", "CCa-PNC",
			"UPN", "FAC" };

	/**
	 * @param em
	 * @param votos
	 * @return
	 */

	// MÃ©todo para comprobar porcentajes si son correctos
	public boolean porcentaje_correctos(Enumeration em, String[] datos) {
		int porcentaje_total = 0;
		int i = 0;
		while (em.hasMoreElements()) {
			String paraName = (String) em.nextElement();
			if (datos[i] != "" && paraName.indexOf("PP") != -1) {
				porcentaje_total += Integer.parseInt(datos[i]);
				if (porcentaje_total > 100) {
					return false;
				}
			}
			if (datos[i] != "" && paraName.indexOf("PSOE") != -1) {
				porcentaje_total += Integer.parseInt(datos[i]);
				if (porcentaje_total > 100) {
					return false;
				}
			}
			if (datos[i] != "" && paraName.indexOf("C's") != -1) {
				porcentaje_total += Integer.parseInt(datos[i]);
				if (porcentaje_total > 100) {
					return false;
				}
			}
			if (datos[i] != "" && paraName.indexOf("PODEMOS-COMPROMÃ�S") != -1) {
				porcentaje_total += Integer.parseInt(datos[i]);
				if (porcentaje_total > 100) {
					return false;
				}
			}
			if (datos[i] != "" && paraName.indexOf("EN COMÃš") != -1) {
				porcentaje_total += Integer.parseInt(datos[i]);
				if (porcentaje_total > 100) {
					return false;
				}
			}
			if (datos[i] != "" && paraName.indexOf("PODEMOS") != -1) {
				porcentaje_total += Integer.parseInt(datos[i]);
				if (porcentaje_total > 100) {
					return false;
				}
			}
			if (datos[i] != "" && paraName.indexOf("CCa-PNC") != -1) {
				porcentaje_total += Integer.parseInt(datos[i]);
				if (porcentaje_total > 100) {
					return false;
				}
			}
			if (datos[i] != "" && paraName.indexOf("DL") != -1) {
				porcentaje_total += Integer.parseInt(datos[i]);
				if (porcentaje_total > 100) {
					return false;
				}
			}
			if (datos[i] != "" && paraName.indexOf("IU-UPeC") != -1) {
				porcentaje_total += Integer.parseInt(datos[i]);
				if (porcentaje_total > 100) {
					return false;
				}
			}
			if (datos[i] != "" && paraName.indexOf("FAC") != -1) {
				porcentaje_total += Integer.parseInt(datos[i]);
				if (porcentaje_total > 100) {
					return false;
				}
			}
			if (datos[i] != "" && paraName.indexOf("ERC-CATS") != -1) {
				porcentaje_total += Integer.parseInt(datos[i]);
				if (porcentaje_total > 100) {
					return false;
				}
			}
			if (datos[i] != ""
					&& paraName.indexOf("PODEMOS-En Marea-ANOVA-EU") != -1) {
				porcentaje_total += Integer.parseInt(datos[i]);
				if (porcentaje_total > 100) {
					return false;
				}
			}
			if (datos[i] != "" && paraName.indexOf("EAJ-PNV") != -1) {
				porcentaje_total += Integer.parseInt(datos[i]);
				if (porcentaje_total > 100) {
					return false;
				}
			}
			if (datos[i] != "" && paraName.indexOf("UPN") != -1) {
				porcentaje_total += Integer.parseInt(datos[i]);
				if (porcentaje_total > 100) {
					return false;
				}
			}
			i++;
		}
		if (porcentaje_total > 100) {
			return false;
		} else {
			return true;
		}
	}

	public List<String[]> calculaEscanos(List<String[]> votos) {

		return votos;

	}

	// Metodo que nos genera el diccionario (Partido:Provincia)->Porcentaje de
	// votos

	public HashMap<String, Integer> crea_mapa(Enumeration em, String[] datos) {
		int i = 0;
		HashMap<String, Integer> datos_mapa = new HashMap<String, Integer>();

		// Ahora recorremos el enumerado de lo que nos envian para ir guardando
		// lo que queremos

		while (em.hasMoreElements()) {
			String paraName = (String) em.nextElement();

			// La linea mas importante del metodo, viendo lo que nos envia el
			// servidor, filtramos lo que no queremos
			// asi nos quedamos solo con los partidos(el servidor envia que
			// sistema es, los escaños/provincia,etc)

			if (datos[i] != "" && paraName.indexOf("sistema") == -1
					&& paraName.indexOf("escaños") == -1
					&& paraName.indexOf("circunscripciones") == -1
					&& paraName.indexOf("votosTable") == -1
					&& paraName.indexOf("votos") == -1
					&& paraName.indexOf("mayoria") == -1) {
				datos_mapa.put(paraName, Integer.parseInt(datos[i]));

			}
			i++;
		}
		// Esto nos sirve como comprobacion de que el diccionario se genera bien

		Iterator it = datos_mapa.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			// System.out.println("Clave " + key + "-> Valor: " +
			// datos_mapa.get(key));
		}
		return datos_mapa;

	}

	// Mapa con (Provincia)-> Escaños de cada provincia

	public HashMap<String, Integer> esca_map(Enumeration em, String[] datos) {
		int i = 0;
		HashMap<String, Integer> esc_mapa = new HashMap<String, Integer>();
		while (em.hasMoreElements()) {
			String paraName = (String) em.nextElement();

			// Al igual que antes, linea mas importante. Ahora solo queremos las
			// lineas en las que el enumerado diga
			// escaños

			if (datos[i] != "" && paraName.indexOf("escaños") != -1) {
				esc_mapa.put(paraName, Integer.parseInt(datos[i]));

			}
			i++;
		}

		// Comprobante de que efectivamente el diccionario guarda lo que
		// buscabamos

		Iterator it = esc_mapa.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			System.out.println("Clave " + key + "-> Valor: "
					+ esc_mapa.get(key));
		}
		return esc_mapa;

	}

	// Metodo para calcular los escaños. Necesita:
	// mapa(partido:provincia)->porcentaje
	// mapa(provincia)->escaños
	// mapa(provincia)->electores HAY QUE HACER EL METODO QUE DEVUELVA ESTO

	public HashMap<String, Integer> calcula_esc(
			HashMap<String, Integer> datos_mapa,
			HashMap<String, Integer> esca_map,
			HashMap<String, Integer> votos_map) {

		// Mapa que guardara los resultados de esta manera (Partido:Provincia)->
		// escaños obtenidos

		HashMap<String, Integer> resultados_total = new HashMap<String, Integer>();

		for (int i = 0; i < provincias_mapa.length - 1; i++) {

			// Mapa que guarda los partidos que se presentan en una provincia i
			// (Partido)-> votos

			HashMap<String, Integer> datos_provincia = new HashMap<String, Integer>();
			HashMap<String, Integer> esca_provincia = new HashMap<String, Integer>();
			for (String key : datos_mapa.keySet()) {

				// Dividimos Partido:Provincia en dos, asi podemos identificar
				// la provincia y compararlo

				String[] partido_provincia = key.split(":");

				// Si la provincia en la que estamos, es la misma que la de la
				// clave del mapa, lo guardamos en el
				// diccionario para calcular los escaños

				if (partido_provincia[1].equals(provincias_mapa[i])) {
					datos_provincia.put(partido_provincia[0],
							datos_mapa.get(key));
				}
			}
			// Ahora ya tenemos el mapa de provincias, por tanto, hay que saber
			// cuantos escaños hay en esa provincia
			int escprovincia = 0;
			for (String key : esca_map.keySet()) {
				String[] escanos_provincia = key.split(":");
				if (escanos_provincia[1].equals(provincias_mapa[i])) {
					escprovincia = esca_map.get(key);
					break;
				}
			}

			// Cogemos los electores de la circunscripcion para hallar los votos
			// de cada partido

			int electores = 0;
			for (String key : votos_map.keySet()) {
				String[] votos_provincia = key.split(":");
				if (votos_provincia[1].equals(provincias_mapa[i])) {
					electores = votos_map.get(key);
					break;
				}
			}

			// Pasamos los porcentajes de votos a votos

			for (String key : datos_provincia.keySet()) {
				int elec_partido = datos_provincia.get(key);
				elec_partido = (elec_partido / 100) * electores;
				datos_provincia.put(key, elec_partido);
			}

			// A continuacion calculamos D'Hont
			for (int z = 0; z < escprovincia; z++) {
				String max = calculaMaxVotos(datos_provincia);
				int votosPartido = datos_provincia.get(max);

				// Si el partido no fue incluido antes, lo guardamos en el mapa
				if (!esca_provincia.containsKey(max)) {
					esca_provincia.put(max, 1);
					votosPartido = votosPartido / 2;
					datos_provincia.put(max, votosPartido);
				} else {
					// Sino aplicamos D'Hont con un partido con mas de un escaño
					esca_provincia.put(max, esca_provincia.get(max) + 1);
					votosPartido = votosPartido / (esca_provincia.get(max) + 1);
					datos_provincia.put(max, votosPartido);
				}
			}
			// Una vez calculado D'Hont de una provincia, lo guardamos en los
			// resultados
			// totales
			for (String key : esca_provincia.keySet()) {
				String partido_provincia = key + ":" + provincias_mapa[i];
				resultados_total.put(key, esca_provincia.get(key));
			}

		}

		return resultados_total;

	}

	// Metodo para calcular que partido tiene el mayor numero de votos

	public String calculaMaxVotos(HashMap<String, Integer> votos_provincia) {
		String max = "";
		int votos = 0;
		// Cogemos el primer valor del diccionario
		Entry<String, Integer> entry = votos_provincia.entrySet().iterator()
				.next();
		max = entry.getKey();
		votos = entry.getValue();
		// Vemos cual es el maximo numero de votos
		for (String key : votos_provincia.keySet()) {
			if (votos_provincia.get(key) > votos) {
				votos = votos_provincia.get(key);
				max = key;
			}
		}
		return max;
	}

	public Escenario crearEscenario(Enumeration em, String[] datos) {
		// *************************Listas para la salida y
		// variables**********************
		// Lista para guardar los nombres de los parametros
		List<String> paramNames = new ArrayList<String>();
		// Lista de partidos, provincias
		List<Provincia> provinciasList = new ArrayList<Provincia>();
		List<Partido> partidosList = new ArrayList<Partido>();
		List<Votos> votosList = new ArrayList<Votos>();

		// Pasar enumeracion a lista para conservar los nombres de los datos
		while (em.hasMoreElements()) {
			paramNames.add((String) em.nextElement());
		}

		// *************************Calculo de los parametros
		// individuales**********************
		// Inicializacion parametros individuales
		String usuario;
		Sistema sistema = null;
		Circunscripciones circunscripciones = null;
		int mayoria;

		// Calculo de los valores de los parametros individuales
		int index = paramNames.indexOf("usuario");
		usuario = datos[index];
		index = paramNames.indexOf("sistema");
		String sistemaString = datos[index];
		if (sistemaString.equals("dhont"))
			sistema = Sistema.DHONDT;
		index = paramNames.indexOf("circunscripciones");
		String circunscripcionesString = datos[index];
		if (circunscripcionesString.equals("provincias"))
			circunscripciones = Circunscripciones.PROVINCIAS;
		if (circunscripcionesString.equals("comunidades"))
			circunscripciones = Circunscripciones.COMUNIDADES;
		if (circunscripcionesString.equals("spain"))
			circunscripciones = Circunscripciones.PAIS;
		index = paramNames.indexOf("mayoria");
		mayoria = Integer.parseInt(datos[index]);

		// ---------------------------PRUEBA------------------------------------
		// System.out.println("usuario: " + usuario + ", sistema: " + sistema +
		// ", circunscripciones: " + circunscripciones + ", mayoria_abs: " +
		// mayoria);
		// ---------------------------------------------------------------------

		// *************************Calculo de provincias**********************
		// Iterator de provincias
		Iterator<Provincia> provinciasIterator = dao.readEscenarios("admin").get(0)
				.getProvincias().iterator();
		// Bucle de provincias
		while (provinciasIterator.hasNext()) {
			Provincia provincia = provinciasIterator.next();
			index = paramNames.indexOf("escaños " + provincia.getId());
			int escanos = 0;
			if (index != -1) {
				escanos = Integer.parseInt(datos[index]);
				provincia = new Provincia(provincia.getNombre(),
						provincia.getId(), provincia.getComunidad(), escanos,
						provincia.getElectores());
				// ---------------------------PRUEBA------------------------------------
				// System.out.println("nombre: " + provincia.getNombre() +
				// ", id: " + provincia.getId() + ", comunidad: " +
				// provincia.getComunidad() + ", escanos: " +
				// provincia.getEscanos() + ", electores: " +
				// provincia.getElectores());
				// ---------------------------------------------------------------------
				provinciasList.add(provincia);
			}
		}

		// *************************Calculo de partidos y
		// votos**********************

		// Iterator de partidos
		Iterator<Partido> partidosIterator = dao.readEscenarios("admin").get(0)
				.getPartidos().iterator();
		// Bucle de partidos si hay siguiente elemento
		while (partidosIterator.hasNext()) {
			Partido partido = partidosIterator.next();
			if (!partidosList.contains(partido)) {
				// ---------------------------PRUEBA------------------------------------
				// System.out.println("siglas: " + partido.getSiglas() +
				// ", nombre: " + partido.getNombre() + ", color: " +
				// partido.getColor() + ", imagen: " + partido.getImagen());
				// ---------------------------------------------------------------------
				partidosList.add(partido);

				// Iterator de provincias
				Iterator<Provincia> provinciasIterator2 = dao
						.readEscenarios("admin").get(0).getProvincias().iterator();
				// Bucle de provincias
				while (provinciasIterator2.hasNext()) {
					Provincia provincia = provinciasIterator2.next();
					// Buscamos el indice que tiene las siglas y la provincia
					// concreta que esta iterando
					index = paramNames.indexOf(partido.getSiglas() + ":"
							+ provincia.getId());
					// System.out.println("index: " + index + ", id: " +
					// partido.getSiglas()+""+provincia.getId() /*+
					// ", valorDatos: " + datos[index]*/);
					if (index != -1) {

						// Si es NP no lo metemos en el array de votos
						if (datos[index] != "NP") {
							Votos votos = new Votos(provincia.getId(),
									partido.getSiglas(),
									Integer.parseInt(datos[index]));
							// ---------------------------PRUEBA------------------------------------
							// System.out.println("provincia: " +
							// votos.getProvincia()+ ", partido: " +
							// votos.getPartido() + ", porcentajes: " +
							// votos.getVotos());
							// ---------------------------------------------------------------------
							votosList.add(votos);
						}
					}
				}

			}

		}
		List<Comentario> comentariosList = new ArrayList<Comentario>();
		Escenario escenario = new Escenario(usuario, votosList, provinciasList,
				partidosList, comentariosList, sistema, circunscripciones,
				mayoria);
		return escenario;

	}

	// Metodo de adaptacion de datos para calcular los escaños
	// Devuelve lista de votos (circunscripcion, partido,voto)
	// a partir de (provincia,partido, porcentaje)
	public List<Votos> votosPorCircunscripcion(Escenario escenario) {
		Circunscripciones circunscripciones = escenario.getCircunscripciones();
		if (circunscripciones == Circunscripciones.PROVINCIAS) {

			List<Votos> votosProvinciasAbsolutos = new ArrayList<Votos>();
			for (Provincia provincia : escenario.getProvincias()) {
				List<Votos> votosProvincia = new ArrayList<Votos>();
				for (Votos voto : escenario.getVotos()) {
					if (voto.getCircunscripcion().equals(provincia.getId())) {
						votosProvincia.add(voto);
					}
				}
				for (Votos votoAbsoluto : calc_votos(votosProvincia,
						provincia.getElectores())) {
					votosProvinciasAbsolutos.add(votoAbsoluto);
				}
			}
			/*
			 * Comprobacion for(Votos voto: votosProvinciasAbsolutos){
			 * System.out.println(voto.toJSONString()); }
			 */
			return (votosProvinciasAbsolutos);

		} else if (circunscripciones == Circunscripciones.COMUNIDADES) {
			HashMap<String, Integer> votosMap = new HashMap();
			List<Votos> votosComunidadesAbsolutos = new ArrayList<Votos>();

			// Primero rellenamos la lista que devolveremos
			for (Comunidades comunidad : Comunidades.values()) {
				for (Partido partido : escenario.getPartidos()) {
					System.out.println(partido.getSiglas()
							+ comunidad.toString());
					votosMap.put(partido.getSiglas() + comunidad.toString(), 0);
				}
			}

			// Actualizo el diccionario
			for (Votos voto : escenario.getVotos()) {
				for (Provincia provincia : escenario.getProvincias()) {
					if (voto.getCircunscripcion().equals(provincia.getId())) {
						System.out.println(voto.getPartido()
								+ provincia.getComunidad());
						int votos = (int) (Math.floor(voto.getVotos()
								* provincia.getElectores()) / 100);
						votosMap.put(
								voto.getPartido() + provincia.getComunidad(),
								votosMap.get(voto.getPartido()
										+ provincia.getComunidad())
										+ votos);
					}
				}
			}

			// Mapeo el diccionario a lista de Votos
			for (Comunidades comunidad : Comunidades.values()) {
				for (Partido partido : escenario.getPartidos()) {
					votosComunidadesAbsolutos.add(new Votos(comunidad
							.toString(), partido.getSiglas(), votosMap
							.get(partido.getSiglas() + comunidad.toString())));
				}
			}

			// Comprobación de que se ha hecho bien
			for (Votos voto : votosComunidadesAbsolutos) {
				System.out.println(voto.toJSONString());
			}

			return (votosComunidadesAbsolutos);

		} else { // Caso circunscripción ESPAÑA
			HashMap<String, Integer> votosMap = new HashMap();
			List<Votos> votosEspanaAbsolutos = new ArrayList<Votos>();
			// Inicializamos mapa auxiliar
			for (Partido partido : escenario.getPartidos()) {
				votosMap.put(partido.getSiglas(), 0);
			}
			// Rellenamos el mapa sumando votos de cada partido
			for (Votos voto : escenario.getVotos()) {
				for (Provincia provincia : escenario.getProvincias()) {
					if (voto.getCircunscripcion().equals(provincia.getId())) {
						int votos = (int) (Math.floor(voto.getVotos()
								* provincia.getElectores()) / 100);
						votosMap.put(voto.getPartido(),
								votosMap.get(voto.getPartido()) + votos);
					}
				}
			}
			// Mapeo el diccionario a lista de Votos
			for (Partido partido : escenario.getPartidos()) {
				votosEspanaAbsolutos.add(new Votos("ESPAÑA", partido
						.getSiglas(), votosMap.get(partido.getSiglas())));
			}
			// Comprobación de que se ha hecho bien
			for (Votos voto : votosEspanaAbsolutos) {
				System.out.println(voto.toJSONString());
			}

		}
		return (null);

	}

	// A partir de un escenario, devuelve un mapa de tipo
	// (Clave: circunscripcion, valor: escaños asignados)
	public HashMap<String, Integer> escanosCircunscripciones(Escenario escenario) {
		Circunscripciones circunscripciones = escenario.getCircunscripciones();
		HashMap<String, Integer> escanosAsignados = new HashMap<String, Integer>();
		if (circunscripciones == Circunscripciones.PROVINCIAS) {
			for (Provincia provincia : escenario.getProvincias()) {
				escanosAsignados.put(provincia.getId(), provincia.getEscanos());
			}
			return (escanosAsignados);
		} else if (circunscripciones == Circunscripciones.COMUNIDADES) {
			// Primero inicializo el diccionario con todos los valores a 0
			for (Comunidades comunidad : Comunidades.values()) {
				escanosAsignados.put(comunidad.toString(), 0);
			}

			// Después voy recorriendo provincias y añadiendo escaños por
			// comunidades
			for (Provincia provincia : escenario.getProvincias()) {
				escanosAsignados.put(provincia.getComunidad(),
						escanosAsignados.get(provincia.getComunidad())
								+ provincia.getEscanos());
			}
			return (escanosAsignados);
		} else {
			escanosAsignados.put("ESPAÑA", 0);
			for (Provincia provincia : escenario.getProvincias()) {
				escanosAsignados.put("ESPAÑA", escanosAsignados.get("ESPAÑA")
						+ provincia.getEscanos());
			}
			return (escanosAsignados);
		}

	}

	// Calcula los escaños por circunscripcion a partir de
	// la lista de votos con datos adaptados a circunscripciones,
	// el mapa que asigna escaños a cada circunscripción
	// y el sistema electoral elegido
	public List<Votos> calcularEscanos(List<Votos> votos,
			HashMap<String, Integer> escanosCircunscripcion, Sistema sistema) {
		// Creamos un diccionario Circunscripcion -> {Partido ->Voto}
		// Siendo {Partido-> Voto} otro diccionario que para cada
		// circunscripción contiene los votos de los partidos que se presentan
		// en ella
		HashMap<String, HashMap<String, Integer>> votosMap = new HashMap<String, HashMap<String, Integer>>();
		// Inicializamos el mapa añadiendo todas las circunscripciones e
		// inicializando el valor, que será el otro diccionario
		for (Votos voto : votos) {
			votosMap.put(voto.getCircunscripcion(),
					new HashMap<String, Integer>());
		}

		for (Votos voto : votos) {
			// Obtenemos el diccionario cuya clave es la comunidad
			HashMap<String, Integer> partidoVoto = votosMap.get(voto
					.getCircunscripcion());
			// Actualizar votosMap con el nuevo partido
			partidoVoto.put(voto.getPartido(), voto.getVotos());
			votosMap.put(voto.getCircunscripcion(), partidoVoto);
		}

		// Lista que se devolverá con (circunscripcion, partido, escaños)
		List<Votos> escanos = new ArrayList<Votos>();
		// Preparamos llamada a metodo de calculo de escaños
		// llamando al metodo con una List<Votos> de cada circunscripcion
		for (String circunscripcion : votosMap.keySet()) {
			List<Votos> votosPorCircunscripcion = new ArrayList<Votos>();
			HashMap<String, Integer> partidosVotos = votosMap
					.get(circunscripcion);
			// Creamos lista de votos en la circunscripcion
			for (String partido : partidosVotos.keySet()) {
				votosPorCircunscripcion.add(new Votos(circunscripcion, partido,
						partidosVotos.get(partido)));
			}

			// AQUÍ Llamamos al calculo de escaños
			if (sistema == Sistema.DHONDT) {
				for (Votos escano : votosPorCircunscripcion) {
					System.out.println(escano.toJSONString());
				}
				for (Votos escano : calcularDhondt(votosPorCircunscripcion,
						escanosCircunscripcion.get(circunscripcion))) {
					escanos.add(escano);
				}

			} // Aquí ira else con cada sistema posible

		}
		// Comprobación de que se ha hecho bien
		/*
		System.out.println("ESCAÑOOOOOOOOOOOOOOOOOOOS");
		for (Votos escano : escanos) {
			System.out.println(escano.toJSONString());
		}
		*/
		return (escanos);

	}
	
	public List<Votos> resultadosCongreso(List<Votos> resultadosCircunscripciones, Escenario escenario){
		ArrayList<Votos> escanos = new ArrayList<Votos>();
		HashMap<String,Integer> escanosMap = new HashMap<String,Integer>();
		for(Partido partido : escenario.getPartidos()){
			escanosMap.put(partido.getSiglas(), 0);
		}
		
		for( Votos escano : resultadosCircunscripciones){
			escanosMap.put(escano.getPartido(), escanosMap.get(escano.getPartido()) + escano.getVotos());
		}
		
		//A partir del diccionario, creamos la lista a devolver
		for (String partido : escanosMap.keySet()) {
			escanos.add(new Votos("ESPAÑA", partido, escanosMap.get(partido)));
		}
		
		for (Votos escano : escanos) {
			System.out.println(escano.toJSONString());
		}
		return(escanos);
	}
	
	
	//Calcula escaños por metodo Dhondt para una circunscripcion
	public List<Votos> calcularDhondt(List<Votos> votosPorCircunscripcion,
			int numEscanos) {
		List<Votos> escanos = new ArrayList<Votos>();
		List<Votos> votosPorCircunscripcionAux = new ArrayList<Votos>();
		for (Votos voto : votosPorCircunscripcion) {
			votosPorCircunscripcionAux.add(new Votos(voto.getCircunscripcion(),
					voto.getPartido(), voto.getVotos()));
		}
		for (int n = 0; n < numEscanos; n++) {
			String ganador = buscarMaximo(votosPorCircunscripcionAux,
					votosPorCircunscripcion);
			escanos = sumarEscano(escanos, ganador, votosPorCircunscripcion
					.get(0).getCircunscripcion());
			votosPorCircunscripcionAux = actualizarVotos(
					votosPorCircunscripcionAux, escanos, ganador);
		}
		return escanos;
	}

	private String buscarMaximo(List<Votos> votos, List<Votos> votosOriginales) {
		int indiceMaximo = 0;
		boolean hayEmpate = false;
		List<Votos> empatados = new ArrayList<Votos>();
		for (Votos voto : votos) {
			if (voto.getVotos() > votos.get(indiceMaximo).getVotos()) {
				indiceMaximo = votos.indexOf(voto);
				hayEmpate = false;
				empatados.clear();
			} else if (voto.getVotos() == votos.get(indiceMaximo).getVotos()) {
				if (!hayEmpate) {
					empatados.add(votos.get(indiceMaximo));
				}
				empatados.add(voto);
				hayEmpate = true;
			}
		}
		if (hayEmpate) {
			// primero, actualizamos a votos absolutos
			for (Votos voto : empatados) {
				for (Votos v : votosOriginales) {
					if (v.getPartido().equals(voto.getPartido())) {
						voto.setVotos(v.getVotos()); // le ponemos los votos
														// originales
					}
				}
			}
			// buscamos máximo en absolutos
			int indiceMaximoAbs = 0;
			boolean hayEmpateAbs = false;
			List<Votos> empatadosAbs = new ArrayList<Votos>();
			for (Votos voto : empatados) {
				if (voto.getVotos() > votos.get(indiceMaximoAbs).getVotos()) {
					indiceMaximoAbs = votos.indexOf(voto);
					hayEmpateAbs = false;
					empatadosAbs.clear();
				} else if (voto.getVotos() == votos.get(indiceMaximoAbs)
						.getVotos()) {
					if (!hayEmpateAbs) {
						empatadosAbs.add(votos.get(indiceMaximoAbs));
					}
					empatadosAbs.add(voto);
					hayEmpateAbs = true;
				}
			}
			if (hayEmpateAbs) {
				Random r = new Random();
				int sorteo = r.nextInt(empatadosAbs.size());
				return empatadosAbs.get(sorteo).getPartido();
			}
			return empatados.get(indiceMaximoAbs).getPartido();
		}
		return votos.get(indiceMaximo).getPartido();
	}

	private List<Votos> sumarEscano(List<Votos> escanos, String ganador,
			String circunscripcion) {
		for (Votos v : escanos) {
			if (v.getPartido().equals(ganador)) {
				v.setVotos(v.getVotos() + 1);
				return escanos;
			}
		}
		escanos.add(new Votos(circunscripcion, ganador, 1));
		return escanos;
	}

	private List<Votos> actualizarVotos(List<Votos> votosAux,
			List<Votos> escanos, String ganador) {
		for (Votos v : votosAux) {
			if (v.getPartido().equals(ganador)) {
				for (Votos e : escanos) {
					if (e.getPartido().equals(ganador)) {
						v.setVotos(v.getVotos() * e.getVotos()
								/ (e.getVotos() + 1));
						return votosAux;
					}
				}
			}
		}
		return null;
	}

	public List<Votos> calc_dhont(List<Votos> votos_prov, int escanos) {
		// Lista que devolvera los escaños de cada partido en la circunscripcion
		List<Votos> escaños = new ArrayList<Votos>();
		// Lista de votos auxiliar con la que trabajaremos
		List<Votos> aux = new ArrayList<Votos>();
		for (int x = 0; x < votos_prov.size(); x++) {
			aux.add(new Votos(votos_prov.get(x).getCircunscripcion(),
					votos_prov.get(x).getPartido(), votos_prov.get(x)
							.getVotos()));
		}
		for (int i = 0; i < escanos; i++) {
			// Obtenemos el indice del que mas votos tiene
			int ind = calculaMaxVotos(aux, votos_prov);
			// Si ese no estaba en la lista lo añadimos
			if (!escaños.contains(votos_prov.get(ind))) {
				escaños.add(new Votos(votos_prov.get(ind).getCircunscripcion(),
						votos_prov.get(ind).getPartido(), 1));
				int votos = aux.get(ind).getVotos() / 2;
				aux.set(ind, new Votos(
						votos_prov.get(ind).getCircunscripcion(), votos_prov
								.get(ind).getPartido(), votos));
			} else {
				// Sumamos un escaño al que tiene mas votos
				escaños.set(ind, new Votos(votos_prov.get(ind)
						.getCircunscripcion(),
						votos_prov.get(ind).getPartido(), votos_prov.get(ind)
								.getVotos() + 1));
				int votos = aux.get(ind).getVotos()
						/ (escaños.get(ind).getVotos() + 1);
				aux.set(ind, new Votos(
						votos_prov.get(ind).getCircunscripcion(), votos_prov
								.get(ind).getPartido(), votos));
			}
		}

		return escaños;
	}

	// Con este metodo cambiamos de porcentajes a votos
	public List<Votos> calc_votos(List<Votos> porcentajes, int electores) {
		int votosb;
		List<Votos> votos_sinporcen = new ArrayList<Votos>();
		for (Votos votos : porcentajes) {
			votosb = (int) (Math.floor(votos.getVotos() * electores) / 100);
			Votos votoss = new Votos(votos.getCircunscripcion(),
					votos.getPartido(), votosb);
			votos_sinporcen.add(votoss);
		}

		return votos_sinporcen;
	}

	// Metodo que calcula que partido sera el que mas votos tiene.
	// IMPORTANTISIMO: Se le pasa el array de votos inicial, y el actual, porque
	// en caso
	// de empate hay que ver los votos originales
	public int calculaMaxVotos(List<Votos> aux, List<Votos> votos_prov) {
		// Variable que nos da el indice donde se halla el maximo
		int indice = 0;
		// Valor incial de donde esta el maximo
		int max = votos_prov.get(0).getVotos();
		// Lista que guarda los partidos en los que hubo empate a votos
		List<Votos> empate = new ArrayList<Votos>();
		// Numero de vueltas que se recorrio el bucle
		int vueltas = 0;
		// Indices del lugar donde se encontraban los partidos en al lista
		int[] indices = new int[20];
		// Variable que dice si hay empates
		int hayEmpate = 0;
		// Numero de empates existentes
		int empates = 0;
		for (Votos voto : aux) {
			// Si un valor es mayor que el maximo que teniamos lo guardamos,
			// ademas del
			// indice en la lista
			if (voto.getVotos() > max) {
				max = voto.getVotos();
				indice = aux.indexOf(voto);
			}
		}
		// Una vez calculado el maximo de la lista de votos, vemos si hay
		// empates
		for (Votos voto : aux) {
			// Si hay empate, guardamos el partido que genero el empate con sus
			// votos totales
			// ademas del indice en el que se encontraba.
			// El !=0 esta porque en la primera vuelta, comparas el primero con
			// el primero, y eso es obviamente igual
			if (voto.getVotos() == max) {
				empate.add(new Votos(votos_prov.get(vueltas)
						.getCircunscripcion(), votos_prov.get(vueltas)
						.getPartido(), votos_prov.get(vueltas).getVotos()));
				indices[empates] = vueltas;
				empates++;
			}
			vueltas++;

		}
		// Si ha habido empates, pasamos a ver quien fue quien tenia mas votos
		// incialmente
		// y ademas estos empates fueron entre quiens mas votos tenian
		// Si la lista tiene tamaño uno, quiere decir que solo habia un partido
		// con el numero maximo
		// y por tanto, no hubo empate
		if ((!empate.isEmpty()) && (empate.size() != 1)) {
			indice = calculaMaxEmpate(empate, indices);
		}

		return indice;
	}

	// Metodo que calcula a quien se le asignara el escaño en caso de empate
	public int calculaMaxEmpate(List<Votos> empate, int[] indices) {
		// Valor del indice en el que se encuentra el partido con mas votos
		int ind = indices[0];
		// Numero de votos del primer partido de los que empataron
		int max = empate.get(0).getVotos();
		// Numero de empates que ha habido
		int empates = 0;
		// Array que guarda los indices de los partidos que empataron en votos
		// iniciales
		int[] empatados = new int[20];

		for (int i = 1; i < empate.size(); i++) {
			// Si un partido, tenia mas votos que el maximo lo guardamos
			if (empate.get(i).getVotos() > max) {
				max = empate.get(i).getVotos();
				ind = indices[i];
			}
		}
		for (int j = 0; j < empate.size(); j++) {
			// Si hubo empate, guardamos los partidos que empataron
			if (empate.get(j).getVotos() == max) {
				empatados[empates] = indices[j];
				empates++;
			}
		}
		// Igual que en el anterior, si solo hay un con max votos, es el que
		// gana
		// Sino se reparte aleatoriamente el escaño
		if (empates != 1) {
			int indaux = 0 + (int) (Math.random() * (empatados.length));
			ind = empatados[indaux];

		}
		return ind;
	}

}
