package es.upm.dit.isst.electolab;

import java.io.IOException;
import java.util.Collections;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.electolab.dao.ElectoLabDAO;
import es.upm.dit.isst.electolab.dao.ElectoLabDAOImpl;
import es.upm.dit.isst.electolab.model.Circunscripciones;
import es.upm.dit.isst.electolab.model.Escenario;
import es.upm.dit.isst.electolab.model.Sistema;

public class GuardarServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Gestión de usuarios
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(req.getRequestURI());
		String urlLinktext = "Login";
		String user = "";
		if (req.getUserPrincipal() != null) {
			user = req.getUserPrincipal().getName();
			url = userService.createLogoutURL(req.getRequestURI());
			urlLinktext = " Logout";
		}
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		
		Cache cache;
		Escenario escenario = null;
		try {
			CacheFactory cacheFactory = CacheManager.getInstance()
					.getCacheFactory();
			cache = cacheFactory.createCache(Collections.emptyMap());
			escenario = (Escenario) cache
					.get(req.getParameter("escenario"));
		} catch (CacheException e) {
			e.printStackTrace();
		}
		
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		dao.createEscenario(escenario);
		resp.sendRedirect("/foro");
		
	}

}
