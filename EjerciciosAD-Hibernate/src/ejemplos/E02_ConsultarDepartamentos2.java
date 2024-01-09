package ejemplos;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.TypedQuery;
import modelo.Departamento;

public class E02_ConsultarDepartamentos2 {

	public static void main(String[] args) {
		Session sesion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select d from Departamento d";
			TypedQuery<Departamento> consulta = sesion.createQuery(sentenciaHQL);//Cumple estándar JPA
			List<Departamento> listaDepartamentos = consulta.getResultList();
			if (listaDepartamentos.size() == 0) {
				System.out.println("No hay departamentos en la base de datos.");
			}
			else {
				for (Departamento departamento : listaDepartamentos) {
					System.out.println(departamento.toString());
				}
				System.out.println("Se han consultado " + listaDepartamentos.size() +
				                   " departamentos de la base de datos.");
			}
		}
		finally {
			if (sesion != null) {
				sesion.close();
				HibernateUtil.closeSessionFactory();
			}
		}
	}

}
