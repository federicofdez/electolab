package es.upm.dit.isst.electolab;

import java.util.ArrayList;
import java.util.List;

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
		if (!dao.exist_grupo("prueba")) {
			dao.create_grupo("prueba", "prueba");
		}
		
		String[] provincias_esc0 = {"alava","albacete","alicante","almeria","asturias",
				"avila","badajoz","barcelona","burgos","caceres",
	        	"cadiz","cantabria","castellon","ceuta","ciudadreal","cordoba",
	        	"cuenca","gerona","granada","guadalajara",
	        	"guipuzcoa","huelva","huesca","baleares","jaen","coruña","rioja","palmas",
	        	"leon","lerida","lugo","madrid","malaga","melilla","murcia","navarra",
	        	"orense","palencia","pontevedra","tenerife","salamanca","segovia","sevilla",
	        	"soria","tarragona","teruel","toledo","Valencia",
	        	"valladolid","vizcaya","zamora","zaragoza"};
		
		String[] partidos_esc0 = { "PP", "PSOE", "PODEMOS", "C's", "EN COMÚ",
				"COMPROMÍS", "ERC-CATS", "DL",
				"En Marea-ANOVA-EU", "IU-UPeC", "EAJ-PNV", "CCa-PNC",
				"UPN","NC", "FAC" };

		if (!dao.exist_escenario("admin")) {
			List<Votos> votos = new ArrayList<Votos>();
			for (int i = 0; i < provincias_esc0.length; i++) {
				for (int j = 0; j < partidos_esc0.length ; j++) {
					votos.add(new Votos(provincias_esc0[i], partidos_esc0[j], 0));
				}
			}

			List<Provincia> provincias = new ArrayList<Provincia>();
			provincias.add(new Provincia("Álava", "alava", "PAÍSVASCO", 4,
					248456));
			provincias.add(new Provincia("Albacete", "albacete",
					"CASTILLALAMANCHA", 4, 304089));
			provincias.add(new Provincia("Alicante", "alicante",
					"VALENCIA", 12, 1220005));
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
			provincias.add(new Provincia("Castellón", "castellon",
					"VALENCIA", 5, 409474));
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
					"CASTILLALAMANCHA", 6, 178749));
			provincias.add(new Provincia("Guipúzcoa", "guipuzcoa",
					"PAÍSVASCO", 6, 555417));
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
			provincias.add(new Provincia("Las Palmas", "palmas",
					"CANARIAS", 8, 798145));
			provincias.add(new Provincia("León", "leon", "CASTILLAYLEÓN", 5,
					398416));
			provincias.add(new Provincia("Lérida", "lerida", "CATALUÑA", 4,
					299069));
			provincias.add(new Provincia("Lugo", "lugo", "GALICIA", 4, 286315));
			provincias.add(new Provincia("Madrid", "madrid",
					"MADRID", 36, 4658397));
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
					"CASTILLALAMANCHA", 5, 509333));
			provincias.add(new Provincia("Valencia", "Valencia", "VALENCIA",
					15, 1893225));
			provincias.add(new Provincia("Valladolid", "valladolid",
					"CASTILLAYLEÓN", 5, 421369));
			provincias.add(new Provincia("Vizcaya", "vizcaya", "PAÍSVASCO", 8,
					913244));
			provincias.add(new Provincia("Zamora", "zamora", "CASTILLAYLEÓN",
					7, 155512));
			provincias.add(new Provincia("Zaragoza", "zaragoza", "ARAGÓN", 3,
					714370));

			List<Partido> partidos = new ArrayList<Partido>();
			partidos.add(new Partido("PP", "Partido Popular",
					"img/logos/pp.png", "02CFF7"));
			partidos.add(new Partido("PSOE", "Partido Socialista",
					"img/logos/psoe.png", "FF0000"));
			partidos.add(new Partido("PODEMOS", "Podemos",
					"img/logos/podemos.png", "742DA1"));
			partidos.add(new Partido("C's", "Ciudadanos",
					"img/logos/ciudadanos.png", "F7771B"));
			partidos.add(new Partido("EN COMÚ", "En comú Podem",
					"img/logos/podemosComun.png", "998EEF"));
			partidos.add(new Partido("COMPROMÍS",
					"Compromís-Podemos-És el moment",
					"img/logos/podemosCompromis.png", "4E05D3"));
			partidos.add(new Partido("ERC-CATS",
					"Esquerra Republicana de Catalunya-Catalunya Sí",
					"img/logos/erc.png", "FFCC00"));
			partidos.add(new Partido("DL", "Democràcia i Llibertat",
					"img/logos/dl.png", "0F178A"));
			partidos.add(new Partido("En Marea-ANOVA-EU", "En Marea",
					"img/logos/podemosMarea.png", "C920DD"));
			partidos.add(new Partido("IU-UPeC",
					"Unidad Popular: Izquierda Unida, Unidad Popular en Común",
					"img/logos/iut.png", "10812D"));
			partidos.add(new Partido("EAJ-PNV",
					"Euzko Alderdi Jeltzalea-Partido Nacionalista Vasco",
					"img/logos/pnv.jpg", "54A106"));
			partidos.add(new Partido("CCa-PNC",
					"Coalición Canaria-Partido Nacionalista Canario",
					"img/logos/cca.png", "DAE705"));
			partidos.add(new Partido("UPN", "Unión del Pueblo Navarro",
					"img/logos/upn.png", "17626A"));
			partidos.add(new Partido("NC", "Nueva Canarias",
					"img/logos/nc.png", "A4FF00"));
			partidos.add(new Partido("FAC", "Foro Asturias",
					"img/logos/foro.jpg", "626262"));

			List<Comentario> comentarios = new ArrayList<Comentario>();

			dao.create_escenario("admin", votos, provincias, partidos,
					comentarios, Sistema.DHONDT, Circunscripciones.PROVINCIAS,
					50);

		}
	}

}
