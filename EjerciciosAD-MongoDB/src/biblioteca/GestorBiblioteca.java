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

				///////////////// INSERTAR LIBRO //////////////////
				case 1:

					Libro libroInsertar = new Libro(Teclado.leerEntero("Codigo: "), Teclado.leerCadena("Titulo: "),
							Teclado.leerEntero("Año: "), Teclado.leerCadena("Genero: "));

					boolean libroInsertartado = AccesoBiblioteca.insertarLibro(libroInsertar);

					if (libroInsertartado == true) {
						System.out.println("Libro insertado con exito");
					} else {
						System.out.println("Libro no insertado");
					}

					break;

				///////////////// CONSULTAR TODOS LOS LIBROS //////////////////
				case 2:

					List<Libro> resultados = AccesoBiblioteca.consultarLibros();

					if (resultados.isEmpty()) {
						System.out.println("No se ha encontrado ningún libro.");
					} else {

						for (Libro l : resultados) {
							System.out.println(l.toString());
						}

						System.out.println("Se han consultado " + resultados.size() + " libros.");
					}

					break;

				///////////////// CONSULTAR LIBRO POR CODIGO //////////////////
				case 3:

					Libro libroConsultadoPorCodigo = AccesoBiblioteca
							.consultarLibroPorCodigo(Teclado.leerEntero("Codigo de libro a consultar: "));

					if (libroConsultadoPorCodigo != null) {
						System.out.println(libroConsultadoPorCodigo.toString());
					} else {
						System.out.println("No se ha consultado ningun libro");
					}

					break;

				///////////////// ACTUALIZAR LIBRO //////////////////
				case 4:

					Libro libroActualizar = new Libro(Teclado.leerEntero("Codigo: "), Teclado.leerCadena("Titulo: "), Teclado.leerEntero("Año: "), Teclado.leerCadena("Genero: "));
					long librosActualizados = AccesoBiblioteca.actualizarLibro(libroActualizar);
					
					if(librosActualizados > 0) {
						System.out.println("Libro insertado con exito");
					}else {
						System.out.println("El libro no se ha actualizado");
					}
					
					break;

				///////////////// ELIMINAR LIBRO POR CODIGO //////////////////
				case 5:

					if (AccesoBiblioteca.eliminarLibroPorCodigo(Teclado.leerEntero("Codigo libro a eliminar: ")) > 0) {
						System.out.println("Libro eliminado correctamente");
					} else {
						System.out.println("No se ha eliminado ningún libro");
					}

					break;

				//////////////////// DEFAULT //////////////////////
				default:
					System.out.println("\nOpcion inválida.");
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
