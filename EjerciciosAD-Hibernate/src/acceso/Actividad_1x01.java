package acceso;

import java.util.List;

import org.hibernate.HibernateException;
import entrada.Teclado;
import modelo.Departamento;

public class Actividad_1x01 {

	// Escribe en consola el men� de opciones del programa principal.
	public static void escribirMenuOpciones() {
		System.out.println();
		System.out.println("(0) Salir del programa.");
		System.out.println("(1) Insertar un departamento en la base de datos.");
		System.out.println("(2) Consultar todos los departamentos de la base de datos.");
		System.out.println("(3) Consultar un departamento, por c�digo, de la base de datos.");
		System.out.println("(4) Actualizar un departamento, por c�digo, de la base de datos.");
		System.out.println("(5) Eliminar un departamento, por c�digo, de la base de datos.");
		System.out.println();
	}

	// Gestiona los departamentos de la base de datos personal con el men� de
	// opciones:
	// (1) Insertar un departamento en la base de datos.
	// (2) Consultar todos los departamentos de la base de datos.
	// (3) Consultar un departamento, por c�digo, de la base de datos.
	// (4) Actualizar un departamento, por c�digo, de la base de datos.
	// (5) Eliminar un departamento, por c�digo, de la base de datos.
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

				// Insertar un departamento en la base de datos.
				case 1:

					Departamento departamentoInsertar = new Departamento(Teclado.leerCadena("�Nombre? "),
							Teclado.leerCadena("�Ubicaci�n? "));
					AccesoDepartamento.insertarUno(departamentoInsertar);
					break;

				// AccesoDepartamento.consultarTodos();
				case 2:

					List<Departamento> listaDepartamentos = AccesoDepartamento.consultarTodos();

					if (listaDepartamentos.size() == 0) {
						System.out.println("No hay departamentos en la base de datos.");
					} else {
						for (Departamento departamento : listaDepartamentos) {
							System.out.println(departamento.toString());
						}
						System.out.println("Se han consultado " + listaDepartamentos.size()
								+ " departamentos de la base de datos.");
					}

					break;

				// Consultar un departamento, por c�digo, de la base de datos.
				case 3:
					int codigoConsulta = Teclado.leerEntero("Ingrese el código del departamento a consultar: ");

					Departamento departamentoConsultado = AccesoDepartamento.consultarUno(codigoConsulta);

					if (departamentoConsultado != null) {
						System.out.println("Departamento encontrado: " + departamentoConsultado.toString());
					} else {
						System.out.println("No se encontró ningún departamento con el código " + codigoConsulta);
					}

					// Actualizar un departamento, por c�digo, de la base de datos.
				case 4:
					int codDeptActualizar = Teclado.leerEntero("Codigo dept: ");
					Departamento nuevoDepartamento = new Departamento();
					nuevoDepartamento.setNombre(Teclado.leerCadena("Nombre?: "));
					nuevoDepartamento.setUbicacion(Teclado.leerCadena("Ubicacion?:"));

					AccesoDepartamento.actualizarUno(codDeptActualizar, nuevoDepartamento);
					break;

				// Eliminar un departamento, por c�digo, de la base de datos.
				case 5:
					AccesoDepartamento.eliminar(Teclado.leerEntero("Introduce el codigo de Departamento a eliminar:"));
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
		HibernateUtil.closeSessionFactory(); // Cerrar la fábrica de sesiones al final de la aplicación.
		System.out.println("Programa finalizado sin errores.");
	}

}
