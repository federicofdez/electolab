package es.upm.dit.isst.electolab;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.electolab.dao.ElectoLabDAO;
import es.upm.dit.isst.electolab.dao.ElectoLabDAOImpl;
import es.upm.dit.isst.electolab.model.Escenario;
import es.upm.dit.isst.electolab.model.Grupo;

public class ForoServlet extends HttpServlet {
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
			urlLinktext = " Logout";
		}
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);

		// Si ha iniciado sesión pero no pertenece a ningún grupo, redirigimos
		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		if(user == ""){
			resp.sendRedirect("/index.jsp");
			return;
		}
		if (user != "" && !dao.existsUsuario(user)) {
			resp.sendRedirect("/registrar.jsp");
			return;
		}
		String grupo = dao.findGroup(user).getNombre();
		List<Escenario> escenarios = dao.readEscenariosGrupo(grupo);
		req.getSession().setAttribute("escenarios", escenarios);
		req.getRequestDispatcher("foro.jsp").forward(req, resp);

	}
}
