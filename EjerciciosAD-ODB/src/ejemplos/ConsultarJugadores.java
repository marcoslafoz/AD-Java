package ejemplos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import modelo.Jugador;

public class ConsultarJugadores {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = null;
		try {
			conexion = emf.createEntityManager();
			TypedQuery<Jugador> consulta = conexion.createQuery("SELECT j FROM Jugador j", 
			                                                    Jugador.class);
			List<Jugador> jugadores = consulta.getResultList();
			if (jugadores.size() == 0) {
				System.out.println("No hay ningún jugador en la base de datos.");
			}
			else {
				for (Jugador jugador : jugadores) {
					System.out.println(jugador.toString());
				}
				System.out.println("Se han consultado " + jugadores.size() + 
				                   " jugadores de la base de datos");
			}
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		emf.close();
	}

}
