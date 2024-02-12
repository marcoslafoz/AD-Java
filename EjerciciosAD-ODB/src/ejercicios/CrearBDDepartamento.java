package ejercicios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import modelo.Departamento;
import modelo.Empleado;

public class CrearBDDepartamento {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/personal.odb");
		EntityManager conexion = null;
		EntityTransaction transaccion = null;

		try {
			conexion = emf.createEntityManager();
			transaccion = conexion.getTransaction();
			transaccion.begin();

			// Crear Departamentos
			Departamento departamento1 = new Departamento();
			departamento1.setNombre("Ventas");
			departamento1.setUbicacion("Zaragoza");

			Departamento departamento2 = new Departamento();
			departamento2.setNombre("Desarrollo");
			departamento2.setUbicacion("Huesca");

			// Crear Empleados
			Empleado empleado1 = new Empleado();
			empleado1.setNombre("Juan");
			empleado1.setFechaAlta("01/01/2022");
			empleado1.setSalario(50000.0);
			empleado1.setDepartamento(departamento1);

			Empleado empleado2 = new Empleado();
			empleado2.setNombre("Maria");
			empleado2.setFechaAlta("02/01/2022");
			empleado2.setSalario(60000.0);
			empleado2.setDepartamento(departamento2);

			conexion.persist(departamento1);
			conexion.persist(departamento2);
			conexion.persist(empleado1);
			conexion.persist(empleado2);

			transaccion.commit();
			System.out.println("Se ha creado una base de datos campeonato con");
			System.out.println("Departamentos, Empleados y otros objetos relacionados.");
		} catch (Exception e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
			throw e;
		} finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		emf.close();
	}
}
