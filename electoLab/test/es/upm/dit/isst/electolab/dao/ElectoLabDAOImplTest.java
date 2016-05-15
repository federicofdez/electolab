package es.upm.dit.isst.electolab.dao;



import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import es.upm.dit.isst.electolab.dao.ElectoLabDAO;
import es.upm.dit.isst.electolab.dao.ElectoLabDAOImpl;
import es.upm.dit.isst.electolab.model.Circunscripciones;
import es.upm.dit.isst.electolab.model.Comentario;
import es.upm.dit.isst.electolab.model.Escenario;
import es.upm.dit.isst.electolab.model.Grupo;
import es.upm.dit.isst.electolab.model.Partido;
import es.upm.dit.isst.electolab.model.Provincia;
import es.upm.dit.isst.electolab.model.Sistema;
import es.upm.dit.isst.electolab.model.Votos;


public class ElectoLabDAOImplTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testCreateEscenario() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
		//Instancia para la prueba
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		// Lista para provincias
		List<Provincia> provincias = new ArrayList<Provincia>();
		provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
				4658397));
		provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
				3, 118502));
		// Lista para partidos
		List<Partido> partidos = new ArrayList<Partido>();
		partidos.add(new Partido("PP", "Partido Popular",
				"img/logos/pp.png", "02CFF7"));
		partidos.add(new Partido("PSOE", "Partido Socialista",
				"img/logos/psoe.png", "FF0000"));
		partidos.add(new Partido("PODEMOS", "Podemos",
				"img/logos/podemos.png", "742DA1"));
		partidos.add(new Partido("Cs", "Ciudadanos",
				"img/logos/ciudadanos.png", "F7771B"));
		// Lista para votos
		List<Votos> votos = new ArrayList<Votos>();
		votos.add(new Votos("segovia", "PP", 39));
		votos.add(new Votos("segovia", "PSOE", 21));
		votos.add(new Votos("segovia", "Cs", 17));
		votos.add(new Votos("segovia", "PODEMOS", 14));
		votos.add(new Votos("madrid", "PP", 33));
		votos.add(new Votos("madrid", "PODEMOS", 20));
		votos.add(new Votos("madrid", "Cs", 18));
		votos.add(new Votos("madrid", "PSOE", 17));
		//Lista para comentarios
		List<Comentario> comentarios = new ArrayList<Comentario>();
		// Establecemos la fecha del escenario
		String fecha = "10/05/2016";
		// Creación del escenario de pruebas
		Escenario escenario = dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
				partidos, comentarios, Sistema.DHONDT,
				Circunscripciones.PROVINCIAS, 50, fecha);
		//Comenzamos las pruebas 
		assertEquals(escenario.getTitulo(), "Escenario_prueba");
		assertEquals(escenario.getFecha(), "10/05/2016");
		assertEquals(escenario.getCircunscripciones(), "PROVINCIAS");
		Votos comp_votos = new Votos("segovia", "PP", 39);
		assertEquals(escenario.getVotos().get(0).toString(), comp_votos.toString());
		Partido comp_partido = new Partido("PP", "Partido Popular",
				"img/logos/pp.png", "02CFF7");
		assertEquals(escenario.getPartidos().get(0).toString(), comp_partido.toString());
		Provincia comp_provincia = new Provincia("Madrid", "madrid", "MADRID", 36,
				4658397);
		assertEquals(escenario.getProvincias().get(0), comp_provincia.toString());
		assertEquals(escenario.getMayoria_abs(), 50);
		assertEquals(escenario.getUsuario(), "usuario_pruebas");
	}

	@Test
	public void testCreateEscenarioEscenario() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
				//Instancia para la prueba
				ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
				// Lista para provincias
				List<Provincia> provincias = new ArrayList<Provincia>();
				provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
						4658397));
				provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
						3, 118502));
				// Lista para partidos
				List<Partido> partidos = new ArrayList<Partido>();
				partidos.add(new Partido("PP", "Partido Popular",
						"img/logos/pp.png", "02CFF7"));
				partidos.add(new Partido("PSOE", "Partido Socialista",
						"img/logos/psoe.png", "FF0000"));
				partidos.add(new Partido("PODEMOS", "Podemos",
						"img/logos/podemos.png", "742DA1"));
				partidos.add(new Partido("Cs", "Ciudadanos",
						"img/logos/ciudadanos.png", "F7771B"));
				// Lista para votos
				List<Votos> votos = new ArrayList<Votos>();
				votos.add(new Votos("segovia", "PP", 39));
				votos.add(new Votos("segovia", "PSOE", 21));
				votos.add(new Votos("segovia", "Cs", 17));
				votos.add(new Votos("segovia", "PODEMOS", 14));
				votos.add(new Votos("madrid", "PP", 33));
				votos.add(new Votos("madrid", "PODEMOS", 20));
				votos.add(new Votos("madrid", "Cs", 18));
				votos.add(new Votos("madrid", "PSOE", 17));
				//Lista para comentarios
				List<Comentario> comentarios = new ArrayList<Comentario>();
				// Establecemos la fecha del escenario
				String fecha = "10/05/2016";
				// Creación del escenario
				Escenario escenario1 = new Escenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
						partidos, comentarios, Sistema.DHONDT,
						Circunscripciones.PROVINCIAS, 50, fecha);
				// Creación del escenario de pruebas 
				Escenario escenario = dao.createEscenario(escenario1);
				//Comenzamos las pruebas 
				assertEquals(escenario.getTitulo(), "Escenario_prueba");
				assertEquals(escenario.getFecha(), "10/05/2016");
				assertEquals(escenario.getCircunscripciones(), "PROVINCIAS");
				Votos comp_votos = new Votos("segovia", "PP", 39);
				assertEquals(escenario.getVotos().get(0).toString(), comp_votos.toString());
				Partido comp_partido = new Partido("PP", "Partido Popular",
						"img/logos/pp.png", "02CFF7");
				assertEquals(escenario.getPartidos().get(0).toString(), comp_partido.toString());
				Provincia comp_provincia = new Provincia("Madrid", "madrid", "MADRID", 36,
						4658397);
				assertEquals(escenario.getProvincias().get(0), comp_provincia.toString());
				assertEquals(escenario.getMayoria_abs(), 50);
				assertEquals(escenario.getUsuario(), "usuario_pruebas");
	}

	@Test
	public void testReadEscenarios() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
				//Instancia para la prueba
				ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
				// Lista para provincias
				List<Provincia> provincias = new ArrayList<Provincia>();
				provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
						4658397));
				provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
						3, 118502));
				// Lista para partidos
				List<Partido> partidos = new ArrayList<Partido>();
				partidos.add(new Partido("PP", "Partido Popular",
						"img/logos/pp.png", "02CFF7"));
				partidos.add(new Partido("PSOE", "Partido Socialista",
						"img/logos/psoe.png", "FF0000"));
				partidos.add(new Partido("PODEMOS", "Podemos",
						"img/logos/podemos.png", "742DA1"));
				partidos.add(new Partido("Cs", "Ciudadanos",
						"img/logos/ciudadanos.png", "F7771B"));
				// Lista para votos
				List<Votos> votos = new ArrayList<Votos>();
				votos.add(new Votos("segovia", "PP", 39));
				votos.add(new Votos("segovia", "PSOE", 21));
				votos.add(new Votos("segovia", "Cs", 17));
				votos.add(new Votos("segovia", "PODEMOS", 14));
				votos.add(new Votos("madrid", "PP", 33));
				votos.add(new Votos("madrid", "PODEMOS", 20));
				votos.add(new Votos("madrid", "Cs", 18));
				votos.add(new Votos("madrid", "PSOE", 17));
				//Lista para comentarios
				List<Comentario> comentarios = new ArrayList<Comentario>();
				// Establecemos la fecha del escenario
				String fecha = "10/05/2016";
				// Creación del escenario de pruebas
				Escenario escenario = dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
						partidos, comentarios, Sistema.DHONDT,
						Circunscripciones.PROVINCIAS, 50, fecha);
				//Comenzamos las pruebas 
				assertEquals(dao.readEscenarios().get(0).toString(), escenario.toString());
		
	}

	@Test
	public void testReadEscenariosString() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
		//Instancia para la prueba
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		// Lista para provincias
		List<Provincia> provincias = new ArrayList<Provincia>();
		provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
				4658397));
		provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
				3, 118502));
		// Lista para partidos
		List<Partido> partidos = new ArrayList<Partido>();
		partidos.add(new Partido("PP", "Partido Popular",
				"img/logos/pp.png", "02CFF7"));
		partidos.add(new Partido("PSOE", "Partido Socialista",
				"img/logos/psoe.png", "FF0000"));
		partidos.add(new Partido("PODEMOS", "Podemos",
				"img/logos/podemos.png", "742DA1"));
		partidos.add(new Partido("Cs", "Ciudadanos",
				"img/logos/ciudadanos.png", "F7771B"));
		// Lista para votos
		List<Votos> votos = new ArrayList<Votos>();
		votos.add(new Votos("segovia", "PP", 39));
		votos.add(new Votos("segovia", "PSOE", 21));
		votos.add(new Votos("segovia", "Cs", 17));
		votos.add(new Votos("segovia", "PODEMOS", 14));
		votos.add(new Votos("madrid", "PP", 33));
		votos.add(new Votos("madrid", "PODEMOS", 20));
		votos.add(new Votos("madrid", "Cs", 18));
		votos.add(new Votos("madrid", "PSOE", 17));
		//Lista para comentarios
		List<Comentario> comentarios = new ArrayList<Comentario>();
		// Establecemos la fecha del escenario
		String fecha = "10/05/2016";
		// Creación del escenario de pruebas
		Escenario escenario = dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
				partidos, comentarios, Sistema.DHONDT,
				Circunscripciones.PROVINCIAS, 50, fecha);
		//Comenzamos las pruebas 
		assertEquals(dao.readEscenarios("usuario_pruebas").toString(), escenario.toString());
	}

	@Test
	public void testReadEscenariosGrupo() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
		//Instancia para la prueba
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		// Lista para provincias
		List<Provincia> provincias = new ArrayList<Provincia>();
		provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
				4658397));
		provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
				3, 118502));
		// Lista para partidos
		List<Partido> partidos = new ArrayList<Partido>();
		partidos.add(new Partido("PP", "Partido Popular",
				"img/logos/pp.png", "02CFF7"));
		partidos.add(new Partido("PSOE", "Partido Socialista",
				"img/logos/psoe.png", "FF0000"));
		partidos.add(new Partido("PODEMOS", "Podemos",
				"img/logos/podemos.png", "742DA1"));
		partidos.add(new Partido("Cs", "Ciudadanos",
				"img/logos/ciudadanos.png", "F7771B"));
		// Lista para votos
		List<Votos> votos = new ArrayList<Votos>();
		votos.add(new Votos("segovia", "PP", 39));
		votos.add(new Votos("segovia", "PSOE", 21));
		votos.add(new Votos("segovia", "Cs", 17));
		votos.add(new Votos("segovia", "PODEMOS", 14));
		votos.add(new Votos("madrid", "PP", 33));
		votos.add(new Votos("madrid", "PODEMOS", 20));
		votos.add(new Votos("madrid", "Cs", 18));
		votos.add(new Votos("madrid", "PSOE", 17));
		//Lista para comentarios
		List<Comentario> comentarios = new ArrayList<Comentario>();
		// Establecemos la fecha del escenario
		String fecha = "10/05/2016";
		// Creación del grupo
				dao.createGrupo("prueba", "prueba", new HashSet<String>());
				dao.createUsuario("usuario_pruebas", "prueba");
				// Creación del escenario de pruebas
				Escenario escenario = dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
						partidos, comentarios, Sistema.DHONDT,
						Circunscripciones.PROVINCIAS, 50, fecha);

		//Comenzamos las pruebas 
		assertEquals(dao.readEscenariosGrupo("prueba").get(0).toString(), escenario.toString());
	}

	@Test
	public void testReadEscenario() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
		//Instancia para la prueba
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		// Lista para provincias
		List<Provincia> provincias = new ArrayList<Provincia>();
		provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
				4658397));
		provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
				3, 118502));
		// Lista para partidos
		List<Partido> partidos = new ArrayList<Partido>();
		partidos.add(new Partido("PP", "Partido Popular",
				"img/logos/pp.png", "02CFF7"));
		partidos.add(new Partido("PSOE", "Partido Socialista",
				"img/logos/psoe.png", "FF0000"));
		partidos.add(new Partido("PODEMOS", "Podemos",
				"img/logos/podemos.png", "742DA1"));
		partidos.add(new Partido("Cs", "Ciudadanos",
				"img/logos/ciudadanos.png", "F7771B"));
		// Lista para votos
		List<Votos> votos = new ArrayList<Votos>();
		votos.add(new Votos("segovia", "PP", 39));
		votos.add(new Votos("segovia", "PSOE", 21));
		votos.add(new Votos("segovia", "Cs", 17));
		votos.add(new Votos("segovia", "PODEMOS", 14));
		votos.add(new Votos("madrid", "PP", 33));
		votos.add(new Votos("madrid", "PODEMOS", 20));
		votos.add(new Votos("madrid", "Cs", 18));
		votos.add(new Votos("madrid", "PSOE", 17));
		//Lista para comentarios
		List<Comentario> comentarios = new ArrayList<Comentario>();
		// Establecemos la fecha del escenario
		String fecha = "10/05/2016";
		// Creación del grupo
		dao.createGrupo("prueba", "prueba", new HashSet<String>());
		dao.createUsuario("usuario_pruebas", "prueba");
		// Creación del escenario de pruebas
		Escenario escenario = dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
				partidos, comentarios, Sistema.DHONDT,
				Circunscripciones.PROVINCIAS, 50, fecha);
			// Buscamos el id del escanario
			Long id = escenario.getId();
		//Comenzamos las pruebas 
		assertEquals(dao.readEscenario(id).toString(), escenario.toString());
	}

	@Test
	public void testExistsEscenario() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
				//Instancia para la prueba
				ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
				// Lista para provincias
				List<Provincia> provincias = new ArrayList<Provincia>();
				provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
						4658397));
				provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
						3, 118502));
				// Lista para partidos
				List<Partido> partidos = new ArrayList<Partido>();
				partidos.add(new Partido("PP", "Partido Popular",
						"img/logos/pp.png", "02CFF7"));
				partidos.add(new Partido("PSOE", "Partido Socialista",
						"img/logos/psoe.png", "FF0000"));
				partidos.add(new Partido("PODEMOS", "Podemos",
						"img/logos/podemos.png", "742DA1"));
				partidos.add(new Partido("Cs", "Ciudadanos",
						"img/logos/ciudadanos.png", "F7771B"));
				// Lista para votos
				List<Votos> votos = new ArrayList<Votos>();
				votos.add(new Votos("segovia", "PP", 39));
				votos.add(new Votos("segovia", "PSOE", 21));
				votos.add(new Votos("segovia", "Cs", 17));
				votos.add(new Votos("segovia", "PODEMOS", 14));
				votos.add(new Votos("madrid", "PP", 33));
				votos.add(new Votos("madrid", "PODEMOS", 20));
				votos.add(new Votos("madrid", "Cs", 18));
				votos.add(new Votos("madrid", "PSOE", 17));
				//Lista para comentarios
				List<Comentario> comentarios = new ArrayList<Comentario>();
				// Establecemos la fecha del escenario
				String fecha = "10/05/2016";
				// Creación del grupo
				dao.createGrupo("prueba", "prueba", new HashSet<String>());
				dao.createUsuario("usuario_pruebas", "prueba");
				// Creación del escenario de pruebas
				dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
						partidos, comentarios, Sistema.DHONDT,
						Circunscripciones.PROVINCIAS, 50, fecha);
			
				//Comenzamos las pruebas 
				assertEquals(dao.existsEscenario("usuario_prueba"), true);
	}

	@Test
	public void testUpdateEscenario() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
		//Instancia para la prueba
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		// Lista para provincias
		List<Provincia> provincias = new ArrayList<Provincia>();
		provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
				4658397));
		provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
				3, 118502));
		// Lista para partidos
		List<Partido> partidos = new ArrayList<Partido>();
		partidos.add(new Partido("PP", "Partido Popular",
				"img/logos/pp.png", "02CFF7"));
		partidos.add(new Partido("PSOE", "Partido Socialista",
				"img/logos/psoe.png", "FF0000"));
		partidos.add(new Partido("PODEMOS", "Podemos",
				"img/logos/podemos.png", "742DA1"));
		partidos.add(new Partido("Cs", "Ciudadanos",
				"img/logos/ciudadanos.png", "F7771B"));
		// Lista para votos
		List<Votos> votos = new ArrayList<Votos>();
		votos.add(new Votos("segovia", "PP", 39));
		votos.add(new Votos("segovia", "PSOE", 21));
		votos.add(new Votos("segovia", "Cs", 17));
		votos.add(new Votos("segovia", "PODEMOS", 14));
		votos.add(new Votos("madrid", "PP", 33));
		votos.add(new Votos("madrid", "PODEMOS", 20));
		votos.add(new Votos("madrid", "Cs", 18));
		votos.add(new Votos("madrid", "PSOE", 17));
		//Lista para comentarios
		List<Comentario> comentarios = new ArrayList<Comentario>();
		// Establecemos la fecha del escenario
		String fecha = "10/05/2016";
		// Creación del grupo
		dao.createGrupo("prueba", "prueba", new HashSet<String>());
		dao.createUsuario("usuario_pruebas", "prueba");
		// Creación del escenario de pruebas
		dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
				partidos, comentarios, Sistema.DHONDT,
				Circunscripciones.PROVINCIAS, 50, fecha);
		Escenario escenario = dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
				partidos, comentarios, Sistema.DHONDT,
				Circunscripciones.PROVINCIAS, 80, fecha);
		dao.updateEscenario(escenario);
		assertEquals(escenario.getMayoria_abs(), 80);
	}

	@Test
	public void testDeleteEscenario() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
		//Instancia para la prueba
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		// Lista para provincias
		List<Provincia> provincias = new ArrayList<Provincia>();
		provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
				4658397));
		provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
				3, 118502));
		// Lista para partidos
		List<Partido> partidos = new ArrayList<Partido>();
		partidos.add(new Partido("PP", "Partido Popular",
				"img/logos/pp.png", "02CFF7"));
		partidos.add(new Partido("PSOE", "Partido Socialista",
				"img/logos/psoe.png", "FF0000"));
		partidos.add(new Partido("PODEMOS", "Podemos",
				"img/logos/podemos.png", "742DA1"));
		partidos.add(new Partido("Cs", "Ciudadanos",
				"img/logos/ciudadanos.png", "F7771B"));
		// Lista para votos
		List<Votos> votos = new ArrayList<Votos>();
		votos.add(new Votos("segovia", "PP", 39));
		votos.add(new Votos("segovia", "PSOE", 21));
		votos.add(new Votos("segovia", "Cs", 17));
		votos.add(new Votos("segovia", "PODEMOS", 14));
		votos.add(new Votos("madrid", "PP", 33));
		votos.add(new Votos("madrid", "PODEMOS", 20));
		votos.add(new Votos("madrid", "Cs", 18));
		votos.add(new Votos("madrid", "PSOE", 17));
		//Lista para comentarios
		List<Comentario> comentarios = new ArrayList<Comentario>();
		// Establecemos la fecha del escenario
		String fecha = "10/05/2016";
		// Creación del grupo
		dao.createGrupo("prueba", "prueba", new HashSet<String>());
		dao.createUsuario("usuario_pruebas", "prueba");
		// Creación del escenario de pruebas
		Escenario escenario = dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
				partidos, comentarios, Sistema.DHONDT,
				Circunscripciones.PROVINCIAS, 50, fecha);
		// id del escenario
		Long id = escenario.getId();
		// Borramos el escenario
		dao.deleteEscenario(id);
		assertEquals(dao.readEscenario(id), null);
	}

	@Test
	public void testCreateComentario() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
		//Instancia para la prueba
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		// Lista para provincias
		List<Provincia> provincias = new ArrayList<Provincia>();
		provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
				4658397));
		provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
				3, 118502));
		// Lista para partidos
		List<Partido> partidos = new ArrayList<Partido>();
		partidos.add(new Partido("PP", "Partido Popular",
				"img/logos/pp.png", "02CFF7"));
		partidos.add(new Partido("PSOE", "Partido Socialista",
				"img/logos/psoe.png", "FF0000"));
		partidos.add(new Partido("PODEMOS", "Podemos",
				"img/logos/podemos.png", "742DA1"));
		partidos.add(new Partido("Cs", "Ciudadanos",
				"img/logos/ciudadanos.png", "F7771B"));
		// Lista para votos
		List<Votos> votos = new ArrayList<Votos>();
		votos.add(new Votos("segovia", "PP", 39));
		votos.add(new Votos("segovia", "PSOE", 21));
		votos.add(new Votos("segovia", "Cs", 17));
		votos.add(new Votos("segovia", "PODEMOS", 14));
		votos.add(new Votos("madrid", "PP", 33));
		votos.add(new Votos("madrid", "PODEMOS", 20));
		votos.add(new Votos("madrid", "Cs", 18));
		votos.add(new Votos("madrid", "PSOE", 17));
		//Lista para comentarios
		List<Comentario> comentarios = new ArrayList<Comentario>();
		// Establecemos la fecha del escenario
		String fecha = "10/05/2016";
		// Creación del grupo
		dao.createGrupo("prueba", "prueba", new HashSet<String>());
		dao.createUsuario("usuario_pruebas", "prueba");
		// Creación del escenario de pruebas
		Escenario escenario = dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
				partidos, comentarios, Sistema.DHONDT,
				Circunscripciones.PROVINCIAS, 50, fecha);
		// id del escenario
				Long id = escenario.getId();
		// comentario
				Comentario comentario_prueba = new Comentario("usuario_prueba", fecha, "Esto es un comentario de prueba");
		//Creación de un comentario
		escenario = dao.createComentario(id, comentario_prueba);
	
		//Comenzamos las pruebas 
		assertEquals(escenario.getComentarios().get(0).toString(), comentario_prueba.toString());
	}

	@Test
	public void testCreateGrupo() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
		//Instancia para la prueba
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		// Lista para provincias
		List<Provincia> provincias = new ArrayList<Provincia>();
		provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
				4658397));
		provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
				3, 118502));
		// Lista para partidos
		List<Partido> partidos = new ArrayList<Partido>();
		partidos.add(new Partido("PP", "Partido Popular",
				"img/logos/pp.png", "02CFF7"));
		partidos.add(new Partido("PSOE", "Partido Socialista",
				"img/logos/psoe.png", "FF0000"));
		partidos.add(new Partido("PODEMOS", "Podemos",
				"img/logos/podemos.png", "742DA1"));
		partidos.add(new Partido("Cs", "Ciudadanos",
				"img/logos/ciudadanos.png", "F7771B"));
		// Lista para votos
		List<Votos> votos = new ArrayList<Votos>();
		votos.add(new Votos("segovia", "PP", 39));
		votos.add(new Votos("segovia", "PSOE", 21));
		votos.add(new Votos("segovia", "Cs", 17));
		votos.add(new Votos("segovia", "PODEMOS", 14));
		votos.add(new Votos("madrid", "PP", 33));
		votos.add(new Votos("madrid", "PODEMOS", 20));
		votos.add(new Votos("madrid", "Cs", 18));
		votos.add(new Votos("madrid", "PSOE", 17));
		//Lista para comentarios
		List<Comentario> comentarios = new ArrayList<Comentario>();
		// Establecemos la fecha del escenario
		String fecha = "10/05/2016";
		// Creación del grupo
		dao.createGrupo("prueba", "prueba", new HashSet<String>());
		dao.createUsuario("usuario_pruebas", "prueba");
		// Creación del escenario de pruebas
		dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
				partidos, comentarios, Sistema.DHONDT,
				Circunscripciones.PROVINCIAS, 50, fecha);
		//Comenzamos las pruebas 
		assertEquals(dao.existsGrupo("prueba"), true);
	}

	@Test
	public void testReadGrupo() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
		//Instancia para la prueba
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		// Lista para provincias
		List<Provincia> provincias = new ArrayList<Provincia>();
		provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
				4658397));
		provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
				3, 118502));
		// Lista para partidos
		List<Partido> partidos = new ArrayList<Partido>();
		partidos.add(new Partido("PP", "Partido Popular",
				"img/logos/pp.png", "02CFF7"));
		partidos.add(new Partido("PSOE", "Partido Socialista",
				"img/logos/psoe.png", "FF0000"));
		partidos.add(new Partido("PODEMOS", "Podemos",
				"img/logos/podemos.png", "742DA1"));
		partidos.add(new Partido("Cs", "Ciudadanos",
				"img/logos/ciudadanos.png", "F7771B"));
		// Lista para votos
		List<Votos> votos = new ArrayList<Votos>();
		votos.add(new Votos("segovia", "PP", 39));
		votos.add(new Votos("segovia", "PSOE", 21));
		votos.add(new Votos("segovia", "Cs", 17));
		votos.add(new Votos("segovia", "PODEMOS", 14));
		votos.add(new Votos("madrid", "PP", 33));
		votos.add(new Votos("madrid", "PODEMOS", 20));
		votos.add(new Votos("madrid", "Cs", 18));
		votos.add(new Votos("madrid", "PSOE", 17));
		//Lista para comentarios
		List<Comentario> comentarios = new ArrayList<Comentario>();
		// Establecemos la fecha del escenario
		String fecha = "10/05/2016";
		// Creación del grupo
		Grupo grupo_prueba = dao.createGrupo("prueba", "prueba", new HashSet<String>());
		dao.createUsuario("usuario_pruebas", "prueba");
		// Creación del escenario de pruebas
		dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
				partidos, comentarios, Sistema.DHONDT,
				Circunscripciones.PROVINCIAS, 50, fecha);
		//Comenzamos las pruebas 
		assertEquals(dao.readGrupo("prueba").toString(), grupo_prueba.toString());
	}

	@Test
	public void testReadGrupos() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
		//Instancia para la prueba
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		// Lista para provincias
		List<Provincia> provincias = new ArrayList<Provincia>();
		provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
				4658397));
		provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
				3, 118502));
		// Lista para partidos
		List<Partido> partidos = new ArrayList<Partido>();
		partidos.add(new Partido("PP", "Partido Popular",
				"img/logos/pp.png", "02CFF7"));
		partidos.add(new Partido("PSOE", "Partido Socialista",
				"img/logos/psoe.png", "FF0000"));
		partidos.add(new Partido("PODEMOS", "Podemos",
				"img/logos/podemos.png", "742DA1"));
		partidos.add(new Partido("Cs", "Ciudadanos",
				"img/logos/ciudadanos.png", "F7771B"));
		// Lista para votos
		List<Votos> votos = new ArrayList<Votos>();
		votos.add(new Votos("segovia", "PP", 39));
		votos.add(new Votos("segovia", "PSOE", 21));
		votos.add(new Votos("segovia", "Cs", 17));
		votos.add(new Votos("segovia", "PODEMOS", 14));
		votos.add(new Votos("madrid", "PP", 33));
		votos.add(new Votos("madrid", "PODEMOS", 20));
		votos.add(new Votos("madrid", "Cs", 18));
		votos.add(new Votos("madrid", "PSOE", 17));
		//Lista para comentarios
		List<Comentario> comentarios = new ArrayList<Comentario>();
		// Establecemos la fecha del escenario
		String fecha = "10/05/2016";
		// Creación del grupo
		Grupo grupo_prueba = dao.createGrupo("prueba", "prueba", new HashSet<String>());
		dao.createUsuario("usuario_pruebas", "prueba");
		// Creación del escenario de pruebas
		dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
				partidos, comentarios, Sistema.DHONDT,
				Circunscripciones.PROVINCIAS, 50, fecha);
		//Comenzamos las pruebas 
		assertEquals(dao.readGrupos().get(0).toString(), grupo_prueba.toString());
	}

	@Test
	public void testExistsGrupo() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
		//Instancia para la prueba
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		// Lista para provincias
		List<Provincia> provincias = new ArrayList<Provincia>();
		provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
				4658397));
		provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
				3, 118502));
		// Lista para partidos
		List<Partido> partidos = new ArrayList<Partido>();
		partidos.add(new Partido("PP", "Partido Popular",
				"img/logos/pp.png", "02CFF7"));
		partidos.add(new Partido("PSOE", "Partido Socialista",
				"img/logos/psoe.png", "FF0000"));
		partidos.add(new Partido("PODEMOS", "Podemos",
				"img/logos/podemos.png", "742DA1"));
		partidos.add(new Partido("Cs", "Ciudadanos",
				"img/logos/ciudadanos.png", "F7771B"));
		// Lista para votos
		List<Votos> votos = new ArrayList<Votos>();
		votos.add(new Votos("segovia", "PP", 39));
		votos.add(new Votos("segovia", "PSOE", 21));
		votos.add(new Votos("segovia", "Cs", 17));
		votos.add(new Votos("segovia", "PODEMOS", 14));
		votos.add(new Votos("madrid", "PP", 33));
		votos.add(new Votos("madrid", "PODEMOS", 20));
		votos.add(new Votos("madrid", "Cs", 18));
		votos.add(new Votos("madrid", "PSOE", 17));
		//Lista para comentarios
		List<Comentario> comentarios = new ArrayList<Comentario>();
		// Establecemos la fecha del escenario
		String fecha = "10/05/2016";
		// Creación del grupo
		dao.createGrupo("prueba", "prueba", new HashSet<String>());
		dao.createUsuario("usuario_pruebas", "prueba");
		// Creación del escenario de pruebas
		dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
				partidos, comentarios, Sistema.DHONDT,
				Circunscripciones.PROVINCIAS, 50, fecha);
		//Comenzamos las pruebas 
		assertEquals(dao.existsGrupo("prueba"), true);
		assertEquals(dao.existsGrupo("pruebaq"), false);
		
	}

	@Test
	public void testUpdateGrupo() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
		//Instancia para la prueba
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		// Lista para provincias
		List<Provincia> provincias = new ArrayList<Provincia>();
		provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
				4658397));
		provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
				3, 118502));
		// Lista para partidos
		List<Partido> partidos = new ArrayList<Partido>();
		partidos.add(new Partido("PP", "Partido Popular",
				"img/logos/pp.png", "02CFF7"));
		partidos.add(new Partido("PSOE", "Partido Socialista",
				"img/logos/psoe.png", "FF0000"));
		partidos.add(new Partido("PODEMOS", "Podemos",
				"img/logos/podemos.png", "742DA1"));
		partidos.add(new Partido("Cs", "Ciudadanos",
				"img/logos/ciudadanos.png", "F7771B"));
		// Lista para votos
		List<Votos> votos = new ArrayList<Votos>();
		votos.add(new Votos("segovia", "PP", 39));
		votos.add(new Votos("segovia", "PSOE", 21));
		votos.add(new Votos("segovia", "Cs", 17));
		votos.add(new Votos("segovia", "PODEMOS", 14));
		votos.add(new Votos("madrid", "PP", 33));
		votos.add(new Votos("madrid", "PODEMOS", 20));
		votos.add(new Votos("madrid", "Cs", 18));
		votos.add(new Votos("madrid", "PSOE", 17));
		//Lista para comentarios
		List<Comentario> comentarios = new ArrayList<Comentario>();
		// Establecemos la fecha del escenario
		String fecha = "10/05/2016";
		// Creación del grupo
		Grupo grupo_pruebas = dao.createGrupo("prueba", "prueba", new HashSet<String>());
		dao.createUsuario("usuario_pruebas", "prueba");
		// Creación del escenario de pruebas
		dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
				partidos, comentarios, Sistema.DHONDT,
				Circunscripciones.PROVINCIAS, 50, fecha);
		Grupo grupo = new Grupo("prueba", "prueba1", new HashSet<String>());
		dao.updateGrupo(grupo);
		//Comenzamos las pruebas 
		assertEquals(grupo_pruebas.getPassword(), "prueba1");
	}

	@Test
	public void testDeleteGrupo() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
				//Instancia para la prueba
				ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
				// Lista para provincias
				List<Provincia> provincias = new ArrayList<Provincia>();
				provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
						4658397));
				provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
						3, 118502));
				// Lista para partidos
				List<Partido> partidos = new ArrayList<Partido>();
				partidos.add(new Partido("PP", "Partido Popular",
						"img/logos/pp.png", "02CFF7"));
				partidos.add(new Partido("PSOE", "Partido Socialista",
						"img/logos/psoe.png", "FF0000"));
				partidos.add(new Partido("PODEMOS", "Podemos",
						"img/logos/podemos.png", "742DA1"));
				partidos.add(new Partido("Cs", "Ciudadanos",
						"img/logos/ciudadanos.png", "F7771B"));
				// Lista para votos
				List<Votos> votos = new ArrayList<Votos>();
				votos.add(new Votos("segovia", "PP", 39));
				votos.add(new Votos("segovia", "PSOE", 21));
				votos.add(new Votos("segovia", "Cs", 17));
				votos.add(new Votos("segovia", "PODEMOS", 14));
				votos.add(new Votos("madrid", "PP", 33));
				votos.add(new Votos("madrid", "PODEMOS", 20));
				votos.add(new Votos("madrid", "Cs", 18));
				votos.add(new Votos("madrid", "PSOE", 17));
				//Lista para comentarios
				List<Comentario> comentarios = new ArrayList<Comentario>();
				// Establecemos la fecha del escenario
				String fecha = "10/05/2016";
				// Creación del grupo
				dao.createGrupo("prueba", "prueba", new HashSet<String>());
				dao.createUsuario("usuario_pruebas", "prueba");
				// Creación del escenario de pruebas
				dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
						partidos, comentarios, Sistema.DHONDT,
						Circunscripciones.PROVINCIAS, 50, fecha);
				// Borramos el grupo
				dao.deleteGrupo("prueba");;
				//Comenzamos las pruebas 
				assertEquals(dao.existsGrupo("prueba"), false);
	}

	@Test
	public void testFindGroup() {
		// Creacción de un escenario base para realizar el conjunto de las purebas
				//Instancia para la prueba
				ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
				// Lista para provincias
				List<Provincia> provincias = new ArrayList<Provincia>();
				provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
						4658397));
				provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
						3, 118502));
				// Lista para partidos
				List<Partido> partidos = new ArrayList<Partido>();
				partidos.add(new Partido("PP", "Partido Popular",
						"img/logos/pp.png", "02CFF7"));
				partidos.add(new Partido("PSOE", "Partido Socialista",
						"img/logos/psoe.png", "FF0000"));
				partidos.add(new Partido("PODEMOS", "Podemos",
						"img/logos/podemos.png", "742DA1"));
				partidos.add(new Partido("Cs", "Ciudadanos",
						"img/logos/ciudadanos.png", "F7771B"));
				// Lista para votos
				List<Votos> votos = new ArrayList<Votos>();
				votos.add(new Votos("segovia", "PP", 39));
				votos.add(new Votos("segovia", "PSOE", 21));
				votos.add(new Votos("segovia", "Cs", 17));
				votos.add(new Votos("segovia", "PODEMOS", 14));
				votos.add(new Votos("madrid", "PP", 33));
				votos.add(new Votos("madrid", "PODEMOS", 20));
				votos.add(new Votos("madrid", "Cs", 18));
				votos.add(new Votos("madrid", "PSOE", 17));
				//Lista para comentarios
				List<Comentario> comentarios = new ArrayList<Comentario>();
				// Establecemos la fecha del escenario
				String fecha = "10/05/2016";
				// Creación del grupo
				Grupo grupo_prueba = dao.createGrupo("prueba", "prueba", new HashSet<String>());
				dao.createUsuario("usuario_pruebas", "prueba");
				// Creación del escenario de pruebas
				dao.createEscenario("usuario_pruebas", "Escenario_prueba", votos, provincias,
						partidos, comentarios, Sistema.DHONDT,
						Circunscripciones.PROVINCIAS, 50, fecha);
				//Comenzamos las pruebas 
				assertEquals(dao.findGroup("usuario_prueba").toString(), grupo_prueba.toString());
	}

	@Test
	public void testCreateUsuario() {
		//Instancia para la prueba
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		// Creación del grupo
		dao.createGrupo("prueba", "prueba", new HashSet<String>());
		dao.createUsuario("usuario_pruebas", "prueba");
		assertEquals(dao.existsUsuario("usuario_prueba"), true);
	}

	@Test
	public void testExistsUsuario() {
		//Instancia para la prueba
				ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
				// Creación del grupo
				dao.createGrupo("prueba", "prueba", new HashSet<String>());
				dao.createUsuario("usuario_pruebas", "prueba");
				assertEquals(dao.existsUsuario("usuario_prueba"), true);
				assertEquals(dao.existsUsuario("usuario_prueba2"), false);
				
	}

	@Test
	public void testDeleteUsuario() {
		//Instancia para la prueba
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		// Creación del grupo
		dao.createGrupo("prueba", "prueba", new HashSet<String>());
		dao.createUsuario("usuario_pruebas", "prueba");
		dao.createUsuario("usuario_pruebas2", "prueba");
		dao.deleteUsuario("usuario_pruebas2");
		assertEquals(dao.existsUsuario("usuario_prueba"), true);
		assertEquals(dao.existsUsuario("usuario_prueba2"), false);
	}

}

