package ejemplos;

import java.util.List;
import java.math.BigDecimal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class E03_ConsultarDatosDeEmpleados {

	public static void main(String[] args) {
		Session sesion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select e.nombre, e.salario from Empleado e";
			Query consulta = sesion.createQuery(sentenciaHQL);
			List<Object> listaResultados = consulta.list();
			if (listaResultados.size() == 0) {
				System.out.println("No hay empleados en la base de datos.");
			}
			else {
				for (int pos = 0 ; pos < listaResultados.size() ; pos++) {
					Object[] resultado = (Object[]) listaResultados.get(pos);
					String nombre = (String) resultado[0];
					BigDecimal salario = (BigDecimal) resultado[1];
					System.out.println("Nombre = " + nombre + 
					                   ", Salario = " + salario.doubleValue());
				}
				System.out.println("Se han consultado " + listaResultados.size() +
		                           " empleados de la base de datos.");
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
