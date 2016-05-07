package es.upm.dit.isst.electolab;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

import es.upm.dit.isst.electolab.dao.ElectoLabDAO;
import es.upm.dit.isst.electolab.dao.ElectoLabDAOImpl;
import es.upm.dit.isst.electolab.model.Circunscripciones;
import es.upm.dit.isst.electolab.model.Comentario;
import es.upm.dit.isst.electolab.model.Escenario;
import es.upm.dit.isst.electolab.model.Partido;
import es.upm.dit.isst.electolab.model.Provincia;
import es.upm.dit.isst.electolab.model.Sistema;
import es.upm.dit.isst.electolab.model.Votos;

@SuppressWarnings("serial")
public class CrearServlet extends HttpServlet {

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

		Escenario escenario = dao.readEscenarios("admin").get(0);
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
		req.getSession().setAttribute("escenario", escenario);
		req.getRequestDispatcher("crear.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		Enumeration em = req.getParameterNames();
		int i = 0;
		String[] datos = new String[req.getContentLength()];
		while (em.hasMoreElements()) {
			String paraName = (String) em.nextElement();
			datos[i] = req.getParameter(paraName);
			i++;
		}

		Escenario escenario = this.crearEscenarioDesdeFormData(req);

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

	private Escenario crearEscenarioDesdeFormData(HttpServletRequest req) {
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();

		String usuario = req.getParameter("usuario");
		String titulo = req.getParameter("titulo");

		SimpleDateFormat formateador = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss", new Locale("es_ES"));
		Date fechaDate = new Date();
		String fecha = formateador.format(fechaDate);

		int mayoria_abs = Integer.parseInt(req.getParameter("mayoria"));

		Sistema sistema = Sistema.valueOf(req.getParameter("sistema"));
		Circunscripciones circunscripciones = Circunscripciones.valueOf(req
				.getParameter("circunscripciones"));

		List<Votos> votos = new ArrayList<Votos>();
		List<Provincia> provincias = new ArrayList<Provincia>();
		// TODO que se puedan crear partidos
		List<Partido> partidos = dao.readEscenarios("admin").get(0)
				.getPartidos();
		List<Comentario> comentarios = new ArrayList<Comentario>();

		for (Provincia p : dao.readEscenarios("admin").get(0).getProvincias()) {
			provincias.add(new Provincia(p.getNombre(), p.getId(), p
					.getComunidad(), Integer.parseInt((String) req.getParameter("escaños " + p.getId())), p
					.getElectores()));
			for (Partido px : partidos) {
				votos.add(new Votos(p.getId(), px.getSiglas(), Integer.parseInt((String) req
						.getParameter(px.getSiglas() + ":" + p.getId()))));
			}
		}
		
		return new Escenario(usuario, titulo, votos, provincias, partidos,
				comentarios, sistema, circunscripciones, mayoria_abs, fecha);
	}
}
