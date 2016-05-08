package es.upm.dit.isst.electolab;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import es.upm.dit.isst.electolab.model.Circunscripciones;
import es.upm.dit.isst.electolab.model.Escenario;
import es.upm.dit.isst.electolab.model.Resultados;
import es.upm.dit.isst.electolab.model.Sistema;
import es.upm.dit.isst.electolab.model.Votos;

public class InformeServlet extends HttpServlet {

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
			urlLinktext = " Logout";
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
				
				if (req.getParameterMap().containsKey("sistema"))
					escenario.setSistema(Sistema.valueOf(req.getParameter("sistema")));
				if (req.getParameterMap().containsKey("circunscripciones"))
					escenario.setCircunscripciones(Circunscripciones.valueOf(req.getParameter("circunscripciones")));
				if (req.getParameterMap().containsKey("mayoria"))
					escenario.setMayoria_abs(Integer.parseInt(req.getParameter("mayoria")));
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
		List<Resultados> resultadosPorCircunscripcion = LOGICA.calcularEscanos(
				votosPorCircunscripcion, escanosPorCircunscripcion,
				escenario.getSistema());
		// por último, agrupamos para el Congreso
		List<Resultados> resultadosCongreso = LOGICA.resultadosCongreso(
				resultadosPorCircunscripcion, escenario);
		
		// Renderizamos vista
		req.setAttribute("resultadosPorCircunscripcion",
				resultadosPorCircunscripcion);
		req.setAttribute("resultadosCongreso", resultadosCongreso);
		req.setAttribute("escenario", escenario);
		
		req.setAttribute("escenarioKey", req.getParameter("escenario"));

		req.getRequestDispatcher("informe.jsp").forward(req, resp);
	}

}
