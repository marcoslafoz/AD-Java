package actividad_1x01;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

import entrada.Teclado;
import modelo.Producto;

public class AccesoProducto {

	public static void mostrarMenu() {
		System.out.println("\nMenú de Opciones:");
		System.out.println("0) Salir del programa.");
		System.out.println("2) Consultar todos los productos.");

	}

	public static void consultarProductos() {
		try {
			Class cl = Class.forName("org.exist.xmldb.DatabaseImpl");
			Database database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database);
			Producto productoTemporal = null;

			String url = "xmldb:exist://localhost:8080/exist/xmlrpc/db/ColeccionPruebas";
			Collection coleccion = DatabaseManager.getCollection(url, "admin", "admin");
			if (coleccion == null) {
				System.out.println("La colección no existe.");
			} else {
				System.out.println("Conectado con éxito a la colección.");

				XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
				String consulta = "for $prod in /productos/produc " + " order by $prod/denominacion " + " return $prod";
				ResourceSet resultados = servicio.query(consulta);

				int contador = 0;
				ResourceIterator iterador = resultados.getIterator();
				while (iterador.hasMoreResources()) {
					Resource recurso = iterador.nextResource();
					String producto = (String) recurso.getContent();
//					System.out.println(producto);
					productoTemporal = new Producto(producto);
					System.out.println(productoTemporal.toString());
					contador++;
				}
				System.out.println("Se han consultado " + contador + " productos.");
				if (contador == 0) {
					System.out.println("No se ha encontrado ningún producto.");
				}

				coleccion.close();
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error de clase no encontrada: " + cnfe.getMessage());
		} catch (InstantiationException ie) {
			System.out.println("Error de instanciación de base de datos XML: " + ie.getMessage());
		} catch (IllegalAccessException iae) {
			System.out.println("Error de acceso ilegal: " + iae.getMessage());
		} catch (XMLDBException xmldbe) {
			System.out.println("Error de base de datos XML: " + xmldbe.getMessage());
		}
	}

	public static void consultarProductoPorCodigo() {
		Collection coleccion = null;
		try {
			Class cl = Class.forName("org.exist.xmldb.DatabaseImpl");
			Database database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database);

			String url = "xmldb:exist://localhost:8080/exist/xmlrpc/db/ColeccionPruebas";
			coleccion = DatabaseManager.getCollection(url, "admin", "admin");
			if (coleccion == null) {
				System.out.println("La colección no existe.");
			} else {
				System.out.println("Conectado con éxito a la colección.");

				String codigo = Teclado.leerCadena("¿Código? ");

				XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");

				String consulta = "for $prod in /productos/produc " + " where $prod/cod_prod = " + codigo
						+ " return $prod";
				ResourceSet resultados = servicio.query(consulta);

				ResourceIterator iterador = resultados.getIterator();
				if (iterador.hasMoreResources()) {
					Resource recurso = iterador.nextResource();
					String producto = (String) recurso.getContent();
					System.out.println(producto);
					Producto productoTemp = new Producto(producto);
					System.out.println(productoTemp.toString());
					System.out.println("Se han consultado 1 producto.");
				} else {
					System.out.println("No se ha encontrado ningún producto.");
				}
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error de clase no encontrada: " + cnfe.getMessage());
		} catch (InstantiationException ie) {
			System.out.println("Error de instanciación de base de datos XML: " + ie.getMessage());
		} catch (IllegalAccessException iae) {
			System.out.println("Error de acceso ilegal: " + iae.getMessage());
		} catch (XMLDBException xmldbe) {
			System.out.println("Error de base de datos XML: " + xmldbe.getMessage());
		} finally {
			if (coleccion != null) {
				try {
					coleccion.close();
				} catch (XMLDBException xmldbe) {
					System.out.println("Error de base de datos XML: " + xmldbe.getMessage());
				}
			}
		}
	}

}
