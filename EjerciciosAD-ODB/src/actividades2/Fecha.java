package actividades2;

import java.util.Random;
import javax.persistence.Embeddable;

// Clase Fecha que contiene d�a, mes y a�o.
@Embeddable
public class Fecha implements Comparable<Fecha> {

	// atributos de una fecha
	private int dia;
	private int mes;
	private int agno;

	// Crea una fecha a partir de 3 par�metros.
	public Fecha(int dia, int mes, int agno) {
		this.dia = dia;
		this.mes = mes;
		this.agno = agno;
	}

	// Crea una fecha por defecto de forma aleatoria.
	public Fecha() {
		Random aleatorio = new Random();
		this.agno = aleatorio.nextInt(2005-1995+1) + 1995; // entre 1995 y 2005
		this.mes = aleatorio.nextInt(12) + 1;              // entre 1 y 12
		int numeroDiasMes = obtenerNumeroDiasDeMes(this.mes, this.agno);
		this.dia = aleatorio.nextInt(numeroDiasMes) + 1;   // entre 1 y n�mero de d�as de mes
	}

	// Devuelve verdadero si un a�o es bisiesto.
	// Devuelve falso en caso contrario.
	// Un a�o es bisiesto si cumple dos condiciones:
	// - El a�o es divisible entre 4.
	// - El a�o no es divisible entre 100 o el a�o es divisible entre 400.
	private static boolean esBisiesto(int agno) {
		boolean bisiesto = false;
		if (agno >= 1583) {
			if ((agno % 4 == 0) && (agno % 100 != 0 || agno % 400 == 0)) {
				bisiesto = true;
			}
		}
		return bisiesto;
	}

	// Devuelve el n�mero de d�as que tiene un mes de un a�o:
	// - Febrero tiene 28 d�as si el a�o no es bisiesto.
	// - Febrero tiene 29 d�as si el a�o es bisiesto.
	// - Abril, Junio, Septiembre y Noviembre tienen 30 d�as.
	// - Enero, Marzo, Mayo, Julio, Agosto, Octubre y Diciembre tienen 31 d�as.
	private static int obtenerNumeroDiasDeMes(int mes, int agno) {
		int numeroDiasMes = 31;
		if (mes == 2) {
			if (esBisiesto(agno)) {
				numeroDiasMes = 29;
			} 
			else {
				numeroDiasMes = 28;
			}
		}
		else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
			numeroDiasMes = 30;
		}
		return numeroDiasMes;
	}

	// Valida los datos de una fecha seg�n las condiciones:
	// - Se sigue el calendario gregoriano, que se basa en a�os bisiestos y a�os no bisiestos.
	// - El a�o debe estar comprendido entre 1583 y 2999.
	// - El mes debe estar comprendido entre 1 y 12.
	// - El d�a debe estar comprendido entre 1 y el n�mero de d�as que tiene el mes del a�o.
	// Devuelve verdadero si los datos de una fecha son v�lidos.
	// Devuelve falso en caso contrario.
	public static boolean esValida(int dia, int mes, int agno) {
		boolean fechaValida = true;
		if (agno < 1583 || agno > 2999) {
			fechaValida = false;
		}
		else if (mes < 1 || mes > 12) {
			fechaValida = false;
		}
		else if (dia < 1 || dia > obtenerNumeroDiasDeMes(mes, agno)) {
			fechaValida = false;
		}
		return fechaValida;
	}

	// Devuelve una cadena de caracteres con el estado de la fecha.
	// Utiliza el formato "DD/MM/AAAA".
	@Override
	public String toString() {
		return 
			String.format("%02d/%02d/%04d", this.dia, this.mes, this.agno);
	}

	// Compara esta fecha con otra fecha 
	// por a�o ascendente, mes ascendente y d�a ascendente.
	@Override
	public int compareTo(Fecha otra) {
		String estaFecha = String.format("%04d%02d%02d", 
		                                 this.agno, this.mes, this.dia);
		String otraFecha = String.format("%04d%02d%02d", 
		                                 otra.agno, otra.mes, otra.dia);
		return estaFecha.compareTo(otraFecha);
	}

}
