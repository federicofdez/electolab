package es.upm.dit.isst.electoLab20;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
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

import es.upm.dit.isst.dao.electoLabDAO;
import es.upm.dit.isst.dao.electoLabDAOImpl;
import es.upm.dit.isst.logica.calculos;
import es.upm.dit.isst.model.Circunscripciones;
import es.upm.dit.isst.model.Comentario;
import es.upm.dit.isst.model.Escenario;
import es.upm.dit.isst.model.Partido;
import es.upm.dit.isst.model.Provincia;
import es.upm.dit.isst.model.Sistema;
import es.upm.dit.isst.model.Votos;

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

		req.setAttribute("provincias", dao.read_escenario("admin")
				.getProvincias());
		req.setAttribute("partidos", dao.read_escenario("admin").getPartidos());
		req.getRequestDispatcher("crear.jsp").forward(req, resp);
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
		electoLabDAO dao = electoLabDAOImpl.getInstance();
		Enumeration datos = req.getParameterNames();

		String usuario = "no registrado";
		int mayoria_abs = Integer.parseInt(req.getParameter("mayoria"));

		Sistema sistema = Sistema.valueOf(req.getParameter("sistema"));
		Circunscripciones circunscripciones = Circunscripciones.valueOf(req
				.getParameter("circunscripciones"));

		List<Votos> votos = new ArrayList<Votos>();
		List<Provincia> provincias = new ArrayList<Provincia>();
		// TODO que se puedan crear partidos
		List<Partido> partidos = dao.read_escenario("admin").getPartidos();
		List<Comentario> comentarios = new ArrayList<Comentario>();

		while (datos.hasMoreElements()) {
			String d = (String) datos.nextElement();
			if (!d.contains(":") && !d.contains(" "))
				continue;
			else if (d.contains(":")) {
				String partido = d.split(":")[0];
				String provincia = d.split(":")[1];
				int num_votos = Integer.parseInt(req.getParameter(d));
				votos.add(new Votos(provincia, partido, num_votos));
			} else if (d.contains(" ")) {
				String id_provincia = d.split(" ")[1];
				Provincia pr = null;
				for (Provincia p : dao.read_escenario("admin").getProvincias()) {
					if (p.getId().equals(id_provincia)) {
						pr = new Provincia(p.getNombre(), id_provincia,
								p.getComunidad(), p.getEscanos(),
								p.getElectores());
						break;
					}
				}
				if (!provincias.contains(pr)) {
					provincias.add(pr);
				}
			}
		}

		return new Escenario(usuario, votos, provincias, partidos, comentarios,
				sistema, circunscripciones, mayoria_abs);
	}
}
