package examen;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CrearBDFichajes {

	private static final String NOMBRE_BD_FICHAJES = "data/fichajes.odb";
	
	public static void main(String[] args) {
		EntityManagerFactory fabricaConexiones = Persistence.createEntityManagerFactory(NOMBRE_BD_FICHAJES);
		System.out.println("Se ha creado una base de datos fichajes:");
		
		Departamento departamento1 = new Departamento("Direcci�n");
		Departamento departamento2 = new Departamento("Recursos Humanos");
		Departamento departamento3 = new Departamento("Contabilidad");
		Departamento departamento4 = new Departamento("Compras");
		Departamento departamento5 = new Departamento("Producci�n");
		Departamento departamento6 = new Departamento("Marketing");
		Departamento departamento7 = new Departamento("Ventas");
		
		EntityManager conexion = null;
		EntityTransaction transaccion = null;
		try {
			conexion = fabricaConexiones.createEntityManager();
			transaccion = conexion.getTransaction();
			transaccion.begin();
			conexion.persist(departamento1);
			conexion.persist(departamento2);
			conexion.persist(departamento3);
			conexion.persist(departamento4);
			conexion.persist(departamento5);
			conexion.persist(departamento6);
			conexion.persist(departamento7);
			transaccion.commit();
			System.out.println("Se han insertado 7 departamentos en la base de datos.");
		}
		finally {
			if (transaccion != null && transaccion.isActive()) {
				transaccion.rollback();
			}
			if (conexion != null) {
				conexion.close();
			}
		}
		
		Fecha fecha1 = new Fecha(16, 1, 2023);
		Fecha fecha2 = new Fecha(17, 1, 2023);
		Fecha fecha3 = new Fecha(18, 1, 2023);
		Fecha fecha4 = new Fecha(19, 1, 2023);
		Fecha fecha5 = new Fecha(20, 1, 2023);
		Empleado empleado01 = new Empleado("Juan", departamento1);
		empleado01.insertar(fecha1, new Fichaje());
		empleado01.insertar(fecha2, new Fichaje());
		empleado01.insertar(fecha3, new Fichaje());
		empleado01.insertar(fecha4, new Fichaje());
		empleado01.insertar(fecha5, new Fichaje());
		Empleado empleado02 = new Empleado("Mar�a", departamento1);
		empleado02.insertar(fecha1, new Fichaje());
		empleado02.insertar(fecha2, new Fichaje());
		empleado02.insertar(fecha3, new Fichaje());
		empleado02.insertar(fecha4, new Fichaje());
		empleado02.insertar(fecha5, new Fichaje());
		Empleado empleado03 = new Empleado("Pedro", departamento2);
		empleado03.insertar(fecha1, new Fichaje());
		empleado03.insertar(fecha2, new Fichaje());
		empleado03.insertar(fecha3, new Fichaje());
		empleado03.insertar(fecha4, new Fichaje());
		empleado03.insertar(fecha5, new Fichaje());
		Empleado empleado04 = new Empleado("Isabel", departamento2);
		empleado04.insertar(fecha1, new Fichaje());
		empleado04.insertar(fecha2, new Fichaje());
		empleado04.insertar(fecha3, new Fichaje());
		empleado04.insertar(fecha4, new Fichaje());
		empleado04.insertar(fecha5, new Fichaje());
		Empleado empleado05 = new Empleado("Pablo", departamento3);
		empleado05.insertar(fecha1, new Fichaje());
		empleado05.insertar(fecha2, new Fichaje());
		empleado05.insertar(fecha3, new Fichaje());
		empleado05.insertar(fecha4, new Fichaje());
		empleado05.insertar(fecha5, new Fichaje());
		Empleado empleado06 = new Empleado("Pilar", departamento3);
		empleado06.insertar(fecha1, new Fichaje());
		empleado06.insertar(fecha2, new Fichaje());
		empleado06.insertar(fecha3, new Fichaje());
		empleado06.insertar(fecha4, new Fichaje());
		empleado06.insertar(fecha5, new Fichaje());
		Empleado empleado07 = new Empleado("Jorge", departamento4);
		empleado07.insertar(fecha1, new Fichaje());
		empleado07.insertar(fecha2, new Fichaje());
		empleado07.insertar(fecha3, new Fichaje());
		empleado07.insertar(fecha4, new Fichaje());
		empleado07.insertar(fecha5, new Fichaje());
		Empleado empleado08 = new Empleado("Elena", departamento4);
		empleado08.insertar(fecha1, new Fichaje());
		empleado08.insertar(fecha2, new Fichaje());
		empleado08.insertar(fecha3, new Fichaje());
		empleado08.insertar(fecha4, new Fichaje());
		empleado08.insertar(fecha5, new Fichaje());
		Empleado empleado09 = new Empleado("Francisco", departamento4);
		empleado09.insertar(fecha1, new Fichaje());
		empleado09.insertar(fecha2, new Fichaje());
		empleado09.insertar(fecha3, new Fichaje());
		empleado09.insertar(fecha4, new Fichaje());
		empleado09.insertar(fecha5, new Fichaje());
		Empleado empleado10 = new Empleado("Marta", departamento5);
		empleado10.insertar(fecha1, new Fichaje());
		empleado10.insertar(fecha2, new Fichaje());
		empleado10.insertar(fecha3, new Fichaje());
		empleado10.insertar(fecha4, new Fichaje());
		empleado10.insertar(fecha5, new Fichaje());
		Empleado empleado11 = new Empleado("Roberto", departamento5);
		empleado11.insertar(fecha1, new Fichaje());
		empleado11.insertar(fecha2, new Fichaje());
		empleado11.insertar(fecha3, new Fichaje());
		empleado11.insertar(fecha4, new Fichaje());
		empleado11.insertar(fecha5, new Fichaje());
		Empleado empleado12 = new Empleado("Teresa", departamento6);
		empleado12.insertar(fecha1, new Fichaje());
		empleado12.insertar(fecha2, new Fichaje());
		empleado12.insertar(fecha3, new Fichaje());
		empleado12.insertar(fecha4, new Fichaje());
		empleado12.insertar(fecha5, new Fichaje());
		Empleado empleado13 = new Empleado("Rub�n", departamento6);
		empleado13.insertar(fecha1, new Fichaje());
		empleado13.insertar(fecha2, new Fichaje());
		empleado13.insertar(fecha3, new Fichaje());
		empleado13.insertar(fecha4, new Fichaje());
		empleado13.insertar(fecha5, new Fichaje());
		Empleado empleado14 = new Empleado("Luc�a", departamento7);
		empleado14.insertar(fecha1, new Fichaje());
		empleado14.insertar(fecha2, new Fichaje());
		empleado14.insertar(fecha3, new Fichaje());
		empleado14.insertar(fecha4, new Fichaje());
		empleado14.insertar(fecha5, new Fichaje());
		Empleado empleado15 = new Empleado("Ricardo", departamento7);
		empleado15.insertar(fecha1, new Fichaje());
		empleado15.insertar(fecha2, new Fichaje());
		empleado15.insertar(fecha3, new Fichaje());
		empleado15.insertar(fecha4, new Fichaje());
		empleado15.insertar(fecha5, new Fichaje());
		Empleado empleado16 = new Empleado("Alicia", departamento7);
		empleado16.insertar(fecha1, new Fichaje());
		empleado16.insertar(fecha2, new Fichaje());
		empleado16.insertar(fecha3, new Fichaje());
		empleado16.insertar(fecha4, new Fichaje());
		empleado16.insertar(fecha5, new Fichaje());
				
		conexion = null;
		transaccion = null;
		try {
			conexion = fabricaConexiones.createEntityManager();
			transaccion = conexion.getTransaction();
			transaccion.begin();
			conexion.persist(empleado01);
			conexion.persist(empleado02);
			conexion.persist(empleado03);
			conexion.persist(empleado04);
			conexion.persist(empleado05);
			conexion.persist(empleado06);
			conexion.persist(empleado07);
			conexion.persist(empleado08);
			conexion.persist(empleado09);
			conexion.persist(empleado10);
			conexion.persist(empleado11);
			conexion.persist(empleado12);
			conexion.persist(empleado13);
			conexion.persist(empleado14);
			conexion.persist(empleado15);
			conexion.persist(empleado16);
			transaccion.commit();
			System.out.println("Se han insertado 16 empleados en la base de datos.");
		}
		finally {
			if (transaccion != null && transaccion.isActive()) {
				transaccion.rollback();
			}
			if (conexion != null) {
				conexion.close();
			}
		}
		
		fabricaConexiones.close();
	}

}
