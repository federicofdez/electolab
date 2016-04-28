package es.upm.dit.isst.electolab;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
import es.upm.dit.isst.electolab.logic.LOGICA;
import es.upm.dit.isst.electolab.model.Escanos;
import es.upm.dit.isst.electolab.model.Escenario;
import es.upm.dit.isst.electolab.model.Votos;

public class SimularServlet extends HttpServlet {

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
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		if (user != "" && !dao.existsUsuario(user)) {
			resp.sendRedirect("/registrar.jsp");
			return;
		}

		Escenario escenario = null;
		if (req.getParameterMap().containsKey("escenario")) {
			Cache cache;
			try {
				CacheFactory cacheFactory = CacheManager.getInstance()
						.getCacheFactory();
				cache = cacheFactory.createCache(Collections.emptyMap());
				escenario = (Escenario) cache
						.get(req.getParameter("escenario"));
			} catch (CacheException e) {
				e.printStackTrace();
			}
		}

		// Si no hay escenario cacheado...
		if (escenario == null)
			escenario = dao.readEscenarios("admin").get(0);

		// Simulamos el Escenario:
		// primero, pasamos los votos de provincias a circunscripciones
		List<Votos> votosPorCircunscripcion = LOGICA
				.calcularVotosAbsolutosPorCircunscripcion(escenario);
		// y lo mismo con los escaños
		HashMap<String, Integer> escanosPorCircunscripcion = LOGICA
				.calcularEscanosPorCircunscripcion(escenario);
		// segundo, calculamos los resultados por circunscripción
		List<Escanos> resultadosPorCircunscripcion = LOGICA.calcularEscanos(
				votosPorCircunscripcion, escanosPorCircunscripcion,
				escenario.getSistema());
		// por último, agrupamos para el Congreso
		List<Escanos> resultadosCongreso = LOGICA.resultadosCongreso(
				resultadosPorCircunscripcion, escenario);

		// Renderizamos vista
		req.setAttribute("resultadosPorCircunscripcion",
				resultadosPorCircunscripcion);
		req.setAttribute("resultadosCongreso", resultadosCongreso);

		req.getRequestDispatcher("simular.jsp").forward(req, resp);
	}

}