package es.upm.dit.isst.electolab.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import es.upm.dit.isst.electolab.model.Circunscripciones;
import es.upm.dit.isst.electolab.model.Comunidades;
import es.upm.dit.isst.electolab.model.Escanos;
import es.upm.dit.isst.electolab.model.Escenario;
import es.upm.dit.isst.electolab.model.Partido;
import es.upm.dit.isst.electolab.model.Provincia;
import es.upm.dit.isst.electolab.model.Sistema;
import es.upm.dit.isst.electolab.model.Votos;

public class LOGICA {

	/**
	 * A partir del objeto Escenario de entrada, obtiene una lista de votos
	 * preparada para aplicar el sistema correspondiente.
	 * 
	 * @param escenario
	 *            el objeto Escenario creado a partir de los datos de entrada
	 * @return lista de Votos absolutos y agrupados por la circunscripción
	 *         elegida
	 */
	public static List<Votos> calcularVotosAbsolutosPorCircunscripcion(
			Escenario escenario) {
		List<Votos> votosAbsolutosPorCircunscripcion = new ArrayList<Votos>();

		switch (escenario.getCircunscripciones()) {

		case PROVINCIAS:
			// para cada provincia
			for (Provincia provincia : escenario.getProvincias())
				// buscamos sus datos de votos
				for (Votos voto : escenario.getVotos())
					if (voto.getCircunscripcion().equals(provincia.getId()) && voto.getVotos() != -1) {
						// los pasamos a absoluto
						Votos votosAbs = new Votos(voto.getCircunscripcion(),
								voto.getPartido(),
								(int) (Math.floor(voto.getVotos()
										* provincia.getElectores()) / 100));
						// y los guardamos
						votosAbsolutosPorCircunscripcion.add(votosAbs);
					}
			break;

		case COMUNIDADES:
			HashMap<String, Integer> aux1 = new HashMap<String, Integer>();
			// para cada dato de votos en una provincia
			for (Votos voto : escenario.getVotos()) {
				// buscamos a qué provincia pertenece
				Provincia p = escenario.getProvincias().get(
						escenario.getProvincias().indexOf(
								new Provincia(voto.getCircunscripcion())));
				// pasamos los votos a absoluto
				int votos = (int) (Math.floor(voto.getVotos()
						* p.getElectores()) / 100);
				// y los guardamos en el mapa auxiliar por comunidad
				String key = voto.getPartido() + ":" + p.getComunidad();
				if (aux1.containsKey(key))
					aux1.put(key, aux1.get(key) + votos);
				else
					aux1.put(key, votos);
			}
			// por último, pasamos el mapa auxiliar a lista de Votos
			for (String k : aux1.keySet()) {
				String partido = k.split(":")[0];
				String comunidad = k.split(":")[1];
				votosAbsolutosPorCircunscripcion.add(new Votos(comunidad,
						partido, aux1.get(k)));
			}
			break;

		case PAIS:
			HashMap<String, Integer> aux2 = new HashMap<String, Integer>();
			// para cada dato de votos
			for (Votos voto : escenario.getVotos()) {
				// buscamos a qué provincia pertenece
				Provincia p = escenario.getProvincias().get(
						escenario.getProvincias().indexOf(
								new Provincia(voto.getCircunscripcion())));
				// pasamos los votos a absoluto
				int votos = (int) (Math.floor(voto.getVotos()
						* p.getElectores()) / 100);
				// y los guardamos en el mapa auxiliar
				String key = voto.getPartido();
				if (aux2.containsKey(key))
					aux2.put(key, aux2.get(key) + votos);
				else
					aux2.put(key, votos);
			}
			// por último, pasamos el mapa auxiliar a lista de Votos
			for (String k : aux2.keySet())
				votosAbsolutosPorCircunscripcion.add(new Votos("ESPAÑA", k,
						aux2.get(k)));
			break;
		}
		return votosAbsolutosPorCircunscripcion;
	}

	/**
	 * A partir del objeto Escenario de entrada, obtiene un mapa con los escaños
	 * a repartir, preparada para aplicar el sistema correspondiente
	 * 
	 * @param escenario
	 *            el objeto Escenario creado a partir de los datos de entrada
	 * @return mapa con los escaños a repartir en cada una de las
	 *         circunscripciones que haya
	 */
	public static HashMap<String, Integer> calcularEscanosPorCircunscripcion(
			Escenario escenario) {
		HashMap<String, Integer> escanosAsignados = new HashMap<String, Integer>();

		for (Provincia provincia : escenario.getProvincias())
			switch (escenario.getCircunscripciones()) {

			case PROVINCIAS:
				escanosAsignados.put(provincia.getId(), provincia.getEscanos());
				break;

			case COMUNIDADES:
				if (escanosAsignados.containsKey(provincia.getComunidad()))
					escanosAsignados.put(provincia.getComunidad(),
							escanosAsignados.get(provincia.getComunidad())
									+ provincia.getEscanos());
				else
					escanosAsignados.put(provincia.getComunidad(),
							provincia.getEscanos());
				break;

			case PAIS:
				if (escanosAsignados.size() == 0)
					escanosAsignados.put("ESPAÑA", provincia.getEscanos());
				else
					escanosAsignados.put(
							"ESPAÑA",
							escanosAsignados.get("ESPAÑA")
									+ provincia.getEscanos());
				break;
			}
		return escanosAsignados;
	}

	/**
	 * Aplica un sistema de proporcionalidad a una lista de votos absolutos,
	 * repartiendo un determinado número de escaños a cada circunscripción.
	 * 
	 * @param votos
	 *            lista con los votos absolutos agrupados por circunscripción
	 * @param escanosCircunscripcion
	 *            mapa con los escaños a repartir en cada una de las
	 *            circunscripciones que haya
	 * @param sistema
	 *            el sistema de proporcionalidad elegido
	 * @return lista con los escaños que resultan del reparto, granulados por
	 *         circunscripciones
	 */
	public static List<Escanos> calcularEscanos(List<Votos> votos,
			HashMap<String, Integer> escanosCircunscripcion, Sistema sistema) {
		// Creamos un diccionario Circunscripcion -> {Partido ->Voto}
		// Siendo {Partido-> Voto} otro diccionario que para cada
		// circunscripción contiene los votos de los partidos que se presentan
		// en ella
		HashMap<String, HashMap<String, Integer>> votosMap = new HashMap<String, HashMap<String, Integer>>();
		// Inicializamos el mapa añadiendo todas las circunscripciones e
		// inicializando el valor, que será el otro diccionario
		for (Votos voto : votos)
			votosMap.put(voto.getCircunscripcion(),
					new HashMap<String, Integer>());

		for (Votos voto : votos) {
			// Obtenemos el diccionario cuya clave es la comunidad
			HashMap<String, Integer> partidoVoto = votosMap.get(voto
					.getCircunscripcion());
			// Actualizar votosMap con el nuevo partido
			partidoVoto.put(voto.getPartido(), voto.getVotos());
			votosMap.put(voto.getCircunscripcion(), partidoVoto);
		}

		// Lista que se devolverá con (circunscripcion, partido, escaños)
		List<Escanos> escanos = new ArrayList<Escanos>();
		// Preparamos llamada a metodo de calculo de escaños
		// llamando al metodo con una List<Votos> de cada circunscripcion
		for (String circunscripcion : votosMap.keySet()) {
			List<Votos> votosPorCircunscripcion = new ArrayList<Votos>();
			HashMap<String, Integer> partidosVotos = votosMap
					.get(circunscripcion);
			// Creamos lista de votos en la circunscripcion
			for (String partido : partidosVotos.keySet())
				votosPorCircunscripcion.add(new Votos(circunscripcion, partido,
						partidosVotos.get(partido)));

			// AQUÍ Llamamos al calculo de escaños
			for (Escanos escano : calcularSistema(sistema,
					votosPorCircunscripcion,
					escanosCircunscripcion.get(circunscripcion)))
				escanos.add(escano);

		}
		return (escanos);

	}

	/**
	 * Agrupa los resultados de todas las circunscripciones para obtener los del
	 * Congreso.
	 * 
	 * @param resultadosCircunscripciones
	 *            lista con los resultados de todas las circunscripciones
	 * @param escenario
	 *            para obtener todos los partidos que se han presentado
	 * @return lista con los resultados agrupados para toda España (Congreso)
	 */
	public static List<Escanos> resultadosCongreso(
			List<Escanos> resultadosCircunscripciones, Escenario escenario) {
		ArrayList<Escanos> escanos = new ArrayList<Escanos>();
		HashMap<String, Integer> escanosMap = new HashMap<String, Integer>();
		for (Partido partido : escenario.getPartidos())
			escanosMap.put(partido.getSiglas(), 0);

		for (Escanos escano : resultadosCircunscripciones)
			escanosMap.put(escano.getPartido(),
					escanosMap.get(escano.getPartido()) + escano.getEscanos());

		// A partir del diccionario, creamos la lista a devolver
		for (String partido : escanosMap.keySet())
			escanos.add(new Escanos("ESPAÑA", partido, escanosMap.get(partido)));

		return (escanos);
	}

	/**
	 * Aplica un sistema de proporcionalidad a los votos de una circunscripción,
	 * para conocer el reparto de escaños resultante en la misma.
	 * 
	 * @param sistema
	 *            el sistema de proporcionalidad a aplicar
	 * @param votosPorCircunscripcion
	 *            lista con los votos agrupados por el tipo de circunscripción
	 *            para el que se hará el cálculo
	 * @param numEscanos
	 *            escaños a repartir en esa circunscripción
	 * @return lista de escaños resultantes del reparto
	 */
	private static List<Escanos> calcularSistema(Sistema sistema,
			List<Votos> votosPorCircunscripcion, int numEscanos) {
		List<Escanos> escanos = new ArrayList<Escanos>();
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
			votosPorCircunscripcionAux = actualizarVotos(sistema,
					votosPorCircunscripcionAux, escanos, ganador);
		}
		return escanos;
	}

	/**
	 * Actualiza los votos aplicando al partido ganador el cociente que
	 * corresponda según el sistema
	 * 
	 * @param sistema
	 *            determina el cociente a aplicar
	 * @param votosAux
	 *            lista de votos que se actualizará
	 * @param escanos
	 *            lista para determinar el cociente a aplicar
	 * @param ganador
	 *            siglas del partido que verá sus votos recalculados
	 * @return la misma lista de entrada, pero actualizada
	 */
	private static List<Votos> actualizarVotos(Sistema sistema,
			List<Votos> votosAux, List<Escanos> escanos, String ganador) {
		for (Votos v : votosAux)
			if (v.getPartido().equals(ganador))
				for (Escanos e : escanos)
					if (e.getPartido().equals(ganador)) {
						if (sistema == Sistema.DHONDT)
							v.setVotos(v.getVotos() * e.getEscanos()
									/ (e.getEscanos() + 1));
						else if (sistema == Sistema.SAINTE)
							v.setVotos(v.getVotos() * (2 * e.getEscanos() - 1)
									/ (2 * e.getEscanos() + 1));
						return votosAux;
					}
		return null;
	}

	/**
	 * Calcula el partido con más votos en una determinada ronda de asignación
	 * de escaños
	 * 
	 * @param votos
	 *            actualizados dependiendo de la ronda
	 * @param votosOriginales
	 *            para comparar en caso de empate
	 * @return siglas del partido que gana la ronda
	 */
	private static String buscarMaximo(List<Votos> votos,
			List<Votos> votosOriginales) {
		int indiceMaximo = 0;
		boolean hayEmpate = false;
		List<Votos> empatados = new ArrayList<Votos>();
		for (Votos voto : votos) {
			if (voto.getVotos() > votos.get(indiceMaximo).getVotos()) {
				indiceMaximo = votos.indexOf(voto);
				hayEmpate = false;
				empatados.clear();
			} else if (indiceMaximo != votos.indexOf(voto)
					&& voto.getVotos() == votos.get(indiceMaximo).getVotos()) {
				if (!hayEmpate)
					empatados.add(new Votos(votos.get(indiceMaximo)
							.getCircunscripcion(), votos.get(indiceMaximo)
							.getPartido(), votos.get(indiceMaximo).getVotos()));
				empatados.add(new Votos(voto.getCircunscripcion(), voto
						.getPartido(), voto.getVotos()));
				hayEmpate = true;
			}
		}
		if (hayEmpate) {
			// primero, actualizamos a votos absolutos
			for (Votos voto : empatados)
				for (Votos v : votosOriginales)
					if (v.getPartido().equals(voto.getPartido()))
						voto.setVotos(v.getVotos()); // le ponemos los votos
														// originales
			// buscamos máximo en absolutos
			int indiceMaximoAbs = 0;
			boolean hayEmpateAbs = false;
			List<Votos> empatadosAbs = new ArrayList<Votos>();
			for (Votos voto : empatados) {
				if (voto.getVotos() > empatados.get(indiceMaximoAbs).getVotos()) {
					indiceMaximoAbs = empatados.indexOf(voto);
					hayEmpateAbs = false;
					empatadosAbs.clear();
				} else if (indiceMaximoAbs != empatados.indexOf(voto)
						&& voto.getVotos() == empatados.get(indiceMaximoAbs)
								.getVotos()) {
					if (!hayEmpateAbs)
						empatadosAbs.add(new Votos(empatados.get(
								indiceMaximoAbs).getCircunscripcion(),
								empatados.get(indiceMaximoAbs).getPartido(),
								empatados.get(indiceMaximoAbs).getVotos()));
					empatadosAbs.add(new Votos(voto.getCircunscripcion(), voto
							.getPartido(), voto.getVotos()));
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

	/**
	 * Suma un escaño al partido que ha ganado una determinada ronda de
	 * asignación de escaños
	 * 
	 * @param escanos
	 *            lista con los escaños, para sumar el nuevo
	 * @param ganador
	 *            siglas del partido que ha ganado la ronda
	 * @param circunscripcion
	 *            en la que el partido recibe el escaño
	 * @return la misma lista de entrada, pero actualizada
	 */
	private static List<Escanos> sumarEscano(List<Escanos> escanos,
			String ganador, String circunscripcion) {
		for (Escanos e : escanos)
			if (e.getPartido().equals(ganador)) {
				e.setEscanos(e.getEscanos() + 1);
				return escanos;
			}
		escanos.add(new Escanos(circunscripcion, ganador, 1));
		return escanos;
	}

}
