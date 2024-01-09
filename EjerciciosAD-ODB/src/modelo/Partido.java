package modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Partido {

	@Id
	private Equipo equipoLocal;
	@Id
	private Equipo equipoVisitante;
	@Id
	private String fecha;
	private String resultado;
	
	public Partido(Equipo equipoLocal, Equipo equipoVisitante, 
	               String fecha, String resultado) {
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.fecha = fecha;
		this.resultado = resultado;
	}

	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return 
			"Partido [NombreEquipoLocal = " + equipoLocal.getNombre() + 
			", NombreEquipoVisitante = " + equipoVisitante.getNombre() + 
			", Fecha = " + fecha + 
			", Resultado = " + resultado + 
			"]";
	}
	
}
