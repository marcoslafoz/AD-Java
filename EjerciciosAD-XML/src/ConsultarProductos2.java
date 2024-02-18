import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

public class ConsultarProductos2 {

	// Se conecta a una colección de la BD XML y la devuelve
	public static Collection conectar(String nombreColeccion) 
	throws ClassNotFoundException, InstantiationException, 
			IllegalAccessException, XMLDBException {
		Class cl = Class.forName("org.exist.xmldb.DatabaseImpl");
		Database database = (Database) cl.newInstance();
		DatabaseManager.registerDatabase(database);
		String url = "xmldb:exist://localhost:8080/exist/xmlrpc/db/" + nombreColeccion;
		Collection coleccion = DatabaseManager.getCollection(url, "admin", "admin");
		return coleccion;
	}
	
	// Se desconecta de la colección de la BD XML
	public static void desconectar(Collection coleccion) 
	throws XMLDBException {
		if (coleccion != null) {
			coleccion.close();
		}
	}
	
	public static void main(String[] args) {
		Collection coleccion = null;
		try {
			coleccion = conectar("ColeccionPruebas");
					
			if (coleccion == null) {
				System.out.println("La colección no existe.");
			}
			else {
				System.out.println("Conectado con éxito a la colección.");
				
				XPathQueryService servicio = 
					(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
				String consulta = "for $prod in /productos/produc " +
								" order by $prod/denominacion " +
								" return $prod";
				ResourceSet resultados = servicio.query(consulta);

				int contador = 0;
				ResourceIterator iterador = resultados.getIterator();
				while (iterador.hasMoreResources()) {
					Resource recurso = iterador.nextResource();
					String producto = (String) recurso.getContent();
					System.out.println(producto);
					contador++;
				}
				System.out.println("Se han consultado " + contador + " productos.");
				if (contador == 0) {
					System.out.println("No se ha encontrado ningún producto.");
				}
				
				// desconectarse de la colección
				//desconectar(coleccion);
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
		
		// mejor desconectar en el finally
		finally {
			try {
				desconectar(coleccion);
			} 
			catch (XMLDBException e) {
				System.out.println("Error de base de datos XML: " + e.getMessage());
			}
		}
	}

}
