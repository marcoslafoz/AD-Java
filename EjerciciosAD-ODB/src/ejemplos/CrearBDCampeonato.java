package ejemplos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Partido;

public class CrearBDCampeonato {

	public static void main(String[] args) {
		Jugador jugador01 = new Jugador("Juan");
		Jugador jugador02 = new Jugador("María");
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
		Jugador jugador13 = new Jugador("Rubén");
		Jugador jugador14 = new Jugador("Lucía");
		Jugador jugador15 = new Jugador("Ricardo");
		Jugador jugador16 = new Jugador("Alicia");
		
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
		
		Partido partido01 = new Partido(equipo1, equipo2, "04/06/2022", "3-0");
		Partido partido02 = new Partido(equipo3, equipo4, "04/06/2022", "2-2");
		Partido partido03 = new Partido(equipo1, equipo3, "18/06/2022", "1-2");
		Partido partido04 = new Partido(equipo2, equipo4, "18/06/2022", "2-0");
		Partido partido05 = new Partido(equipo1, equipo4, "02/07/2022", "0-0");
		Partido partido06 = new Partido(equipo2, equipo3, "02/07/2022", "1-1");
		Partido partido07 = new Partido(equipo2, equipo1, "16/07/2022", "1-0");
		Partido partido08 = new Partido(equipo4, equipo3, "16/07/2022", "2-3");
		Partido partido09 = new Partido(equipo3, equipo1, "30/07/2022", "0-1");
		Partido partido10 = new Partido(equipo4, equipo2, "30/07/2022", "3-3");
		Partido partido11 = new Partido(equipo4, equipo1, "13/08/2022", "0-2");
		Partido partido12 = new Partido(equipo3, equipo2, "13/08/2022", "2-1");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = null;
		EntityTransaction transaccion = null;
		try {
			conexion = emf.createEntityManager();
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
			System.out.println("Se ha creado una base de datos campeonato con");
			System.out.println("16 jugadores, 4 equipos y 12 partidos.");
		}
		catch (Exception e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
			throw e;
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		emf.close();
	}

}
