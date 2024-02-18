package examen;

import java.util.Random;
import javax.persistence.Embeddable;

// Clase Fichaje que contiene entrada y salida.
@Embeddable
public class Fichaje {
	
	// atributos de un fichaje
	private Tiempo entrada;
	private Tiempo salida;
	
	// Crea un fichaje a partir de 2 par�metros.
	public Fichaje(Tiempo entrada, Tiempo salida) {
		this.entrada = entrada;
		this.salida = salida;
	}
	
	// Crea un fichaje por defecto de forma aleatoria.
	public Fichaje() {
		Random aleatorio = new Random();
		int horaEntrada = aleatorio.nextInt(5) + 6;
		int minutoEntrada = aleatorio.nextInt(60);
		this.entrada = new Tiempo(horaEntrada, minutoEntrada);
		int horaSalida = aleatorio.nextInt(5) + 14;
		int minutoSalida = aleatorio.nextInt(60);
		this.salida = new Tiempo(horaSalida, minutoSalida);
	}

	// Devuelve una cadena de caracteres con el estado del fichaje.
	@Override
	public String toString() {
		return
			"[Entrada = " + this.entrada.toString() +
			", Salida = " + this.salida.toString() +
			"]";
	}
	
}
