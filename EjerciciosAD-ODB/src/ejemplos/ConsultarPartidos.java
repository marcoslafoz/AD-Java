package ejemplos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import modelo.Partido;

public class ConsultarPartidos {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = null;
		try {
			conexion = emf.createEntityManager();
			TypedQuery<Partido> consulta = conexion.createQuery("SELECT p FROM Partido p ORDER BY p.fecha ASC", 
			                                                    Partido.class);
			List<Partido> partidos = consulta.getResultList();
			if (partidos.size() == 0) {
				System.out.println("No hay ningún partido en la base de datos.");
			}
			else {
				for (Partido partido : partidos) {
					System.out.println(partido.toString());
				}
				System.out.println("Se han consultado " + partidos.size() + 
				                   " partidos de la base de datos");
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
