package es.upm.dit.isst.electolab;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServlet;

import es.upm.dit.isst.electolab.dao.ElectoLabDAO;
import es.upm.dit.isst.electolab.dao.ElectoLabDAOImpl;
import es.upm.dit.isst.electolab.model.Circunscripciones;
import es.upm.dit.isst.electolab.model.Comentario;
import es.upm.dit.isst.electolab.model.Partido;
import es.upm.dit.isst.electolab.model.Provincia;
import es.upm.dit.isst.electolab.model.Sistema;
import es.upm.dit.isst.electolab.model.Votos;

public class InitServlet extends HttpServlet {
	@Override
	public void init() throws javax.servlet.ServletException {

		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		if (!dao.existsGrupo("prueba")) {
			dao.createGrupo("prueba", "prueba", new HashSet<String>());
		}
		if (!dao.existsGrupo("prueba2")) {
			dao.createGrupo("prueba2", "prueba", new HashSet<String>());
		}
		if (!dao.existsUsuario("admin")) {
			dao.createUsuario("admin", "prueba");
		}

		String[] provincias_esc0 = { "alava", "albacete", "alicante",
				"almeria", "asturias", "avila", "badajoz", "barcelona",
				"burgos", "caceres", "cadiz", "cantabria", "castellon",
				"ceuta", "ciudadreal", "cordoba", "cuenca", "gerona",
				"granada", "guadalajara", "guipuzcoa", "huelva", "huesca",
				"baleares", "jaen", "coruña", "rioja", "palmas", "leon",
				"lerida", "lugo", "madrid", "malaga", "melilla", "murcia",
				"navarra", "orense", "palencia", "pontevedra", "tenerife",
				"salamanca", "segovia", "sevilla", "soria", "tarragona",
				"teruel", "toledo", "valencia", "valladolid", "vizcaya",
				"zamora", "zaragoza" };

		String[] partidos_esc0 = { "PP", "PSOE", "PODEMOS", "Cs", "EN COMÚ",
				"COMPROMÍS", "ERC-CATSI", "DL", "En Marea-ANOVA-EU", "IU-UPeC",
				"PNV", "CCa-PNC", "PACMA", "UPYD", "LOS VERDES", "VOX", "EH",
				"PUM+J", "PCPE", "NÓS", "unio.cat", "CHUNTA ARAGONESIST",
				"MÉS", "EL PI", "GBAI" };

		if (!dao.existsEscenario("admin")) {
			List<Votos> votos = new ArrayList<Votos>();

			List<Provincia> provincias = new ArrayList<Provincia>();
			provincias.add(new Provincia("Álava", "alava", "PAÍSVASCO", 4,
					248456));
			provincias.add(new Provincia("Albacete", "albacete",
					"CASTILLALAMANCHA", 4, 304089));
			provincias.add(new Provincia("Alicante", "alicante", "VALENCIA",
					12, 1220005));
			provincias.add(new Provincia("Almería", "almeria", "ANDALUCÍA", 6,
					452589));
			provincias.add(new Provincia("Asturias", "asturias", "ASTURIAS", 8,
					876171));
			provincias.add(new Provincia("Ávila", "avila", "CASTILLAYLEÓN", 3,
					132575));
			provincias.add(new Provincia("Badajoz", "badajoz", "EXTREMADURA",
					6, 548707));
			provincias.add(new Provincia("Barcelona", "barcelona", "CATALUÑA",
					31, 3974408));
			provincias.add(new Provincia("Burgos", "burgos", "CASTILLAYLEÓN",
					4, 284916));
			provincias.add(new Provincia("Cáceres", "caceres", "EXTREMADURA",
					4, 335845));
			provincias.add(new Provincia("Cádiz", "cadiz", "ANDALUCÍA", 9,
					968097));
			provincias.add(new Provincia("Cantabria", "cantabria", "CANTABRIA",
					5, 464081));
			provincias.add(new Provincia("Castellón", "castellon", "VALENCIA",
					5, 409474));
			provincias.add(new Provincia("Ceuta", "ceuta", "CEUTA", 1, 59213));
			provincias.add(new Provincia("Ciudad Real", "ciudadreal",
					"CASTILLALAMANCHA", 5, 396293));
			provincias.add(new Provincia("Córdoba", "cordoba", "ANDALUCÍA", 6,
					635086));
			provincias.add(new Provincia("Cuenca", "cuenca",
					"CASTILLALAMANCHA", 3, 156088));
			provincias.add(new Provincia("Gerona", "gerona", "CATALUÑA", 6,
					496127));
			provincias.add(new Provincia("Granada", "granada", "ANDALUCÍA", 7,
					702887));
			provincias.add(new Provincia("Guadalajara", "guadalajara",
					"CASTILLALAMANCHA", 3, 178749));
			provincias.add(new Provincia("Guipúzcoa", "guipuzcoa", "PAÍSVASCO",
					6, 555417));
			provincias.add(new Provincia("Huelva", "huelva", "ANDALUCÍA", 5,
					389811));
			provincias.add(new Provincia("Huesca", "huesca", "ARAGÓN", 3,
					167331));
			provincias.add(new Provincia("Islas Baleares", "baleares",
					"BALEARES", 8, 748577));
			provincias
					.add(new Provincia("Jaén", "jaen", "ANDALUCÍA", 5, 522173));
			provincias.add(new Provincia("La Coruña", "coruña", "GALICIA", 8,
					936602));
			provincias.add(new Provincia("La Rioja", "rioja", "LARIOJA", 4,
					233087));
			provincias.add(new Provincia("Las Palmas", "palmas", "CANARIAS", 8,
					798145));
			provincias.add(new Provincia("León", "leon", "CASTILLAYLEÓN", 5,
					398416));
			provincias.add(new Provincia("Lérida", "lerida", "CATALUÑA", 4,
					299069));
			provincias.add(new Provincia("Lugo", "lugo", "GALICIA", 4, 286315));
			provincias.add(new Provincia("Madrid", "madrid", "MADRID", 36,
					4658397));
			provincias.add(new Provincia("Málaga", "malaga", "ANDALUCÍA", 11,
					1113952));
			provincias.add(new Provincia("Melilla", "melilla", "MELILLA", 1,
					53257));
			provincias.add(new Provincia("Murcia", "murcia", "MURCIA", 10,
					1003799));
			provincias.add(new Provincia("Navarra", "navarra", "NAVARRA", 5,
					478330));
			provincias.add(new Provincia("Orense", "orense", "GALICIA", 4,
					267704));
			provincias.add(new Provincia("Palencia", "palencia",
					"CASTILLAYLEÓN", 3, 137517));
			provincias.add(new Provincia("Pontevedra", "pontevedra", "GALICIA",
					7, 774937));
			provincias.add(new Provincia("Santa Cruz de Tenerife", "tenerife",
					"CANARIAS", 7, 280762));
			provincias.add(new Provincia("Salamanca", "salamanca",
					"CASTILLAYLEÓN", 4, 733362));
			provincias.add(new Provincia("Segovia", "segovia", "CASTILLAYLEÓN",
					3, 118502));
			provincias.add(new Provincia("Sevilla", "sevilla", "ANDALUCÍA", 12,
					1501271));
			provincias.add(new Provincia("Soria", "soria", "CASTILLAYLEÓN", 2,
					70718));
			provincias.add(new Provincia("Tarragona", "tarragona", "CATALUÑA",
					6, 547971));
			provincias.add(new Provincia("Teruel", "teruel", "ARAGÓN", 3,
					105453));
			provincias.add(new Provincia("Toledo", "toledo",
					"CASTILLALAMANCHA", 6, 509333));
			provincias.add(new Provincia("Valencia", "valencia", "VALENCIA",
					15, 1893225));
			provincias.add(new Provincia("Valladolid", "valladolid",
					"CASTILLAYLEÓN", 5, 421369));
			provincias.add(new Provincia("Vizcaya", "vizcaya", "PAÍSVASCO", 8,
					913244));
			provincias.add(new Provincia("Zamora", "zamora", "CASTILLAYLEÓN",
					3, 155512));
			provincias.add(new Provincia("Zaragoza", "zaragoza", "ARAGÓN", 7,
					714370));

			List<Partido> partidos = new ArrayList<Partido>();
			partidos.add(new Partido("PP", "Partido Popular",
					"img/logos/pp.png", "02CFF7"));
			partidos.add(new Partido("PSOE", "Partido Socialista",
					"img/logos/psoe.png", "FF0000"));
			partidos.add(new Partido("PODEMOS", "Podemos",
					"img/logos/podemos.png", "742DA1"));
			partidos.add(new Partido("Cs", "Ciudadanos",
					"img/logos/ciudadanos.png", "F7771B"));
			partidos.add(new Partido("EN COMÚ", "En comú Podem",
					"img/logos/podemosComun.png", "998EEF"));
			partidos.add(new Partido("COMPROMÍS",
					"Compromís-Podemos-És el moment",
					"img/logos/podemosCompromis.png", "4E05D3"));
			partidos.add(new Partido("ERC-CATSI",
					"Esquerra Republicana de Catalunya-Catalunya Sí",
					"img/logos/erc.png", "FFCC00"));
			partidos.add(new Partido("DL", "Democràcia i Llibertat",
					"img/logos/dl.png", "0F178A"));
			partidos.add(new Partido("En Marea-ANOVA-EU",
					"Podemos - En Marea - ANOVA - EU",
					"img/logos/podemosMarea.png", "C920DD"));
			partidos.add(new Partido("IU-UPeC",
					"Unidad Popular: Izquierda Unida, Unidad Popular en Común",
					"img/logos/iut.png", "10812D"));
			partidos.add(new Partido("PNV", "Partido Nacionalista Vasco",
					"img/logos/pnv.jpg", "54A106"));
			partidos.add(new Partido("CCa-PNC",
					"Coalición Canaria-Partido Nacionalista Canario",
					"img/logos/cca.png", "DAE705"));
			partidos.add(new Partido("PACMA",
					"Partido Animalista Contra el Maltrato Animal",
					"img/logos/pacma.jpg", "A4BB0F"));
			partidos.add(new Partido("UPYD",
					"Unión Progreso y Democracia",
					"img/logos/upyd.jpg", "E5007E"));
			partidos.add(new Partido("LOS VERDES",
					"Recortes Cero - Los Verdes",
					"img/logos/losverdes.png", "00862D"));
			partidos.add(new Partido("VOX",
					"Vox",
					"img/logos/vox.png", "5AC035"));
			partidos.add(new Partido("EH",
					"EH Bildu",
					"img/logos/eh.jpg", "A0DF1F"));
			partidos.add(new Partido("PUM+J",
					"Por Un Mundo Más Justo",
					"img/logos/pum.png", "4BBCED"));
			partidos.add(new Partido("PCPE",
					"Partido Comunista de los Pueblos Españoles",
					"img/logos/pcpe.jpg", "9D0400"));
			partidos.add(new Partido("NÓS",
					"Nós-Unidade Popular",
					"img/logos/nos.jpg", "BBCC4A"));
			partidos.add(new Partido("unio.cat",
					"Unió Democràtica de Catalunya",
					"img/logos/unio.jpg", "1F53A8"));
			partidos.add(new Partido("CHUNTA ARAGONESIST",
					"Chunta Aragonesista",
					"img/logos/cha.png", "AC0018"));
			partidos.add(new Partido("MÉS",
					"Més per Mallorca",
					"img/logos/mes.jpg", "CCD200"));
			partidos.add(new Partido("EL PI",
					"El Pi-Proposta per les Illes",
					"img/logos/elpi.jpg", "590074"));
			partidos.add(new Partido("GBAI",
					"Geroa Bai",
					"img/logos/gbai.jpg", "D32E12"));

			List<Comentario> comentarios = new ArrayList<Comentario>();

			String titulo = "Escenario inicial";

			SimpleDateFormat formateador = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss", new Locale("es_ES"));
			Date fechaDate = new Date();
			String fecha = formateador.format(fechaDate);

			dao.createEscenario("admin", titulo, votos, provincias, partidos,
					comentarios, Sistema.DHONDT, Circunscripciones.PROVINCIAS,
					50, fecha);

			// ______________________________________________________________
			// Escenario 2015

			List<Provincia> provincias2015 = new ArrayList<Provincia>();
			List<Partido> partidos2015 = new ArrayList<Partido>();
			List<Votos> votos2015 = new ArrayList<Votos>();
			List<Comentario> comentarios2015 = new ArrayList<Comentario>();
			String titulo2015 = "Escenario 2015";
			SimpleDateFormat formateador2015 = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss", new Locale("es_ES"));
			Date fechaDate2015 = new Date();
			String fecha2015 = formateador.format(fechaDate);

			for (Provincia p : provincias)
				provincias2015.add(new Provincia(p.getNombre(), p.getId(), p
						.getComunidad(), p.getEscanos(), p.getElectores()));

			for (Partido p : partidos)
				partidos2015.add(new Partido(p.getSiglas(), p.getNombre(), p
						.getImagen(), p.getColor()));

			votos2015.add(new Votos("alava", "PODEMOS", 27));
			votos2015.add(new Votos("alava", "PP", 19));
			votos2015.add(new Votos("alava", "PNV", 16));
			votos2015.add(new Votos("alava", "PSOE", 14));
			votos2015.add(new Votos("alava", "EH", 12));
			votos2015.add(new Votos("alava", "Cs", 6));
			votos2015.add(new Votos("alava", "IU-UPeC", 4));
			votos2015.add(new Votos("alava", "PACMA", 1));
			votos2015.add(new Votos("alava", "UPYD", 0));
			votos2015.add(new Votos("alava", "LOS VERDES", 0));
			votos2015.add(new Votos("alava", "PCPE", 0));
			votos2015.add(new Votos("badajoz", "PSOE", 37));
			votos2015.add(new Votos("badajoz", "PP", 34));
			votos2015.add(new Votos("badajoz", "PODEMOS", 12));
			votos2015.add(new Votos("badajoz", "Cs", 11));
			votos2015.add(new Votos("badajoz", "IU-UPeC", 3));
			votos2015.add(new Votos("badajoz", "PACMA", 1));
			votos2015.add(new Votos("badajoz", "UPYD", 0));
			votos2015.add(new Votos("badajoz", "LOS VERDES", 0));
			votos2015.add(new Votos("castellon", "PP", 31));
			votos2015.add(new Votos("castellon", "COMPROMÍS", 24));
			votos2015.add(new Votos("castellon", "PSOE", 21));
			votos2015.add(new Votos("castellon", "Cs", 15));
			votos2015.add(new Votos("castellon", "IU-UPec", 3));
			votos2015.add(new Votos("castellon", "PACMA", 0));
			votos2015.add(new Votos("castellon", "UPYD", 0));
			votos2015.add(new Votos("castellon", "VOX", 0));
			votos2015.add(new Votos("castellon", "PCPE", 0));
			votos2015.add(new Votos("castellon", "LOS VERDES", 0));
			votos2015.add(new Votos("gerona", "DL", 25));
			votos2015.add(new Votos("gerona", "ERC-CATSI", 23));
			votos2015.add(new Votos("gerona", "EN COMÚ", 16));
			votos2015.add(new Votos("gerona", "PSOE", 12));
			votos2015.add(new Votos("gerona", "Cs", 9));
			votos2015.add(new Votos("gerona", "PP", 8));
			votos2015.add(new Votos("gerona", "unio.cat", 1));
			votos2015.add(new Votos("gerona", "PACMA", 1));
			votos2015.add(new Votos("gerona", "LOS VERDES", 0));
			votos2015.add(new Votos("gerona", "PCPE", 0));
			votos2015.add(new Votos("gerona", "UPYD", 0));
			votos2015.add(new Votos("huesca", "PP", 32));
			votos2015.add(new Votos("huesca", "PSOE", 24));
			votos2015.add(new Votos("huesca", "PODEMOS", 17));
			votos2015.add(new Votos("huesca", "Cs", 16));
			votos2015.add(new Votos("huesca", "CHUNTA ARAGONESIST", 5));
			votos2015.add(new Votos("huesca", "UPYD", 0));
			votos2015.add(new Votos("huesca", "PACMA", 0));
			votos2015.add(new Votos("huesca", "PCPE", 0));
			votos2015.add(new Votos("huesca", "LOS VERDES", 0));
			votos2015.add(new Votos("malaga", "PP", 28));
			votos2015.add(new Votos("malaga", "PSOE", 26));
			votos2015.add(new Votos("malaga", "PODEMOS", 17));
			votos2015.add(new Votos("malaga", "Cs", 17));
			votos2015.add(new Votos("malaga", "IU-UPeC", 6));
			votos2015.add(new Votos("malaga", "PACMA", 1));
			votos2015.add(new Votos("malaga", "UPYD", 0));
			votos2015.add(new Votos("malaga", "VOX", 0));
			votos2015.add(new Votos("malaga", "PCPE", 0));
			votos2015.add(new Votos("malaga", "LOS VERDES", 0));
			votos2015.add(new Votos("salamanca", "PP", 42));
			votos2015.add(new Votos("salamanca", "PSOE", 21));
			votos2015.add(new Votos("salamanca", "Cs", 16));
			votos2015.add(new Votos("salamanca", "PODEMOS", 12));
			votos2015.add(new Votos("salamanca", "IU-UPeC", 3));
			votos2015.add(new Votos("salamanca", "UPYD", 0));
			votos2015.add(new Votos("salamanca", "PACMA", 0));
			votos2015.add(new Votos("salamanca", "VOX", 0));
			votos2015.add(new Votos("salamanca", "LOS VERDES", 0));
			votos2015.add(new Votos("salamanca", "PCPE", 0));
			votos2015.add(new Votos("tarragona", "EN COMÚ", 20));
			votos2015.add(new Votos("tarragona", "ERC-CATSI", 17));
			votos2015.add(new Votos("tarragona", "PSOE", 15));
			votos2015.add(new Votos("tarragona", "DL", 15));
			votos2015.add(new Votos("tarragona", "Cs", 14));
			votos2015.add(new Votos("tarragona", "PP", 12));
			votos2015.add(new Votos("tarragona", "unio.cat", 1));
			votos2015.add(new Votos("tarragona", "PACMA", 1));
			votos2015.add(new Votos("tarragona", "LOS VERDES", 0));
			votos2015.add(new Votos("tarragona", "PCPE", 0));
			votos2015.add(new Votos("tarragona", "UPYD", 0));
			votos2015.add(new Votos("vizcaya", "PNV", 27));
			votos2015.add(new Votos("vizcaya", "PODEMOS", 26));
			votos2015.add(new Votos("vizcaya", "PSOE", 12));
			votos2015.add(new Votos("vizcaya", "EH", 12));
			votos2015.add(new Votos("vizcaya", "PP", 11));
			votos2015.add(new Votos("vizcaya", "Cs", 3));
			votos2015.add(new Votos("vizcaya", "IU-UpeC", 2));
			votos2015.add(new Votos("vizcaya", "PACMA", 0));
			votos2015.add(new Votos("vizcaya", "UPYD", 0));
			votos2015.add(new Votos("vizcaya", "LOS VERDES", 0));
			votos2015.add(new Votos("vizcaya", "PUM+J", 0));
			votos2015.add(new Votos("vizcaya", "PCPE", 0));
			votos2015.add(new Votos("ceuta", "PP", 44));
			votos2015.add(new Votos("ceuta", "PSOE", 23));
			votos2015.add(new Votos("ceuta", "PODEMOS", 14));
			votos2015.add(new Votos("ceuta", "Cs", 13));
			votos2015.add(new Votos("ceuta", "IU-UPeC", 1));
			votos2015.add(new Votos("ceuta", "PACMA", 1));
			votos2015.add(new Votos("ceuta", "UPYD", 0));
			votos2015.add(new Votos("ceuta", "LOS VERDES", 0));
			votos2015.add(new Votos("navarra", "PP", 28));
			votos2015.add(new Votos("navarra", "PODEMOS", 22));
			votos2015.add(new Votos("navarra", "PSOE", 15));
			votos2015.add(new Votos("navarra", "EH", 9));
			votos2015.add(new Votos("navarra", "GBAI", 8));
			votos2015.add(new Votos("navarra", "Cs", 7));
			votos2015.add(new Votos("navarra", "IU-UPeC", 4));
			votos2015.add(new Votos("navarra", "PACMA", 0));
			votos2015.add(new Votos("navarra", "UPYD", 0));
			votos2015.add(new Votos("navarra", "LOS VERDES", 0));
			votos2015.add(new Votos("albacete", "PP", 37));
			votos2015.add(new Votos("albacete", "PSOE", 28));
			votos2015.add(new Votos("albacete", "Cs", 15));
			votos2015.add(new Votos("albacete", "PODEMOS", 13));
			votos2015.add(new Votos("albacete", "IU-UPeC", 4));
			votos2015.add(new Votos("albacete", "PACMA", 0));
			votos2015.add(new Votos("albacete", "UPYD", 0));
			votos2015.add(new Votos("albacete", "VOX", 0));
			votos2015.add(new Votos("albacete", "LOS VERDES", 0));
			votos2015.add(new Votos("albacete", "PCPE", 0));
			votos2015.add(new Votos("barcelona", "EN COMÚ", 26));
			votos2015.add(new Votos("barcelona", "PSOE", 16));
			votos2015.add(new Votos("barcelona", "ERC-CATSI", 14));
			votos2015.add(new Votos("barcelona", "Cs", 13));
			votos2015.add(new Votos("barcelona", "DL", 13));
			votos2015.add(new Votos("barcelona", "PP", 11));
			votos2015.add(new Votos("barcelona", "unio.cat", 1));
			votos2015.add(new Votos("barcelona", "PACMA", 1));
			votos2015.add(new Votos("barcelona", "LOS VERDES", 0));
			votos2015.add(new Votos("barcelona", "UPYD", 0));
			votos2015.add(new Votos("barcelona", "PCPE", 0));
			votos2015.add(new Votos("ciudadreal", "PP", 38));
			votos2015.add(new Votos("ciudadreal", "PSOE", 31));
			votos2015.add(new Votos("ciudadreal", "PODEMOS", 12));
			votos2015.add(new Votos("ciudadreal", "Cs", 12));
			votos2015.add(new Votos("ciudadreal", "IU-UPeC", 3));
			votos2015.add(new Votos("ciudadreal", "PACMA", 0));
			votos2015.add(new Votos("ciudadreal", "UPYD", 0));
			votos2015.add(new Votos("ciudadreal", "VOX", 0));
			votos2015.add(new Votos("ciudadreal", "LOS VERDES", 0));
			votos2015.add(new Votos("granada", "PP", 31));
			votos2015.add(new Votos("granada", "PSOE", 31));
			votos2015.add(new Votos("granada", "PODEMOS", 16));
			votos2015.add(new Votos("granada", "Cs", 13));
			votos2015.add(new Votos("granada", "IU-UPeC", 5));
			votos2015.add(new Votos("granada", "PACMA", 0));
			votos2015.add(new Votos("granada", "UPYD", 0));
			votos2015.add(new Votos("granada", "VOX", 0));
			votos2015.add(new Votos("granada", "PCPE", 0));
			votos2015.add(new Votos("granada", "LOS VERDES", 0));
			votos2015.add(new Votos("granada", "PUM+J", 0));
			votos2015.add(new Votos("jaen", "PSOE", 38));
			votos2015.add(new Votos("jaen", "PP", 31));
			votos2015.add(new Votos("jaen", "PODEMOS", 12));
			votos2015.add(new Votos("jaen", "Cs", 10));
			votos2015.add(new Votos("jaen", "IU-UPeC", 4));
			votos2015.add(new Votos("jaen", "PACMA", 0));
			votos2015.add(new Votos("jaen", "UPYD", 0));
			votos2015.add(new Votos("jaen", "VOX", 0));
			votos2015.add(new Votos("jaen", "PCPE", 0));
			votos2015.add(new Votos("jaen", "LOS VERDES", 0));
			votos2015.add(new Votos("orense", "PP", 44));
			votos2015.add(new Votos("orense", "PSOE", 23));
			votos2015.add(new Votos("orense", "En Marea-ANOVA-EU", 17));
			votos2015.add(new Votos("orense", "Cs", 7));
			votos2015.add(new Votos("orense", "NÓS", 3));
			votos2015.add(new Votos("orense", "PACMA", 0));
			votos2015.add(new Votos("orense", "UPYD", 0));
			votos2015.add(new Votos("orense", "LOS VERDES", 0));
			votos2015.add(new Votos("tenerife", "PP", 28));
			votos2015.add(new Votos("tenerife", "PSOE", 21));
			votos2015.add(new Votos("tenerife", "PODEMOS", 19));
			votos2015.add(new Votos("tenerife", "CCa-PNC", 12));
			votos2015.add(new Votos("tenerife", "Cs", 10));
			votos2015.add(new Votos("tenerife", "IU-UPeC", 3));
			votos2015.add(new Votos("tenerife", "PACMA", 1));
			votos2015.add(new Votos("tenerife", "UPYD", 0));
			votos2015.add(new Votos("tenerife", "LOS VERDES", 0));
			votos2015.add(new Votos("tenerife", "VOX", 0));
			votos2015.add(new Votos("tenerife", "PCPE", 0));
			votos2015.add(new Votos("teruel", "PP", 36));
			votos2015.add(new Votos("teruel", "PSOE", 25));
			votos2015.add(new Votos("teruel", "PODEMOS", 15));
			votos2015.add(new Votos("teruel", "Cs", 14));
			votos2015.add(new Votos("teruel", "CHUNTA ARAGONESIST", 5));
			votos2015.add(new Votos("teruel", "UPYD", 0));
			votos2015.add(new Votos("teruel", "PACMA", 0));
			votos2015.add(new Votos("teruel", "LOS VERDES", 0));
			votos2015.add(new Votos("teruel", "PCPE", 0));
			votos2015.add(new Votos("zamora", "PP", 42));
			votos2015.add(new Votos("zamora", "PSOE", 22));
			votos2015.add(new Votos("zamora", "PODEMOS", 13));
			votos2015.add(new Votos("zamora", "Cs", 12));
			votos2015.add(new Votos("zamora", "IU-UPeC", 4));
			votos2015.add(new Votos("zamora", "UPYD", 0));
			votos2015.add(new Votos("zamora", "PACMA", 0));
			votos2015.add(new Votos("zamora", "LOS VERDES", 0));
			votos2015.add(new Votos("zamora", "PCPE", 0));
			votos2015.add(new Votos("rioja", "PP", 38));
			votos2015.add(new Votos("rioja", "PSOE", 23));
			votos2015.add(new Votos("rioja", "PODEMOS", 17));
			votos2015.add(new Votos("rioja", "Cs", 15));
			votos2015.add(new Votos("rioja", "IU-UPeC", 4));
			votos2015.add(new Votos("rioja", "UPYD", 0));
			votos2015.add(new Votos("rioja", "PACMA", 0));
			votos2015.add(new Votos("rioja", "LOS VERDES", 0));
			votos2015.add(new Votos("rioja", "PCPE", 0));
			votos2015.add(new Votos("asturias", "PP", 30));
			votos2015.add(new Votos("asturias", "PSOE", 23));
			votos2015.add(new Votos("asturias", "PODEMOS", 21));
			votos2015.add(new Votos("asturias", "Cs", 13));
			votos2015.add(new Votos("asturias", "IU-UPeC", 8));
			votos2015.add(new Votos("asturias", "PACMA", 0));
			votos2015.add(new Votos("asturias", "UPYD", 0));
			votos2015.add(new Votos("asturias", "VOX", 0));
			votos2015.add(new Votos("asturias", "PCPE", 0));
			votos2015.add(new Votos("asturias", "LOS VERDES", 0));
			votos2015.add(new Votos("alicante", "PP", 32));
			votos2015.add(new Votos("alicante", "COMPROMÍS", 22));
			votos2015.add(new Votos("alicante", "PSOE", 20));
			votos2015.add(new Votos("alicante", "Cs", 17));
			votos2015.add(new Votos("alicante", "IU-UPeC", 3));
			votos2015.add(new Votos("alicante", "PACMA", 0));
			votos2015.add(new Votos("alicante", "UPYD", 0));
			votos2015.add(new Votos("alicante", "VOX", 0));
			votos2015.add(new Votos("alicante", "LOS VERDES", 0));
			votos2015.add(new Votos("alicante", "PCPE", 0));
			votos2015.add(new Votos("burgos", "PP", 38));
			votos2015.add(new Votos("burgos", "PSOE", 20));
			votos2015.add(new Votos("burgos", "PODEMOS", 17));
			votos2015.add(new Votos("burgos", "Cs", 15));
			votos2015.add(new Votos("burgos", "IU-UPeC", 4));
			votos2015.add(new Votos("burgos", "UPYD", 1));
			votos2015.add(new Votos("burgos", "PACMA", 0));
			votos2015.add(new Votos("burgos", "VOX", 0));
			votos2015.add(new Votos("burgos", "PCPE", 0));
			votos2015.add(new Votos("burgos", "LOS VERDES", 0));
			votos2015.add(new Votos("cordoba", "PSOE", 32));
			votos2015.add(new Votos("cordoba", "PP", 30));
			votos2015.add(new Votos("cordoba", "PODEMOS", 14));
			votos2015.add(new Votos("cordoba", "Cs", 11));
			votos2015.add(new Votos("cordoba", "IU-UPeC", 8));
			votos2015.add(new Votos("cordoba", "PACMA", 0));
			votos2015.add(new Votos("cordoba", "UPYD", 0));
			votos2015.add(new Votos("cordoba", "PCPE", 0));
			votos2015.add(new Votos("cordoba", "LOS VERDES", 0));
			votos2015.add(new Votos("guadalajara", "PP", 34));
			votos2015.add(new Votos("guadalajara", "PSOE", 22));
			votos2015.add(new Votos("guadalajara", "Cs", 18));
			votos2015.add(new Votos("guadalajara", "PODEMOS", 17));
			votos2015.add(new Votos("guadalajara", "IU-UPeC", 4));
			votos2015.add(new Votos("guadalajara", "PACMA", 0));
			votos2015.add(new Votos("guadalajara", "UPYD", 0));
			votos2015.add(new Votos("guadalajara", "VOX", 0));
			votos2015.add(new Votos("guadalajara", "LOS VERDES", 0));
			votos2015.add(new Votos("leon", "PP", 35));
			votos2015.add(new Votos("leon", "PSOE", 25));
			votos2015.add(new Votos("leon", "PODEMOS", 17));
			votos2015.add(new Votos("leon", "Cs", 13));
			votos2015.add(new Votos("leon", "IU-UPeC", 4));
			votos2015.add(new Votos("leon", "UPYD", 0));
			votos2015.add(new Votos("leon", "PACMA", 0));
			votos2015.add(new Votos("leon", "VOX", 0));
			votos2015.add(new Votos("leon", "PCPE", 0));
			votos2015.add(new Votos("leon", "LOS VERDES", 0));
			votos2015.add(new Votos("palencia", "PP", 40));
			votos2015.add(new Votos("palencia", "PSOE", 24));
			votos2015.add(new Votos("palencia", "Cs", 14));
			votos2015.add(new Votos("palencia", "PODEMOS", 13));
			votos2015.add(new Votos("palencia", "IU-UPeC", 4));
			votos2015.add(new Votos("palencia", "UPYD", 0));
			votos2015.add(new Votos("palencia", "PACMA", 0));
			votos2015.add(new Votos("palencia", "LOS VERDES", 0));
			votos2015.add(new Votos("palencia", "PCPE", 0));
			votos2015.add(new Votos("segovia", "PP", 39));
			votos2015.add(new Votos("segovia", "PSOE", 21));
			votos2015.add(new Votos("segovia", "Cs", 17));
			votos2015.add(new Votos("segovia", "PODEMOS", 14));
			votos2015.add(new Votos("segovia", "IU-UPeC", 4));
			votos2015.add(new Votos("segovia", "UPYD", 1));
			votos2015.add(new Votos("segovia", "PACMA", 0));
			votos2015.add(new Votos("segovia", "LOS VERDES", 0));
			votos2015.add(new Votos("segovia", "PCPE", 0));
			votos2015.add(new Votos("toledo", "PP", 38));
			votos2015.add(new Votos("toledo", "PSOE", 27));
			votos2015.add(new Votos("toledo", "Cs", 13));
			votos2015.add(new Votos("toledo", "PODEMOS", 13));
			votos2015.add(new Votos("toledo", "IU-UPeC", 3));
			votos2015.add(new Votos("toledo", "PACMA", 0));
			votos2015.add(new Votos("toledo", "UPYD", 0));
			votos2015.add(new Votos("toledo", "VOX", 0));
			votos2015.add(new Votos("toledo", "LOS VERDES", 0));
			votos2015.add(new Votos("toledo", "PCPE", 0));
			votos2015.add(new Votos("zaragoza", "PP", 30));
			votos2015.add(new Votos("zaragoza", "PSOE", 22));
			votos2015.add(new Votos("zaragoza", "PODEMOS", 19));
			votos2015.add(new Votos("zaragoza", "Cs", 17));
			votos2015.add(new Votos("zaragoza", "CHUNTA ARAGONESIST", 6));
			votos2015.add(new Votos("zaragoza", "UPYD", 0));
			votos2015.add(new Votos("zaragoza", "PACMA", 0));
			votos2015.add(new Votos("zaragoza", "VOX", 0));
			votos2015.add(new Votos("zaragoza", "LOS VERDES", 0));
			votos2015.add(new Votos("zaragoza", "PCPE", 0));
			votos2015.add(new Votos("madrid", "PP", 33));
			votos2015.add(new Votos("madrid", "PODEMOS", 20));
			votos2015.add(new Votos("madrid", "Cs", 18));
			votos2015.add(new Votos("madrid", "PSOE", 17));
			votos2015.add(new Votos("madrid", "IU-UPeC", 5));
			votos2015.add(new Votos("madrid", "UPYD", 1));
			votos2015.add(new Votos("madrid", "PACMA", 0));
			votos2015.add(new Votos("madrid", "VOX", 0));
			votos2015.add(new Votos("madrid", "LOS VERDES", 0));
			votos2015.add(new Votos("madrid", "PUM+J", 0));
			votos2015.add(new Votos("madrid", "PCPE", 0));
			votos2015.add(new Votos("almeria", "PP", 38));
			votos2015.add(new Votos("almeria", "PSOE", 29));
			votos2015.add(new Votos("almeria", "Cs", 14));
			votos2015.add(new Votos("almeria", "PODEMOS", 13));
			votos2015.add(new Votos("almeria", "IU-UPeC", 3));
			votos2015.add(new Votos("almeria", "PACMA", 0));
			votos2015.add(new Votos("almeria", "UPYD", 0));
			votos2015.add(new Votos("almeria", "VOX", 0));
			votos2015.add(new Votos("almeria", "PCPE", 0));
			votos2015.add(new Votos("almeria", "LOS VERDES", 0));
			votos2015.add(new Votos("almeria", "PUM+J", 0));
			votos2015.add(new Votos("caceres", "PP", 35));
			votos2015.add(new Votos("caceres", "PSOE", 34));
			votos2015.add(new Votos("caceres", "PODEMOS", 13));
			votos2015.add(new Votos("caceres", "Cs", 11));
			votos2015.add(new Votos("caceres", "IU-UPeC", 2));
			votos2015.add(new Votos("caceres", "PACMA", 0));
			votos2015.add(new Votos("caceres", "UPYD", 0));
			votos2015.add(new Votos("caceres", "VOX", 0));
			votos2015.add(new Votos("caceres", "LOS VERDES", 0));
			votos2015.add(new Votos("coruña", "PP", 35));
			votos2015.add(new Votos("coruña", "En Marea-ANOVA-EU", 26));
			votos2015.add(new Votos("coruña", "PSOE", 20));
			votos2015.add(new Votos("coruña", "Cs", 9));
			votos2015.add(new Votos("coruña", "NÓS", 4));
			votos2015.add(new Votos("coruña", "PACMA", 0));
			votos2015.add(new Votos("coruña", "UPYD", 0));
			votos2015.add(new Votos("coruña", "LOS VERDES", 0));
			votos2015.add(new Votos("coruña", "PCPE", 0));
			votos2015.add(new Votos("coruña", "VOX", 0));
			votos2015.add(new Votos("guipuzcoa", "PODEMOS", 25));
			votos2015.add(new Votos("guipuzcoa", "PNV", 23));
			votos2015.add(new Votos("guipuzcoa", "EH", 20));
			votos2015.add(new Votos("guipuzcoa", "PSOE", 13));
			votos2015.add(new Votos("guipuzcoa", "PP", 8));
			votos2015.add(new Votos("guipuzcoa", "Cs", 3));
			votos2015.add(new Votos("guipuzcoa", "IU-UPeC", 2));
			votos2015.add(new Votos("guipuzcoa", "PACMA", 0));
			votos2015.add(new Votos("guipuzcoa", "UPYD", 0));
			votos2015.add(new Votos("guipuzcoa", "LOS VERDES", 0));
			votos2015.add(new Votos("lerida", "DL", 24));
			votos2015.add(new Votos("lerida", "ERC-CATSI", 22));
			votos2015.add(new Votos("lerida", "EN COMÚ", 15));
			votos2015.add(new Votos("lerida", "PSOE", 13));
			votos2015.add(new Votos("lerida", "PP", 11));
			votos2015.add(new Votos("lerida", "Cs", 8));
			votos2015.add(new Votos("lerida", "unio.cat", 2));
			votos2015.add(new Votos("lerida", "PACMA", 0));
			votos2015.add(new Votos("lerida", "UPYD", 0));
			votos2015.add(new Votos("lerida", "VOX", 0));
			votos2015.add(new Votos("lerida", "LOS VERDES", 0));
			votos2015.add(new Votos("lerida", "PCPE", 0));
			votos2015.add(new Votos("palmas", "PP", 28));
			votos2015.add(new Votos("palmas", "PODEMOS", 26));
			votos2015.add(new Votos("palmas", "PSOE", 22));
			votos2015.add(new Votos("palmas", "Cs", 12));
			votos2015.add(new Votos("palmas", "CCa-PNC", 4));
			votos2015.add(new Votos("palmas", "IU-UPeC", 2));
			votos2015.add(new Votos("palmas", "PACMA", 1));
			votos2015.add(new Votos("palmas", "UPYD", 0));
			votos2015.add(new Votos("palmas", "LOS VERDES", 0));
			votos2015.add(new Votos("palmas", "PCPE", 0));
			votos2015.add(new Votos("palmas", "VOX", 0));
			votos2015.add(new Votos("palmas", "PUM+J", 0));
			votos2015.add(new Votos("sevilla", "PSOE", 33));
			votos2015.add(new Votos("sevilla", "PP", 25));
			votos2015.add(new Votos("sevilla", "PODEMOS", 19));
			votos2015.add(new Votos("sevilla", "Cs", 13));
			votos2015.add(new Votos("sevilla", "IU-UPeC", 5));
			votos2015.add(new Votos("sevilla", "PACMA", 0));
			votos2015.add(new Votos("sevilla", "UPYD", 0));
			votos2015.add(new Votos("sevilla", "VOX", 0));
			votos2015.add(new Votos("sevilla", "LOS VERDES", 0));
			votos2015.add(new Votos("sevilla", "PCPE", 0));
			votos2015.add(new Votos("valencia", "PP", 30));
			votos2015.add(new Votos("valencia", "COMPROMÍS", 27));
			votos2015.add(new Votos("valencia", "PSOE", 18));
			votos2015.add(new Votos("valencia", "Cs", 15));
			votos2015.add(new Votos("valencia", "IU-UPeC", 4));
			votos2015.add(new Votos("valencia", "PACMA", 0));
			votos2015.add(new Votos("valencia", "UPYD", 0));
			votos2015.add(new Votos("valencia", "VOX", 0));
			votos2015.add(new Votos("valencia", "LOS VERDES", 0));
			votos2015.add(new Votos("valencia", "PCPE", 0));
			votos2015.add(new Votos("baleares", "PP", 29));
			votos2015.add(new Votos("baleares", "PODEMOS", 23));
			votos2015.add(new Votos("baleares", "PSOE", 18));
			votos2015.add(new Votos("baleares", "Cs", 14));
			votos2015.add(new Votos("baleares", "MÉS", 7));
			votos2015.add(new Votos("baleares", "EL PI", 2));
			votos2015.add(new Votos("baleares", "IU-UPeC", 2));
			votos2015.add(new Votos("baleares", "PACMA", 1));
			votos2015.add(new Votos("baleares", "UPYD", 0));
			votos2015.add(new Votos("baleares", "LOS VERDES", 0));
			votos2015.add(new Votos("melilla", "PP", 43));
			votos2015.add(new Votos("melilla", "PSOE", 24));
			votos2015.add(new Votos("melilla", "Cs", 15));
			votos2015.add(new Votos("melilla", "PODEMOS", 11));
			votos2015.add(new Votos("melilla", "IU-UPeC", 1));
			votos2015.add(new Votos("melilla", "PACMA", 1));
			votos2015.add(new Votos("melilla", "UPYD", 0));
			votos2015.add(new Votos("melilla", "LOS VERDES", 0));
			votos2015.add(new Votos("avila", "PP", 46));
			votos2015.add(new Votos("avila", "PSOE", 19));
			votos2015.add(new Votos("avila", "Cs", 15));
			votos2015.add(new Votos("avila", "PODEMOS", 11));
			votos2015.add(new Votos("avila", "IU-UPeC", 3));
			votos2015.add(new Votos("avila", "UPYD", 0));
			votos2015.add(new Votos("avila", "PACMA", 0));
			votos2015.add(new Votos("avila", "VOX", 0));
			votos2015.add(new Votos("avila", "LOS VERDES", 0));
			votos2015.add(new Votos("cadiz", "PSOE", 28));
			votos2015.add(new Votos("cadiz", "PP", 27));
			votos2015.add(new Votos("cadiz", "PODEMOS", 20));
			votos2015.add(new Votos("cadiz", "Cs", 14));
			votos2015.add(new Votos("cadiz", "IU-UPeC", 6));
			votos2015.add(new Votos("cadiz", "PACMA", 1));
			votos2015.add(new Votos("cadiz", "UPYD", 0));
			votos2015.add(new Votos("cadiz", "VOX", 0));
			votos2015.add(new Votos("cadiz", "LOS VERDES", 0));
			votos2015.add(new Votos("cadiz", "PCPE", 0));
			votos2015.add(new Votos("cuenca", "PP", 41));
			votos2015.add(new Votos("cuenca", "PSOE", 30));
			votos2015.add(new Votos("cuenca", "PODEMOS", 11));
			votos2015.add(new Votos("cuenca", "Cs", 10));
			votos2015.add(new Votos("cuenca", "IU-UPeC", 2));
			votos2015.add(new Votos("cuenca", "PACMA", 0));
			votos2015.add(new Votos("cuenca", "UPYD", 0));
			votos2015.add(new Votos("cuenca", "VOX", 0));
			votos2015.add(new Votos("cuenca", "LOS VERDES", 0));
			votos2015.add(new Votos("cuenca", "PCPE", 0));
			votos2015.add(new Votos("huelva", "PSOE", 36));
			votos2015.add(new Votos("huelva", "PP", 28));
			votos2015.add(new Votos("huelva", "PODEMOS", 15));
			votos2015.add(new Votos("huelva", "Cs", 11));
			votos2015.add(new Votos("huelva", "IU-UPeC", 4));
			votos2015.add(new Votos("huelva", "PACMA", 0));
			votos2015.add(new Votos("huelva", "UPYD", 0));
			votos2015.add(new Votos("huelva", "VOX", 0));
			votos2015.add(new Votos("huelva", "LOS VERDES", 0));
			votos2015.add(new Votos("huelva", "PCPE", 0));
			votos2015.add(new Votos("lugo", "PP", 42));
			votos2015.add(new Votos("lugo", "PSOE", 23));
			votos2015.add(new Votos("lugo", "En Marea-ANOVA-EU", 19));
			votos2015.add(new Votos("lugo", "Cs", 7));
			votos2015.add(new Votos("lugo", "NÓS", 3));
			votos2015.add(new Votos("lugo", "PACMA", 0));
			votos2015.add(new Votos("lugo", "UPYD", 0));
			votos2015.add(new Votos("lugo", "LOS VERDES", 0));
			votos2015.add(new Votos("lugo", "PCPE", 0));
			votos2015.add(new Votos("pontevedra", "PP", 34));
			votos2015.add(new Votos("pontevedra", "En Marea-ANOVA-EU", 27));
			votos2015.add(new Votos("pontevedra", "PSOE", 20));
			votos2015.add(new Votos("pontevedra", "Cs", 9));
			votos2015.add(new Votos("pontevedra", "NÓS", 4));
			votos2015.add(new Votos("pontevedra", "PACMA", 0));
			votos2015.add(new Votos("pontevedra", "UPYD", 0));
			votos2015.add(new Votos("pontevedra", "LOS VERDES", 0));
			votos2015.add(new Votos("pontevedra", "PCPE", 0));
			votos2015.add(new Votos("soria", "PP", 38));
			votos2015.add(new Votos("soria", "PSOE", 23));
			votos2015.add(new Votos("soria", "PODEMOS", 16));
			votos2015.add(new Votos("soria", "Cs", 15));
			votos2015.add(new Votos("soria", "IU-UPeC", 3));
			votos2015.add(new Votos("soria", "UPYD", 0));
			votos2015.add(new Votos("soria", "PACMA", 0));
			votos2015.add(new Votos("soria", "LOS VERDES", 0));
			votos2015.add(new Votos("soria", "PCPE", 0));
			votos2015.add(new Votos("valladolid", "PP", 36));
			votos2015.add(new Votos("valladolid", "PSOE", 21));
			votos2015.add(new Votos("valladolid", "Cs", 17));
			votos2015.add(new Votos("valladolid", "PODEMOS", 15));
			votos2015.add(new Votos("valladolid", "IU-UPeC", 5));
			votos2015.add(new Votos("valladolid", "UPYD", 0));
			votos2015.add(new Votos("valladolid", "PACMA", 0));
			votos2015.add(new Votos("valladolid", "VOX", 0));
			votos2015.add(new Votos("valladolid", "LOS VERDES", 0));
			votos2015.add(new Votos("valladolid", "PCPE", 0));
			votos2015.add(new Votos("cantabria", "PP", 36));
			votos2015.add(new Votos("cantabria", "PSOE", 22));
			votos2015.add(new Votos("cantabria", "PODEMOS", 17));
			votos2015.add(new Votos("cantabria", "Cs", 15));
			votos2015.add(new Votos("cantabria", "IU-UPeC", 4));
			votos2015.add(new Votos("cantabria", "PACMA", 0));
			votos2015.add(new Votos("cantabria", "UPYD", 0));
			votos2015.add(new Votos("cantabria", "VOX", 0));
			votos2015.add(new Votos("cantabria", "LOS VERDES", 0));
			votos2015.add(new Votos("cantabria", "PCPE", 0));
			votos2015.add(new Votos("murcia", "PP", 40));
			votos2015.add(new Votos("murcia", "PSOE", 20));
			votos2015.add(new Votos("murcia", "Cs", 17));
			votos2015.add(new Votos("murcia", "PODEMOS", 15));
			votos2015.add(new Votos("murcia", "IU-UPeC", 3));
			votos2015.add(new Votos("murcia", "UPYD", 0));
			votos2015.add(new Votos("murcia", "VOX", 0));
			votos2015.add(new Votos("murcia", "LOS VERDES", 0));
			votos2015.add(new Votos("murcia", "PCPE", 0));

			dao.createEscenario("admin", titulo2015, votos2015, provincias2015,
					partidos2015, comentarios2015, Sistema.DHONDT,
					Circunscripciones.PROVINCIAS, 50, fecha2015);
		}
		
	}

}
