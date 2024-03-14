package biblioteca;

import java.util.List;

import org.bson.Document;

import entrada.Teclado;;

public class GestorBiblioteca {
	public static void main(String[] args) {
		
		
		
		try {
			int opcion = -1;

			while (opcion != 0) {
				System.out.println(AccesoBiblioteca.mostrarMenu()); 
				opcion = Teclado.leerEntero("Introduce una opción: ");

				switch (opcion) {
				case 0:

					System.out.println("Saliendo del programa.");
					break;

				///////////////// CONSULTAR TODOS LOS PRODUCTOS //////////////////
				
				case 2:

					List<Libro> resultados = null;
					
					resultados = AccesoBiblioteca.consultarLibros();
					
					if (resultados.isEmpty()) {
						System.out.println("No se ha encontrado ningún libro.");
					} else {
						
						for(Libro l : resultados) {
							System.out.println(l.toString());
						}
						
						System.out.println("Se han consultado " + resultados.size() + " libros.");
					}

					break;



				//////////////////// DEFAULT //////////////////////
				default:
					System.out.println("\nOpcion inválida.");
					break;

				}
			}
		} catch (Exception e) {
			System.out.println("Error al acceder a la base de datos eXist-db:");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
