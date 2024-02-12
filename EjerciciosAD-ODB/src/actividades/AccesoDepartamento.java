package actividades;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
			TypedQuery<Departamento> consulta = conexion.createQuery("SELECT d FROM Departamento d", Departamento.class);
			List<Departamento> departamentos = consulta.getResultList();
			if (departamentos.size() == 0) {
				System.out.println("No hay ningun departamento en la base de datos.");
			} else {
				for (Departamento d : departamentos) {
					System.out.println(d.toString());
				}
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
            TypedQuery<Departamento> consulta = conexion.createQuery(
                    "SELECT d FROM Departamento d WHERE d.nombre = :nombre", Departamento.class);
            consulta.setParameter("nombre", nombre);

            List<Departamento> departamentos = consulta.getResultList();

            if (departamentos.isEmpty()) {
                System.out.println("No hay ningun departamento en la base de datos.");
            } else {
                for (Departamento d : departamentos) {
                    System.out.println(d.toString());
                }
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
            //e.printStackTrace();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

        emf.close();
    }
}
