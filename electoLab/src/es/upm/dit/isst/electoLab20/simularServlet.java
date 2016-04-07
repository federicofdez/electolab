package es.upm.dit.isst.electoLab20;

import java.io.IOException;



import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.dao.electoLabDAO;
import es.upm.dit.isst.dao.electoLabDAOImpl;
import es.upm.dit.isst.logica.calculos;

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
		//int total_votos=0;
		int i = 0;
		String[] datos2 = new String[600]; 
		while(em.hasMoreElements()){
			String paraName = (String) em.nextElement();
			//total_votos = calculos.total_votos(req.getParameterNames(), req.getParameter(paraName));
			// if(req.getParameter(paraName) != "" && paraName.indexOf("PP") != -1 ){				
				// total_votos += Integer.parseInt(req.getParameter(paraName));
			//System.out.println(paraName + "=" + req.getParameter(paraName) );
			datos2[i] = req.getParameter(paraName);
			if(paraName.indexOf("sistema") != -1){
				System.out.println(req.getParameter(paraName));
			}
				//System.out.println(paraName + datos2[i]);
			i++;
			//req.setAttribute("total_votos", total_votos);
			//}
		}
		
		calculos calc = calculos.getInstance();
		if(calc.porcentaje_correctos(req.getParameterNames(), datos2)){
			System.out.println("Porcentajes correctos");
		}else{
			System.out.println("Cuidado porcentajes mal ajustados");
		}

		String votos = String.valueOf(calc.total_votos(req.getParameterNames(), datos2));
		System.out.println("votos totales = " +  votos);
		resp.sendRedirect("/simular.jsp");
		
		
	}

}
