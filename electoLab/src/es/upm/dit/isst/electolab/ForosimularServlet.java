package es.upm.dit.isst.electolab;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.electolab.dao.ElectoLabDAO;
import es.upm.dit.isst.electolab.dao.ElectoLabDAOImpl;
import es.upm.dit.isst.electolab.logic.LOGICA;
import es.upm.dit.isst.electolab.model.Comentario;
import es.upm.dit.isst.electolab.model.Escanos;
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
		if (user == "") {
			resp.sendRedirect("/index.jsp");
			return;
		}
		if (user != "" && req.getParameter("escenarioId") == null ) {
			resp.sendRedirect("/index.jsp");
			return;
		}

		Escenario escenario = null;
		if (req.getParameter("escenarioId") != null){
			 escenario = dao.readEscenario(Long.parseLong(req.getParameter("escenarioId")));
			System.out.println("TIPO DE OBJETO" + escenario.toString());
		List<Votos> votosPorCircunscripcion = LOGICA.calcularVotosAbsolutosPorCircunscripcion(escenario);
		HashMap<String, Integer> escanosPorCircunscripcion = LOGICA.calcularEscanosPorCircunscripcion(escenario);
		List<Escanos> resultadosPorCircunscripcion = LOGICA.calcularEscanos(votosPorCircunscripcion,
						escanosPorCircunscripcion, escenario.getSistema());
		List<Escanos> resultadosCongreso = LOGICA.resultadosCongreso(resultadosPorCircunscripcion, escenario);

		req.setAttribute("resultadosPorCircunscripcion",
				resultadosPorCircunscripcion);
		req.setAttribute("resultadosCongreso", resultadosCongreso);
		req.getSession().setAttribute("escenario",
				escenario);
		req.getRequestDispatcher("comentarios.jsp").forward(req, resp);
		}
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Gestión de usuarios
		
				ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
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
				if (user != "" && !dao.existsUsuario(user)) {
					resp.sendRedirect("/registrar.jsp");
					return;
				}
				
				String texto = req.getParameter("comentario");
				SimpleDateFormat formateador = new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss", new Locale("es_ES"));
				Date fechaDate = new Date();
				String fecha = formateador.format(fechaDate);
				Comentario comentario = new Comentario(user, fecha, texto);
				Escenario escenario = dao.createComentario(Long.parseLong(req.getParameter("escenarioId")), comentario);
				req.getSession().setAttribute("escenario", dao.readEscenarios("admin").get(0));
				req.getRequestDispatcher("comentarios.jsp").forward(req, resp);
			}
		
}
