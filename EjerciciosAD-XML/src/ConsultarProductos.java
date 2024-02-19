import org.xmldb.api.*;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;

public class ConsultarProductos {

	public static void main(String[] args) {

		try {
			Class cl = Class.forName("org.exist.xmldb.DatabaseImpl");
			Database database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database);
			
			String url = "xmldb:exist://localhost:8080/exist/xmlrpc/db/ColeccionPruebas";
			Collection coleccion = DatabaseManager.getCollection(url, "admin", "admin");
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
				
				coleccion.close();
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
		
	}

}
