package modelo;

import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Jugador {

	@Id @GeneratedValue
	private int codigo;
	private String nombre;
	private String fechaNacimiento;
	
	public Jugador(String nombre) {
		Random aleatorio = new Random();
		int dia = aleatorio.nextInt(30) + 1;
		int mes = aleatorio.nextInt(12) + 1;
		int agno = aleatorio.nextInt(10) + 2000;
		this.nombre = nombre;
		this.fechaNacimiento = String.format("%02d/%02d/%04d", dia, mes, agno);
	}

	public int getCodigo() {
		return codigo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return 
			"Jugador [Código = " + codigo +
			", Nombre = " + nombre + 
			", FechaNacimiento = " + fechaNacimiento + 
			"]";
	}
	
}
