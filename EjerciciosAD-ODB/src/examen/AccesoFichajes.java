package examen;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AccesoFichajes {
	public static void mostrarMenu() {
		System.out.println("\nMenú de Opciones:");
		System.out.println("0) Salir del programa.");
		System.out.println("1) Insertar un departamento en la base de datos.");
		System.out.println("2) Eliminar un departamento en la base de datos.");
		System.out.println("1) Actualizar un departamento en la base de datos.");
	}

	////////////////////////////////////////////
	public static boolean insertarDepartamento(Departamento departamento) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/fichajes.odb");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		boolean departamentoInsertado = false;

		try {
			transaction.begin();

			entityManager.persist(departamento);

			transaction.commit();
			departamentoInsertado = true;
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}

		emf.close();
		return departamentoInsertado;
	}

	////////////////////////////////////////////
	public static Empleado consultarEmpleadoPorNombre(String nombre) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/fichajes.odb");
		EntityManager conexion = emf.createEntityManager();

		Empleado empleado = null;

		try {
			TypedQuery<Empleado> consulta = conexion.createQuery("SELECT e FROM Empleado e WHERE e.nombre= :nombre",
					Empleado.class);
			consulta.setParameter("nombre", nombre);

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

	////////////////////////////////////////////
	public static Departamento consultarDepartamentoPorCodigo(int codigo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/fichajes.odb");
		EntityManager conexion = emf.createEntityManager();

		Departamento departamento = null;

		try {
			TypedQuery<Departamento> consulta = conexion
					.createQuery("SELECT d FROM Departamento d WHERE d.codigo= :codigo", Departamento.class);
			consulta.setParameter("codigo", codigo);

			List<Departamento> departamentos = consulta.getResultList();

			if (!departamentos.isEmpty())
				departamento = departamentos.get(0);

		} finally {
			if (conexion != null && conexion.isOpen()) {
				conexion.close();
			}
		}
		return departamento;

	}

	////////////////////////////////////////
	public static boolean actualizarEmpleado(String nombre, String nombreDpto, double presupuestoDpto) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/fichajes.odb");
		EntityManager conexion = emf.createEntityManager();

		boolean empleadoActualizado = false;

		try {

			conexion.getTransaction().begin();

			Empleado empleado = conexion.find(Empleado.class, nombre);

			if (empleado != null) {

				Departamento departamento = new Departamento(nombreDpto, presupuestoDpto);
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

	////////////////////////////////////////////
	public static List<Empleado> consultarEmpleadosPorDepartamento(Departamento departamento) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/fichajes.odb");
		EntityManager conexion = null;
		List<Empleado> empleados = null;
		try {
			conexion = emf.createEntityManager();
			TypedQuery<Empleado> consulta = conexion.createQuery(
					"SELECT e FROM Empleado e JOIN e.departamento d WHERE d = :departamento", Empleado.class);
			consulta.setParameter("departamento", departamento);
			empleados = consulta.getResultList();

		} finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		emf.close();
		return empleados;
	}

	////////////////////////////////////////////
	public static boolean eliminarDepartamento(int codigo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/fichajes.odb");
		EntityManager conexion = emf.createEntityManager();
		boolean departamentoEliminado = false;

		try {
			conexion.getTransaction().begin(); // Comienza la transacción

			TypedQuery<Departamento> consulta = conexion
					.createQuery("SELECT d FROM Departamento d WHERE d.codigo = :codigo", Departamento.class);
			consulta.setParameter("codigo", codigo);

			List<Departamento> departamentos = consulta.getResultList();

			if (departamentos.isEmpty()) {

			} else {
				for (Departamento d : departamentos) {
					conexion.remove(d); // Elimina el departamento de la base de datos
					departamentoEliminado = true;
				}

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
		return departamentoEliminado;
	}

}
