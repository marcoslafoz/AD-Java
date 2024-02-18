package actividades1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import actividades2.Fecha;

public class AccesoJugador {
	public static void mostrarMenu() {
		System.out.println("\nMenú de Opciones:");
		System.out.println("0) Salir del programa.");
		System.out.println("1) Insertar un jugador en la base de datos.");
		System.out.println("2) Consultar todos los jugadores de la base de datos.");
		System.out.println("3) Consultar un jugador, por código, de la base de datos.");
		System.out.println("4) Actualizar un jugador, por código, de la base de datos.");
		System.out.println("5) Eliminar un jugador, por código, de la base de datos");
	}

	////////////////////////////////////////////
	public static boolean insertarJugador(Jugador jugador) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		boolean jugadorInsertado = false;

		try {

			transaction.begin();
			entityManager.persist(jugador);
			transaction.commit();
			jugadorInsertado = true;

		} catch (Exception e) {

			if (transaction != null && transaction.isActive())
				transaction.rollback();
			e.printStackTrace();

		} finally {
			if (entityManager != null && entityManager.isOpen())
				entityManager.close();
		}

		emf.close();
		return jugadorInsertado;
	}

	////////////////////////////////////////////
	public static List<Jugador> consultarJugadores() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = null;
		List<Jugador> jugadores = null;

		try {

			conexion = emf.createEntityManager();
			// TODO: "SELECT j FROM Jugador j ORDER BY j.nombre ASC"
			TypedQuery<Jugador> consulta = conexion.createQuery("SELECT j FROM Jugador j ORDER BY j.codigo DESC", Jugador.class);
			jugadores = consulta.getResultList();

		} finally {
			if (conexion != null)
				conexion.close();

		}

		emf.close();
		return jugadores;
	}

	////////////////////////////////////////////
	public static Jugador consultarJugadorPorCodigo(int codigo) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = emf.createEntityManager();

		Jugador jugador = null;

		try {
			TypedQuery<Jugador> consulta = conexion.createQuery("SELECT j FROM Jugador j WHERE j.codigo = :codigo",
					Jugador.class);
			consulta.setParameter("codigo", codigo);

			List<Jugador> jugadores = consulta.getResultList();

			if (!jugadores.isEmpty())
				jugador = jugadores.get(0);

		} finally {
			if (conexion != null && conexion.isOpen()) {
				conexion.close();
			}
		}
		return jugador;

	}

	////////////////////////////////////////////
	public static boolean actualizarJugador(int codigo, String nombreActualizar, Fecha fechaActualizar) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = emf.createEntityManager();

		boolean jugadorActualizado = false;

		try {
			conexion.getTransaction().begin();

			Jugador jugador = conexion.find(Jugador.class, codigo);

			if (jugador == null) {
				jugadorActualizado = false;
			} else {

				jugador.setNombre(nombreActualizar);
				jugador.setFechaNacimiento(fechaActualizar);
				conexion.getTransaction().commit();
				jugadorActualizado = true;
			}
		} catch (Exception e) {

			if (conexion.getTransaction().isActive())
				conexion.getTransaction().rollback();
			jugadorActualizado = false;

		} finally {
			if (conexion != null && conexion.isOpen())
				conexion.close();
		}

		return jugadorActualizado;
	}

	public static Equipo consultarEquipoDeJugador(Jugador jugador) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = null;
		Equipo equipo = null;
		try {
			conexion = emf.createEntityManager();
			// Se realiza una consulta para encontrar el equipo que contiene al jugador.
			TypedQuery<Equipo> consulta = conexion
					.createQuery("SELECT e FROM Equipo e JOIN e.jugadores j WHERE j = :jugador", Equipo.class);
			consulta.setParameter("jugador", jugador);
			equipo = consulta.getSingleResult();
		} catch (Exception e) {
			// En caso de que no se encuentre ningún equipo con el jugador, se maneja la
			// excepción y se retorna null.
			// Esto indica que el jugador no está asignado a ningún equipo.
		} finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		emf.close();
		return equipo;
	}

	////////////////////////////////////////////
	public static boolean eliminarJugador(int codigoJugador) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = emf.createEntityManager();
		boolean jugadorEliminado = false;

		try {
			conexion.getTransaction().begin();
			Jugador jugador = conexion.find(Jugador.class, codigoJugador);
			if (jugador != null) {
				conexion.remove(jugador);
				jugadorEliminado = true;
			}
			conexion.getTransaction().commit();
		} catch (Exception e) {
			if (conexion.getTransaction().isActive()) {
				conexion.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (conexion != null && conexion.isOpen()) {
				conexion.close();
			}
		}

		return jugadorEliminado;
	}

}
