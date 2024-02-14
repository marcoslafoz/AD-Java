package actividades2;

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
		System.out.println("1) Insertar un empleado en la base de datos. (El departamento tendrá que existir previamente.)");
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

	////////////////////////////////////////////
	public static Empleado consultarEmpleadoPorCodigo(int codigo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/personal.odb");
		EntityManager conexion = emf.createEntityManager();

		Empleado empleado = null;

		try {
			TypedQuery<Empleado> consulta = conexion.createQuery("SELECT e FROM Empleado e WHERE e.codigo = :codigo",
					Empleado.class);
			consulta.setParameter("codigo", codigo);

			List<Empleado> empleados = consulta.getResultList();

			if (!empleados.isEmpty())
				empleado = empleados.get(0);

		} finally {
			if (conexion != null && conexion.isOpen()) {
				conexion.close();
			}
		}
		return empleado;

	}

	public static boolean eliminarEmpleado(int codigo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/personal.odb");
		EntityManager conexion = emf.createEntityManager();

		boolean empleadoEliminado = false;

		try {
			conexion.getTransaction().begin(); // Comienza la transacción

			TypedQuery<Empleado> consulta = conexion.createQuery("SELECT e FROM Empleado e WHERE e.codigo = :codigo",
					Empleado.class);
			consulta.setParameter("codigo", codigo);

			List<Empleado> empleados = consulta.getResultList();

			if (empleados.isEmpty()) {
				// System.out.println("No hay ningún departamento con el nombre especificado en
				// la base de datos.");
			} else {
				for (Empleado e : empleados) {
					conexion.remove(e); // Elimina el departamento de la base de datos
					// System.out.println("Se ha eliminado el empleado : " + e.toString());

				}
				empleadoEliminado = true;
				// System.out.println("Se han eliminado " + empleados.size() + " departamento(s)
				// de la base de datos");
			}

			conexion.getTransaction().commit(); // Confirma la transacción
		} catch (Exception e) {
			if (conexion.getTransaction().isActive()) {
				conexion.getTransaction().rollback(); // Si hay un error, se revierte la transacción
			}
			e.printStackTrace();
		} finally {

			if (conexion != null && conexion.isOpen()) {
				conexion.close(); // Cierra la conexión
			}

		}
		return empleadoEliminado;
	}

	////////////////////////////////////////////
	public static boolean actualizarEmpleado(int codigo, String nombre, String fechaAlta, double salario,
			Departamento departamento) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/personal.odb");
		EntityManager conexion = emf.createEntityManager();

		boolean empleadoActualizado = false;

		try {

			conexion.getTransaction().begin();

			Empleado empleado = conexion.find(Empleado.class, codigo);

			if (empleado != null) {
				empleado.setNombre(nombre);
				empleado.setFechaAlta(fechaAlta);
				empleado.setSalario(salario);
				empleado.setDepartamento(departamento);
				conexion.getTransaction().commit();
				empleadoActualizado = true;
			}

		} catch (Exception e) {

			if (conexion.getTransaction().isActive())
				conexion.getTransaction().rollback();
			empleadoActualizado = false;
			System.out.println("Error al actualizar el departamento: " + e.getMessage());

		} finally {

			if (conexion != null && conexion.isOpen())
				conexion.close();

		}

		return empleadoActualizado;
	}

}
