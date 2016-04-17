package es.upm.dit.isst.electoLab20;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.UUID;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.servlet.RequestDispatcher;
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

public class simularServlet extends HttpServlet {

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
		
		if (req.getParameterMap().containsKey("escenario")){
			Cache cache;
			try {
				CacheFactory cacheFactory = CacheManager.getInstance()
						.getCacheFactory();
				cache = cacheFactory.createCache(Collections.emptyMap());
				Escenario escenario = (Escenario) cache.get(req.getAttribute("escenario"));
				req.setAttribute("escenario", escenario);
			} catch (CacheException e) {
				e.printStackTrace();
			}
		}
		req.getRequestDispatcher("simular.jsp").forward(req, resp);
	}

}