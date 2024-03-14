package ejemplos;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entrada.Teclado;

public class InsertarLibro {
	
	public static void main(String[] args) {
		String titulo = Teclado.leerCadena("¿Título? ");
		String autor = Teclado.leerCadena("¿Autor? ");
		int agno = Teclado.leerEntero("¿Año? ");
		String genero = Teclado.leerCadena("¿Género? ");
		MongoClient cliente = new MongoClient();
		MongoDatabase bd = cliente.getDatabase("biblioteca");
		MongoCollection<Document> libros = bd.getCollection("libros");
		Document libro = new Document();
		libro.put("titulo", titulo);
		libro.put("autor", autor);
		libro.put("agno", agno);
		libro.put("genero", genero);
		libros.insertOne(libro);
		System.out.println("Se ha insertado un libro.");
		cliente.close();
	}
	
}
