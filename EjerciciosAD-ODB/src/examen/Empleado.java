package examen;

import java.util.Map;
import java.util.TreeMap;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

// Clase Empleado que contiene nombre, departamento y mapa de fichajes.
@Entity
public class Empleado {

	// atributos de un empleado
	@Id
	private String nombre;
	private Departamento departamento;
	@OneToMany
	private Map<Fecha,Fichaje> fichajes;
	
	// Crea un equipo a partir de 2 par�metros.
	public Empleado(String nombre, Departamento departamento) {
		this.nombre = nombre;
		this.departamento = departamento;
		this.fichajes = new TreeMap<Fecha,Fichaje>();
	}

	// Inserta un fichaje en el mapa de fichajes del empleado.
	// Devuelve verdadero si lo ha insertado.
	// Devuelve falso en caso contrario.
	public boolean insertar(Fecha fecha, Fichaje fichaje) {
		if (this.fichajes.put(fecha, fichaje) == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Devuelve una cadena de caracteres con el estado del empleado.
	@Override
	public String toString() {
		if (this.fichajes == null) {
			return
				"Empleado [Nombre = " + this.nombre +
				", Denominaci�nDepartamento = " + this.departamento.getDenominacion() +
				"]";
		}
		else {
			return
				"Empleado [Nombre = " + this.nombre +
				", Denominaci�nDepartamento = " + this.departamento.getDenominacion() +
				", N�meroFichajes = " + this.fichajes.size() +
				"]";
		}	
	}

	// Modifica el departamento del empleado.
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
}
