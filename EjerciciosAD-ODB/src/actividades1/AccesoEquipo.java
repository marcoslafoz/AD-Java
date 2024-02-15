package actividades1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Departamento;
import modelo.Equipo;
import modelo.Fecha;
import modelo.Jugador;

public class AccesoEquipo {
	public static void mostrarMenu() {
		System.out.println("\nMenú de Opciones:");
	}

	////////////////////////////////////////////
	public static void mostrarEquiposConJugadores() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
        EntityManager conexion = emf.createEntityManager();

        try {
            TypedQuery<Equipo> consultaEquipos = conexion.createQuery("SELECT e FROM Equipo e", Equipo.class);
            for (Equipo equipo : consultaEquipos.getResultList()) {
                System.out.println(equipo.toString());
                System.out.println("Jugadores:");
//                for (Jugador jugador : equipo.getJugadores()) {
//                    System.out.println("- " + jugador.getNombre());
//                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conexion != null && conexion.isOpen()) {
                conexion.close();
            }
            emf.close();
        }
    }

    //////////////////////////////////////7
    public static Equipo consultarEquipoPorCodigo(int codigo) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
        EntityManager entityManager = emf.createEntityManager();
        Equipo equipo = null;

        try {
            equipo = entityManager.find(Equipo.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
            emf.close();
        }

        return equipo;
    }

}
