package actividades;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entrada.Teclado;
import modelo.Departamento;

public class AccesoDepartamento {
	public static void mostrarMenu() {
		System.out.println("\nMenú de Opciones:");
		System.out.println("0) Salir del programa.");
		System.out.println("1) Insertar un departamento en la base de datos.");
		System.out.println("2) Consultar todos los departamentos de la base de datos.");
		System.out.println("3) Consultar un departamento por nombre.");
		System.out.println("4) Actualizar un departamento por nombre.");
		System.out.println("5) Eliminar un departamento por nombre.");
	}

	////////////////////////////////////////////
	public static void consultarDepartamentos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/personal.odb");
		EntityManager conexion = null;
		try {
			conexion = emf.createEntityManager();
			TypedQuery<Departamento> consulta = conexion.createQuery("SELECT d FROM Departamento d",
					Departamento.class);
			List<Departamento> departamentos = consulta.getResultList();
			if (departamentos.size() == 0) {
				System.out.println("No hay ningun departamento en la base de datos.");
			} else {
				for (Departamento d : departamentos)
					System.out.println(d.toString());
				System.out.println("Se han consultado " + departamentos.size() + " jugadores de la base de datos");
			}
		} finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		emf.close();
	}

	////////////////////////////////////////////
	public static void consultarDepartamentosPorNombre(String nombre) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/personal.odb");
		EntityManager conexion = emf.createEntityManager();

		try {
			TypedQuery<Departamento> consulta = conexion
					.createQuery("SELECT d FROM Departamento d WHERE d.nombre = :nombre", Departamento.class);
			consulta.setParameter("nombre", nombre);

			List<Departamento> departamentos = consulta.getResultList();

			if (departamentos.isEmpty()) {
				System.out.println("No hay ningun departamento en la base de datos.");
			} else {
				for (Departamento d : departamentos)
					System.out.println(d.toString());
				System.out.println("Se han consultado " + departamentos.size() + " departamentos de la base de datos");
			}
		} finally {
			if (conexion != null && conexion.isOpen()) {
				conexion.close();
			}
		}
	}

	////////////////////////////////////////////
	public static void insertarDepartamento(Departamento departamento) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/personal.odb");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();

			entityManager.persist(departamento);

			transaction.commit();
			System.out.println("Departamento insertado correctamente.");
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
	}

	////////////////////////////////////////////
	public static void eliminarDepartamento(String nombre) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/personal.odb");
		EntityManager conexion = emf.createEntityManager();

		try {
			conexion.getTransaction().begin(); // Comienza la transacción

			TypedQuery<Departamento> consulta = conexion
					.createQuery("SELECT d FROM Departamento d WHERE d.nombre = :nombre", Departamento.class);
			consulta.setParameter("nombre", nombre);

			List<Departamento> departamentos = consulta.getResultList();

			if (departamentos.isEmpty()) {
				System.out.println("No hay ningún departamento con el nombre especificado en la base de datos.");
			} else {
				for (Departamento d : departamentos) {
					conexion.remove(d); // Elimina el departamento de la base de datos
					System.out.println("Se ha eliminado el departamento con éxito: " + d.toString());
				}
				System.out.println("Se han eliminado " + departamentos.size() + " departamento(s) de la base de datos");
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
	}

	////////////////////////////////////////////
	public static void actualizarDepartamento(int codigo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/personal.odb");
		EntityManager conexion = emf.createEntityManager();

		try {
			conexion.getTransaction().begin();

			Departamento departamento = conexion.find(Departamento.class, codigo);

			if (departamento == null) {
				System.out.println("No se encontró ningún departamento con el código " + codigo);
			} else {
				System.out.println("Departamento encontrado: " + departamento.toString());
				departamento.setNombre(Teclado.leerCadena("Nuevo nombre: "));
				departamento.setUbicacion(Teclado.leerCadena("Nueva ubicación: "));
				conexion.getTransaction().commit();
				System.out.println("Departamento actualizado correctamente.");
			}
		} catch (Exception e) {
			if (conexion.getTransaction().isActive()) {
				conexion.getTransaction().rollback();
			}
			System.out.println("Error al actualizar el departamento: " + e.getMessage());
		} finally {
			if (conexion != null && conexion.isOpen()) {
				conexion.close();
			}
		}
	}

}
