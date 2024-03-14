package ejemplos;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import entrada.Teclado;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;

public class ConsultarLibrosPorAutor {

	public static void main(String[] args) {
		String autor = Teclado.leerCadena("¿Autor? ");
		MongoClient cliente = new MongoClient();
		MongoDatabase bd = cliente.getDatabase("biblioteca");
		MongoCollection<Document> libros = bd.getCollection("libros");
		MongoCursor<Document> cursor = libros.find(eq("autor", autor))
                                             .sort(ascending("agno"))
                                             .iterator();
		int contador = 0;
		while (cursor.hasNext()) {
			Document libro = cursor.next();
			System.out.println("ID=" + libro.getObjectId("_id") +
	                           ", Código=" + libro.getInteger("codigo") +
	                           ", Título=" + libro.getString("titulo") +
	                           ", Autor=" + libro.getString("autor") +
	                           ", Año=" + libro.getInteger("agno") +
	                           ", Género=" + libro.getString("genero"));
			contador++;
		}
		if (contador == 0) {
			System.out.println("No se ha encontrado ningún libro con autor " + autor + ".");
		}
		else {
			System.out.println("Se han consultado " + contador + " libros.");
		}
		cliente.close();
	}

}
