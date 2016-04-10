package es.upm.dit.isst.electoLab20;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(req.getRequestURI());
		String urlLinktext = "Login";
		String user = "";
		if(req.getUserPrincipal() != null){
			user = req.getUserPrincipal().getName();
			url = userService.createLogoutURL(req.getRequestURI());
			urlLinktext = user + " :Logout";
		}
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		
		
		electoLabDAO dao = electoLabDAOImpl.getInstance();
		calculos calc = calculos.getInstance();
		
		//localhost:8888/_ah/admin
		//Prueba db Partido
		List<String> provincias2 = new ArrayList<String>();
		 { 
		     provincias2.add(new String("Álava"));
		     provincias2.add(new String("Albacete"));
		 }
		 List<Double> votos2 = new ArrayList<Double>();
		 {
		     votos2.add(new Double(1212212));		     
		 }
		dao.create_partido("PPSOEPRUEBA","Partido Probador de todos","img/logos/psoe.png","2",provincias2,votos2,0);
		//System.out.println("leer" + dao.read_partidos());
		dao.delete_partido("PPSOEPRUEBA",0);
		
		
		//Prueba db Provincia
		//dao.create_provincia("LostCity","Ciudad perdida","Perdida",3, 1,1);
		//dao.create_provincia("LostCity","Ciudad perdida","Perdida",3, 1,2);

		//System.out.println(dao.read_provincia("LostCity"));
		//System.out.println(dao.read_provincias());
		//dao.delete_provincia("LostCity",1);
		//System.out.println(dao.read_provincia("LostCity"));
		
		
		//Prueba db Escenario
		/*dao.create_escenario(1, 5, "DHont1", "provincias", 5, 5, 99);
		System.out.println(dao.read_escenario(1));
		System.out.println(dao.read_escenarios());
		dao.delete_escenario(1);
		System.out.println(dao.read_escenarios());
		*/
		

		//Habría que hacerlo con el dao
		req.getSession().setAttribute("provincias", calc.getProvincias());
		req.getSession().setAttribute("partidos", calc.getPartidos());
		
		resp.sendRedirect("/crear.jsp");
	}

}
