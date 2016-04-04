package es.upm.dit.isst.electoLab20;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.dao.electoLabDAO;
import es.upm.dit.isst.dao.electoLabDAOImpl;
import es.upm.dit.isst.model.Partido;

@SuppressWarnings("serial")
public class crearServlet extends HttpServlet {
	 private String[] provincias = {"Alava","Albacete","Alicante","Almería","Asturias","Avila","Badajoz","Barcelona","Burgos","Cáceres",
	            		                	"Cádiz","Cantabria","Castellón","Ceuta","Ciudad Real","Córdoba","Cuenca","Gerona","Granada","Guadalajara",
	            		                	"Guipúzcoa","Huelva","Huesca","Islas Baleares","Jaén","La Coruña","La Rioja","Las Palmas","León","Lérida","Lugo","Madrid","Málaga","Melilla","Murcia","Navarra",
	            		                	"Orense","Palencia","Pontevedra","Santa Cruz de Tenerife","Salamanca","Segovia","Sevilla","Soria","Tarragona",
	            		                	"Teruel","Toledo","Valencia","Valladolid","Vizcaya","Zamora","Zaragoza"};
	 
	 private String[] electores = {"248.456","304.089","1.220.005","452.589","876.171","132.575","548.707","3.974.408","284.916","335.845",
         	"968.097","464.081","409.474","59.213","396.293","635.086","156.088","496.127","702.887","178.749",
         	"555.417","389.811","167.331","748.577","522.173","936.602","233.087","798.145","398.416","299.069","286.315","4.658.397","1.113.952","53.257","1.003.799","478.330",
         	"267.704","137.517","774.937","280.762","733.362","118.502","1.501.271","70.718","547.971",
         	"105.453","509.333","1.893.225","421.369","913.244","155.512","714.370"};
	 List<String[]> partidos = new ArrayList<String[]>();
	 {
	     partidos.add(new String[]{"PP","Partido Popular","img/logos/pp.png","1"});
	     partidos.add(new String[]{"PSOE","Partido Socialista","img/logos/psoe.png","2"});
	     partidos.add(new String[]{"PODEMOS","Podemos","img/logos/podemos.png","3"});
	     partidos.add(new String[]{"C's","Ciudadanos","img/logos/ciudadanos.png","4"});
	     partidos.add(new String[]{"EN COMÚ","En comú Podem","img/logos/podemosComun.png","5","Barcelona,Tarragona,Lérida,Gerona"});
	     partidos.add(new String[]{"PODEMOS-COMPROMÍS","Compromís-Podemos-És el moment","img/logos/podemosCompromis.png","6","Alicante,Castellón,Valencia"});
	     partidos.add(new String[]{"ERC-CATS","Esquerra Republicana de Catalunya-Catalunya Sí","img/logos/erc.png","7","Barcelona,Tarragona,Lérida,Gerona"});
	     partidos.add(new String[]{"DL","Democràcia i Llibertat","img/logos/dl.png","8","Barcelona,Tarragona,Lérida,Gerona"});
	     partidos.add(new String[]{"PODEMOS-En Marea-ANOVA-EU","En Marea","img/logos/podemosMarea.png","9","A Coruña,Lugo,Orense,Pontevedra"});
	     partidos.add(new String[]{"IU-UPeC","Unidad Popular: Izquierda Unida, Unidad Popular en Común","img/logos/iut.png","10"});
	     partidos.add(new String[]{"EAJ-PNV","Euzko Alderdi Jeltzalea-Partido Nacionalista Vasco","img/logos/pnv.jpg","11","Álava,Guipúzcoa,Vizcaya"});
	     partidos.add(new String[]{"CCa-PNC","Coalición Canaria-Partido Nacionalista Canario","img/logos/cca.png","12","Las Palmas,Santa Cruz de Tenerife"});
	     partidos.add(new String[]{"UPN","Unión del Pueblo Navarro","img/logos/upn.png","13","Navarra"});
	     partidos.add(new String[]{"FAC","Foro Asturias","img/logos/foro.jpg","14","Asturias"});
	 }

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
		req.getSession().setAttribute("provincias", provincias);
		req.getSession().setAttribute("electores", electores);
		req.getSession().setAttribute("partidos", partidos);
		dao.create("PP", "Partido Popular","img/logos/pp.png", 1, "Todas");
		dao.create("PSOE","Partido Socialista","img/logos/psoe.png",2,"Todas");

		//dao.delete("PP");
		System.out.println(dao.read());
		
		resp.sendRedirect("/crear.jsp");
	}

}
