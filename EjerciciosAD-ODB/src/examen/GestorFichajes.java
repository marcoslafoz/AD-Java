package examen;

import java.util.List;
import entrada.Teclado;

public class GestorFichajes {
	public static void main(String[] args) {

		int opcion = 1;

		while (opcion != 0) {
			AccesoFichajes.mostrarMenu();
			opcion = Teclado.leerEntero("Introduce una opción: ");

			switch (opcion) {
			case 0:
				System.out.println("Saliendo del programa.");
				break;

			// INSERTAR DEPARTAMENTO
			case 1:

				String denominacionDptoIns = Teclado.leerCadena("Introduce la denominacion: ");
				double presupuestoDptoIns = Teclado.leerNatural("Introduce el presupuesto: ");

				Departamento departamentoInsertar = new Departamento(denominacionDptoIns, presupuestoDptoIns);

				boolean departamentoInsertado = AccesoFichajes.insertarDepartamento(departamentoInsertar);

				if (departamentoInsertado) {
					System.out.println("Se ha insertado un departamento en la base de datos");
				} else {
					System.out.println("Error al insertar un departamento");
				}

				break;

			// ELIMINAR UN DEPARTAMENTO
			case 2:

				int codigoDepartamentoEliminar = Teclado.leerEntero("Introduce el codigo del departamento");
				Departamento departamentoEliminar = AccesoFichajes
						.consultarDepartamentoPorCodigo(codigoDepartamentoEliminar);

				List<Empleado> listaEmpleados = AccesoFichajes.consultarEmpleadosPorDepartamento(departamentoEliminar);

				if (listaEmpleados.size() > 0) {
					System.out.println("Se han encontrado " + listaEmpleados.size() + " empleados relacionados");
					for (Empleado e : listaEmpleados)
						System.out.println(e.toString());

				} else {
					boolean departamentoEliminado = AccesoFichajes.eliminarDepartamento(codigoDepartamentoEliminar);

					if (departamentoEliminado) {
						System.out.println("Se ha eliminado un departamento de la base de datos ");
					} else {
						System.out.println("No existe ningun departamento con ese codigo en la base de datoss");
					}
				}

				break;

			// ACTUALIZAR UN DEPARTAMENTO
			case 3:

				String nombreEmpleadoActualizar = Teclado.leerCadena("Introduce el nombre del empleado a actualizar: ");
				Empleado empleadoConsultar = AccesoFichajes.consultarEmpleadoPorNombre(nombreEmpleadoActualizar);

				if (empleadoConsultar != null) {

					Departamento departamentoConsultar = AccesoFichajes.consultarDepartamentoPorCodigo(
							Teclado.leerEntero("Introduce el codigo del departamento: "));

					if (departamentoConsultar != null) {

						String nombreDepartamentoActualizar = Teclado
								.leerCadena("Introduce el nombre del departamento: ");
						double presupuestoDepartamentoActualizar = Teclado
								.leerReal("Introduce el presupuesto del departamento: ");

						boolean empleadoActualizado = AccesoFichajes.actualizarEmpleado(nombreEmpleadoActualizar,
								nombreDepartamentoActualizar, presupuestoDepartamentoActualizar);

						if (empleadoActualizado) {
							System.out.println("Empleado actualizado correctamente");
						} else {
							System.out.println("Error al actualizar el empleado");
						}

					} else {
						System.out.println("No existe ningun departamento con ese codigo en la base de datos");
					}
				} else {
					System.out.println("No existe un empleado con ese nombre en la base de datos");

				}

				break;

			default:
				System.out.println("\nNo existe esa opcion.");
				break;

			}
		}
	}
}
