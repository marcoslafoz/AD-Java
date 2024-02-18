package actividades1;

import entrada.Teclado;
import modelo.Equipo;


public class Actividad_1x02 {
	public static void main(String[] args) {

		int opcion = 1;

		while (opcion != 0) {
			AccesoJugador.mostrarMenu();
			opcion = Teclado.leerEntero("Introduce una opción: ");

			switch (opcion) {
			case 0:
				System.out.println("Saliendo del programa.");
				break;

			case 1:

				AccesoEquipo.mostrarEquiposConJugadores();

				break;

				case 2:
				Equipo equipoConsultado = AccesoEquipo.consultarEquipoPorCodigo(1);
				System.out.println(equipoConsultado.toString()); 
				break;

			

			default:
				System.out.println("\nLa opción de menú debe estar comprendida entre 0 y 5.");
				break;

			}
		}
	}
}
