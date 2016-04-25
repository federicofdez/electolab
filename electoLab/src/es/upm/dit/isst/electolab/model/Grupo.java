package es.upm.dit.isst.electolab.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.appengine.api.datastore.Text;

@Entity
public class Grupo implements Serializable {

	@Id
	private String nombre;
	private String password;
	private Text usuariosJSON;

	public Grupo(String nombre, String password, Text usuariosJSON) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.usuariosJSON = usuariosJSON;
	}
	
	public Grupo(String nombre, String password, Set<String> usuarios) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.setUsuarios(usuarios);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Text getUsuariosJSON() {
		return usuariosJSON;
	}
	
	public void setUsuariosJSON(Text usuariosJSON) {
		this.usuariosJSON = usuariosJSON;
	}
	
	public Set<String> getUsuarios() {
		JSONArray obj = (JSONArray) JSONValue.parse(this.usuariosJSON.getValue());
		Set<String> setUsuarios = new HashSet<String>();
		for (Object o : obj)
			setUsuarios.add((String) o);
		return setUsuarios;
	}
	
	public void setUsuarios(Set<String> usuarios) {
		JSONArray array = new JSONArray();
		for (String u : usuarios) {
			array.add(u);
		}
		this.usuariosJSON = new Text(array.toJSONString());
	}

	@Override
	public String toString() {
		return "Grupo [nombre=" + nombre + ", password=" + password + "]";
	}

}
