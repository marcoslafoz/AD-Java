package ejemplos;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import modelo.Departamento;

public class E01_ConsultarDepartamentos1 {

	public static void main(String[] args) {
		Session sesion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select d from Departamento d";
			Query consulta = sesion.createQuery(sentenciaHQL);
			List<Departamento> listaDepartamentos = consulta.list();
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
