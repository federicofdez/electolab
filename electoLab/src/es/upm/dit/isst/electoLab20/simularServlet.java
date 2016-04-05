package es.upm.dit.isst.electoLab20;

import java.io.IOException;



import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class simularServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(req.getRequestURI());
		String urlLinktext = "Login";
		String user = "";
		if(req.getUserPrincipal() != null){
			user = req.getUserPrincipal().getName();
			url = userService.createLogoutURL(req.getRequestURI());
			urlLinktext = user + " :Logout";
		}
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		//String alba = req.getParameter("C'sSanta Cruz de Tenerife");
		//System.out.println(alba);
		Enumeration em= req.getParameterNames();
		while(em.hasMoreElements()){
			String paraName = (String) em.nextElement();
			System.out.println(paraName + "=" + req.getParameter(paraName) );
		}
		resp.sendRedirect("/simular.jsp");

		
	}

}
