package actividades;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entrada.Teclado;
import modelo.Departamento;
import modelo.Empleado;

public class AccesoEmpleado {
	public static void mostrarMenu() {
		System.out.println("\nMenú de Opciones:");
		System.out.println("0) Salir del programa.");
		System.out.println(
				"1) Insertar un empleado en la base de datos. (El departamento tendrá que existir previamente.)");
		System.out.println("2) Consultar todos los empleados de la base de datos.");
		System.out.println("3) Consultar un empleado, por código, de la base de datos.");
		System.out.println("4) Actualizar un empleado, por código, de la base de datos.");
		System.out.println("5) Eliminar un empleado, por código, de la base de datos.");
	}

	////////////////////////////////////////////
	public static void consultarEmpleados() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/personal.odb");
		EntityManager conexion = null;
		try {
			conexion = emf.createEntityManager();
			TypedQuery<Empleado> consulta = conexion.createQuery("SELECT e FROM Empleado e", Empleado.class);
			List<Empleado> empleados = consulta.getResultList();
			if (empleados.size() == 0) {
				System.out.println("No hay ningun empleado en la base de datos.");
			} else {
				for (Empleado e : empleados)
					System.out.println(e.toString());
				System.out.println("Se han consultado " + empleados.size() + " empleados de la base de datos");
			}
		} finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		emf.close();
	}

	public static void insertarEmpleados() {

		int codigoDptoEmpleado = Teclado.leerEntero("Codigo departamento: ");
		Departamento dptoEmpleado = AccesoDepartamento.consultarDepartamentosPorCodigo(codigoDptoEmpleado);

		if (dptoEmpleado != null) {

			Empleado empleado = new Empleado();

			empleado.setNombre(Teclado.leerCadena("Nombre: "));
			empleado.setFechaAlta(Teclado.leerCadena("Fecha alta: "));
			empleado.setSalario(Teclado.leerReal("Salario: "));
			empleado.setDepartamento(dptoEmpleado);

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/personal.odb");
			EntityManager entityManager = emf.createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();

			try {
				transaction.begin();

				entityManager.persist(empleado);

				transaction.commit();
				System.out.println("Empleado insertado correctamente.");
			} catch (Exception e) {
				if (transaction != null && transaction.isActive()) {
					transaction.rollback();
				}
				// e.printStackTrace();
			} finally {
				if (entityManager != null && entityManager.isOpen()) {
					entityManager.close();
				}
			}

			emf.close();
		} else {
			System.out.println("No existe ningun departamento con id = " + codigoDptoEmpleado);
		}

	}

}
