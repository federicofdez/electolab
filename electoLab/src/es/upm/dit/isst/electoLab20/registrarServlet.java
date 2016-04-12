package es.upm.dit.isst.electoLab20;

import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.dao.electoLabDAO;
import es.upm.dit.isst.dao.electoLabDAOImpl;

public class registrarServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		electoLabDAO dao = electoLabDAOImpl.getInstance();
		String correo = req.getParameter("email");
		dao.create_usuario(correo);
		resp.sendRedirect("/electolab");
		
	}

}
