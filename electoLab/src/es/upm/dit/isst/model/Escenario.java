package es.upm.dit.isst.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

	@Entity
public class Escenario implements Serializable {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
	private int votos_totales;
	private String sistema;
	private String circunscripciones;
	private int mayoria_abs;
	private int total_escaños;
	private int total_circuns;

		public Escenario(
			int votos_totales, String sistema, String circunscripciones,
			int mayoria_abs, int total_escaños, int total_circuns) {
		//this.partidos = partidos;
		//this.provincias = provincias;
		this.votos_totales = votos_totales;
		this.sistema = sistema;
		this.circunscripciones = circunscripciones;
		this.mayoria_abs = mayoria_abs;
		this.total_escaños = total_escaños;
		this.total_circuns = total_circuns;
	}

		//public List<Partido> getPartidos() {
			//return partidos;
		//}

		//public void setPartidos(List<Partido> partidos) {
			//this.partidos = partidos;
		//}

		/*public List<Provincia> getProvincias() {
			return provincias;
		}

		public void setProvincias(List<Provincia> provincias) {
			this.provincias = provincias;
		}*/

		public int getVotos_totales() {
			return votos_totales;
		}

		public void setVotos_totales(int votos_totales) {
			this.votos_totales = votos_totales;
		}

		public String getSistema() {
			return sistema;
		}

		public void setSistema(String sistema) {
			this.sistema = sistema;
		}

		public String getCircunscripciones() {
			return circunscripciones;
		}

		public void setCircunscripciones(String circunscripciones) {
			this.circunscripciones = circunscripciones;
		}

		public int getMayoria_abs() {
			return mayoria_abs;
		}

		public void setMayoria_abs(int mayoria_abs) {
			this.mayoria_abs = mayoria_abs;
		}

		public int getTotal_escaños() {
			return total_escaños;
		}

		public void setTotal_escaños(int total_escaños) {
			this.total_escaños = total_escaños;
		}

		public int getTotal_circuns() {
			return total_circuns;
		}

		public void setTotal_circuns(int total_circuns) {
			this.total_circuns = total_circuns;
		}


		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Escenario other = (Escenario) obj;
			if (circunscripciones == null) {
				if (other.circunscripciones != null)
					return false;
			} else if (!circunscripciones.equals(other.circunscripciones))
				return false;
			if (mayoria_abs != other.mayoria_abs)
				return false;
			/*if (partidos == null) {
				if (other.partidos != null)
					return false;
			} else if (!partidos.equals(other.partidos))
				return false;
			if (provincias == null) {
				if (other.provincias != null)
					return false;
			} else if (!provincias.equals(other.provincias))
				return false;*/
			if (sistema == null) {
				if (other.sistema != null)
					return false;
			} else if (!sistema.equals(other.sistema))
				return false;
			if (total_circuns != other.total_circuns)
				return false;
			if (total_escaños != other.total_escaños)
				return false;
			if (votos_totales != other.votos_totales)
				return false;
			return true;
		}
		

}
