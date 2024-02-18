package acceso;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import entrada.Teclado;
import modelo.Departamento;
import modelo.Empleado;

public class Actividad_1x02 {

	// Escribe en consola el men� de opciones del programa principal.
	public static void escribirMenuOpciones() {
		System.out.println();
		System.out.println("(0) Salir del programa.");
		System.out.println("(1) Insertar un empleado en la base de datos.");
		System.out.println("(2) Consultar todos los empleados de la base de datos.");
		System.out.println("(3) Consultar un empleado, por c�digo, de la base de datos.");
		System.out.println("(4) Actualizar un empleado, por c�digo, de la base de datos.");
		System.out.println("(5) Eliminar un empleado, por c�digo, de la base de datos.");
		System.out.println();
	}

	// Gestiona los empleados de la base de datos personal con el men� de opciones:
	// (1) Insertar un empleado en la base de datos.
	// (2) Consultar todos los empleados de la base de datos.
	// (3) Consultar un empleado, por c�digo, de la base de datos.
	// (4) Actualizar un empleado, por c�digo, de la base de datos.
	// (5) Eliminar un empleado, por c�digo, de la base de datos.
	public static void main(String[] args) {
		int opcion;

		do {
			escribirMenuOpciones();
			opcion = Teclado.leerEntero("�Opci�n (0-5)? ");
			try {
				switch (opcion) {
				// Salir del programa.
				case 0:
					break;

				// Insertar un empleado en la base de datos.
				case 1:
					// (Departamento departamento, String nombre, String fechaAlta, BigDecimal
					// salario
					int codigoIns = Teclado.leerEntero("Introduce codigo del departamento donde quieres ponerlo");
					Departamento departamento = AccesoDepartamento.consultarUno(codigoIns);
					System.out.println(departamento.toString());
					if (departamento != null) {
						String nombre = Teclado.leerCadena("Introduce nombre");
						String fechaalta = Teclado.leerCadena("Fecha alta");
						double salario = Teclado.leerReal("Salario");
						// O puedes usar BigDecimal.valueOf(val)
						Empleado empleado = new Empleado(departamento, nombre, fechaalta, BigDecimal.valueOf(salario));
						empleado.setCodigo((short) 99);
						System.out.println(empleado.toString());
						AccesoEmpleado.insertarUno(empleado);
					}
					break;

				// Consultar todos los empleados de la base de datos.
				case 2:

					List<Empleado> listaEmpleados = AccesoEmpleado.consultarTodos();

					if (listaEmpleados.size() == 0) {
						System.out.println("No hay empleados en la base de datos.");
					} else {
						for (Empleado empleado : listaEmpleados) {
							System.out.println(empleado.toString());
						}
						System.out.println(
								"Se han consultado " + listaEmpleados.size() + " empleados de la base de datos.");
					}

					break;

				// Consultar un empleado, por c�digo, de la base de datos.
				case 3:
					Empleado empleado;
					int codigoCons = Teclado.leerEntero("Introduce codigo del empleado que quieres buscar:");
					empleado = AccesoEmpleado.consultarUno(codigoCons);
					System.out.println(empleado.toString());
					break;

				// Actualizar un empleado, por c�digo, de la base de datos.
				case 4:
					int codigoAct = Teclado.leerEntero("Introduce codigo del empleado que quieres actualizar");
					empleado = AccesoEmpleado.consultarUno(codigoAct);

					if (empleado != null) {
						String nuevonombre = Teclado.leerCadena("Nuevo nombre?");
						String nuevafechaalta = Teclado.leerCadena("Nueva fecha alta?");
						double nuevosalario = Teclado.leerReal("Nuevo salario?");

						empleado = new Empleado(empleado.getDepartamento(), nuevonombre, nuevafechaalta,
								BigDecimal.valueOf(nuevosalario));

						boolean actualizar = AccesoEmpleado.actualizarUno(codigoAct, empleado);
						if (actualizar) {
							System.out.println("Se ha actualizado un empleado");
						} else {
							System.out.println("No se ha podido actualizar el empleado");
						}
					} else {
						System.out.println("No existe ningun empleado con ese codigo");
					}

					break;

				// Eliminar un empleado, por c�digo, de la base de datos.
				case 5:
					int codigo = Teclado.leerEntero("Introduce codigo del empleado que quieres eliminar");
					empleado = AccesoEmpleado.consultarUno(codigo);

					if (empleado != null) {
						boolean eliminado = AccesoEmpleado.eliminarUno(codigo);
						if (eliminado) {
							System.out.println("Se ha eliminado un empleado");
						} else {
							System.out.println("No se ha podido eliminar el empleado");
						}
					} else {
						System.out.println("No existe ningun empleado con ese codigo");
					}
					break;
				case 6:
					//codigo = Teclado.leerEntero("Introduce codigo del departamento para actualizar los salarios de sus empleados");
					AccesoEmpleado.actualizarSalarioVarios(1);
					break;

				// opci�n de men� no v�lida
				default:
					System.out.println("La opci�n de men� debe estar comprendida entre 0 y 5.");
				}
			} catch (HibernateException he) {
				System.out.println("Error al acceder a la base de datos MySQL con Hibernate:");
				System.out.println(he.getMessage());
			}
		} while (opcion != 0);
		System.out.println("Programa finalizado sin errores.");
		HibernateUtil.closeSessionFactory();
	}

}
