package es.upm.dit.isst.electoLab20;

import java.io.IOException;
import javax.servlet.http.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.dao.electoLabDAO;
import es.upm.dit.isst.dao.electoLabDAOImpl;

@SuppressWarnings("serial")
public class ElectoLabServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		electoLabDAO dao = electoLabDAOImpl.getInstance();
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(req.getRequestURI());
		String urlLinktext = "Login";
		String user = "";
		if(req.getUserPrincipal() != null){
			user = req.getUserPrincipal().getName();
			System.out.println(user);
			url = userService.createLogoutURL(req.getRequestURI());
			System.out.println(url);
			urlLinktext = user + " :Logout";
		}
		
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		
		if(user != "" && !dao.exist_usuario(user)){
			resp.sendRedirect("/registrar.jsp");
		}else{
		
		
		resp.sendRedirect("/index.jsp");
		}
		
	}
}
