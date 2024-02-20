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

import entrada.Teclado;
import modelo.Producto;
import utils.ConfigDB;

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
		Class cl = Class.forName(ConfigDB.EXIST_DRIVER);
		Database database = (Database) cl.newInstance();
		DatabaseManager.registerDatabase(database);
		return DatabaseManager.getCollection(ConfigDB.DB_URL, ConfigDB.DB_USERNAME, ConfigDB.DB_PASSWORD);
	}

	///////////////// CONSULTAR TODOS LOS PRODUCTOS //////////////////
	public static List<Producto> consultarProductos()
			throws ClassNotFoundException, IllegalAccessException, InstantiationException, XMLDBException {

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

		} finally {
			if (coleccion != null && coleccion.isOpen()) {
				coleccion.close();
			}
		}

		return listaProductos;
	}

	////////////////// CONSULTAR PRODUCTO POR CÓDIGO //////////////////////
	public static Producto consultarProductoPorCodigo(int codigo)
			throws ClassNotFoundException, IllegalAccessException, InstantiationException, XMLDBException {

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
		} finally {
			if (coleccion != null && coleccion.isOpen()) {
				coleccion.close();
			}
		}
		return producto;
	}

	////////////////// CONSULTAR CÓDIGO MÁXIMO //////////////////////
	public static int consultarCodigoMaximo()
			throws ClassNotFoundException, IllegalAccessException, InstantiationException, XMLDBException {

		int codigoMaximo = 0;

		Collection coleccion = null;
		try {

			coleccion = conectar();

			XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");

			String consulta = "let $maximo := max(/productos/produc/cod_prod)" + "return $maximo";
			ResourceSet resultados = servicio.query(consulta);

			ResourceIterator iterador = resultados.getIterator();
			if (iterador.hasMoreResources()) {
				Resource recurso = iterador.nextResource();
				codigoMaximo = Integer.parseInt((String) recurso.getContent());

			}
		} finally {
			if (coleccion != null && coleccion.isOpen()) {
				coleccion.close();
			}
		}

		return codigoMaximo;
	}

	////////////////// INSERTAR PRODUCTO //////////////////////
	public static void insertarProducto(int codigo)
			throws ClassNotFoundException, IllegalAccessException, InstantiationException, XMLDBException {

		Collection coleccion = null;
		try {

			coleccion = conectar();

			XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");

			String denominacion = Teclado.leerCadena("¿Denominación? ");
			double precio = Teclado.leerReal("¿Precio? ");
			int stockActual = Teclado.leerEntero("¿Stock Actual? ");
			int stockMinimo = Teclado.leerEntero("¿Stock Mínimo? ");
			int codigoZona = Teclado.leerEntero("¿Código de Zona? ");

			String sentenciaInsertarProducto = "update insert " + "<produc>" + "<cod_prod>" + codigo + "</cod_prod>"
					+ "<denominacion>" + denominacion + "</denominacion>" + "<precio>" + String.format("%.2f", precio)
					+ "</precio>" + "<stock_actual>" + stockActual + "</stock_actual>" + "<stock_minimo>" + stockMinimo
					+ "</stock_minimo>" + "<cod_zona>" + codigoZona + "</cod_zona>" + "</produc> " + "into /productos";
			ResourceSet resultados2 = servicio.query(sentenciaInsertarProducto);

			System.out.println("Se ha insertado con éxito un nuevo producto en el XML.");

		} finally {
			if (coleccion != null && coleccion.isOpen()) {
				coleccion.close();
			}
		}
	}

	////////////////// ELIMINAR PRODUCTO //////////////////////
	public static boolean eliminarProducto(int codigo)
			throws ClassNotFoundException, IllegalAccessException, InstantiationException, XMLDBException {

		boolean productoEliminado = false;

		Collection coleccion = null;
		try {

			coleccion = conectar();

			XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");

			Producto productoEliminar = consultarProductoPorCodigo(codigo);

			if (productoEliminar != null) {
				String sentenciaEliminarProducto = "update delete " + "/productos/produc[cod_prod = " + codigo + "]";
				ResourceSet resultados = servicio.query(sentenciaEliminarProducto);
				productoEliminado = true;
			}

		} finally {
			if (coleccion != null && coleccion.isOpen()) {
				coleccion.close();
			}
		}

		return productoEliminado;
	}

	////////////////// ACTUALIZAR PRODUCTO //////////////////////
	public static boolean actualizarProducto(int codigo)
			throws ClassNotFoundException, IllegalAccessException, InstantiationException, XMLDBException {

		boolean productoActualizado = false;

		Collection coleccion = null;
		try {

			coleccion = conectar();

			XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");

			Producto productoActualizar = consultarProductoPorCodigo(codigo);

			if (productoActualizar != null) {

				String denominacion = Teclado.leerCadena("¿Denominación? ");
				double precio = Teclado.leerReal("¿Precio? ");
				int stockActual = Teclado.leerEntero("¿Stock Actual? ");
				int stockMinimo = Teclado.leerEntero("¿Stock Mínimo? ");
				int codigoZona = Teclado.leerEntero("¿Código de Zona? ");

				String sentenciaActualizarProducto = "update replace " + "/productos/produc[cod_prod = " + codigo
						+ "] with " + "<produc>" + "<cod_prod>" + codigo + "</cod_prod>" + "<denominacion>"
						+ denominacion + "</denominacion>" + "<precio>" + String.format("%.2f", precio) + "</precio>"
						+ "<stock_actual>" + stockActual + "</stock_actual>" + "<stock_minimo>" + stockMinimo
						+ "</stock_minimo>" + "<cod_zona>" + codigoZona + "</cod_zona>" + "</produc>";
				ResourceSet resultados = servicio.query(sentenciaActualizarProducto);

				productoActualizado = true;

			}

		} finally {
			if (coleccion != null && coleccion.isOpen()) {
				coleccion.close();
			}
		}

		return productoActualizado;
	}

}
