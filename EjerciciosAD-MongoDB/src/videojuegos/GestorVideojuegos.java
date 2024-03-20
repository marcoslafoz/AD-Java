package videojuegos;

import java.util.List;
import entrada.Teclado;

public class GestorVideojuegos {
	public static void main(String[] args) {

		try {
			int opcion = -1;

			while (opcion != 0) {
				System.out.println(AccesoVideojuegos.mostrarMenu());
				opcion = Teclado.leerEntero("Introduce una opción: ");

				switch (opcion) {
				case 0:

					System.out.println("Saliendo del programa.");
					break;

				///////////////// INSERTAR ELEMENTO //////////////////
				case 1:

					Videojuego elementoInsertar = new Videojuego(Teclado.leerEntero("Codigo: "),
							Teclado.leerCadena("Titulo: "), Teclado.leerEntero("Año: "),
							Teclado.leerCadena("Desarrollador: "), Teclado.leerReal("Precio: "));

					boolean elementoInsertartado = AccesoVideojuegos.insertarElemento(elementoInsertar);

					if (elementoInsertartado == true) {
						System.out.println("Elemento insertado con exito");
					} else {
						System.out.println("Elemento no insertado");
					}

					break;

				///////////////// CONSULTAR TODOS LOS ELEMENTOS //////////////////
				case 2:

					List<Videojuego> resultadosConsultarTodos = AccesoVideojuegos.consultarTodos();

					if (resultadosConsultarTodos.isEmpty()) {
						System.out.println("No se ha encontrado ningún elemento.");
					} else {

						for (Videojuego e : resultadosConsultarTodos) {
							System.out.println(e.toString());
						}

						System.out.println("Se han consultado " + resultadosConsultarTodos.size() + " elemento.");
					}

					break;

				///////////////// CONSULTAR ELEMENTO POR CODIGO //////////////////
				case 3:

					Videojuego elementoConsultadoPorCodigo = AccesoVideojuegos
							.consultarElementoPorCodigo(Teclado.leerEntero("Codigo de libro a consultar: "));

					if (elementoConsultadoPorCodigo != null) {
						System.out.println(elementoConsultadoPorCodigo.toString());
					} else {
						System.out.println("No se ha consultado ningun elemento");
					}

					break;

				///////////////// ACTUALIZAR ELEMENTO //////////////////
				case 4:

					Videojuego elementoActualizar = new Videojuego(Teclado.leerEntero("Codigo: "),
							Teclado.leerCadena("Titulo: "), Teclado.leerEntero("Año: "),
							Teclado.leerCadena("Desarrollador: "), Teclado.leerReal("Precios: "));

					long elementosActualizados = AccesoVideojuegos.actualizarElemento(elementoActualizar);

					if (elementosActualizados > 0) {
						System.out.println("Elemento insertado con exito");
					} else {
						System.out.println("El Elemento no se ha podido actualizado");
					}

					break;

				///////////////// ELIMINAR LIBRO POR CODIGO //////////////////
				case 5:

					long elementoEliminarPorCodigo = AccesoVideojuegos
							.eliminarElementoPorCodigo(Teclado.leerEntero("Codigo elemento a eliminar: "));

					if (elementoEliminarPorCodigo > 0) {
						System.out.println("Elemento eliminado correctamente");
					} else {
						System.out.println("No se ha eliminado ningún elemento");
					}

					break;

				///////////////// CONSULTAR CODIGO MAXIMO //////////////////
				case 6:

					System.out.println(AccesoVideojuegos.consultarCodigoMaximo());

					break;

				///////////////// CONSULTAR ELEMENTOS FILTRADOS //////////////////
				case 7:

					List<Videojuego> resultadosFiltrados = AccesoVideojuegos.consultarElementosFiltrados(10, 30);

					if (resultadosFiltrados.isEmpty()) {
						System.out.println("No se ha encontrado ningún elemento.");
					} else {

						for (Videojuego ef : resultadosFiltrados) {
							System.out.println(ef.toString());
						}

						System.out.println("Se han consultado " + resultadosFiltrados.size() + " elemento.");
					}

					break;

				///////////////// CONSULTAR ELEMENTOS FILTRADOS POR AÑO//////////////////
				case 8:

					List<Videojuego> resultadosFiltradosPorAgno = AccesoVideojuegos.consultarPorAgno(2013);

					if (resultadosFiltradosPorAgno.isEmpty()) {
						System.out.println("No se ha encontrado ningún elemento.");
					} else {

						for (Videojuego ef : resultadosFiltradosPorAgno) {
							System.out.println(ef.toString());
						}

						System.out.println("Se han consultado " + resultadosFiltradosPorAgno.size() + " elemento.");
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
