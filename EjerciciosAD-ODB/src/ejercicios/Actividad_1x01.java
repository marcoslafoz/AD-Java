package ejercicios;

import entrada.Teclado;
import modelo.Departamento;


public class Actividad_1x01 {
	public static void main(String[] args) {

		int opcion = 1;

		while (opcion != 0) {
			AccesoDepartamento.mostrarMenu();
			opcion = Teclado.leerEntero("Introduce una opción: ");

			switch (opcion) {
			case 0:
				System.out.println("Saliendo del programa.");
				break;

			case 1:
				Departamento departamentoInsertar = new Departamento();
				departamentoInsertar.setNombre(Teclado.leerCadena("Introduce el nombre: "));
				departamentoInsertar.setUbicacion(Teclado.leerCadena("Introduce la ubicación: "));
				AccesoDepartamento.insertarDepartamento(departamentoInsertar);
				break;

			case 2:
				AccesoDepartamento.consultarDepartamentos();
				break;

			case 3:
				AccesoDepartamento.consultarDepartamentosPorNombre(
						Teclado.leerCadena("Introduce el nombre del departamento a consultar: "));

				break;

			}
		}
	}
}
