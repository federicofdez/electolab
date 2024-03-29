package es.upm.dit.isst.electolab;

import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.electolab.dao.ElectoLabDAO;
import es.upm.dit.isst.electolab.dao.ElectoLabDAOImpl;

public class RegistrarServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ElectoLabDAO dao = ElectoLabDAOImpl.getInstance();
		String correo = req.getParameter("email");
		String grupo = req.getParameter("grupo");
		String password = req.getParameter("password");
		String error = "";
		if(dao.existsUsuario(correo)){
			error = "El usuario " + correo + " ya está registrado en otro grupo";
			req.getSession().setAttribute("error", error);
		} else
			if (dao.readGrupo(grupo) == null) {
				error = "No existe un grupo con el nombre: " + grupo;
				req.getSession().setAttribute("error", error);
			} else {
				if (!dao.readGrupo(grupo).getPassword().equals(password)) {
					error = "Contraseña incorrecta, póngase en contacto con su empresa";
					req.getSession().setAttribute("error", error);
				}
			}
			if (error == "" && dao.readGrupo(grupo).getPassword().equals(password)) {
				dao.createUsuario(correo, grupo);
			}
		resp.sendRedirect("/electolab");
	}
}
