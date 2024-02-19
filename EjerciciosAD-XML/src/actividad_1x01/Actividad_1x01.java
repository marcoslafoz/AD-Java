package actividad_1x01;

import entrada.Teclado;

public class Actividad_1x01 {
	public static void main(String[] args) {

		int opcion = 1;

		while (opcion != 0) {
			AccesoProducto.mostrarMenu();
			opcion = Teclado.leerEntero("Introduce una opción: ");

			switch (opcion) {
			case 0:

				System.out.println("Saliendo del programa.");
				break;

			case 2:

				AccesoProducto.consultarProductos();

				break;

			case 3: 
				
				AccesoProducto.consultarProductoPorCodigo();
				break;
				
			default:
				System.out.println("\nOpcion inválida.");
				break;

			}
		}
	}
}
