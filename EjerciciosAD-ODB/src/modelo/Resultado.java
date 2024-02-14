package modelo;

import javax.persistence.Embeddable;

// Clase Resultado que contiene puntuacion local y puntuación visitante.
@Embeddable
public class Resultado {

	// atributos de un resultado
	private int puntuacionLocal;
	private int puntuacionVisitante;
	
	// Crea un resultado a partir de 2 parámetros.
	public Resultado(int puntuacionLocal, int puntuacionVisitante) {
		this.puntuacionLocal = puntuacionLocal;
		this.puntuacionVisitante = puntuacionVisitante;
	}
	
	// Valida los datos de un resultado según las condiciones:
	// - La puntuación local debe ser cero o positiva.
	// - La puntuación visitante debe ser cero o positiva.
	// Devuelve verdadero si los datos de un resultado son válidos.
	// Devuelve falso en caso contrario.
	public static boolean esValido(int puntuacionLocal, int puntuacionVisitante) {
		if (puntuacionLocal >= 0 && puntuacionVisitante >= 0) {
			return true;
		}
		else {
			return false;
		}
	}

	// Devuelve una cadena de caracteres con el estado del resultado.
	// Utiliza el formato "L-V".
	@Override
	public String toString() {
		return 
			String.format("%d-%d", this.puntuacionLocal, this.puntuacionVisitante);				
	}
	
}
