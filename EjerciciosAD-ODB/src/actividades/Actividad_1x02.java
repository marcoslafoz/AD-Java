package actividades;

import entrada.Teclado;

/*
ACTIVIDAD 1x02
Codifica una clase Actividad_1x02 que incluya un programa principal main. Este programa gestionará
los empleados de la base de datos personal mediante el siguiente menú de opciones:
0) Salir del programa.
1) Insertar un empleado en la base de datos. (El departamento tendrá que existir previamente.)
2) Consultar todos los empleados de la base de datos.
3) Consultar un empleado, por código, de la base de datos.
4) Actualizar un empleado, por código, de la base de datos.
5) Eliminar un empleado, por código, de la base de datos.

*/

public class Actividad_1x02 {
	public static void main(String[] args) {

		int opcion = 1;

		while (opcion != 0) {
			AccesoEmpleado.mostrarMenu();
			opcion = Teclado.leerEntero("Introduce una opción: ");

			switch (opcion) {
			case 0:
				System.out.println("Saliendo del programa.");
				break;
			
			case 1:
				AccesoEmpleado.insertarEmpleados();
				break;

			case 2:
				AccesoEmpleado.consultarEmpleados();
				break;

			default:
				System.out.println("\nNo existe esa opcion.");
				break;

			}
		}
	}
}
