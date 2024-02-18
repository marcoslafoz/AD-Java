package acceso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ejemplos.HibernateUtil;
import modelo.Empleado;

public class AccesoEmpleado {

	// Inserta un empleado en la base de datos.
	// Lanza una excepci�n de Hibernate
	// si ocurre un error al acceder a la base de datos.
	public static void insertarUno(Empleado empleado) throws HibernateException {
		Session sesion = null;
		Transaction transaccion = null;
		try {

			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			transaccion = sesion.beginTransaction();

			sesion.save(empleado);
			transaccion.commit();
			System.out.println("Se ha insertado un departamento en la base de datos.");
		}

		finally {
			if (sesion != null) {
				sesion.close();
			}
		}
	}

	// Consulta todos los empleados de la base de datos.
	// Devuelve una lista de empleados con los resultados.
	// Lanza una excepci�n de Hibernate
	// si ocurre un error al acceder a la base de datos.
	public static List<Empleado> consultarTodos() throws HibernateException {
		Session sesion = null;
		List<Empleado> listaEmpleados = new ArrayList<>();

		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			String sentenciaHQL = "select e from Empleado e";
			// String sentenciaHQL = "select e from Empleado e join fetch e.departamento
			// where e.codigo";
			Query<Empleado> consulta = sesion.createQuery(sentenciaHQL, Empleado.class);
			listaEmpleados = consulta.list();
		} finally {
			if (sesion != null) {
				sesion.close();
			}
		}

		return listaEmpleados;
	}

	// Consulta un empleado, por c�digo, de la base de datos.
	// Devuelve el empleado consultado de la base de datos que tiene un c�digo dado.
	// Devuelve null si no existe ning�n empleado con un c�digo dado.
	// Lanza una excepci�n de Hibernate
	// si ocurre un error al acceder a la base de datos.
	public static Empleado consultarUno(int codigo) throws HibernateException {
		Empleado empleado = null;
		Session sesion = null;

		try {
			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();

			empleado = sesion.get(Empleado.class, (short) codigo);

			if (empleado != null) {
				empleado.getDepartamento();
			}

		} finally {
			if (sesion != null) {
				sesion.close();
			}
		}
		return empleado;
	}

	// Actualiza un empleado, por c�digo, de la base de datos.
	// Devuelve verdadero si la base de datos contiene un empleado con un c�digo
	// dado y
	// este empleado ha sido actualizado con un nuevo departamento, un nuevo nombre,
	// una nueva fecha de alta y un nuevo salario.
	// Devuelve falso si no existe ning�n empleado con un c�digo dado.
	// Lanza una excepci�n de Hibernate
	// si ocurre un error al acceder a la base de datos.
	public static boolean actualizarUno(int codigo, Empleado nuevoEmpleado) throws HibernateException {

		boolean actualizado = false;
		Session sesion = null;
		Transaction transaccion = null;
		/*
		 * String nombre = Teclado.leerCadena("Introduce nombre"); String fechaalta =
		 * Teclado.leerCadena("Fecha alta"); double salario =
		 * Teclado.leerReal("Salario");
		 */
		try {

			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			transaccion = sesion.beginTransaction();
			Empleado empleado = sesion.get(Empleado.class, (short) codigo);
			if (empleado != null) {

				String nombre = nuevoEmpleado.getNombre();
				String fechaalta = nuevoEmpleado.getFechaAlta();
				BigDecimal salario = nuevoEmpleado.getSalario();
				empleado.setNombre(nombre);
				empleado.setFechaAlta(fechaalta);
				empleado.setSalario(salario);
				sesion.update(empleado);
				transaccion.commit();
				actualizado = true;

			}
		} catch (Exception e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
			System.out.println("Error de MySQL o Hibernate: " + e.getMessage());
		}

		finally {
			if (sesion != null) {
				sesion.close();
				// HibernateUtil.closeSessionFactory();
			}
		}

		return actualizado;
	}

	// Elimina un empleado, por c�digo, de la base de datos.
	// Devuelve verdadero si la base de datos contiene un empleado con un c�digo
	// dado y
	// este empleado ha sido eliminado.
	// Devuelve falso si no existe ning�n departamento con un c�digo dado.
	// Lanza una excepci�n de Hibernate
	// si ocurre un error al acceder a la base de datos.
	public static boolean eliminarUno(int codigo) throws HibernateException {

		boolean eliminado = false;
		Empleado empleado;
		Session sesion = null;
		Transaction transaccion = null;
		try {

			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			transaccion = sesion.beginTransaction();
			empleado = sesion.get(Empleado.class, (short) codigo);
			if (empleado != null) {
				sesion.delete(empleado);
				transaccion.commit();
				eliminado = true;

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

		return eliminado;
	}

	// Actualiza los salarios de los empleados del departamento
	// nº(codigodepartamento)

	public static boolean actualizarSalarioVarios(int codigodepartamento) throws HibernateException {

		boolean actualizado = false;
		Session sesion = null;
		Transaction transaccion = null;

		try {

			SessionFactory fabricaSesiones = HibernateUtil.getSessionFactory();
			sesion = fabricaSesiones.openSession();
			transaccion = sesion.beginTransaction();
//			String sentenciaHQL = "select e from Empleado e join fetch e.departamento where e.departamento.codigo like 10";//+ codigodepartamento;
//			TypedQuery<Empleado> consulta = sesion.createQuery(sentenciaHQL);// Cumple est�ndar JPA
			List<Empleado> listaEmpleado = consultarTodos();
			for (Empleado empleado : listaEmpleado) {
				empleado = sesion.get(Empleado.class, (short) empleado.getCodigo());
				// sumar bigdecimal: (bigdecimal).add(n)
				// restar //(bigdecimal).substract(n)
				// multiplicar (bigdecimal).multiply(n)
				// Dividir el primer objeto BigDecimal por el segundo (con redondeo)
				// BigDecimal division = numero1.divide(numero2, 2, BigDecimal.ROUND_HALF_UP);
				empleado.setSalario(empleado.getSalario().multiply(new BigDecimal("1.5")));
				sesion.update(empleado);
				transaccion.commit();
				actualizado = true;
			}

		} catch (Exception e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
		}

		finally {
			if (sesion != null) {
				sesion.close();
				// HibernateUtil.closeSessionFactory();
			}
		}

		return actualizado;
	}

}
