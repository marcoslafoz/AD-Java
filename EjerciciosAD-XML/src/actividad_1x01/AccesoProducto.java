package actividad_1x01;

import java.util.ArrayList;
import java.util.List;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;
import modelo.Producto;

public class AccesoProducto {

	/////////////// MOSTRAR MENÚ ////////////////
	public static void mostrarMenu() {
		System.out.println("\nMenú de Opciones:");
		System.out.println("0) Salir del programa.");
		System.out.println("1) Insertar un producto en la base de datos.");
		System.out.println("2) Consultar todos los productos.");
		System.out.println("3) Consultar un producto, por código, de la base de datos.");
		System.out.println("4) Actualizar un producto, por código, de la base de datos.");
		System.out.println("5) Eliminar un producto, por código, de la base de datos.");
	}

	//////////////////// CONECTAR //////////////////////
	private static Collection conectar()
			throws ClassNotFoundException, IllegalAccessException, InstantiationException, XMLDBException {
		final String NOMBRE_COLECCION = "ColeccionPruebas";
		final String URL = "xmldb:exist://localhost:8080/exist/xmlrpc/db/" + NOMBRE_COLECCION;
		Class cl = Class.forName("org.exist.xmldb.DatabaseImpl");
		Database database = (Database) cl.newInstance();
		DatabaseManager.registerDatabase(database);
		return DatabaseManager.getCollection(URL, "admin", "admin");
	}

	///////////////// CERRAR CONEXIÓN //////////////////////
	private static void cerrarConexion(Collection coleccion) {
		if (coleccion != null) {
			try {
				coleccion.close();
			} catch (XMLDBException xmldbe) {
				System.out.println("Error de base de datos XML: " + xmldbe.getMessage());
			}
		}
	}

	///////////////// CONSULTAR TODOS LOS PRODUCTOS //////////////////
	public static List<Producto> consultarProductos() {
		Collection coleccion = null;
		List<Producto> listaProductos = null;
		try {

			coleccion = conectar();
			
			XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
			String consulta = "for $prod in /productos/produc order by $prod/denominacion return $prod";
			ResourceSet resultados = servicio.query(consulta);
			ResourceIterator iterador = resultados.getIterator();
			
			listaProductos = new ArrayList<>();

			while (iterador.hasMoreResources()) {
				Resource recurso = iterador.nextResource();
				String productoString = (String) recurso.getContent();
				Producto productoTemp = new Producto(productoString);
				listaProductos.add(productoTemp);
			}

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | XMLDBException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			cerrarConexion(coleccion);
		}
		return listaProductos;
	}

	//////////////////// CONSULTAR PRODUCTO POR CÓDIGO //////////////////////
	public static Producto consultarProductoPorCodigo(int codigo) {

		Producto producto = null;
		Collection coleccion = null;
		try {

			coleccion = conectar();

			XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");

			String consulta = "for $prod in /productos/produc where $prod/cod_prod = " + codigo + " return $prod";
			ResourceSet resultados = servicio.query(consulta);

			ResourceIterator iterador = resultados.getIterator();
			if (iterador.hasMoreResources()) {
				Resource recurso = iterador.nextResource();
				String productoString = (String) recurso.getContent();
				producto = new Producto(productoString);
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | XMLDBException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			cerrarConexion(coleccion);
		}
		return producto;
	}
}
