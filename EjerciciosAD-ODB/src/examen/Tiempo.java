package examen;

import java.time.LocalDateTime;
import javax.persistence.Embeddable;

// Clase Tiempo que contiene hora y minuto.
@Embeddable
public class Tiempo {

	// atributos de un tiempo
	private int hora;
	private int minuto;

	// Crea un tiempo a partir de la hora del sistema.
	public Tiempo() {
		LocalDateTime ahora = LocalDateTime.now(); 
		this.hora = ahora.getHour();
		this.minuto = ahora.getMinute();
	}
	
	// Crea un tiempo a partir 2 par�metros.
	public Tiempo(int hora, int minuto) {
		this.hora = hora;
		this.minuto = minuto;
	}

	// Valida los datos de un tiempo seg�n las condiciones: 
	// - La hora debe estar comprendida entre 0 y 23.
	// - El minuto debe estar comprendido entre 0 y 59.
	// Devuelve verdadero si los datos de un tiempo son v�lidos.
	// Devuelve falso en caso contrario.
	public static boolean esValido(int hora, int minuto) {
		boolean tiempoValido = true;
		if (hora < 0 || hora > 23) {
			tiempoValido = false;
		}
		else if (minuto < 0 || minuto > 59) {
			tiempoValido = false;
		}
		return tiempoValido;
	}

	// Devuelve una cadena de caracteres con el estado del tiempo.
	// Utiliza el formato "HH:MM".
	@Override
	public String toString() {
		return 
			String.format("%02d:%02d", this.hora, this.minuto);				
	}

}
