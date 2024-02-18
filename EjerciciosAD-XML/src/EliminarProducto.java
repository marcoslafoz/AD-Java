import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;
import entrada.Teclado;

public class EliminarProducto {

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
					String sentenciaEliminarProducto = 
							"update delete " +
							"/productos/produc[cod_prod = " + codigo + "]";
					ResourceSet resultados2 = servicio.query(sentenciaEliminarProducto);
					
					System.out.println("Se ha eliminado con éxito un producto del XML.");
				}
				else {
					System.out.println("No existe un producto con el código " + codigo + " en el XML.");
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
