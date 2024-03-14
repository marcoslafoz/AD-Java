package ejemplos;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConsultarLibros {

	public static void main(String[] args) {
		MongoClient cliente = new MongoClient();
		MongoDatabase bd = cliente.getDatabase("biblioteca");
		MongoCollection<Document> libros = bd.getCollection("libros");
		List<Document> resultados = libros.find()
		                                  .into(new ArrayList<Document>());
		if (resultados.isEmpty()) {
			System.out.println("No se ha encontrado ningún libro.");
		}
		else {
			for (int i = 0 ; i < resultados.size() ; i++) {
				Document libro = resultados.get(i);
				System.out.println(libro.toString());
			}
			System.out.println("Se han consultado " + resultados.size() + " libros.");
		}
		cliente.close();
	}

}
