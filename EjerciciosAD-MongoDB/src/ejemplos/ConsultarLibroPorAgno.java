package ejemplos;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entrada.Teclado;

import static com.mongodb.client.model.Filters.eq;

public class ConsultarLibroPorAgno {

	public static void main(String[] args) {
		int agno = Teclado.leerEntero("¿Año? ");
		MongoClient cliente = new MongoClient();
		MongoDatabase bd = cliente.getDatabase("biblioteca");
		MongoCollection<Document> libros = bd.getCollection("libros");
		Document libro = libros.find(eq("agno", agno))
		                       .first();
		if (libro == null) {
			System.out.println("No se ha encontrado ningún libro con año " + agno + ".");
		}
		else {
			System.out.println("ID=" + libro.getObjectId("_id") +
			                   ", Código=" + libro.getInteger("codigo") +
			                   ", Título=" + libro.getString("titulo") +
			                   ", Autor=" + libro.getString("autor") +
			                   ", Año=" + libro.getInteger("agno") +
			                   ", Género=" + libro.getString("genero"));
		}
		cliente.close();
	}

}
