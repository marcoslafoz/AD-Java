package modelo;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

// Clase Equipo que contiene nombre, ciudad y conjunto de jugadores.
@Entity
public class Equipo {

	// atributos de un equipo
	@Id
	private String nombre;
	private String ciudad;
	@OneToMany
	private Set<Jugador> jugadores;
	
	// Crea un equipo a partir de 2 parámetros.
	public Equipo(String nombre, String ciudad) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.jugadores = new LinkedHashSet<Jugador>();
	}

	// Inserta un jugador en el conjunto de jugadores del equipo.
	// Devuelve verdadero si ha insertado el jugador en el conjunto de jugadores.
	// Devuelve falso en caso contrario.
	public boolean insertar(Jugador jugador) {
		boolean insertado = this.jugadores.add(jugador);
		return insertado;
	}
	
	// Devuelve el nombre del equipo.
	public String getNombre() {
		return this.nombre;
	}

	// Devuelve la ciudad del equipo.
	public String getCiudad() {
		return this.ciudad;
	}

	// Modifica la ciudad del equipo.
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	// Devuelve una cadena de caracteres con el estado del equipo.
	@Override
	public String toString() {
		if (this.jugadores == null) {
			return 
				"Equipo [Nombre = " + this.nombre + 
				", Ciudad = " + this.ciudad + 
				"]";
		}
		else {
			String[] nombresJugadores = new String[this.jugadores.size()];
			int posicion = 0;
			for (Jugador jugador : this.jugadores) {
				nombresJugadores[posicion] = jugador.getNombre();
				posicion++;
			}
			return 
				"Equipo [Nombre = " + this.nombre + 
				", Ciudad = " + this.ciudad + 
				", NombresJugadores = " + Arrays.toString(nombresJugadores) + 
				"]";
		}
	}
	
}
