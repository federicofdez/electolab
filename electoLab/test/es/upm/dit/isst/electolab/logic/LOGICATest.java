package es.upm.dit.isst.electolab.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.upm.dit.isst.electolab.model.Circunscripciones;
import es.upm.dit.isst.electolab.model.Comentario;
import es.upm.dit.isst.electolab.model.Escenario;
import es.upm.dit.isst.electolab.model.Partido;
import es.upm.dit.isst.electolab.model.Provincia;
import es.upm.dit.isst.electolab.model.Resultados;
import es.upm.dit.isst.electolab.model.Sistema;
import es.upm.dit.isst.electolab.model.Votos;

public class LOGICATest {
	
	private Escenario escenario;
	
	@Before
	public void setUp() throws Exception {
		List<Votos> votos = new ArrayList<Votos>();
		List<Provincia> provincias = new ArrayList<Provincia>();
		List<Partido> partidos = new ArrayList<Partido>();
		
		Sistema sistema = Sistema.DHONDT;
		Circunscripciones circunscripciones = Circunscripciones.PROVINCIAS;
		
		escenario = new Escenario("", "", votos, provincias, partidos, new ArrayList<Comentario>(),
				sistema, circunscripciones, 50, "");
	}
	
	@After
	public void tearDown() throws Exception {
		escenario.getPartidos().clear();
		escenario.getProvincias().clear();
		escenario.getVotos().clear();
		escenario.getComentarios().clear();
		escenario.setPartidosJSON(null);
		escenario.setProvinciasJSON(null);
		escenario.setVotosJSON(null);
		escenario.setComentariosJSON(null);
		escenario = null;
	}

	@Test
	public void testCalcularVotosAbsolutosPorCircunscripcionPorProvincias() {
		List<Provincia> provincias = escenario.getProvincias();
		List<Partido> partidos = escenario.getPartidos();
		List<Votos> votos = escenario.getVotos();
		
		provincias.add(new Provincia("Provincia prueba", "provincia-prueba", "CASTILLALAMANCHA", 4, 1000));
		partidos.add(new Partido("P1", "Partido 1", "", ""));
		partidos.add(new Partido("P2", "Partido 1", "", ""));
		votos.add(new Votos("provincia-prueba", "P1", 80));
		votos.add(new Votos("provincia-prueba", "P2", 20));
		
		escenario.setProvincias(provincias);
		escenario.setPartidos(partidos);
		escenario.setVotos(votos);
		
		List<Votos> votosAbsolutos = LOGICA.calcularVotosAbsolutosPorCircunscripcion(escenario);
		assertEquals(800, votosAbsolutos.get(0).getVotos());
		assertEquals("provincia-prueba", votosAbsolutos.get(0).getCircunscripcion());
		assertEquals(200, votosAbsolutos.get(1).getVotos());
		assertEquals("provincia-prueba", votosAbsolutos.get(1).getCircunscripcion());
	}
	
	@Test
	public void testCalcularVotosAbsolutosPorCircunscripcionPorComunidades() {
		List<Provincia> provincias = escenario.getProvincias();
		List<Partido> partidos = escenario.getPartidos();
		List<Votos> votos = escenario.getVotos();
		
		provincias.add(new Provincia("Provincia prueba 1", "provincia-prueba-1", "CASTILLALAMANCHA", 4, 1000));
		provincias.add(new Provincia("Provincia prueba 2", "provincia-prueba-2", "CASTILLALAMANCHA", 4, 2000));
		partidos.add(new Partido("P1", "Partido 1", "", ""));
		partidos.add(new Partido("P2", "Partido 1", "", ""));
		votos.add(new Votos("provincia-prueba-1", "P1", 80));
		votos.add(new Votos("provincia-prueba-1", "P2", 20));
		votos.add(new Votos("provincia-prueba-2", "P1", 20));
		votos.add(new Votos("provincia-prueba-2", "P2", 80));
		
		escenario.setProvincias(provincias);
		escenario.setPartidos(partidos);
		escenario.setVotos(votos);
		escenario.setCircunscripciones(Circunscripciones.COMUNIDADES);
		
		List<Votos> votosAbsolutos = LOGICA.calcularVotosAbsolutosPorCircunscripcion(escenario);
		assertEquals(1200, votosAbsolutos.get(0).getVotos());
		assertEquals("CASTILLALAMANCHA", votosAbsolutos.get(0).getCircunscripcion());
		assertEquals(1800, votosAbsolutos.get(1).getVotos());
		assertEquals("CASTILLALAMANCHA", votosAbsolutos.get(1).getCircunscripcion());
	}
	
	@Test
	public void testCalcularVotosAbsolutosPorCircunscripcionPorPais() {
		List<Provincia> provincias = escenario.getProvincias();
		List<Partido> partidos = escenario.getPartidos();
		List<Votos> votos = escenario.getVotos();
		
		provincias.add(new Provincia("Provincia prueba 1", "provincia-prueba-1", "CASTILLALAMANCHA", 4, 1000));
		provincias.add(new Provincia("Provincia prueba 2", "provincia-prueba-2", "VALENCIA", 4, 2000));
		partidos.add(new Partido("P1", "Partido 1", "", ""));
		partidos.add(new Partido("P2", "Partido 1", "", ""));
		votos.add(new Votos("provincia-prueba-1", "P1", 80));
		votos.add(new Votos("provincia-prueba-1", "P2", 20));
		votos.add(new Votos("provincia-prueba-2", "P1", 20));
		votos.add(new Votos("provincia-prueba-2", "P2", 80));
		
		escenario.setProvincias(provincias);
		escenario.setPartidos(partidos);
		escenario.setVotos(votos);
		escenario.setCircunscripciones(Circunscripciones.PAIS);
		
		List<Votos> votosAbsolutos = LOGICA.calcularVotosAbsolutosPorCircunscripcion(escenario);
		assertEquals(1200, votosAbsolutos.get(0).getVotos());
		assertEquals("ESPAÑA", votosAbsolutos.get(0).getCircunscripcion());
		assertEquals(1800, votosAbsolutos.get(1).getVotos());
		assertEquals("ESPAÑA", votosAbsolutos.get(1).getCircunscripcion());
	}

	@Test
	public void testCalcularEscanosPorCircunscripcionPorProvincias() {
		List<Provincia> provincias = escenario.getProvincias();
		
		provincias.add(new Provincia("Provincia prueba 1", "provincia-prueba-1", "CASTILLALAMANCHA", 4, 1000));
		provincias.add(new Provincia("Provincia prueba 2", "provincia-prueba-2", "CASTILLALAMANCHA", 8, 1000));
		provincias.add(new Provincia("Provincia prueba 3", "provincia-prueba-3", "VALENCIA", 3, 1000));
		provincias.add(new Provincia("Provincia prueba 4", "provincia-prueba-4", "VALENCIA", 6, 1000));
		
		escenario.setProvincias(provincias);
		
		HashMap<String, Integer> escanosPorCircunscripcion = LOGICA.calcularEscanosPorCircunscripcion(escenario);
		assertEquals(4, (int)escanosPorCircunscripcion.get("provincia-prueba-1"));
		assertEquals(8, (int)escanosPorCircunscripcion.get("provincia-prueba-2"));
		assertEquals(3, (int)escanosPorCircunscripcion.get("provincia-prueba-3"));
		assertEquals(6, (int)escanosPorCircunscripcion.get("provincia-prueba-4"));
		assertNull(escanosPorCircunscripcion.get("CASTILLALAMANCHA"));
		assertNull(escanosPorCircunscripcion.get("VALENCIA"));
	}
	
	@Test
	public void testCalcularEscanosPorCircunscripcionPorComunidades() {
		List<Provincia> provincias = escenario.getProvincias();
		
		provincias.add(new Provincia("Provincia prueba 1", "provincia-prueba-1", "CASTILLALAMANCHA", 4, 1000));
		provincias.add(new Provincia("Provincia prueba 2", "provincia-prueba-2", "CASTILLALAMANCHA", 8, 1000));
		provincias.add(new Provincia("Provincia prueba 3", "provincia-prueba-3", "VALENCIA", 3, 1000));
		provincias.add(new Provincia("Provincia prueba 4", "provincia-prueba-4", "VALENCIA", 6, 1000));
		
		escenario.setProvincias(provincias);
		escenario.setCircunscripciones(Circunscripciones.COMUNIDADES);
		
		HashMap<String, Integer> escanosPorCircunscripcion = LOGICA.calcularEscanosPorCircunscripcion(escenario);
		assertEquals(12, (int)escanosPorCircunscripcion.get("CASTILLALAMANCHA"));
		assertEquals(9, (int)escanosPorCircunscripcion.get("VALENCIA"));
		assertNull(escanosPorCircunscripcion.get("provincia-prueba-1"));
		assertNull(escanosPorCircunscripcion.get("provincia-prueba-2"));
		assertNull(escanosPorCircunscripcion.get("provincia-prueba-3"));
		assertNull(escanosPorCircunscripcion.get("provincia-prueba-4"));
	}
	
	@Test
	public void testCalcularEscanosPorCircunscripcionPorPais() {
		List<Provincia> provincias = escenario.getProvincias();
		
		provincias.add(new Provincia("Provincia prueba 1", "provincia-prueba-1", "CASTILLALAMANCHA", 4, 1000));
		provincias.add(new Provincia("Provincia prueba 2", "provincia-prueba-2", "CASTILLALAMANCHA", 8, 1000));
		provincias.add(new Provincia("Provincia prueba 3", "provincia-prueba-3", "VALENCIA", 3, 1000));
		provincias.add(new Provincia("Provincia prueba 4", "provincia-prueba-4", "VALENCIA", 6, 1000));
		
		escenario.setProvincias(provincias);
		escenario.setCircunscripciones(Circunscripciones.PAIS);
		
		HashMap<String, Integer> escanosPorCircunscripcion = LOGICA.calcularEscanosPorCircunscripcion(escenario);
		assertEquals(21, (int)escanosPorCircunscripcion.get("ESPAÑA"));
		assertNull(escanosPorCircunscripcion.get("provincia-prueba-1"));
		assertNull(escanosPorCircunscripcion.get("provincia-prueba-2"));
		assertNull(escanosPorCircunscripcion.get("provincia-prueba-3"));
		assertNull(escanosPorCircunscripcion.get("provincia-prueba-4"));
		assertNull(escanosPorCircunscripcion.get("CASTILLALAMANCHA"));
		assertNull(escanosPorCircunscripcion.get("VALENCIA"));
	}

	@Test
	public void testCalcularEscanosDHondt() {
		List<Provincia> provincias = escenario.getProvincias();
		List<Partido> partidos = escenario.getPartidos();
		
		provincias.add(new Provincia("Provincia prueba", "provincia-prueba-1", "CASTILLALAMANCHA", 7, 855000));
		partidos.add(new Partido("P1", "Partido 1", "", ""));
		partidos.add(new Partido("P2", "Partido 2", "", ""));
		partidos.add(new Partido("P3", "Partido 3", "", ""));
		partidos.add(new Partido("P4", "Partido 4", "", ""));
		partidos.add(new Partido("P5", "Partido 5", "", ""));
		
		escenario.setProvincias(provincias);
		escenario.setPartidos(partidos);
		
		List<Votos> votosAbsolutosPorCircunscripcion = new ArrayList<Votos>();
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-1", "P1", 340000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-1", "P2", 280000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-1", "P3", 160000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-1", "P4", 60000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-1", "P5", 15000));
		
		HashMap<String, Integer> escanosPorCircunscripcion = LOGICA.calcularEscanosPorCircunscripcion(escenario);
		List<Resultados> resultados = LOGICA.calcularEscanos(votosAbsolutosPorCircunscripcion, escanosPorCircunscripcion, Sistema.DHONDT);
		
		assertEquals(3, resultados.size());
		assertEquals(3, resultados.get(0).getEscanos());
		assertEquals(3, resultados.get(1).getEscanos());
		assertEquals(1, resultados.get(2).getEscanos());
		assertEquals("provincia-prueba-1", resultados.get(0).getCircunscripcion());
		assertEquals("provincia-prueba-1", resultados.get(1).getCircunscripcion());
		assertEquals("provincia-prueba-1", resultados.get(2).getCircunscripcion());
		assertEquals("P1", resultados.get(0).getPartido());
		assertEquals("P2", resultados.get(1).getPartido());
		assertEquals("P3", resultados.get(2).getPartido());
	}
	
	@Test
	public void testCalcularEscanosSainteLague() {
		List<Provincia> provincias = escenario.getProvincias();
		List<Partido> partidos = escenario.getPartidos();
		
		provincias.add(new Provincia("Provincia prueba", "provincia-prueba-1", "CASTILLALAMANCHA", 7, 855000));
		partidos.add(new Partido("P1", "Partido 1", "", ""));
		partidos.add(new Partido("P2", "Partido 2", "", ""));
		partidos.add(new Partido("P3", "Partido 3", "", ""));
		partidos.add(new Partido("P4", "Partido 4", "", ""));
		partidos.add(new Partido("P5", "Partido 5", "", ""));
		
		escenario.setProvincias(provincias);
		escenario.setPartidos(partidos);
		
		List<Votos> votosAbsolutosPorCircunscripcion = new ArrayList<Votos>();
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-1", "P1", 340000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-1", "P2", 280000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-1", "P3", 160000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-1", "P4", 60000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-1", "P5", 15000));
		
		HashMap<String, Integer> escanosPorCircunscripcion = LOGICA.calcularEscanosPorCircunscripcion(escenario);
		List<Resultados> resultados = LOGICA.calcularEscanos(votosAbsolutosPorCircunscripcion, escanosPorCircunscripcion, Sistema.SAINTE);
		
		assertEquals(4, resultados.size());
		assertEquals(3, resultados.get(0).getEscanos());
		assertEquals(2, resultados.get(1).getEscanos());
		assertEquals(1, resultados.get(2).getEscanos());
		assertEquals(1, resultados.get(3).getEscanos());
		assertEquals("provincia-prueba-1", resultados.get(0).getCircunscripcion());
		assertEquals("provincia-prueba-1", resultados.get(1).getCircunscripcion());
		assertEquals("provincia-prueba-1", resultados.get(2).getCircunscripcion());
		assertEquals("provincia-prueba-1", resultados.get(3).getCircunscripcion());
		assertEquals("P1", resultados.get(0).getPartido());
		assertEquals("P2", resultados.get(1).getPartido());
		assertEquals("P3", resultados.get(2).getPartido());
		assertEquals("P4", resultados.get(3).getPartido());
	}

	@Test
	public void testResultadosCongreso() {
		List<Provincia> provincias = escenario.getProvincias();
		List<Partido> partidos = escenario.getPartidos();
		
		provincias.add(new Provincia("Provincia prueba 1", "provincia-prueba-1", "CASTILLALAMANCHA", 7, 855000));
		provincias.add(new Provincia("Provincia prueba 2", "provincia-prueba-2", "CASTILLALAMANCHA", 7, 855000));
		partidos.add(new Partido("P1", "Partido 1", "", ""));
		partidos.add(new Partido("P2", "Partido 2", "", ""));
		partidos.add(new Partido("P3", "Partido 3", "", ""));
		partidos.add(new Partido("P4", "Partido 4", "", ""));
		partidos.add(new Partido("P5", "Partido 5", "", ""));
		
		escenario.setProvincias(provincias);
		escenario.setPartidos(partidos);
		
		List<Votos> votosAbsolutosPorCircunscripcion = new ArrayList<Votos>();
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-1", "P1", 340000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-1", "P2", 280000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-1", "P3", 160000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-1", "P4", 60000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-1", "P5", 15000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-2", "P1", 340000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-2", "P2", 280000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-2", "P3", 160000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-2", "P4", 60000));
		votosAbsolutosPorCircunscripcion.add(new Votos("provincia-prueba-2", "P5", 15000));
		
		HashMap<String, Integer> escanosPorCircunscripcion = LOGICA.calcularEscanosPorCircunscripcion(escenario);
		List<Resultados> resultados = LOGICA.calcularEscanos(votosAbsolutosPorCircunscripcion, escanosPorCircunscripcion, Sistema.DHONDT);
		
		List<Resultados> resultadosCongreso = LOGICA.resultadosCongreso(resultados, escenario);
		
		assertEquals(5, resultadosCongreso.size());
		assertEquals("P5", resultadosCongreso.get(0).getPartido());
		assertEquals("P4", resultadosCongreso.get(1).getPartido());
		assertEquals("P1", resultadosCongreso.get(2).getPartido());
		assertEquals("P3", resultadosCongreso.get(3).getPartido());
		assertEquals("P2", resultadosCongreso.get(4).getPartido());
		assertEquals(0, resultadosCongreso.get(0).getEscanos());
		assertEquals(0, resultadosCongreso.get(1).getEscanos());
		assertEquals(6, resultadosCongreso.get(2).getEscanos());
		assertEquals(2, resultadosCongreso.get(3).getEscanos());
		assertEquals(6, resultadosCongreso.get(4).getEscanos());
		assertEquals("ESPAÑA", resultadosCongreso.get(0).getCircunscripcion());
		assertEquals("ESPAÑA", resultadosCongreso.get(1).getCircunscripcion());
		assertEquals("ESPAÑA", resultadosCongreso.get(2).getCircunscripcion());
		assertEquals("ESPAÑA", resultadosCongreso.get(3).getCircunscripcion());
		assertEquals("ESPAÑA", resultadosCongreso.get(4).getCircunscripcion());

	}

}
