package es.upm.dit.isst.electoLab20;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.UUID;

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

import es.upm.dit.isst.dao.electoLabDAO;
import es.upm.dit.isst.dao.electoLabDAOImpl;
import es.upm.dit.isst.logica.calculos;
import es.upm.dit.isst.model.Escenario;

@SuppressWarnings("serial")
public class crearServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Gestión de usuarios
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(req.getRequestURI());
		String urlLinktext = "Login";
		String user = "";
		if (req.getUserPrincipal() != null) {
			user = req.getUserPrincipal().getName();
			url = userService.createLogoutURL(req.getRequestURI());
			urlLinktext = user + " :Logout";
		}
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);

		// Si ha iniciado sesión pero no pertenece a ningún grupo, redirigimos
		electoLabDAO dao = electoLabDAOImpl.getInstance();
		if (user != "" && !dao.exist_usuario(user)) {
			resp.sendRedirect("/registrar.jsp");
			return;
		}
		
		req.getSession().setAttribute("provincias",
				dao.read_escenario("admin").getProvincias());
		req.getSession().setAttribute("partidos",
				dao.read_escenario("admin").getPartidos());
		resp.sendRedirect("/crear.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		electoLabDAO dao = electoLabDAOImpl.getInstance();
		Enumeration em = req.getParameterNames();
		int i = 0;
		String[] datos = new String[req.getContentLength()];
		while (em.hasMoreElements()) {
			String paraName = (String) em.nextElement();
			datos[i] = req.getParameter(paraName);
			i++;
		}

		calculos calc = calculos.getInstance();
		Escenario escenario = calc.crearEscenario(em, datos);

		Cache cache;
		try {
			CacheFactory cacheFactory = CacheManager.getInstance()
					.getCacheFactory();
			cache = cacheFactory.createCache(Collections.emptyMap());
			String escenarioKey = UUID.randomUUID().toString();
			cache.put(escenarioKey, escenario);
			resp.sendRedirect("/simular?escenario=" + escenarioKey);
		} catch (CacheException e) {
			e.printStackTrace();
		}
	}
}
