package actividades1;

import java.util.List;

import entrada.Teclado;
import modelo.Fecha;
import modelo.Jugador;

public class Actividad_1x01 {
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

				System.out.println("Introduce la fecha de nacimiento");
				int diaInsertar = Teclado.leerEntero("Introduce el dia: ");
				int mesInsertar = Teclado.leerEntero("Introduce el mes: ");
				int anyoInsertar = Teclado.leerEntero("Introduce el año: ");
				String nombreInsertar = Teclado.leerCadena("Introduce el nombre: ");

				Fecha fechaInsertar = new Fecha(diaInsertar, mesInsertar, anyoInsertar);
				Jugador jugadorInsertar = new Jugador(nombreInsertar, fechaInsertar);

				boolean jugadorInsertado = AccesoJugador.insertarJugador(jugadorInsertar);

				if (jugadorInsertado) {
					System.out.println("Jugador insertado correctamente");
				} else {
					System.out.println("Error al insertar un jugador");
				}

				break;

			case 2:

				List<Jugador> listaJugadoresConsultar = AccesoJugador.consultarJugadores();

				if (listaJugadoresConsultar.size() == 0) {
					System.out.println("No hay ningun jugador en la base de datos.");
				} else {
					for (Jugador j : listaJugadoresConsultar) {
						System.out.println(j.toString());
					}
					System.out.println(
							"Se han consultado " + listaJugadoresConsultar.size() + " jugadores de la base de datos");
				}

				break;

			case 3:

				int codigoJugadorConsultar = Teclado.leerEntero("Introduce en codigo del jugador a consultar: ");
				Jugador jugadorConsultadoPorCodigo = AccesoJugador.consultarJugadorPorCodigo(codigoJugadorConsultar);

				if (jugadorConsultadoPorCodigo != null) {
					System.out.println(jugadorConsultadoPorCodigo.toString());
				} else {
					System.out.println("No se ha encontrado ningun jugador");
				}

				break;

			case 4:

				int codigoJugadorActualizar = Teclado.leerEntero("Código de jugador a actualizar:");
				int diaActualizar = Teclado.leerEntero("Introduce el dia: ");
				int mesActualizar = Teclado.leerEntero("Introduce el mes: ");
				int anyoActualizar = Teclado.leerEntero("Introduce el año: ");
				String nombreActualizar = Teclado.leerCadena("Introduce el nombre: ");

				Fecha fechaActualizar = new Fecha(diaActualizar, mesActualizar, anyoActualizar);

				boolean jugadorActualizado = AccesoJugador.actualizarJugador(codigoJugadorActualizar, nombreActualizar,
						fechaActualizar);

				if (jugadorActualizado) {
					System.out.println("Se ha actualizado un jugador de la base de datos.");
				} else {
					System.out.println("No existe ningún jugador con ese código en la base de datos");
				}

				break;

			default:
				System.out.println("\nLa opción de menú debe estar comprendida entre 0 y 5.");
				break;

			}
		}
	}
}
