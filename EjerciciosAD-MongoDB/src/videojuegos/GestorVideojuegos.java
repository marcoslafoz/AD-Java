package videojuegos;

import java.util.List;

import org.bson.Document;

import entrada.Teclado;;

public class GestorVideojuegos {
	public static void main(String[] args) {

		try {
			int opcion = -1;

			while (opcion != 0) {
				System.out.println(AccesoVideojuegos.mostrarMenu());
				opcion = Teclado.leerEntero("Introduce una opci�n: ");

				switch (opcion) {
				case 0:

					System.out.println("Saliendo del programa.");
					break;

				///////////////// OPCI�N 1 //////////////////
				case 1:
					break;

				//////////////////// DEFAULT //////////////////////
				default:
					System.out.println("\nOpcion inv�lida.");
					break;

				}
			}
		} catch (Exception e) {
			System.out.println("Error al acceder a la base de datos");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
