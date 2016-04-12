package es.upm.dit.isst.electoLab20;

import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.dao.electoLabDAO;
import es.upm.dit.isst.dao.electoLabDAOImpl;

public class registrarServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		electoLabDAO dao = electoLabDAOImpl.getInstance();
		String correo = req.getParameter("email");
		String grupo = req.getParameter("grupo");
		String password = req.getParameter("password");
		String error = "";


		if(dao.read_grupo(grupo) == null){
			error = "No existe un grupo con el nombre: " + grupo;
			req.getSession().setAttribute("error",error);
		}
		else{
			if(!dao.read_grupo(grupo).getPassword().equals(password)){
				error = "Contraseña incorrecta, póngase en contacto con su empresa";
				req.getSession().setAttribute("error",error);
			}
		}
		if(error == "" && dao.read_grupo(grupo).getPassword().equals(password)){
			dao.create_usuario(correo);		
		}
		resp.sendRedirect("/electolab");
	}
}
