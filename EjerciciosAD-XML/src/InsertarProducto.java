import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;
import entrada.Teclado;

public class InsertarProducto {

	public static void main(String[] args) {

		Collection coleccion = null;
		try {
			Class cl = Class.forName("org.exist.xmldb.DatabaseImpl");
			Database database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database);

			String url = "xmldb:exist://localhost:8080/exist/xmlrpc/db/ColeccionPruebas";
			coleccion = DatabaseManager.getCollection(url, "admin", "admin");
			if (coleccion == null) {
				System.out.println("La colección no existe.");
			}
			else {
				System.out.println("Conectado con éxito a la colección.");

				int codigo = Teclado.leerEntero("¿Código? ");
				String sentenciaBuscarProductoPorCodigo = 
						"for $prod in /productos/produc " +
						" where $prod/cod_prod = " + codigo +
						" return $prod";
				XPathQueryService servicio = 
						(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
				ResourceSet resultados = servicio.query(sentenciaBuscarProductoPorCodigo);
				
				ResourceIterator iterador = resultados.getIterator();
				if (iterador.hasMoreResources()) {
					System.out.println("Ya existe un producto con el código " + codigo + " en el XML.");
				}
				else {
					String denominacion = Teclado.leerCadena("¿Denominación? ");
					double precio = Teclado.leerReal("¿Precio? ");
					int stockActual = Teclado.leerEntero("¿Stock Actual? ");
					int stockMinimo = Teclado.leerEntero("¿Stock Mínimo? ");
					int codigoZona = Teclado.leerEntero("¿Código de Zona? ");
					
					String sentenciaInsertarProducto = 
							"update insert " +
								"<produc>" +
									"<cod_prod>" + codigo + "</cod_prod>" +
									"<denominacion>" + denominacion + "</denominacion>" +
									"<precio>" + String.format("%.2f", precio) + "</precio>" +
									"<stock_actual>" + stockActual + "</stock_actual>" +
									"<stock_minimo>" + stockMinimo + "</stock_minimo>" +
									"<cod_zona>" + codigoZona + "</cod_zona>" +
								"</produc> " +
							"into /productos";
					ResourceSet resultados2 = servicio.query(sentenciaInsertarProducto);
					
					System.out.println("Se ha insertado con éxito un nuevo producto en el XML.");
				}
							
			}
		} 
		catch (ClassNotFoundException cnfe) {
			System.out.println("Error de clase no encontrada: " + cnfe.getMessage());
		} 
		catch (InstantiationException ie) {
			System.out.println("Error de instanciación de base de datos XML: " + ie.getMessage());
		} 
		catch (IllegalAccessException iae) {
			System.out.println("Error de acceso ilegal: " + iae.getMessage());
		} 
		catch (XMLDBException xmldbe) {
			System.out.println("Error de base de datos XML: " + xmldbe.getMessage());
		}
		finally {
			if (coleccion != null) {
				try {
					coleccion.close();
				} 
				catch (XMLDBException xmldbe) {
					System.out.println("Error de base de datos XML: " + xmldbe.getMessage());
				}
			}
		}

	}

}
