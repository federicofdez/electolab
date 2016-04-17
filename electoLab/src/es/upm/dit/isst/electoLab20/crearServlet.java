package es.upm.dit.isst.electoLab20;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.dao.electoLabDAO;
import es.upm.dit.isst.dao.electoLabDAOImpl;
import es.upm.dit.isst.logica.calculos;
import es.upm.dit.isst.model.Partido;
import es.upm.dit.isst.model.Provincia;

@SuppressWarnings("serial")
public class crearServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(req.getRequestURI());
		String urlLinktext = "Login";
		String user = "";
		if (req.getUserPrincipal() != null) {
			user = req.getUserPrincipal().getName();
			System.out.println(user);
			url = userService.createLogoutURL(req.getRequestURI());
			System.out.println(url);
			urlLinktext = user + " :Logout";
		}

		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);

		electoLabDAO dao = electoLabDAOImpl.getInstance();

		if (user != "" && !dao.exist_usuario(user)) {
			resp.sendRedirect("/registrar.jsp");
		} else {

			calculos calc = calculos.getInstance();

			// localhost:8888/_ah/admin
			// Prueba db Partido
			/*
			 * List<String> provincias2 = new ArrayList<String>(); {
			 * provincias2.add(new String("Álava")); provincias2.add(new
			 * String("Albacete")); } List<Double> votos2 = new
			 * ArrayList<Double>(); { votos2.add(new Double(1212212)); }
			 * //dao.create_partido
			 * ("PPSOEPRUEBA","Partido Probador de todos","img/logos/psoe.png"
			 * ,"2",provincias2,votos2,0); //System.out.println("leer" +
			 * dao.read_partidos()); //dao.delete_partido("PPSOEPRUEBA",0);
			 */

			// Prueba db Provincia
			// dao.create_provincia("LossstCity","Ciudad perdida","Perdida",3,
			// 1,1);
			// dao.create_provincia("LostCity","Ciudad perdida","Perdida",3,
			// 1,2);

			// System.out.println(dao.read_provincia("LostCity"));
			// System.out.println(dao.read_provincias());
			// dao.delete_provincia("LossstCity",1);
			// System.out.println(dao.read_provincia("LostCity"));

			// Prueba db Escenario
			/*
			 * Map<String,Integer> votos = new HashMap<String,Integer>(); {
			 * votos.put("PP:PirateBay",99999);
			 * votos.put("PODEMOS:Podemoslandia", 1);
			 * 
			 * } Map<String,Integer> escProv = new HashMap<String,Integer>(); {
			 * escProv.put("PirateBay",5); escProv.put("Podemoslandia", 1);
			 * 
			 * } List<String> comentarios = new ArrayList<String>(); String[]
			 * array = new
			 * String[]{"Comentario1","Este escenario es una mierda enorme"
			 * ,"Hoy"}; Gson gson = new Gson(); //convert java object to JSON
			 * format String json = gson.toJson(array); comentarios.add(json);
			 * 
			 * System.out.println(json);
			 * dao.create_escenario("Carlos",votos,escProv
			 * ,"DHont","España",150,comentarios);
			 * 
			 * //System.out.println(dao.read_escenario(1));
			 * System.out.println(dao.read_escenarios());
			 * //dao.delete_escenario(1);
			 * //System.out.println(dao.read_escenarios());
			 */

			// Habría que hacerlo con el dao
			req.getSession().setAttribute("provincias",
					dao.read_escenario("admin").getProvincias());
			req.getSession().setAttribute("partidos",
					dao.read_escenario("admin").getPartidos());

			resp.sendRedirect("/crear.jsp");
		}
	}

}
