package ejemplos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import modelo.Equipo;

public class ConsultarEquipos {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = null;
		try {
			conexion = emf.createEntityManager();
			TypedQuery<Equipo> consulta = conexion.createQuery("SELECT e FROM Equipo e", 
			                                                    Equipo.class);
			List<Equipo> equipos = consulta.getResultList();
			if (equipos.size() == 0) {
				System.out.println("No hay ning�n equipo en la base de datos.");
			}
			else {
				for (Equipo equipo : equipos) {
					System.out.println(equipo.toString());
				}
				System.out.println("Se han consultado " + equipos.size() + 
				                   " equipos de la base de datos");
			}
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
		}
	}

}
