package es.upm.dit.isst.electoLab20;

import java.io.IOException;



import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.dao.electoLabDAO;
import es.upm.dit.isst.dao.electoLabDAOImpl;
import es.upm.dit.isst.logica.calculos;
import es.upm.dit.isst.model.Escenario;
import es.upm.dit.isst.model.Partido;

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
		
		electoLabDAO dao = electoLabDAOImpl.getInstance();
		if(user != "" && !dao.exist_usuario(user)){
			resp.sendRedirect("/registrar.jsp");
		}else{


		Enumeration em= req.getParameterNames();
		//int total_votos=0;
		int i = 0;
		String[] datos2 = new String[req.getContentLength()]; 
		while(em.hasMoreElements()){
			String paraName = (String) em.nextElement();
			//total_votos = calculos.total_votos(req.getParameterNames(), req.getParameter(paraName));
			// if(req.getParameter(paraName) != "" && paraName.indexOf("PP") != -1 ){				
				// total_votos += Integer.parseInt(req.getParameter(paraName));
			//System.out.println(paraName + "=" + req.getParameter(paraName) );
			datos2[i] = req.getParameter(paraName);
			//if(paraName.indexOf("sistema") != -1){
				//System.out.println(req.getParameter(paraName));
			//}
			//if(paraName.indexOf("avila") != -1){
				//System.out.println(req.getParameter(paraName));
			//}
				//System.out.println(paraName + datos2[i]);
			i++;
			//req.setAttribute("total_votos", total_votos);
			//}
		}
	
		calculos calc = calculos.getInstance();
		//System.out.println(calc.calcula_esca(req.getParameterNames(), datos2));
		for(int w=0; w < 10; w++){
		//System.out.println("Total escaños " + calc.metodo_dhont_Avila(req.getParameterNames(), datos2)[w]);
		
		}
		//if(calc.porcentaje_correctos(req.getParameterNames(), datos2)){
			//System.out.println("Porcentajes correctos");
		//}else{
			//System.out.println("Cuidado porcentajes mal ajustados");
		//}

		//String votos = String.valueOf(calc.total_votos(req.getParameterNames(), datos2));
		
		//List<Partido> votosTabla = calc.calculaVotos(req.getParameterNames(), datos2);
		//req.getSession().setAttribute("votos", votosTabla);
		//System.out.println(req.getParameter("mayoria"));
		
		
		//calc.calcula_esc(calc.crea_mapa(req.getParameterNames(), datos2));
		//calc.crea_mapa(req.getParameterNames(), datos2);
		//calc.esca_map(req.getParameterNames(), datos2);

		Escenario escenario = calc.crearEscenario(req.getParameterNames(), datos2);
		//System.out.println(calc.votosPorCircunscripcion(escenario).toString());
		HashMap<String, Integer> escanosCircunscripciones = calc.escanosCircunscripciones(escenario);
		
		//calc.calc_es_b(escenario );
		
		resp.sendRedirect("/simular.jsp");
		}
		
	}

}
