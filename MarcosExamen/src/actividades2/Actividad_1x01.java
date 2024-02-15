package actividades2;

import entrada.Teclado;
import modelo.Departamento;

/*
ACTIVIDAD 1x01
Codifica una clase Actividad_1x01 que incluya un programa principal main. Este programa gestionará
los departamentos de la base de datos personal mediante el siguiente menú de opciones:
0) Salir del programa.
1) Insertar un departamento en la base de datos.
2) Consultar todos los departamentos de la base de datos.
3) Consultar un departamento, por clave, de la base de datos.
4) Actualizar un departamento, por clave, de la base de datos.
5) Eliminar un departamento, por clave, de la base de datos.
*/

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

			case 4:
				AccesoDepartamento.actualizarDepartamento(
						Teclado.leerEntero("Introduce el codigo del departamento a actualizar: "));
				break;

			case 5:
				AccesoDepartamento
						.eliminarDepartamento(Teclado.leerCadena("Introduce en nombre del departamento a eliminar: "));
				break;

			default:
				System.out.println("\nNo existe esa opcion.");
				break;

			}
		}
	}
}
