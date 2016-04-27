package es.upm.dit.isst.electolab.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.appengine.api.datastore.Text;

@Entity
public class Escenario implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String usuario;

	private Text votosJSON; // List<Votos>
	private Text provinciasJSON; // List<Provincia>
	private Text partidosJSON; // List<Partido>
	private Text comentariosJSON; // List<Comentario>

	private Sistema sistema;
	private Circunscripciones circunscripciones;
	private int mayoria_abs;

	public Escenario(String usuario, Text votosJSON,
			Text provinciasJSON, Text partidosJSON, Text comentariosJSON,
			Sistema sistema, Circunscripciones circunscripciones,
			int mayoria_abs) {
		super();
		this.usuario = usuario;
		this.votosJSON = votosJSON;
		this.provinciasJSON = provinciasJSON;
		this.partidosJSON = partidosJSON;
		this.comentariosJSON = comentariosJSON;
		this.sistema = sistema;
		this.circunscripciones = circunscripciones;
		this.mayoria_abs = mayoria_abs;
	}
	
	public Escenario(String usuario, List<Votos> votos,
			List<Provincia> provincias, List<Partido> partidos,
			List<Comentario> comentarios, Sistema sistema,
			Circunscripciones circunscripciones, int mayoria_abs) {
		super();
		this.usuario = usuario;
		this.setVotos(votos);
		this.setProvincias(provincias);
		this.setPartidos(partidos);
		this.setComentarios(comentarios);
		this.sistema = sistema;
		this.circunscripciones = circunscripciones;
		this.mayoria_abs = mayoria_abs;
	}

	public Long getId() {
		return id;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getVotosJSON() {
		return votosJSON.getValue();
	}

	public String getProvinciasJSON() {
		return provinciasJSON.getValue();
	}

	public String getPartidosJSON() {
		return partidosJSON.getValue();
	}

	public String getComentariosJSON() {
		return comentariosJSON.getValue();
	}

	public Sistema getSistema() {
		return sistema;
	}

	public Circunscripciones getCircunscripciones() {
		return circunscripciones;
	}

	public int getMayoria_abs() {
		return mayoria_abs;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setVotosJSON(String votosJSON) {
		this.votosJSON = new Text(votosJSON);
	}

	public void setProvinciasJSON(String provinciasJSON) {
		this.provinciasJSON = new Text(provinciasJSON);
	}

	public void setPartidosJSON(String partidosJSON) {
		this.partidosJSON = new Text(partidosJSON);
	}

	public void setComentariosJSON(String comentariosJSON) {
		this.comentariosJSON = new Text(comentariosJSON);
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public void setCircunscripciones(Circunscripciones circunscripciones) {
		this.circunscripciones = circunscripciones;
	}

	public void setMayoria_abs(int mayoria_abs) {
		this.mayoria_abs = mayoria_abs;
	}

	// métodos que utilizaremos desde fuera como getters y setters

	public List<Votos> getVotos() {
		JSONArray obj = (JSONArray) JSONValue.parse(this.votosJSON.getValue());

		List<Votos> listaVotos = new ArrayList<Votos>();
		for (Object o : obj) {
			JSONObject jsonObject = (JSONObject) o;
			String provincia = (String) jsonObject.get("circunscripcion");
			String partido = (String) jsonObject.get("partido");
			int votos = ((Long) jsonObject.get("votos")).intValue();
			listaVotos.add(new Votos(provincia, partido, votos));
		}

		return listaVotos;
	}

	public List<Provincia> getProvincias() {
		JSONArray obj = (JSONArray) JSONValue.parse(this.provinciasJSON.getValue());
		List<Provincia> listaProvincias = new ArrayList<Provincia>();
		for (Object o : obj) {
			JSONObject jsonObject = (JSONObject) o;
			String nombre = (String) jsonObject.get("nombre");
			String id = (String) jsonObject.get("id");
			String comunidad = (String) jsonObject.get("comunidad");
			int escanos = ((Long) jsonObject.get("escanos")).intValue();
			int electores = ((Long) jsonObject.get("electores")).intValue();
			listaProvincias.add(new Provincia(nombre,id, comunidad, escanos,
					electores));
		}

		return listaProvincias;
	}

	public List<Partido> getPartidos() {
		JSONArray obj = (JSONArray) JSONValue.parse(this.partidosJSON.getValue());

		List<Partido> listaPartidos = new ArrayList<Partido>();
		for (Object o : obj) {
			JSONObject jsonObject = (JSONObject) o;
			String siglas = (String) jsonObject.get("siglas");
			String nombre = (String) jsonObject.get("nombre");
			String imagen = (String) jsonObject.get("imagen");
			String color = (String) jsonObject.get("color");
			listaPartidos.add(new Partido(siglas, nombre, imagen, color));
		}

		return listaPartidos;
	}

	public List<Comentario> getComentarios() {
		JSONArray obj = (JSONArray) JSONValue.parse(this.comentariosJSON.getValue());

		List<Comentario> listaComentarios = new ArrayList<Comentario>();
		for (Object o : obj) {
			JSONObject jsonObject = (JSONObject) o;
			String usuario = (String) jsonObject.get("usuario");
			String fecha = (String) jsonObject.get("fecha");
			String texto = (String) jsonObject.get("texto");
			listaComentarios.add(new Comentario(usuario, fecha, texto));
		}

		return listaComentarios;
	}

	public void setVotos(List<Votos> votos) {
		JSONArray array = new JSONArray();
		for (Votos v : votos) {
			array.add(v);
		}
		this.votosJSON = new Text(array.toJSONString());
	}

	public void setProvincias(List<Provincia> provincias) {
		JSONArray array = new JSONArray();
		for (Provincia p : provincias) {
			array.add(p);
		}
		this.provinciasJSON = new Text(array.toJSONString());
	}

	public void setPartidos(List<Partido> partidos) {
		JSONArray array = new JSONArray();
		for (Partido p : partidos) {
			array.add(p);
		}
		this.partidosJSON = new Text(array.toJSONString());
	}

	public void setComentarios(List<Comentario> comentarios) {
		JSONArray array = new JSONArray();
		for (Comentario c : comentarios) {
			array.add(c);
		}
		this.comentariosJSON = new Text(array.toJSONString());
	}
	

	@Override
	public String toString() {
		return "Escenario ["
				+ "id=" + id + ", "
				+ "usuario=" + usuario + ", "
				+ "votosJSON=" + votosJSON + ", "
				+ "provinciasJSON=" + provinciasJSON + ", "
				+ "partidosJSON=" + partidosJSON + ", "
				+ "comentariosJSON=" + comentariosJSON + ", "
				+ "sistema=" + sistema + ", "
				+ "circunscripciones=" + circunscripciones + ", "
				+ "mayoria_abs=" + mayoria_abs
				+ "]";
	}

}
