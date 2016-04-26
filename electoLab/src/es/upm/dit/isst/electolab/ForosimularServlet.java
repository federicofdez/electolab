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
import es.upm.dit.isst.electolab.logic.calculos;
import es.upm.dit.isst.electolab.model.Escenario;
import es.upm.dit.isst.electolab.model.Votos;

public class ForosimularServlet extends HttpServlet{
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
		System.out.println("id: " + req.getParameter("escenarioId"));
		if (req.getParameter("escenarioId") != null){
			 escenario = dao.readEscenario(Long.parseLong(req.getParameter("escenarioId")));
			System.out.println("TIPO DE OBJETO" + escenario.toString());
		List<Votos> votosPorCircunscripcion = calculos.getInstance()
				.votosPorCircunscripcion(escenario);
		HashMap<String, Integer> escanosPorCircunscripcion = calculos
				.getInstance().escanosCircunscripciones(escenario);
		List<Votos> resultadosPorCircunscripcion = calculos.getInstance()
				.calcularEscanos(votosPorCircunscripcion,
						escanosPorCircunscripcion, escenario.getSistema());
		List<Votos> resultadosCongreso = calculos.getInstance()
				.resultadosCongreso(resultadosPorCircunscripcion, escenario);

		req.setAttribute("resultadosPorCircunscripcion",
				resultadosPorCircunscripcion);
		req.setAttribute("resultadosCongreso", resultadosCongreso);
		req.setAttribute("comentarios",
				escenario.getComentarios());
		req.getRequestDispatcher("comentarios.jsp").forward(req, resp);
		}
	}


}
