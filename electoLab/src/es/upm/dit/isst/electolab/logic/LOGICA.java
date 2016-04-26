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

public class LOGICA {

	// Metodo de adaptacion de datos para calcular los escaños
	// Devuelve lista de Votos (circunscripcion, partido,voto)
	// a partir de (provincia, partido, porcentaje)
	public static List<Votos> votosPorCircunscripcion(Escenario escenario) {
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
				for (Votos votoAbsoluto : calcularVotosAbsolutos(
						votosProvincia, provincia.getElectores())) {
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
	public static HashMap<String, Integer> escanosCircunscripciones(Escenario escenario) {
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
	public static List<Votos> calcularEscanos(List<Votos> votos,
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
				for (Votos escano : calcularDHondt(votosPorCircunscripcion,
						escanosCircunscripcion.get(circunscripcion))) {
					escanos.add(escano);
				}

			} // Aquí ira else con cada sistema posible

		}
		return (escanos);

	}

	public static List<Votos> resultadosCongreso(
			List<Votos> resultadosCircunscripciones, Escenario escenario) {
		ArrayList<Votos> escanos = new ArrayList<Votos>();
		HashMap<String, Integer> escanosMap = new HashMap<String, Integer>();
		for (Partido partido : escenario.getPartidos()) {
			escanosMap.put(partido.getSiglas(), 0);
		}

		for (Votos escano : resultadosCircunscripciones) {
			escanosMap.put(escano.getPartido(),
					escanosMap.get(escano.getPartido()) + escano.getVotos());
		}

		// A partir del diccionario, creamos la lista a devolver
		for (String partido : escanosMap.keySet()) {
			escanos.add(new Votos("ESPAÑA", partido, escanosMap.get(partido)));
		}

		for (Votos escano : escanos) {
			System.out.println(escano.toJSONString());
		}
		return (escanos);
	}
	
	// Con este metodo cambiamos de porcentajes a votos
	private static List<Votos> calcularVotosAbsolutos(List<Votos> porcentajes,
			int electores) {
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

	// Calcula escaños por metodo Dhondt para una circunscripcion
	private static List<Votos> calcularDHondt(List<Votos> votosPorCircunscripcion,
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

	private static String buscarMaximo(List<Votos> votos, List<Votos> votosOriginales) {
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

	private static List<Votos> sumarEscano(List<Votos> escanos, String ganador,
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

	private static List<Votos> actualizarVotos(List<Votos> votosAux,
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

}
