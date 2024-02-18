package actividades1;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import actividades2.Fecha;

// Clase Jugador que contiene c�digo, nombre y fecha de nacimiento.
@Entity
public class Jugador {
	
	// atributos de un jugador
	@Id 
	@GeneratedValue
	private int codigo;
	private String nombre;
	private Fecha fechaNacimiento;
	
	// Crea un jugador a partir de 2 par�metros.
	public Jugador(String nombre, Fecha fechaNacimiento) {
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	// Crea un jugador a partir de 1 par�metro.
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.fechaNacimiento = new Fecha();
	}

	// Devuelve el nombre del jugador.
	public String getNombre() {
		return this.nombre;
	}
	
	// Modifica el nombre del jugador
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	// Devuelve la fecha de nacimiento del jugador.
	public Fecha getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	// Modifica la fecha de nacimiento del jugador.
	public void setFechaNacimiento(Fecha fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	// Devuelve una cadena de caracteres con el estado del jugador.
	@Override
	public String toString() {
		return 
			"Jugador [C�digo = " + this.codigo +
			", Nombre = " + this.nombre + 
			", FechaNacimiento = " + this.fechaNacimiento.toString() + 
			"]";
	}

	// Devuelve un c�digo hash calculado a partir del c�digo del jugador. 
	@Override
	public int hashCode() {
		return Objects.hash(this.codigo);
	}

	// Devuelve verdadero si este jugador es igual a otro jugador.
	// Devuelve falso en caso contrario.
	// Dos jugadores son iguales cuando sus c�digos son iguales.
	@Override
	public boolean equals(Object objeto) {
		if (this == objeto) {
			return true;
		}
		if (objeto == null) {
			return false;
		}
		if (getClass() != objeto.getClass()) {
			return false;
		}
		Jugador otro = (Jugador) objeto;
		return (this.codigo == otro.codigo);
	}
	
}
