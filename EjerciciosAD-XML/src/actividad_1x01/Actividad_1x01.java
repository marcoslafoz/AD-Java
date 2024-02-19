package actividad_1x01;

import java.util.List;

import entrada.Teclado;
import modelo.Producto;

public class Actividad_1x01 {
	public static void main(String[] args) {

		int opcion = -1;

		while (opcion != 0) {
			AccesoProducto.mostrarMenu();
			opcion = Teclado.leerEntero("Introduce una opción: ");

			switch (opcion) {
			case 0:

				System.out.println("Saliendo del programa.");
				break;

			///////////////// CONSULTAR TODOS LOS PRODUCTOS //////////////////
			case 2:

				List<Producto> listaProductosConsultar = AccesoProducto.consultarProductos();

				if (listaProductosConsultar.size() > 0) {
					for (Producto p : listaProductosConsultar) {
						System.out.println(p.toString());
					}
					System.out.println("Se han consultado " + listaProductosConsultar.size() + " productos");
				} else {
					System.out.println("No se han encontrado  productos");
				}

				break;

			//////////////////// CONSULTAR PRODUCTO POR CÓDIGO //////////////////////
			case 3:

				int codigoProductoConsultar = Teclado.leerEntero("Introduce el código del departamento a consultar");
				Producto productoConsultarPorCodigo = AccesoProducto
						.consultarProductoPorCodigo(codigoProductoConsultar);

				if (productoConsultarPorCodigo != null) {
					System.out.println(productoConsultarPorCodigo.toString());
					System.out.println("Se ha consultado 1 producto");
				} else {
					System.out.println("No se ha encontrado ningun producto");
				}

				break;

			//////////////////// DEFAULT //////////////////////
			default:
				System.out.println("\nOpcion inválida.");
				break;

			}
		}
	}
}
