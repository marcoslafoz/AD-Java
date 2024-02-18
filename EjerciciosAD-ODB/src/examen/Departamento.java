package examen;

import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// Clase Departamento que contiene c�digo, denominaci�n y presupuesto.
@Entity
public class Departamento {

	// atributos de un departamento
	@Id 
	@GeneratedValue
	private int codigo;
	private String denominacion;
	private double presupuesto;
	
	// Crea un departamento a partir de 2 par�metros.
	public Departamento(String denominacion, double presupuesto) {
		this.denominacion = denominacion;
		this.presupuesto = presupuesto;
	}
	
	// Crea un departamento a partir de 1 par�metro.
	public Departamento(String denominacion) {
		Random aleatorio = new Random();
		this.denominacion = denominacion;
		this.presupuesto = aleatorio.nextDouble() * 3000 + 1000;
	}
	
	// Devuelve una cadena de caracteres con el estado del departamento.
	@Override
	public String toString() {
		return
			"Departamento [C�digo = " + this.codigo +
			", Denominaci�n = " + this.denominacion +
			", Presupuesto = " + String.format("%.2f", this.presupuesto) +
			"]";
	}
	
	// Devuelve la denominacion del departamento.
	public String getDenominacion() {
		return this.denominacion;
	}

}
