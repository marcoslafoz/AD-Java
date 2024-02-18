package actividades1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import actividades2.Fecha;

public class CrearBDCampeonato {

	private static final String NOMBRE_BD_CAMPEONATO = "data/campeonato.odb";

	public static void main(String[] args) {
		EntityManagerFactory fabricaConexiones = Persistence.createEntityManagerFactory(NOMBRE_BD_CAMPEONATO);
		System.out.println("Se ha creado una base de datos campeonato:");

		Jugador jugador01 = new Jugador("Juan");
		Jugador jugador02 = new Jugador("Mar�a");
		Jugador jugador03 = new Jugador("Pedro");
		Jugador jugador04 = new Jugador("Isabel");
		Jugador jugador05 = new Jugador("Pablo");
		Jugador jugador06 = new Jugador("Pilar");
		Jugador jugador07 = new Jugador("Jorge");
		Jugador jugador08 = new Jugador("Elena");
		Jugador jugador09 = new Jugador("Francisco");
		Jugador jugador10 = new Jugador("Marta");
		Jugador jugador11 = new Jugador("Roberto");
		Jugador jugador12 = new Jugador("Teresa");
		Jugador jugador13 = new Jugador("Rub�n");
		Jugador jugador14 = new Jugador("Luc�a");
		Jugador jugador15 = new Jugador("Ricardo");
		Jugador jugador16 = new Jugador("Alicia");

		EntityManager conexion = null;
		EntityTransaction transaccion = null;
		try {
			conexion = fabricaConexiones.createEntityManager();
			transaccion = conexion.getTransaction();
			transaccion.begin();
			conexion.persist(jugador01);
			conexion.persist(jugador02);
			conexion.persist(jugador03);
			conexion.persist(jugador04);
			conexion.persist(jugador05);
			conexion.persist(jugador06);
			conexion.persist(jugador07);
			conexion.persist(jugador08);
			conexion.persist(jugador09);
			conexion.persist(jugador10);
			conexion.persist(jugador11);
			conexion.persist(jugador12);
			conexion.persist(jugador13);
			conexion.persist(jugador14);
			conexion.persist(jugador15);
			conexion.persist(jugador16);
			transaccion.commit();
			System.out.println("Se han insertado 16 jugadores en la base de datos.");
		} catch (Exception e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
			throw e;
		} finally {
			if (conexion != null) {
				conexion.close();
			}
		}

		Equipo equipo1 = new Equipo("Rojo", "Zaragoza");
		equipo1.insertar(jugador01);
		equipo1.insertar(jugador02);
		equipo1.insertar(jugador03);
		equipo1.insertar(jugador04);
		Equipo equipo2 = new Equipo("Verde", "Huesca");
		equipo2.insertar(jugador05);
		equipo2.insertar(jugador06);
		equipo2.insertar(jugador07);
		equipo2.insertar(jugador08);
		Equipo equipo3 = new Equipo("Azul", "Teruel");
		equipo3.insertar(jugador09);
		equipo3.insertar(jugador10);
		equipo3.insertar(jugador11);
		equipo3.insertar(jugador12);
		Equipo equipo4 = new Equipo("Amarillo", "Calatayud");
		equipo4.insertar(jugador13);
		equipo4.insertar(jugador14);
		equipo4.insertar(jugador15);
		equipo4.insertar(jugador16);

		Partido partido01 = new Partido(equipo1, equipo2, new Fecha(4, 6, 2022), new Resultado(3, 0));
		Partido partido02 = new Partido(equipo3, equipo4, new Fecha(4, 6, 2022), new Resultado(2, 2));
		Partido partido03 = new Partido(equipo1, equipo3, new Fecha(18, 6, 2022), new Resultado(1, 2));
		Partido partido04 = new Partido(equipo2, equipo4, new Fecha(18, 6, 2022), new Resultado(2, 0));
		Partido partido05 = new Partido(equipo1, equipo4, new Fecha(2, 7, 2022), new Resultado(0, 0));
		Partido partido06 = new Partido(equipo2, equipo3, new Fecha(2, 7, 2022), new Resultado(1, 1));
		Partido partido07 = new Partido(equipo2, equipo1, new Fecha(16, 7, 2022), new Resultado(1, 0));
		Partido partido08 = new Partido(equipo4, equipo3, new Fecha(16, 7, 2022), new Resultado(2, 3));
		Partido partido09 = new Partido(equipo3, equipo1, new Fecha(30, 7, 2022), new Resultado(0, 1));
		Partido partido10 = new Partido(equipo4, equipo2, new Fecha(30, 7, 2022), new Resultado(3, 3));
		Partido partido11 = new Partido(equipo4, equipo1, new Fecha(13, 8, 2022), new Resultado(0, 2));
		Partido partido12 = new Partido(equipo3, equipo2, new Fecha(13, 8, 2022), new Resultado(2, 1));

		conexion = null;
		transaccion = null;
		try {
			conexion = fabricaConexiones.createEntityManager();
			transaccion = conexion.getTransaction();
			transaccion.begin();
			conexion.persist(equipo1);
			conexion.persist(equipo2);
			conexion.persist(equipo3);
			conexion.persist(equipo4);
			conexion.persist(partido01);
			conexion.persist(partido02);
			conexion.persist(partido03);
			conexion.persist(partido04);
			conexion.persist(partido05);
			conexion.persist(partido06);
			conexion.persist(partido07);
			conexion.persist(partido08);
			conexion.persist(partido09);
			conexion.persist(partido10);
			conexion.persist(partido11);
			conexion.persist(partido12);
			transaccion.commit();
			System.out.println("Se han insertado 4 equipos en la base de datos.");
			System.out.println("Se han insertado 12 partidos en la base de datos.");
		} catch (Exception e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
			throw e;
		} finally {
			if (conexion != null) {
				conexion.close();
			}
		}

		fabricaConexiones.close();
	}

}
