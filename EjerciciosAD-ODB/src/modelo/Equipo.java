package modelo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Equipo {

	@Id
	private String nombre;
	private String ciudad;
	private List<Jugador> jugadores;
	
	public Equipo(String nombre, String ciudad) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.jugadores = new LinkedList<Jugador>();
	}

	public boolean insertar(Jugador jugador) {
		boolean insertado = false;
		if (this.jugadores != null) {
			insertado = this.jugadores.add(jugador);
		}
		return insertado;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	@Override
	public String toString() {
		String[] nombresJugadores = new String[this.jugadores.size()];
		for (int posicion = 0 ; posicion < this.jugadores.size() ; posicion++) {
			Jugador jugador = this.jugadores.get(posicion);
			nombresJugadores[posicion] = jugador.getNombre();
		}
		return 
			"Equipo [Nombre = " + nombre + 
			", Ciudad = " + ciudad + 
			", NombresJugadores = " + Arrays.toString(nombresJugadores) + 
			"]";
	}
	
}
