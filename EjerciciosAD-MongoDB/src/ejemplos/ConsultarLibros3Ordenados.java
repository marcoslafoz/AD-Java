package ejemplos;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Sorts.descending;

public class ConsultarLibros3Ordenados {

	public static void main(String[] args) {
		MongoClient cliente = new MongoClient();
		MongoDatabase bd = cliente.getDatabase("biblioteca");
		MongoCollection<Document> libros = bd.getCollection("libros");
		MongoCursor<Document> cursor = libros.find()
		                                     .sort(descending("agno"))
		                                     .iterator();
		int contador = 0;
		while (cursor.hasNext()) {
			Document libro = cursor.next();
			System.out.println("ID=" + libro.getObjectId("_id") +
	                           ", C�digo=" + libro.getInteger("codigo") +
	                           ", T�tulo=" + libro.getString("titulo") +
	                           ", Autor=" + libro.getString("autor") +
	                           ", A�o=" + libro.getInteger("agno") +
	                           ", G�nero=" + libro.getString("genero"));
			contador++;
		}
		if (contador == 0) {
			System.out.println("No se ha encontrado ning�n libro.");
		}
		else {
			System.out.println("Se han consultado " + contador + " libros.");
		}
		cliente.close();
	}

}
