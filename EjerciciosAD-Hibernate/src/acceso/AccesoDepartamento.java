package acceso;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ejemplos.HibernateUtil;
import modelo.Departamento;

public class AccesoDepartamento {

	// Inserta un departamento en la base de datos.
	// Lanza una excepci�n de Hibernate
	// si ocurre un error al acceder a la base de datos.
	public static void insertarUno(Departamento departamento) throws HibernateException {
		Session sesion = null;
		Transaction transaccion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			transaccion = sesion.beginTransaction();
			sesion.save(departamento);
			transaccion.commit();
			System.out.println("Se ha insertado un departamento en la base de datos.");
		} catch (Exception e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
		} finally {
			if (sesion != null) {
				sesion.close();
			}
		}
	}

	// Consulta todos los departamentos de la base de datos.
	// Devuelve una lista de departamentos con los resultados.
	// Lanza una excepci�n de Hibernate
	// si ocurre un error al acceder a la base de datos.
	@SuppressWarnings("unchecked")
	public static List<Departamento> consultarTodos() throws HibernateException {

		Session sesion = null;

		List<Departamento> listaDepartamentos = new ArrayList<Departamento>();

		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select d from Departamento d";
			@SuppressWarnings("rawtypes")
			Query consulta = sesion.createQuery(sentenciaHQL);
			listaDepartamentos = consulta.list();
			return listaDepartamentos;
		} finally {
			if (sesion != null) {
				sesion.close();
			}
		}

	}

	// Consulta un departamento, por c�digo, de la base de datos.
	// Devuelve el departamento consultado de la base de datos que tiene un c�digo
	// dado.
	// Devuelve null si no existe ning�n departamento con un c�digo dado.
	// Lanza una excepci�n de Hibernate
	// si ocurre un error al acceder a la base de datos.
	public static Departamento consultarUno(int codigo) throws HibernateException {

		Departamento departamento = null;

		Session sesion = null;

		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			departamento = sesion.get(Departamento.class, (short) codigo);

		} finally {
			if (sesion != null) {
				sesion.close();
			}
		}

		return departamento;
	}

	// Actualiza un departamento, por c�digo, de la base de datos.
	// Devuelve verdadero si la base de datos contiene un departamento con un c�digo
	// dado y
	// este departamento ha sido actualizado con un nuevo nombre y una nueva
	// ubicaci�n.
	// Devuelve falso si no existe ning�n departamento con un c�digo dado.
	// Lanza una excepci�n de Hibernate
	// si ocurre un error al acceder a la base de datos.
	public static boolean actualizarUno(int codigo, Departamento nuevoDepartamento) throws HibernateException {

		boolean actualizado = false;
		Session sesion = null;
		Transaction transaccion = null;

		try {

			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			transaccion = sesion.beginTransaction();

			Departamento departamento = sesion.get(Departamento.class, (short) codigo);

			if (departamento == null) {
				System.out.println("No se ha encontrado ning�n departamento con c�digo " + codigo);
			} else {

				departamento.setNombre(nuevoDepartamento.getNombre());
				departamento.setUbicacion(nuevoDepartamento.getUbicacion());
				sesion.update(departamento);
				transaccion.commit();
				System.out.println("Se ha actualizado un departamento de la base de datos.");
				actualizado = true;
			}
		} catch (Exception e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
		} finally {
			if (sesion != null) {
				sesion.close();
			}
		}

		return actualizado;
	}

	// Elimina un departamento, por c�digo, de la base de datos.
	// Devuelve verdadero si la base de datos contiene un departamento con un c�digo
	// dado y
	// este departamento ha sido eliminado.
	// Devuelve falso si no existe ning�n departamento con un c�digo dado.
	// Lanza una excepci�n de Hibernate
	// si ocurre un error al acceder a la base de datos.
	public static boolean eliminar(int codigo) throws HibernateException {

		boolean eliminado = false;

		Session sesion = null;
		Transaction transaccion = null;
		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			transaccion = sesion.beginTransaction();
			Departamento departamento = sesion.get(Departamento.class, (short) codigo);
			if (departamento == null) {
				System.out.println("No se ha encontrado ning�n departamento con c�digo " + codigo);
			} else {
				sesion.delete(departamento);
				transaccion.commit();
				System.out.println("Se ha eliminado un departamento de la base de datos.");
				eliminado = true;
			}
		} catch (Exception e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
			System.out.println("Error de MySQL o Hibernate: " + e.getMessage());
		} finally {
			if (sesion != null) {
				sesion.close();
			}
		}

		return eliminado;
	}

}
