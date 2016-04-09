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

		req.getSession().setAttribute("provincias", calc.getProvincias());
		req.getSession().setAttribute("partidos", calc.getPartidos());
		//dao.create("PP", "Partido Popular","img/logos/pp.png", 1, "Todas");
		//dao.create("PSOE","Partido Socialista","img/logos/psoe.png",2,"Todas");
		//dao.create_provincia("Segovia", 100 , 100);
		//dao.create_escenario(100, "D'hont", "provincias", 50, 100, 1);

		//dao.delete("PP");
		//System.out.println(dao.read());
		//System.out.println(dao.read_provincias());
		//System.out.println(dao.read_escenarios());
		
		resp.sendRedirect("/crear.jsp");
	}

}
