package modelo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

// Clase Partido que contiene equipo local, equipo visitante, fecha y resultado.
@Entity
public class Partido {

	// atributos de un partido
	@ManyToOne
	private Equipo equipoLocal;
	@ManyToOne
	private Equipo equipoVisitante;
	private Fecha fecha;
	private Resultado resultado;
	
	// Crea un partido a partir de 4 parámetros.
	public Partido(Equipo equipoLocal, Equipo equipoVisitante, 
	               Fecha fecha, Resultado resultado) {
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.fecha = fecha;
		this.resultado = resultado;
	}

	// Devuelve el equipo local del partido.
	public Equipo getEquipoLocal() {
		return this.equipoLocal;
	}

	// Modifica el equipo local del partido.
	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	// Devuelve el equipo visitante del partido.
	public Equipo getEquipoVisitante() {
		return this.equipoVisitante;
	}

	// Modifica el equipo visitante del partido.
	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	// Devuelve la fecha del partido.
	public Fecha getFecha() {
		return this.fecha;
	}

	// Modifica la fecha del partido.
	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	// Devuelve el resultado del partido.
	public Resultado getResultado() {
		return this.resultado;
	}

	// Modifica el resultado del partido.
	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	// Devuelve una cadena de caracteres con el estado del partido.
	@Override
	public String toString() {
		return 
			"Partido [NombreEquipoLocal = " + this.equipoLocal.getNombre() + 
			", NombreEquipoVisitante = " + this.equipoVisitante.getNombre() + 
			", Fecha = " + this.fecha.toString() + 
			", Resultado = " + this.resultado.toString() + 
			"]";
	}
	
}
