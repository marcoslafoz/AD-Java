package ejemplos;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import entrada.Teclado;

import static com.mongodb.client.model.Filters.eq;

public class EliminarLibro {

	public static void main(String[] args) {
		int codigo = Teclado.leerEntero("¿Código? ");
		MongoClient cliente = new MongoClient();
		MongoDatabase bd = cliente.getDatabase("biblioteca");
		MongoCollection<Document> amigos = bd.getCollection("libros");
		DeleteResult resultado = amigos.deleteOne(eq("codigo", codigo));
		long librosEliminados = resultado.getDeletedCount();
		if (librosEliminados == 0) {
			System.out.println("No se ha encontrado ningún libro con código " + codigo + ".");
		}
		else {
			System.out.println("Se ha eliminado " + librosEliminados + " libro.");
		}
		cliente.close();
	}

}
