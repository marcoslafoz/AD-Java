package actividades1;

import javax.persistence.Embeddable;

// Clase Resultado que contiene puntuacion local y puntuaci�n visitante.
@Embeddable
public class Resultado {

	// atributos de un resultado
	private int puntuacionLocal;
	private int puntuacionVisitante;
	
	// Crea un resultado a partir de 2 par�metros.
	public Resultado(int puntuacionLocal, int puntuacionVisitante) {
		this.puntuacionLocal = puntuacionLocal;
		this.puntuacionVisitante = puntuacionVisitante;
	}
	
	// Valida los datos de un resultado seg�n las condiciones:
	// - La puntuaci�n local debe ser cero o positiva.
	// - La puntuaci�n visitante debe ser cero o positiva.
	// Devuelve verdadero si los datos de un resultado son v�lidos.
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
